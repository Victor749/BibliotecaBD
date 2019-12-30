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
public class Transacciones {
    
    private final CapaDatos capaDatos;
    private static final SimpleDateFormat fecha_hora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public Transacciones() {
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
    
    public <T> List <T> consultar(Class<T> clase, String where, String orderBy) {
        String nombreTabla = clase.getSimpleName();
        List<T> lista = capaDatos.selectBD(clase, nombreTabla, where, orderBy);
        return lista;
    }
    
    public <T> List <T> consultarT(Class<T> clase, String where, String orderBy) {
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
                    Ejemplar ejemplar = this.consultarT(Ejemplar.class, "edicion_isbn = '" + alquiler.getEdicion_isbn() + "' AND id = " + alquiler.getEjemplar_id(), null).get(0);
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
    
    public void insertarEjemplares(List<Ejemplar> lista) {
        try {
            capaDatos.inicioT();
            lista.forEach((ejemplar) -> {
                capaDatos.insertT(ejemplar, Ejemplar.class.getSimpleName());
            });
            capaDatos.commitT();
            capaDatos.finT();
        } catch(Exception e) {
            capaDatos.rollbackT();
            capaDatos.finT();
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
