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
    
    public Ejemplar(String edicion_isbn, int id, int planta_id, int estante_id, String observaciones) {
        this.edicion_isbn = edicion_isbn;
        this.id = id;
        this.planta_id = planta_id;
        this.estante_id = estante_id;
        this.prestado = 0;
        this.observaciones = observaciones;
        this.mal_estado = 0;
    }

    public Ejemplar(String edicion_isbn, int id, int planta_id, int estante_id) {
        this.edicion_isbn = edicion_isbn;
        this.id = id;
        this.planta_id = planta_id;
        this.estante_id = estante_id;
        this.prestado = 0;
        this.mal_estado = 0;
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

    public int getMal_estado() {
        return mal_estado;
    }

    public void setMal_estado(int mal_estado) {
        this.mal_estado = mal_estado;
    }
    
    @Override
    public String toString() {
        return "Ejemplar{" + "edicion_isbn=" + edicion_isbn + ", id=" + id + ", planta_id=" + planta_id + ", estante_id=" + estante_id + ", prestado=" + prestado + ", observaciones=" + observaciones + '}';
    }
    
    public static String[] nombreAtributos() {
        String[] atributos = {"edicion_isbn", "id", "planta_id", "estante_id", "prestado", "observaciones", "mal_estado"};
        return atributos;
    }
    
    private String edicion_isbn;
    private int id;
    private int planta_id;
    private int estante_id;
    private int prestado;
    private String observaciones;
    private int mal_estado;
    
}
