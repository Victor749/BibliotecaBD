/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.CapaDatos;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class CapaNegocio {
    
    private CapaDatos capaDatos; // Instancia de la Capa de Datos
    private static final SimpleDateFormat fecha_hora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private String usuario;
    private String contrasena;
    
    // Los métodos genéricos para CRUD a nivel de Capa de Negocio
    // Hacen un mapeo básico del nombre de la tablas, el cual es 
    // el mismo que el nombre de las clases
    
    public CapaNegocio(String usuario, String contrasena){
        this.usuario = usuario;
        this.contrasena = contrasena;
        capaDatos = new CapaDatos(usuario,contrasena);
    }
    
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
            where = capaDatos.queryWhereString(clase, capaDatos.obtenerClavePrimaria(nombreTabla)[0], valorAtributoEntidadFuerte);
        }
        return capaDatos.nextID(nombreTabla, where);
    }
    
    // Obtiene el proximo id de una entidad determinada
    // valorAtributoEntidadFuerte = 0 para Entidad Fuerte
    public int proximoID(Class<?> clase, int valorAtributoEntidadFuerte) {
        String nombreTabla = clase.getSimpleName();
        String where = null;
        if (valorAtributoEntidadFuerte != 0) {
            where = capaDatos.queryWhereString(clase, capaDatos.obtenerClavePrimaria(nombreTabla)[0], String.valueOf(valorAtributoEntidadFuerte));
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
                usuario.setUltima_fecha_hora_devolucion(fecha_hora_estimada_entrega);
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
                    String [] valores = {alquiler.getEdicion_isbn(), String.valueOf(alquiler.getEjemplar_id())};
                    Ejemplar ejemplar = this.consultarT(Ejemplar.class, capaDatos.queryOneWhereString(Ejemplar.class, clavePrimariaEjemplar, valores)/*Ejemplar.nombreAtributos()[0] + " = '" + alquiler.getEdicion_isbn() + "' AND " + Ejemplar.nombreAtributos()[1] + " = " + alquiler.getEjemplar_id()*/, null).get(0);
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
    
    // Transacción para borrar préstamo
    public int borrarPrestamo(Usuario usuario, List<Alquiler> lista) {
        try {
            if (usuario.getPuede_prestamo() == 0) {
                capaDatos.inicioT();
                String nombreTabla = Usuario.class.getSimpleName();
                usuario.setPuede_prestamo(1);
                capaDatos.updateT(usuario, nombreTabla, capaDatos.obtenerClavePrimariaT(nombreTabla));
                /*nombreTabla = Ejemplar.class.getSimpleName();
                String [] clavePrimariaEjemplar = capaDatos.obtenerClavePrimariaT(nombreTabla);
                for (Alquiler alquiler : lista) {
                    String [] valores = {alquiler.getEdicion_isbn(), String.valueOf(alquiler.getEjemplar_id())};*/
                    //Ejemplar ejemplar = this.consultarT(Ejemplar.class, capaDatos.queryOneWhereString(Ejemplar.class, clavePrimariaEjemplar, valores)/*Ejemplar.nombreAtributos()[0] + " = '" + alquiler.getEdicion_isbn() + "' AND " + Ejemplar.nombreAtributos()[1] + " = " + alquiler.getEjemplar_id()*/, null).get(0);
                    /*ejemplar.setPrestado(0);
                    capaDatos.updateT(ejemplar, nombreTabla, clavePrimariaEjemplar);
                }*/
                nombreTabla = Alquiler.class.getSimpleName();
                String [] clavePrimariaAlquiler = capaDatos.obtenerClavePrimariaT(nombreTabla);
                for (Alquiler alquiler : lista) {
                    capaDatos.deleteT(alquiler, nombreTabla, clavePrimariaAlquiler);
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
        String [] valores = {cedula};
        List<Usuario> lista = consultar(Usuario.class, capaDatos.queryOneWhereString(Usuario.class, capaDatos.obtenerClavePrimaria(Usuario.class.getSimpleName()), valores), null);
        return lista;
    }
    
    public List<Usuario> buscarUsuarios(String busqueda, int orden) {
        List<Usuario> lista = consultar(Usuario.class, capaDatos.searchWhere(Usuario.class, busqueda), Usuario.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Usuario> todosUsuarios(int orden) {
        List<Usuario> lista = consultar(Usuario.class, null, Usuario.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Autor> consultarAutor(int id) {
        String [] valores = {String.valueOf(id)};
        List<Autor> lista = consultar(Autor.class, capaDatos.queryOneWhereString(Autor.class, capaDatos.obtenerClavePrimaria(Autor.class.getSimpleName()), valores), null);
        return lista;
    }
    
    public List<Autor> consultarAutor(String nombre) {
        List<Autor> lista = consultar(Autor.class, capaDatos.queryWhereString(Autor.class, Autor.nombreAtributos()[1], nombre), null);
        return lista;
    }
    
    public List<Autor> consultarAutores(String busqueda, int limite) {
        List<Autor> lista = consultar(Autor.class, capaDatos.queryWhereLikeString(Autor.class, Autor.nombreAtributos()[1], busqueda), Autor.nombreAtributos()[1], limite);
        return lista;
    }
    
    public List<Autor> buscarAutores(String busqueda, int orden) {
        List<Autor> lista;
        lista = consultar(Autor.class, capaDatos.searchWhere(Autor.class, busqueda), Autor.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Autor> todosAutores(int orden) {
        List<Autor> lista = consultar(Autor.class, null, Autor.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Editorial> consultarEditorial(int id) {
        String [] valores = {String.valueOf(id)};
        List<Editorial> lista = consultar(Editorial.class, capaDatos.queryOneWhereString(Editorial.class, capaDatos.obtenerClavePrimaria(Editorial.class.getSimpleName()), valores), null);
        return lista;
    }
    
    public List<Editorial> consultarEditorial(String nombre) {
        List<Editorial> lista = consultar(Editorial.class, capaDatos.queryWhereString(Editorial.class, Editorial.nombreAtributos()[1], nombre), null);
        return lista;
    }
    
    public List<Editorial> consultarEditoriales(String busqueda, int limite) {
        List<Editorial> lista = consultar(Editorial.class, capaDatos.queryWhereLikeString(Editorial.class, Editorial.nombreAtributos()[1], busqueda), Editorial.nombreAtributos()[1], limite);
        return lista;
    }
    
    public List<Editorial> buscarEditoriales(String busqueda, int orden) {
        List<Editorial> lista;
        lista = consultar(Editorial.class, capaDatos.searchWhere(Editorial.class, busqueda), Editorial.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Editorial> todosEditoriales(int orden) {
        List<Editorial> lista = consultar(Editorial.class, null, Editorial.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Planta> consultarPlanta(int id) {
        String [] valores = {String.valueOf(id)};
        List<Planta> lista = consultar(Planta.class, capaDatos.queryOneWhereString(Planta.class, capaDatos.obtenerClavePrimaria(Planta.class.getSimpleName()), valores), null);
        return lista;
    }
    
    public List<Planta> buscarPlantas(String busqueda, int orden) {
        List<Planta> lista;
        lista = consultar(Planta.class, capaDatos.searchWhere(Planta.class, busqueda), Planta.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Planta> todasPlantas(int orden) {
        List<Planta> lista = consultar(Planta.class, null, Planta.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Estante> consultarEstante(int idPlanta, int id) {
        String [] valores = {String.valueOf(idPlanta), String.valueOf(id)};
        List<Estante> lista = consultar(Estante.class, capaDatos.queryOneWhereString(Estante.class, capaDatos.obtenerClavePrimaria(Estante.class.getSimpleName()), valores), null);
        return lista;
    }
    
    public List<Estante> consultarEstantes(int idPlanta) {
        List<Estante> lista = consultar(Estante.class, capaDatos.queryWhereString(Estante.class, Estante.nombreAtributos()[0], String.valueOf(idPlanta)), null);
        return lista;
    }
    
    public List<Estante> buscarEstantes(String busqueda, int orden) {
        List<Estante> lista;
        lista = consultar(Estante.class, capaDatos.searchWhere(Estante.class, busqueda), Estante.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Estante> todosEstantes(int orden) {
        List<Estante> lista = consultar(Estante.class, null, Estante.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Libro> consultarLibro(int id) {
        String [] valores = {String.valueOf(id)};
        List<Libro> lista = consultar(Libro.class, capaDatos.queryOneWhereString(Libro.class, capaDatos.obtenerClavePrimaria(Libro.class.getSimpleName()), valores), null);
        return lista;
    }
    
    public List<Libro> consultarLibro(String nombre) {
        List<Libro> lista = consultar(Libro.class, capaDatos.queryWhereString(Libro.class, Libro.nombreAtributos()[1], nombre), null);
        return lista;
    }
    
    public List<Libro> consultarLibros(String busqueda, int limite) {
        List<Libro> lista = consultar(Libro.class, capaDatos.queryWhereLikeString(Libro.class, Libro.nombreAtributos()[1], busqueda), Libro.nombreAtributos()[1], limite);
        return lista;
    }
    
    public List<Libro> buscarLibros(String busqueda, int orden) {
        List<Libro> lista;
        lista = consultar(Libro.class, capaDatos.searchWhere(Libro.class, busqueda), Libro.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Libro> todosLibros(int orden) {
        List<Libro> lista = consultar(Libro.class, null, Libro.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Edicion> consultarEdicion(String isbn) {
        String [] valores = {isbn};
        List<Edicion> lista = consultar(Edicion.class, capaDatos.queryOneWhereString(Edicion.class, capaDatos.obtenerClavePrimaria(Edicion.class.getSimpleName()), valores), null);
        return lista;
    }
    
    public List<Edicion> buscarEdiciones(String busqueda, int orden) {
        List<Edicion> lista;
        lista = consultar(Edicion.class, capaDatos.searchWhere(Edicion.class, busqueda), Edicion.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Edicion> todasEdiciones(int orden) {
        List<Edicion> lista = consultar(Edicion.class, null, Edicion.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Ejemplar> consultarEjemplar(String isbn, int id) {
        String [] valores = {isbn, String.valueOf(id)};
        List<Ejemplar> lista = consultar(Ejemplar.class, capaDatos.queryOneWhereString(Ejemplar.class, capaDatos.obtenerClavePrimaria(Ejemplar.class.getSimpleName()), valores), null);
        return lista;
    }
    
    public List<Ejemplar> consultarEjemplares(String isbn) {
        List<Ejemplar> lista = consultar(Ejemplar.class, capaDatos.queryWhereString(Ejemplar.class, Ejemplar.nombreAtributos()[0], isbn), null);
        return lista;
    }
    
    public List<Ejemplar> buscarEjemplares(String busqueda, int orden) {
        List<Ejemplar> lista;
        lista = consultar(Ejemplar.class, capaDatos.searchWhere(Ejemplar.class, busqueda), Ejemplar.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Ejemplar> todosEjemplares(int orden) {
        List<Ejemplar> lista = consultar(Ejemplar.class, null, Ejemplar.nombreAtributos()[orden]);
        return lista;
    }
    
    public List<Alquiler> consultarAlquiler(String usuario_cedula) {
        List<Alquiler> lista = consultar(Alquiler.class, capaDatos.queryGivenOneOtherNull(Alquiler.nombreAtributos()[0], Alquiler.nombreAtributos()[5], usuario_cedula), null);
        return lista;
    }
    
    public List<Alquiler> buscarAlquileres(String busqueda, int orden) {
        List<Alquiler> lista;
        lista = consultar(Alquiler.class, capaDatos.searchWhere(Alquiler.class, busqueda), Alquiler.nombreAtributos()[orden]);
        return lista;
    }
    
    public List <Alquiler> todosAlquileres(int orden) {
        List<Alquiler> lista = consultar(Alquiler.class, null, Alquiler.nombreAtributos()[orden]);
        return lista;
    }
    
    public void crearUsuario(String nuevoUsuario, String contrasenaNuevo, String tipo) throws SQLException{
        capaDatos.crearUsuario(nuevoUsuario,contrasenaNuevo, tipo);
    }
    
    public void iniciar() {
        capaDatos.iniciar();
    }
    
    public List<Pedido> todosPedidos() {
        List<Pedido> lista = consultar(Pedido.class, null, "fecha_hora");
        return lista;
    }
    
    public List<Desabastecimiento> todosDesabastecimientos() {
        List<Desabastecimiento> lista = consultar(Desabastecimiento.class, null, "fecha_hora");
        return lista;
    }
    
     //Lamada a metodos de la capa de datos
    public ArrayList<String> solicitarConsultaParaCombo(String sqlStatement) throws SQLException{
        return capaDatos.makeQueryForCombo(sqlStatement);
    }
    public ArrayList<String> solicitarConsultaParaComboFOR(String sqlStatement) throws SQLException{
        return capaDatos.makeQueryForComboFOR(sqlStatement);
    }
    public ArrayList<Object> solicitarConsultaParaTabla(String sqlStatement) throws SQLException{
        return capaDatos.makeQueryForTable(sqlStatement);
    }
  
}
