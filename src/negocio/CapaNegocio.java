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
    
    private final CapaDatos capaDatos; // Instancia de la Capa de Datos
    private static final SimpleDateFormat fecha_hora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public CapaNegocio() {
        capaDatos = new CapaDatos();
    }
    
    // Los métodos genéricos para CRUD a nivel de Capa de Negocio
    // Hacen un mapeo básico del nombre de la tablas, el cual es 
    // el mismo que el nombre de las clases
    
    // Envia a la capa de datos un objeto para insertar
    public void insertar(Object objeto) {
        capaDatos.insertBD(objeto, objeto.getClass().getSimpleName());
    }
    
    // Envia a la capa de datos un objeto para actualizar
    public void actualizar(Object objeto) {
        String nombreTabla = objeto.getClass().getSimpleName();
        capaDatos.updateBD(objeto, nombreTabla, capaDatos.obtenerClavePrimaria(nombreTabla));
    }
    
    // Envia a la capa de datos un objeto para eliminar
    public void eliminar(Object objeto) {
        String nombreTabla = objeto.getClass().getSimpleName();
        capaDatos.deleteBD(objeto, nombreTabla, capaDatos.obtenerClavePrimaria(nombreTabla));
    }
    
    // Métodos para reazlizar consultas de la BD
    // La capa de datos devuelve una lista de objetos genéricos
    
    // Se pasan de atributos una cadena con las condiciones (where) de la consulta y una cadena que indica el orden de la misma
    private <T> List <T> consultar(Class<T> clase, String where, String orderBy) {
        String nombreTabla = clase.getSimpleName();
        List<T> lista = capaDatos.selectBD(clase, nombreTabla, where, orderBy);
        return lista;
    }
    
    // También se puede limitar el número de tuplas que devuelve la consulta
    private <T> List <T> consultar(Class<T> clase, String where, String orderBy, int limit) {
        String nombreTabla = clase.getSimpleName();
        List<T> lista = capaDatos.selectBD(clase, nombreTabla, where, orderBy, limit);
        return lista;
    }
    
    // Para consultar en medio de una transacción
    // Ya que los otros métodos se conectan y desconectan de la base de datos
    private <T> List <T> consultarT(Class<T> clase, String where, String orderBy) {
        String nombreTabla = clase.getSimpleName();
        List<T> lista = capaDatos.selectT(clase, nombreTabla, where, orderBy);
        return lista;
    }
    
    // Obtiene el proximo id de una entidad determinada
    // valorAtributoEntidadFuerte = null para Entidad Fuerte
    public int proximoID(Class<?> clase, String valorAtributoEntidadFuerte) {
        String nombreTabla = clase.getSimpleName();
        String where = null;
        if (!(valorAtributoEntidadFuerte == null || valorAtributoEntidadFuerte.isEmpty())) {
            where = capaDatos.obtenerClavePrimaria(nombreTabla)[0] + " = '" + valorAtributoEntidadFuerte + "'";
        }
        return capaDatos.nextID(nombreTabla, where);
    }
    
    // Obtiene el proximo id de una entidad determinada
    // valorAtributoEntidadFuerte = 0 para Entidad Fuerte
    public int proximoID(Class<?> clase, int valorAtributoEntidadFuerte) {
        String nombreTabla = clase.getSimpleName();
        String where = null;
        if (valorAtributoEntidadFuerte != 0) {
            where = capaDatos.obtenerClavePrimaria(nombreTabla)[0] + " = " + valorAtributoEntidadFuerte;
        }
        return capaDatos.nextID(nombreTabla, where);
    }
    
    // Transacciones
    
    // Transacción para hacer préstamo
    public String hacerPrestamo(Usuario usuario, List<Ejemplar> lista, String fecha_hora_estimada_entrega) {
        try {
            if (usuario.getPuede_prestamo() == 1) {
                capaDatos.inicioT();
                String fecha_hora_prestamo = fecha_hora.format(new Timestamp(System.currentTimeMillis()));
                String nombreTabla = Ejemplar.class.getSimpleName();
                String [] clavePrimariaEjemplar = capaDatos.obtenerClavePrimariaT(nombreTabla);
                for (Ejemplar ejemplar : lista) {
                    if (ejemplar.getPrestado() == 1) {
                        capaDatos.rollbackT();
                        capaDatos.finT();
                        return "ISBN: " + ejemplar.getEdicion_isbn() + " Id: " + ejemplar.getId(); // Un libro ya está prestado
                    } else {
                        ejemplar.setPrestado(1);
                        capaDatos.insertT(new Alquiler(usuario.getCedula(), ejemplar.getEdicion_isbn(), ejemplar.getId(), fecha_hora_prestamo, fecha_hora_estimada_entrega), Alquiler.class.getSimpleName());
                        capaDatos.updateT(ejemplar, nombreTabla, clavePrimariaEjemplar);
                    }
                }
                usuario.setPuede_prestamo(0);
                nombreTabla = Usuario.class.getSimpleName();
                capaDatos.updateT(usuario, nombreTabla, capaDatos.obtenerClavePrimariaT(nombreTabla));
                capaDatos.commitT();
                capaDatos.finT();
                return "OK"; // Éxito
            } else {
                return "U"; // El usuario tiene un préstamo pendiente
            }
        } catch(Exception e) {
            capaDatos.rollbackT();
            capaDatos.finT();
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    // Transacción para devolver préstamo
    public int devolverPrestamo(Usuario usuario, List<Alquiler> lista) {
        try {
            if (usuario.getPuede_prestamo() == 0) {
                capaDatos.inicioT();
                String fecha_hora_entrega = fecha_hora.format(new Timestamp(System.currentTimeMillis()));
                String nombreTabla = Usuario.class.getSimpleName();
                usuario.setPuede_prestamo(1);
                capaDatos.updateT(usuario, nombreTabla, capaDatos.obtenerClavePrimariaT(nombreTabla));
                nombreTabla = Alquiler.class.getSimpleName();
                String [] clavePrimariaAlquiler = capaDatos.obtenerClavePrimariaT(nombreTabla);
                for (Alquiler alquiler : lista) {
                    alquiler.setFecha_hora_entrega(fecha_hora_entrega);
                    capaDatos.updateT(alquiler, nombreTabla, clavePrimariaAlquiler);
                }
                nombreTabla = Ejemplar.class.getSimpleName();
                String [] clavePrimariaEjemplar = capaDatos.obtenerClavePrimariaT(nombreTabla);
                for (Alquiler alquiler : lista) {
                    Ejemplar ejemplar = this.consultarT(Ejemplar.class, Ejemplar.nombreAtributos()[0] + " = '" + alquiler.getEdicion_isbn() + "' AND " + Ejemplar.nombreAtributos()[1] + " = " + alquiler.getEjemplar_id(), null).get(0);
                    ejemplar.setPrestado(0);
                    capaDatos.updateT(ejemplar, nombreTabla, clavePrimariaEjemplar);
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
    
    // Consultas específicas para alimentar la UI
    // Se usan los métodos de consulta anteriormente definidos
    
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
    
    public List<Ejemplar> consultarEjemplares(String isbn) {
        List<Ejemplar> lista = consultar(Ejemplar.class, Ejemplar.nombreAtributos()[0] + " = '" + isbn + "'", null);
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
    
    public List<Alquiler> consultarAlquiler(String usuario_cedula) {
        List<Alquiler> lista = consultar(Alquiler.class, Alquiler.nombreAtributos()[0] + " = '" + usuario_cedula + "' AND " + Alquiler.nombreAtributos()[5] + " IS NULL", null);
        return lista;
    }
    
    public List<Alquiler> buscarAlquileres(String busqueda, int orden) {
        List<Alquiler> lista;
        lista = consultar(Alquiler.class, Alquiler.nombreAtributos()[0] + " LIKE '%" + busqueda + "%' OR " + Alquiler.nombreAtributos()[1] + " LIKE '%" + busqueda + "%' OR " + Alquiler.nombreAtributos()[3] + " LIKE '%" + busqueda + "%' OR " + Alquiler.nombreAtributos()[4] + " LIKE '%" + busqueda + "%' OR " + Alquiler.nombreAtributos()[5] + " LIKE '%" + busqueda + "%'", Alquiler.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Alquiler> todosAlquileres(int orden) {
        List<Alquiler> lista = consultar(Alquiler.class, null, Alquiler.nombreAtributos()[orden]);
        return lista;
    }
  
}
