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
public class Ejemplar {
    
    public Ejemplar() {}
    
    public Ejemplar(String edicion_isbn, int id, int planta_id, int estante_id, int prestado, String observaciones) {
        this.edicion_isbn = edicion_isbn;
        this.id = id;
        this.planta_id = planta_id;
        this.estante_id = estante_id;
        this.prestado = prestado;
        this.observaciones = observaciones;
    }

    public Ejemplar(String edicion_isbn, int id, int planta_id, int estante_id, int prestado) {
        this.edicion_isbn = edicion_isbn;
        this.id = id;
        this.planta_id = planta_id;
        this.estante_id = estante_id;
        this.prestado = prestado;
    }

    public String getEdicion_isbn() {
        return edicion_isbn;
    }

    public void setEdicion_isbn(String edicion_isbn) {
        this.edicion_isbn = edicion_isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlanta_id() {
        return planta_id;
    }

    public void setPlanta_id(int planta_id) {
        this.planta_id = planta_id;
    }

    public int getEstante_id() {
        return estante_id;
    }

    public void setEstante_id(int estante_id) {
        this.estante_id = estante_id;
    }

    public int getPrestado() {
        return prestado;
    }

    public void setPrestado(int prestado) {
        this.prestado = prestado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "edicion_isbn=" + edicion_isbn + ", id=" + id + ", planta_id=" + planta_id + ", estante_id=" + estante_id + ", prestado=" + prestado + ", observaciones=" + observaciones + '}';
    }
    
    public static String[] nombreAtributos() {
        String[] atributos = {"edicion_isbn", "id", "planta_id", "estante_id", "prestado", "observaciones"};
        return atributos;
    }
    
    private String edicion_isbn;
    private int id;
    private int planta_id;
    private int estante_id;
    private int prestado;
    private String observaciones;
    
}
