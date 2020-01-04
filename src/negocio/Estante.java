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
public class Estante {
    
    public Estante() {}

    public Estante(int planta_id, int id, String nombre) {
        this.planta_id = planta_id;
        this.id = id;
        this.nombre = nombre;
    }

    public int getPlanta_id() {
        return planta_id;
    }

    public void setPlanta_id(int planta_id) {
        this.planta_id = planta_id;
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
        return "Estante{" + "planta_id=" + planta_id + ", id=" + id + ", nombre=" + nombre + '}';
    }
    
    public static String[] nombreAtributos() {
        String[] atributos = {"planta_id", "id", "nombre"};
        return atributos;
    }
    
    private int planta_id;
    private int id;
    private String nombre;
    
}
