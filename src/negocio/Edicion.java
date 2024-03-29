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
public class Edicion {
    
    public Edicion() {}

    public Edicion(String isbn, int libro_id, int editorial_id, int numero, String fecha, String descripcion) {
        this.isbn = isbn;
        this.libro_id = libro_id;
        this.editorial_id = editorial_id;
        this.numero = numero;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    
    public Edicion(String isbn, int libro_id, int editorial_id, int numero, String fecha) {
        this.isbn = isbn;
        this.libro_id = libro_id;
        this.editorial_id = editorial_id;
        this.numero = numero;
        this.fecha = fecha;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
    }

    public int getEditorial_id() {
        return editorial_id;
    }

    public void setEditorial_id(int editorial_id) {
        this.editorial_id = editorial_id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Edicion{" + "isbn=" + isbn + ", libro_id=" + libro_id + ", editorial_id=" + editorial_id + ", numero=" + numero + ", fecha=" + fecha + ", descripcion=" + descripcion + '}';
    }
    
    public static String[] nombreAtributos() {
        String[] atributos = {"isbn", "libro_id", "editorial_id", "numero", "fecha", "descripcion"};
        return atributos;
    }
    
    private String isbn;
    private int libro_id;
    private int editorial_id;
    private int numero;
    private String fecha;
    private String descripcion;
    
}
