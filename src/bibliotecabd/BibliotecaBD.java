/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecabd;

import datos.CapaDatos;
import java.sql.Timestamp;
import java.util.List;
import negocio.*;

/**
 *
 * @author USUARIO
 */
public class BibliotecaBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Pruebas Únicamente
        
        //CapaDatos cp = new CapaDatos();
        /*cp.nextID("Autor", null)*/
        //Alquiler objeto = new Alquiler("1234567890", "4444444444444", 1, new Timestamp(0), new Timestamp(0));
        //cp.insertBD(objeto, "Alquiler");
        //objeto.setFecha_hora_entrega(new Timestamp(1234567890));
        //cp.updateBD(objeto, "Alquiler", cp.obtenerClavePrimaria("Alquiler"));
        //cp.deleteBD(objeto, "Alquiler", cp.obtenerClavePrimaria("Alquiler"));
        
         /*
        List<Alquiler> lista = cp.consulta(Alquiler.class, "Alquiler", null);
        lista.forEach((objeto1) -> {
            System.out.println(objeto1.toString());
        });*/

        //CapaDatos cp = new CapaDatos();
        /*cp.nextID("Edicion", null)*/
        //Edicion objeto = new Edicion("4444444334444", 1, 1, 1, new Timestamp(0));
        //cp.insertBD(objeto, "Edicion");
        //objeto.setDescripcion("Nada Nuevo, Original");
        //cp.updateBD(objeto, "Edicion", cp.obtenerClavePrimaria("Edicion"));
        //cp.deleteBD(objeto, "Edicion", cp.obtenerClavePrimaria("Edicion"));
        
        /*List<Edicion> lista = cp.consulta(Edicion.class, "Edicion", null);
        lista.forEach((objeto1) -> {
            System.out.println(objeto1.toString());
        });*/
    }
    
}
