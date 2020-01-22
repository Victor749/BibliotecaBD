/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author USUARIO
 */
public class Pedido {

    public String getLibro_nombre() {
        return libro_nombre;
    }

    public void setLibro_nombre(String libro_nombre) {
        this.libro_nombre = libro_nombre;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
    
    private String libro_nombre;
    private String fecha_hora;
    
}
