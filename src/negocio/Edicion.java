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
public class Edicion {
    
    public Edicion() {}

    public Edicion(String isbn, int libro_id, int editorial_id, int numero, Timestamp fecha, String descripcion) {
        this.isbn = isbn;
        this.libro_id = libro_id;
        this.editorial_id = editorial_id;
        this.numero = numero;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    
    public Edicion(String isbn, int libro_id, int editorial_id, int numero, Timestamp fecha) {
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
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
    
    private String isbn;
    private int libro_id;
    private int editorial_id;
    private int numero;
    private Timestamp fecha;
    private String descripcion;
    
}
