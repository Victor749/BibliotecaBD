/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecabd;

import ui.*;
//import java.util.List;
//import negocio.*;

/**
 *
 * @author USUARIO
 */
public class BibliotecaBD {
    
    //private static final SimpleDateFormat fecha_hora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //private static final SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Pruebas Únicamente
        
        //CapaNegocio tr = new CapaNegocio();
        
        //CapaDatos cp = new CapaDatos();
        /*cp.nextID("Autor", null)*/
        //Alquiler objeto = new Alquiler("1234567890", "4444444444444", 1, fecha_hora.format(new Timestamp(0)), fecha_hora.format(new Timestamp(123456789)));
        //cp.insertBD(objeto, "Alquiler");
        //objeto.setFecha_hora_entrega(fecha_hora.format(new Timestamp(999999999)));
        //cp.updateBD(objeto, "Alquiler", cp.obtenerClavePrimaria("Alquiler"));
        //cp.deleteBD(objeto, "Alquiler", cp.obtenerClavePrimaria("Alquiler"));
        
        /*List<Alquiler> lista = tr.consultar(Alquiler.class, null, null);
        lista.forEach((objeto1) -> {
            System.out.println(objeto1.toString());
        });*/
        

        //CapaDatos cp = new CapaDatos();
        /*cp.nextID("Edicion", null)*/
        //Edicion objeto = new Edicion("1111111111666", 1, 1, 1, fecha.format(new Timestamp(0)));
        //cp.insertBD(objeto, "Edicion");
        //objeto.setDescripcion("Nada Nuevo, Original");
        //cp.updateBD(objeto, "Edicion", cp.obtenerClavePrimaria("Edicion"));
        //cp.deleteBD(objeto, "Edicion", cp.obtenerClavePrimaria("Edicion"));
        
        
        /*List<Edicion> lista1 = tr.consultar(Edicion.class, null, "fecha");
        lista1.forEach((objeto1) -> {
            System.out.println(objeto1.toString());
        });*/
        
        /*try {
            tr.insertar(objeto);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }*/
        
        //System.out.println(cp.nextID(Estante.class.getSimpleName(), "planta_id = 22"));
        
        
        /*Timestamp t = new Timestamp(System.currentTimeMillis());
        String fecha_hora_cadena = fecha_hora.format(t);
        String fecha_cadena = fecha.format(t);
        System.out.println(fecha_hora_cadena);
        System.out.println(fecha_cadena);*/
        
        //Transacciones tr = new Transacciones();
        
        /*System.out.println("USUARIOS\n");
        
        List<Usuario> lista = tr.consultar(Usuario.class, null, null);
        lista.forEach((objeto) -> {
            System.out.println(objeto.toString());
        });*/
        
        /*System.out.println("\n\nEJEMPLARES\n");
        
        List<Ejemplar> lista1 = tr.consultar(Ejemplar.class, null, null);
        lista1.forEach((objeto1) -> {
            System.out.println(objeto1.toString());
        });*/
        
        /*System.out.println("\n\nALQUILERES\n");
        
        List<Alquiler> lista2 = tr.consultar(Alquiler.class, null, null);
        lista2.forEach((objeto2) -> {
            System.out.println(objeto2.toString());
        });*/
        
        
        //Usuario usuario = lista.get(1);
        /*List<Ejemplar> ejemplares = new ArrayList<>();
        ejemplares.add(lista1.get(9));
        ejemplares.add(lista1.get(10));
        
        System.out.println(tr.hacerPrestamo(usuario, ejemplares, "2019-12-31 20:00:00"));*/
        
        /*List<Alquiler> alquileres = new ArrayList<>();
        alquileres.add(lista2.get(0));
        alquileres.add(lista2.get(1));
        
        System.out.println(tr.devolverPrestamo(usuario, alquileres));*/
        
        /*List<Ejemplar> ejemplares1 = new ArrayList<>();
        int nextId = tr.proximoID(Ejemplar.class, "7777777777777");
        int numeroEjemplaresIngresar = 9;
        for (int i = nextId; i < numeroEjemplaresIngresar + nextId; i++)
            ejemplares1.add(new Ejemplar("7777777777777", i, 1, 1, 0));
        tr.insertarEjemplares(ejemplares1);*/
        
        UIMenu ui = new UIMenu();
        ui.iniciar();
    }
    
}
