/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Timestamp;

/**
 *
 * @author USUARIO
 */
public class Alquiler {

    public Alquiler(String usuario_cedula, String edicion_isbn, int ejemplar_id, Timestamp fecha_hora_prestamo, Timestamp fecha_hora_estimada_entrega) {
        this.usuario_cedula = usuario_cedula;
        this.edicion_isbn = edicion_isbn;
        this.ejemplar_id = ejemplar_id;
        this.fecha_hora_prestamo = fecha_hora_prestamo;
        this.fecha_hora_estimada_entrega = fecha_hora_estimada_entrega;
    }

    public String getUsuario_cedula() {
        return usuario_cedula;
    }

    public void setUsuario_cedula(String usuario_cedula) {
        this.usuario_cedula = usuario_cedula;
    }

    public String getEdicion_isbn() {
        return edicion_isbn;
    }

    public void setEdicion_isbn(String edicion_isbn) {
        this.edicion_isbn = edicion_isbn;
    }

    public int getEjemplar_id() {
        return ejemplar_id;
    }

    public void setEjemplar_id(int ejemplar_id) {
        this.ejemplar_id = ejemplar_id;
    }

    public Timestamp getFecha_hora_prestamo() {
        return fecha_hora_prestamo;
    }

    public void setFecha_hora_prestamo(Timestamp fecha_hora_prestamo) {
        this.fecha_hora_prestamo = fecha_hora_prestamo;
    }

    public Timestamp getFecha_hora_estimada_entrega() {
        return fecha_hora_estimada_entrega;
    }

    public void setFecha_hora_estimada_entrega(Timestamp fecha_hora_estimada_entrega) {
        this.fecha_hora_estimada_entrega = fecha_hora_estimada_entrega;
    }

    public Timestamp getFecha_hora_entrega() {
        return fecha_hora_entrega;
    }

    public void setFecha_hora_entrega(Timestamp fecha_hora_entrega) {
        this.fecha_hora_entrega = fecha_hora_entrega;
    }
    
    private String usuario_cedula;
    private String edicion_isbn;
    private int ejemplar_id; 
    private Timestamp fecha_hora_prestamo;
    private Timestamp fecha_hora_estimada_entrega;
    private Timestamp fecha_hora_entrega;
    
}
