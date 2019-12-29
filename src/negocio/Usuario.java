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
public class Usuario {

    public Usuario(String cedula, String nombre, String direccion, int puede_prestamo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.puede_prestamo = puede_prestamo;
    }

    public Usuario(String cedula, String nombre, int puede_prestamo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.puede_prestamo = puede_prestamo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public int getPuede_prestamo() {
        return puede_prestamo;
    }

    public void setPuede_prestamo(int puede_prestamo) {
        this.puede_prestamo = puede_prestamo;
    }
    
    private String cedula;
    private String nombre;
    private String direccion;
    private int puede_prestamo;
    
}
