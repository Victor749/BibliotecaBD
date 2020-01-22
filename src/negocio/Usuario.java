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
    
    public Usuario() {}

    public Usuario(String cedula, String nombre, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.puede_prestamo = 1;
        this.vetado = 0;
    }

    public Usuario(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.puede_prestamo = 1;
        this.vetado = 0;
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
    
    public int getVetado() {
        return vetado;
    }

    public void setVetado(int vetado) {
        this.vetado = vetado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", nombre=" + nombre + ", direccion=" + direccion + ", puede_prestamo=" + puede_prestamo + '}';
    }
    
    public static String[] nombreAtributos() {
        String[] atributos = {"cedula", "nombre", "direccion", "puede_prestamo", "vetado"};
        return atributos;
    }
    
    private String cedula;
    private String nombre;
    private String direccion;
    private int puede_prestamo;
    private int vetado;
    
}
