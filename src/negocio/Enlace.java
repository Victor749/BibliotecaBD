/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.CapaDatos;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class Enlace {
    
    //Clase que se usa para la conexion o enlace de la capa de datos hacia la interfaz de usuario
    
    CapaDatos capaDatos;

    // Se inica la capa de datos en el constructor
    public Enlace() {
        capaDatos = new CapaDatos();
    }
    
    //Lamada a metodos de la capa de datos
    public ArrayList<String> solicitarConsultaParaCombo(String sqlStatement) throws SQLException{
        return capaDatos.makeQueryForCombo(sqlStatement);
    }
    public ArrayList<String> solicitarConsultaParaComboFOR(String sqlStatement) throws SQLException{
        return capaDatos.makeQueryForComboFOR(sqlStatement);
    }
    public ArrayList<Object> solicitarConsultaParaTabla(String sqlStatement) throws SQLException{
        return capaDatos.makeQueryForTable(sqlStatement);
    }
    
    
    
    
    
}
