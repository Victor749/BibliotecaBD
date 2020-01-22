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
public class Libro {
    
    public Libro() {}

    public Libro(int id, String titulo, int autor_id) {
        this.id = id;
        this.titulo = titulo;
        this.autor_id = autor_id;
        this.baja_disponibilidad = 0;
        this.ejemplares_totales = 0;
        this.ejemplares_prestados = 0;
        this.ejemplares_daniados = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }
    
    public int getBaja_disponibilidad() {
        return baja_disponibilidad;
    }

    public void setBaja_disponibilidad(int baja_disponibilidad) {
        this.baja_disponibilidad = baja_disponibilidad;
    }
    
     public int getEjemplares_totales() {
        return ejemplares_totales;
    }

    public void setEjemplares_totales(int ejemplares_totales) {
        this.ejemplares_totales = ejemplares_totales;
    }

    public int getEjemplares_prestados() {
        return ejemplares_prestados;
    }

    public void setEjemplares_prestados(int ejemplares_prestados) {
        this.ejemplares_prestados = ejemplares_prestados;
    }

    public int getEjemplares_daniados() {
        return ejemplares_daniados;
    }

    public void setEjemplares_daniados(int ejemplares_daniados) {
        this.ejemplares_daniados = ejemplares_daniados;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor_id=" + autor_id + '}';
    }
    
    public static String[] nombreAtributos() {
        String[] atributos = {"id", "titulo", "autor_id", "baja_disponibilidad"};
        return atributos;
    }
    
    private int id;
    private String titulo;
    private int autor_id;
    private int baja_disponibilidad;
    private int ejemplares_totales;
    private int ejemplares_prestados;
    private int ejemplares_daniados;
    
}
