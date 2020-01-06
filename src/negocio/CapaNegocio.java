/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.CapaDatos;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class CapaNegocio {
    
    private final CapaDatos capaDatos;
    private static final SimpleDateFormat fecha_hora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public CapaNegocio() {
        capaDatos = new CapaDatos();
    }
    
    public void insertar(Object objeto) {
        capaDatos.insertBD(objeto, objeto.getClass().getSimpleName());
    }
    
    public void actualizar(Object objeto) {
        String nombreTabla = objeto.getClass().getSimpleName();
        capaDatos.updateBD(objeto, nombreTabla, capaDatos.obtenerClavePrimaria(nombreTabla));
    }
    
    public void eliminar(Object objeto) {
        String nombreTabla = objeto.getClass().getSimpleName();
        capaDatos.deleteBD(objeto, nombreTabla, capaDatos.obtenerClavePrimaria(nombreTabla));
    }
    
    private <T> List <T> consultar(Class<T> clase, String where, String orderBy) {
        String nombreTabla = clase.getSimpleName();
        List<T> lista = capaDatos.selectBD(clase, nombreTabla, where, orderBy);
        return lista;
    }
    
    private <T> List <T> consultar(Class<T> clase, String where, String orderBy, int limit) {
        String nombreTabla = clase.getSimpleName();
        List<T> lista = capaDatos.selectBD(clase, nombreTabla, where, orderBy, limit);
        return lista;
    }
    
    private <T> List <T> consultarT(Class<T> clase, String where, String orderBy) {
        String nombreTabla = clase.getSimpleName();
        List<T> lista = capaDatos.selectT(clase, nombreTabla, where, orderBy);
        return lista;
    }
    
    // valorAtributoEntidadFuerte = null para Entidad Fuerte
    public int proximoID(Class<?> clase, String valorAtributoEntidadFuerte) {
        String nombreTabla = clase.getSimpleName();
        String where = null;
        if (!(valorAtributoEntidadFuerte == null || valorAtributoEntidadFuerte.isEmpty())) {
            where = capaDatos.obtenerClavePrimaria(nombreTabla)[0] + " = '" + valorAtributoEntidadFuerte + "'";
        }
        return capaDatos.nextID(nombreTabla, where);
    }
    
    // valorAtributoEntidadFuerte = 0 para Entidad Fuerte
    public int proximoID(Class<?> clase, int valorAtributoEntidadFuerte) {
        String nombreTabla = clase.getSimpleName();
        String where = null;
        if (valorAtributoEntidadFuerte != 0) {
            where = capaDatos.obtenerClavePrimaria(nombreTabla)[0] + " = " + valorAtributoEntidadFuerte;
        }
        return capaDatos.nextID(nombreTabla, where);
    }
    
    public int hacerPrestamo(Usuario usuario, List<Ejemplar> lista, String fecha_hora_estimada_entrega) {
        try {
            if (usuario.getPuede_prestamo() == 1) {
                capaDatos.inicioT();
                String fecha_hora_prestamo = fecha_hora.format(new Timestamp(System.currentTimeMillis()));
                String nombreTabla = Ejemplar.class.getSimpleName();
                for (Ejemplar ejemplar : lista) {
                    if (ejemplar.getPrestado() == 1) {
                        capaDatos.rollbackT();
                        capaDatos.finT();
                        return 2; // Un libro ya está prestado
                    } else {
                        ejemplar.setPrestado(1);
                        capaDatos.insertT(new Alquiler(usuario.getCedula(), ejemplar.getEdicion_isbn(), ejemplar.getId(), fecha_hora_prestamo, fecha_hora_estimada_entrega), Alquiler.class.getSimpleName());
                        capaDatos.updateT(ejemplar, nombreTabla, capaDatos.obtenerClavePrimariaT(nombreTabla));
                    }
                }
                usuario.setPuede_prestamo(0);
                nombreTabla = Usuario.class.getSimpleName();
                capaDatos.updateT(usuario, nombreTabla, capaDatos.obtenerClavePrimariaT(nombreTabla));
                capaDatos.commitT();
                capaDatos.finT();
                return 0; // Éxito
            } else {
                return 1; // El usuario tiene un préstamo pendiente
            }
        } catch(Exception e) {
            capaDatos.rollbackT();
            capaDatos.finT();
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    public int devolverPrestamo(Usuario usuario, List<Alquiler> lista) {
        try {
            if (usuario.getPuede_prestamo() == 0) {
                capaDatos.inicioT();
                String fecha_hora_entrega = fecha_hora.format(new Timestamp(System.currentTimeMillis()));
                String nombreTabla = Usuario.class.getSimpleName();
                usuario.setPuede_prestamo(1);
                capaDatos.updateT(usuario, nombreTabla, capaDatos.obtenerClavePrimariaT(nombreTabla));
                nombreTabla = Alquiler.class.getSimpleName();
                for (Alquiler alquiler : lista) {
                    alquiler.setFecha_hora_entrega(fecha_hora_entrega);
                    capaDatos.updateT(alquiler, nombreTabla, capaDatos.obtenerClavePrimariaT(nombreTabla));
                }
                nombreTabla = Ejemplar.class.getSimpleName();
                for (Alquiler alquiler : lista) {
                    Ejemplar ejemplar = this.consultarT(Ejemplar.class, Edicion.nombreAtributos()[0] + " = '" + alquiler.getEdicion_isbn() + "' AND " + Edicion.nombreAtributos()[1] + " = " + alquiler.getEjemplar_id(), null).get(0);
                    ejemplar.setPrestado(0);
                    capaDatos.updateT(ejemplar, nombreTabla, capaDatos.obtenerClavePrimariaT(nombreTabla));
                }
                capaDatos.commitT();
                capaDatos.finT();
                return 0; // Éxito
            } else {
                return 1; // El usuario no tiene un préstamo pendiente
            }
        } catch(Exception e) {
            capaDatos.rollbackT();
            capaDatos.finT();
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    public List<Usuario> consultarUsuario(String cedula) {
        List<Usuario> lista = consultar(Usuario.class, Usuario.nombreAtributos()[0] + " = '" + cedula + "'", null);
        return lista;
    }
    
    public List<Usuario> buscarUsuarios(String busqueda, int orden) {
        List<Usuario> lista = consultar(Usuario.class, "UPPER(" + Usuario.nombreAtributos()[0] + ") LIKE UPPER('%" + busqueda + "%') OR " + "UPPER(" +  Usuario.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%') OR " + "UPPER(" + Usuario.nombreAtributos()[2] + ") LIKE UPPER('%" + busqueda + "%')", Usuario.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Usuario> todosUsuarios(int orden) {
        List<Usuario> lista = consultar(Usuario.class, null, Usuario.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Autor> consultarAutor(int id) {
        List<Autor> lista = consultar(Autor.class, Autor.nombreAtributos()[0] + " = " + id, null);
        return lista;
    }
    
    public List<Autor> consultarAutor(String nombre) {
        List<Autor> lista = consultar(Autor.class, Autor.nombreAtributos()[1] + " = '" + nombre + "'", null);
        return lista;
    }
    
    public List<Autor> consultarAutores(String busqueda, int limite) {
        List<Autor> lista = consultar(Autor.class, Autor.nombreAtributos()[1] + " LIKE '%" + busqueda + "%'", Autor.nombreAtributos()[1], limite);
        return lista;
    }
    
    public List<Autor> buscarAutores(String busqueda, int orden) {
        List<Autor> lista;
        try {
            int id = Integer.parseInt(busqueda);
            lista = consultar(Autor.class, Autor.nombreAtributos()[0] + " = " + String.valueOf(id) + " OR " + "UPPER(" + Autor.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%')", Autor.nombreAtributos()[orden]);
        } catch (NumberFormatException e) {
            lista = consultar(Autor.class, "UPPER(" + Autor.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%')", Autor.nombreAtributos()[orden]);
        }
        return lista;
    }
    
    public List <Autor> todosAutores(int orden) {
        List<Autor> lista = consultar(Autor.class, null, Autor.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Editorial> consultarEditorial(int id) {
        List<Editorial> lista = consultar(Editorial.class, Editorial.nombreAtributos()[0] + " = " + id, null);
        return lista;
    }
    
    public List<Editorial> consultarEditorial(String nombre) {
        List<Editorial> lista = consultar(Editorial.class, Editorial.nombreAtributos()[1] + " = '" + nombre + "'", null);
        return lista;
    }
    
    public List<Editorial> consultarEditoriales(String busqueda, int limite) {
        List<Editorial> lista = consultar(Editorial.class, Editorial.nombreAtributos()[1] + " LIKE '%" + busqueda + "%'", Editorial.nombreAtributos()[1], limite);
        return lista;
    }
    
    public List<Editorial> buscarEditoriales(String busqueda, int orden) {
        List<Editorial> lista;
        try {
            int id = Integer.parseInt(busqueda);
            lista = consultar(Editorial.class, Editorial.nombreAtributos()[0] + " = " + String.valueOf(id) + " OR " + "UPPER(" + Editorial.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%')", Editorial.nombreAtributos()[orden]);
        } catch (NumberFormatException e) {
            lista = consultar(Editorial.class, "UPPER(" + Editorial.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%')", Editorial.nombreAtributos()[orden]);
        }
        return lista;
    }
    
    public List <Editorial> todosEditoriales(int orden) {
        List<Editorial> lista = consultar(Editorial.class, null, Editorial.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Planta> consultarPlanta(int id) {
        List<Planta> lista = consultar(Planta.class, Planta.nombreAtributos()[0] + " = " + id, null);
        return lista;
    }
    
    public List<Planta> buscarPlantas(String busqueda, int orden) {
        List<Planta> lista;
        try {
            int id = Integer.parseInt(busqueda);
            lista = consultar(Planta.class, Planta.nombreAtributos()[0] + " = " + String.valueOf(id) + " OR " + "UPPER(" + Planta.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%')", Planta.nombreAtributos()[orden]);
        } catch (NumberFormatException e) {
            lista = consultar(Planta.class, "UPPER(" + Planta.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%')", Planta.nombreAtributos()[orden]);
        }
        return lista;
    }
    
    public List <Planta> todasPlantas(int orden) {
        List<Planta> lista = consultar(Planta.class, null, Planta.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Estante> consultarEstante(int idPlanta, int id) {
        List<Estante> lista = consultar(Estante.class, Estante.nombreAtributos()[0] + " = " + idPlanta + " AND " + Estante.nombreAtributos()[1] + " = " + id, null);
        return lista;
    }
    
    public List<Estante> consultarEstantes(int idPlanta) {
        List<Estante> lista = consultar(Estante.class, Estante.nombreAtributos()[0] + " = " + idPlanta, null);
        return lista;
    }
    
    public List<Estante> buscarEstantes(String busqueda, int orden) {
        List<Estante> lista;
        try {
            int id = Integer.parseInt(busqueda);
            lista = consultar(Estante.class, Estante.nombreAtributos()[0] + " = " + String.valueOf(id) + " OR " + Estante.nombreAtributos()[1] + " = " + String.valueOf(id) + " OR " + "UPPER(" + Estante.nombreAtributos()[2] + ") LIKE UPPER('%" + busqueda + "%')", Estante.nombreAtributos()[orden]);
        } catch (NumberFormatException e) {
            lista = consultar(Estante.class, "UPPER(" + Estante.nombreAtributos()[2] + ") LIKE UPPER('%" + busqueda + "%')", Estante.nombreAtributos()[orden]);
        }
        return lista;
    }
    
    public List <Estante> todosEstantes(int orden) {
        List<Estante> lista = consultar(Estante.class, null, Estante.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Libro> consultarLibro(int id) {
        List<Libro> lista = consultar(Libro.class, Libro.nombreAtributos()[0] + " = " + id, null);
        return lista;
    }
    
    public List<Libro> consultarLibro(String nombre) {
        List<Libro> lista = consultar(Libro.class, Libro.nombreAtributos()[1] + " = '" + nombre + "'", null);
        return lista;
    }
    
    public List<Libro> consultarLibros(String busqueda, int limite) {
        List<Libro> lista = consultar(Libro.class, Libro.nombreAtributos()[1] + " LIKE '%" + busqueda + "%'", Libro.nombreAtributos()[1], limite);
        return lista;
    }
    
    public List<Libro> buscarLibros(String busqueda, int orden) {
        List<Libro> lista;
        try {
            int id = Integer.parseInt(busqueda);
            lista = consultar(Libro.class, Libro.nombreAtributos()[0] + " = " + String.valueOf(id) + " OR " + "UPPER(" + Libro.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%')" + Libro.nombreAtributos()[2] + " = " + String.valueOf(id), Libro.nombreAtributos()[orden]);
        } catch (NumberFormatException e) {
            lista = consultar(Libro.class, "UPPER(" + Libro.nombreAtributos()[1] + ") LIKE UPPER('%" + busqueda + "%')", Libro.nombreAtributos()[orden]);
        }
        return lista;
    }
    
    public List <Libro> todosLibros(int orden) {
        List<Libro> lista = consultar(Libro.class, null, Libro.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Edicion> consultarEdicion(String isbn) {
        List<Edicion> lista = consultar(Edicion.class, Edicion.nombreAtributos()[0] + " = '" + isbn + "'", null);
        return lista;
    }
    
    public List<Edicion> buscarEdiciones(String busqueda, int orden) {
        List<Edicion> lista;
        try {
            int id = Integer.parseInt(busqueda);
            lista = consultar(Edicion.class, Edicion.nombreAtributos()[0] + " LIKE '%" + busqueda + "%' OR " + Edicion.nombreAtributos()[1] + " = " + String.valueOf(id) + " OR " + Edicion.nombreAtributos()[2] + " = " + String.valueOf(id) + " OR " + Edicion.nombreAtributos()[3] + " = " + String.valueOf(id) + " OR UPPER(" + Edicion.nombreAtributos()[4] + ") LIKE UPPER('%" + busqueda + "%')" + " OR UPPER(" + Edicion.nombreAtributos()[5] + ") LIKE UPPER('%" + busqueda + "%')", Edicion.nombreAtributos()[orden]);
        } catch (NumberFormatException e) {
            lista = consultar(Edicion.class, Edicion.nombreAtributos()[0] + " LIKE '%" + busqueda + "%'" + " OR UPPER(" + Edicion.nombreAtributos()[4] + ") LIKE UPPER('%" + busqueda + "%')" + " OR UPPER(" + Edicion.nombreAtributos()[5] + ") LIKE UPPER('%" + busqueda + "%')", Edicion.nombreAtributos()[orden]);
        }
        return lista;
    }
    
    public List <Edicion> todasEdiciones(int orden) {
        List<Edicion> lista = consultar(Edicion.class, null, Edicion.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Ejemplar> consultarEjemplar(String isbn, int id) {
        List<Ejemplar> lista = consultar(Ejemplar.class, Ejemplar.nombreAtributos()[0] + " = '" + isbn + "' AND " + Ejemplar.nombreAtributos()[1] + " = " + String.valueOf(id), null);
        return lista;
    }
    
    public List<Ejemplar> buscarEjemplares(String busqueda, int orden) {
        List<Ejemplar> lista;
        try {
            int id = Integer.parseInt(busqueda);
            lista = consultar(Ejemplar.class, Ejemplar.nombreAtributos()[0] + " LIKE '%" + busqueda + "%' OR " + Ejemplar.nombreAtributos()[1] + " = " + String.valueOf(id) + " OR " + Ejemplar.nombreAtributos()[2] + " = " + String.valueOf(id) + " OR " + Ejemplar.nombreAtributos()[3] + " = " + String.valueOf(id) + " OR UPPER(" + Ejemplar.nombreAtributos()[5] + ") LIKE UPPER('%" + busqueda + "%')", Ejemplar.nombreAtributos()[orden]);
        } catch (NumberFormatException e) {
            lista = consultar(Ejemplar.class, Ejemplar.nombreAtributos()[0] + " LIKE '%" + busqueda + "%'" + " OR UPPER(" + Ejemplar.nombreAtributos()[5] + ") LIKE UPPER('%" + busqueda + "%')", Ejemplar.nombreAtributos()[orden]);
        }
        return lista;
    }
    
    public List<Ejemplar> todosEjemplares(int orden) {
        List<Ejemplar> lista = consultar(Ejemplar.class, null, Ejemplar.nombreAtributos()[orden]);
        return lista;
    }
  
}
