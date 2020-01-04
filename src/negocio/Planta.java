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
public class Planta {
    
    public Planta() {}

    public Planta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Planta{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    public static String[] nombreAtributos() {
        String[] atributos = {"id", "nombre"};
        return atributos;
    }
    
    private int id;
    private String nombre;
    
}
