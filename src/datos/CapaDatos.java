/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class CapaDatos {
    
    // Capa de Datos Genérica
    
    private Connection conexion;
    
    private void conectarBD() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","usuario","1234");
        } catch(SQLException | ClassNotFoundException | IllegalArgumentException e) {
            String mensaje = "No se puede conectar a la BD: " + e.getMessage();
            throw new RuntimeException(mensaje, e);
        }
    }
    
    private void desconectarBD() {
        try {
            conexion.close();
        } catch(SQLException | IllegalArgumentException e) {
            String mensaje = "Error al desconectar de la BD: " + e.getMessage();
            throw new RuntimeException(mensaje, e);
        }
    }   
    
    private String sentenciaInsertSQL(Class<?> clase, String nombreTabla) {
        StringBuilder campos = new StringBuilder();
        StringBuilder valores = new StringBuilder();
        for(Field campo : clase.getDeclaredFields()) {
            if(campos.length() > 1) {
                campos.append(",");
                valores.append(",");
            }
            campos.append(campo.getName());
            valores.append("?");
        }
        String sql = "INSERT INTO " + nombreTabla + " (" + campos.toString() + ") VALUES (" + valores.toString() + ")";
        return sql; 
    }
    
    private PreparedStatement insertSQL(Object objeto, String nombreTabla) {
        PreparedStatement sentencia = null;
        try {
            Class<?> clase = objeto.getClass(); 
            String sql = sentenciaInsertSQL(clase, nombreTabla);
            sentencia = conexion.prepareStatement(sql); 
            Field[] campos = clase.getDeclaredFields();
            for(int i = 0; i < campos.length; i++) {
                Field campo = campos[i];
                campo.setAccessible(true); 
                Object valor = campo.get(objeto);
                sentencia.setObject((i + 1), valor);
            }
        } catch(SecurityException | IllegalArgumentException | IllegalAccessException | SQLException e) {
            String mensaje = "Error durante creación de sentencia INSERT SQL: " + e.getMessage();
            throw new RuntimeException(mensaje, e);
        } 
        return sentencia;
    }
    
    public void insertBD(Object objeto, String nombreTabla) {
        try {
            conectarBD();
            conexion.setAutoCommit(false);
            PreparedStatement sentencia = insertSQL(objeto, nombreTabla);
            sentencia.executeUpdate();
            conexion.commit();
            desconectarBD();
        } catch (SQLException | IllegalArgumentException e) {
            try {
                conexion.rollback();
                desconectarBD();
            } catch (SQLException ex) {
                String string = "Error durante rollback: " + ex.getMessage();
                throw new RuntimeException(string, ex);
            }
            String string = "Error durante ejecución de sentencia INSERT SQL: " + e.getMessage();
            throw new RuntimeException(string, e);
        }
    }
    
    private boolean compCadenaArreglo(String cadena, String [] arreglo) {
        for (String partePK : arreglo) {
            if (cadena.equals(partePK)) {
                return true;
            }
        }
        return false;
    }
    
    private String sentenciaUpdateSQL(Class<?> clase, String nombreTabla, String [] clavePrimaria) {
        StringBuilder sets = new StringBuilder();
        StringBuilder where = new StringBuilder();
        for(Field campo : clase.getDeclaredFields()) {
            String nombre = campo.getName();
            String par = nombre + " = ?";
            if(compCadenaArreglo(nombre, clavePrimaria)) {
                if(where.length() > 1) {
                    where.append(" AND ");
                }
                where.append(par);
            } else {
                if(sets.length() > 1) {
                    sets.append(", ");
                }
                sets.append(par);
            }
        }
        if(where == null) {
            String mensaje = "Clave Primaria no encontrada en '" + clase.getName() + "'";
            throw new IllegalArgumentException(mensaje);
        }
        String sql = "UPDATE " + nombreTabla + " SET " + sets.toString() + " WHERE " + where.toString();
        return sql;
    }
    
    private PreparedStatement updateSQL(Object objeto, String nombreTabla, String [] clavePrimaria) {
        PreparedStatement sentencia = null;
        try {
            Class<?> clase = objeto.getClass();
            String sql = sentenciaUpdateSQL(clase, nombreTabla, clavePrimaria);
            sentencia = conexion.prepareStatement(sql);
            Field[] campos = clase.getDeclaredFields();
            Field [] camposClave = new Field [clavePrimaria.length];
            int numeroCamposClave = 0;
            Field [] camposNoClave = new Field [campos.length - clavePrimaria.length];
            int numeroCamposNoClave = 0;
            for (Field campo : campos) {
                campo.setAccessible(true);
                String nombre = campo.getName();
                if(compCadenaArreglo(nombre, clavePrimaria)) {
                    camposClave[numeroCamposClave] = campo;
                    numeroCamposClave++;
                } else {
                    camposNoClave[numeroCamposNoClave] = campo;
                    numeroCamposNoClave++;
                }
            }
            for(int i = 0; i < numeroCamposNoClave; i++) {
                Field campoNoClave = camposNoClave[i];
                campoNoClave.setAccessible(true);
                Object valor = campoNoClave.get(objeto);
                sentencia.setObject((i + 1), valor);
            }
            for(int i = 0; i < numeroCamposClave; i++) {
                Field campoClave = camposClave[i];
                campoClave.setAccessible(true);
                Object valor = campoClave.get(objeto);
                sentencia.setObject((i + 1 + numeroCamposNoClave), valor);
            }
        } catch(SecurityException | IllegalArgumentException | IllegalAccessException | SQLException e) {
            String mensaje = "Error durante creación de sentencia UPDATE SQL: " + e.getMessage();
            throw new RuntimeException(mensaje,e);
        }
        return sentencia;
    }
    
    public void updateBD(Object objeto, String nombreTabla, String [] clavePrimaria) {
        try {
            conectarBD();
            conexion.setAutoCommit(false);
            PreparedStatement sentencia = updateSQL(objeto, nombreTabla, clavePrimaria);
            sentencia.executeUpdate();
            conexion.commit();
            desconectarBD();
        } catch (SQLException | IllegalArgumentException e) {
            try {
                conexion.rollback();
                desconectarBD();
            } catch (SQLException ex) {
                String string = "Error durante rollback: " + ex.getMessage();
                throw new RuntimeException(string, ex);
            }
            String string = "Error durante ejecución de sentencia UPDATE SQL: " + e.getMessage();
            throw new RuntimeException(string, e);
        }
    }
    
    private String sentenciaDeleteSQL(Class<?> clase, String nombreTabla, String [] clavePrimaria) {
        StringBuilder where = new StringBuilder();
        for(Field campo : clase.getDeclaredFields()) {
            String nombre = campo.getName();
            String par = nombre + " = ?";
            if(compCadenaArreglo(nombre, clavePrimaria)) {
                if(where.length() > 1) {
                    where.append(" AND ");
                }
                where.append(par);
            }
        }
        if(where == null) {
            String mensaje = "Clave Primaria no encontrada en '" + clase.getName() + "'";
            throw new IllegalArgumentException(mensaje);
        }
        String sql = "DELETE FROM " + nombreTabla + " WHERE " + where.toString();
        return sql;
    }
    
    private PreparedStatement deleteSQL(Object objeto, String nombreTabla, String [] clavePrimaria) {
        PreparedStatement sentencia = null;
        try {
            Class<?> clase = objeto.getClass();
            String sql = sentenciaDeleteSQL(clase, nombreTabla, clavePrimaria);
            sentencia = conexion.prepareStatement(sql);
            Field[] campos = clase.getDeclaredFields();
            Field [] camposClave = new Field [clavePrimaria.length];
            int numeroCamposClave = 0;
            for (Field campo : campos) {
                campo.setAccessible(true);
                String nombre = campo.getName();
                if(compCadenaArreglo(nombre, clavePrimaria)) {
                   camposClave[numeroCamposClave] = campo;
                   numeroCamposClave++;
                } 
            }
            for(int i = 0; i < numeroCamposClave; i++) {
                Field campoClave = camposClave[i];
                campoClave.setAccessible(true);
                Object valor = campoClave.get(objeto);
                sentencia.setObject((i + 1), valor);
            }
        } catch(SecurityException | IllegalArgumentException | IllegalAccessException | SQLException e) {
            String mensaje = "Error durante creación de sentencia DELETE SQL: " + e.getMessage();
            throw new RuntimeException(mensaje,e);
        }
        return sentencia;
    }
    
    public void deleteBD(Object objeto, String nombreTabla, String [] clavePrimaria) {
        try {
            conectarBD();
            conexion.setAutoCommit(false);
            PreparedStatement sentencia = deleteSQL(objeto, nombreTabla, clavePrimaria);
            sentencia.executeUpdate();
            conexion.commit();
            desconectarBD();
        } catch (SQLException | IllegalArgumentException e) {
            try {
                conexion.rollback();
                desconectarBD();
            } catch (SQLException ex) {
                String string = "Error durante rollback: " + ex.getMessage();
                throw new RuntimeException(string, ex);
            }
            String string = "Error durante ejecución de sentencia DELETE SQL: " + e.getMessage();
            throw new RuntimeException(string, e);
        }
    }
    
    public <T> List <T> selectBD(Class<T> tipo, String nombreTabla, String where, String orderBy) {
        List<T> lista = new ArrayList<>();
        conectarBD();
        String sql = "SELECT * FROM " + nombreTabla;
        if(!(where == null || where.isEmpty())) {
             sql += " WHERE " + where;
        }
        if(!(orderBy == null || orderBy.isEmpty())) {
             sql += " ORDER BY " + orderBy;
        }
        try (Statement sentencia = conexion.createStatement()) {
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()) {
                T t = tipo.newInstance();
                cargarResultadoObjeto(resultado, t);
                lista.add(t);
            }
        } catch(InstantiationException | IllegalAccessException | SQLException e) {
            throw new RuntimeException("Error durante generación de consulta: " + e.getMessage(), e);
        }
        desconectarBD();
        return lista;
    }
    
    private void cargarResultadoObjeto(ResultSet resultado, Object objeto) throws IllegalArgumentException, IllegalAccessException, SQLException { 
        Class<?> clase = objeto.getClass();
        for(Field campo : clase.getDeclaredFields()) {
            String nombre = campo.getName();
            campo.setAccessible(true);
            Object valor = resultado.getObject(nombre);
            if (valor != null) {
                Class<?>  tipo = campo.getType();
                if(esTipoPrimitivo(tipo)) {
                    Class<?> hechoObjeto = objetoTipoPrimitivo(tipo);
                    if (hechoObjeto.equals(Integer.class)) {
                        valor = Integer.parseInt(valor.toString());
                    } else if (hechoObjeto.equals(Long.class)) {
                        valor = Long.parseLong(valor.toString());
                    } else if (hechoObjeto.equals(Double.class)) {
                        valor = Double.parseDouble(valor.toString());
                    } else if (hechoObjeto.equals(Float.class)) {
                        valor = Float.parseFloat(valor.toString());
                    } else if (hechoObjeto.equals(Boolean.class)) {
                        valor = Boolean.parseBoolean(valor.toString());
                    } else if (hechoObjeto.equals(Byte.class)) {
                        valor = Byte.parseByte(valor.toString());
                    } else if (hechoObjeto.equals(Character.class)) {
                        valor = valor.toString().charAt(0);
                    } else {
                        valor = Short.parseShort(valor.toString());
                    }
                    valor = hechoObjeto.cast(valor);
                }
            }
            campo.set(objeto, valor);
        }
    }
    
    private boolean esTipoPrimitivo(Class<?> tipo) {
        return (tipo == int.class || tipo == long.class || tipo == double.class  || tipo == float.class || tipo == boolean.class || tipo == byte.class || tipo == char.class || tipo == short.class);
    }
 
    private Class<?> objetoTipoPrimitivo (Class<?> tipo) {
        if(tipo == int.class){return Integer.class;}
        else if(tipo == long.class){return Long.class;}
        else if (tipo == double.class){return Double.class;}
        else if(tipo == float.class){return Float.class;}
        else if(tipo == boolean.class){return Boolean.class;}
        else if(tipo == byte.class){return Byte.class;}
        else if(tipo == char.class){return Character.class;}
        else if(tipo == short.class){return Short.class;}
        else {
           String mensaje = "Clase '" + tipo.getName() + "' no es un tipo primitivo.";
           throw new IllegalArgumentException(mensaje);
        }
    }
    
    public String [] obtenerClavePrimaria(String nombreTabla) {
        try {
            List<String> pk = new ArrayList<>();
            conectarBD();
            String sql = "SELECT cols.column_name FROM all_constraints cons, all_cons_columns cols WHERE cols.table_name = '" + nombreTabla.toUpperCase() + "' AND cons.constraint_type = 'P' AND cons.constraint_name = cols.constraint_name AND cons.owner = cols.owner ORDER BY cols.table_name, cols.position";
            try (Statement sentencia = conexion.createStatement()) {
                ResultSet resultado = sentencia.executeQuery(sql);
                while (resultado.next())
                    pk.add(resultado.getString(1).toLowerCase());
            }
            desconectarBD();
            String [] clavePrimaria = pk.toArray(new String[pk.size()]);
            return clavePrimaria;
        } catch (SQLException e) {
            throw new RuntimeException("Error durante obtención de clave primaria: " + e.getMessage(), e);
        }
    }
    
    // Transacciones
    
    public void insertT(Object objeto, String nombreTabla) {
        try {
            conexion.setAutoCommit(false);
            PreparedStatement sentencia = insertSQL(objeto, nombreTabla);
            sentencia.executeUpdate();
        } catch (SQLException | IllegalArgumentException e) {
            String string = "Error durante Operación de Transacción (Inserción): " + e.getMessage();
            throw new RuntimeException(string, e);
        }
    }
    
    public void updateT(Object objeto, String nombreTabla, String [] clavePrimaria) {
        try {
            conexion.setAutoCommit(false);
            PreparedStatement sentencia = updateSQL(objeto, nombreTabla, clavePrimaria);
            sentencia.executeUpdate();
        } catch (SQLException | IllegalArgumentException e) {
            String string = "Error durante Operación de Transacción (Actualización): " + e.getMessage();
            throw new RuntimeException(string, e);
        }
    }
    
    public void deleteT(Object objeto, String nombreTabla, String [] clavePrimaria) {
        try {
            conexion.setAutoCommit(false);
            PreparedStatement sentencia = deleteSQL(objeto, nombreTabla, clavePrimaria);
            sentencia.executeUpdate();
        } catch (SQLException | IllegalArgumentException e) {
            String string = "Error durante Operación de Transacción (Eliminación): " + e.getMessage();
            throw new RuntimeException(string, e);
        }
    }
    
    public <T> List <T> selectT(Class<T> tipo, String nombreTabla, String where, String orderBy) {
        List<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + nombreTabla;
        if(!(where == null || where.isEmpty())) {
             sql += " WHERE " + where;
        }
        if(!(orderBy == null || orderBy.isEmpty())) {
             sql += " ORDER BY " + orderBy;
        }
        try (Statement sentencia = conexion.createStatement()) {
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()) {
                T t = tipo.newInstance();
                cargarResultadoObjeto(resultado, t);
                lista.add(t);
            }
        } catch(InstantiationException | IllegalAccessException | SQLException e) {
            throw new RuntimeException("Error durante generación de consulta: " + e.getMessage(), e);
        }
        return lista;
    }
    
    public String [] obtenerClavePrimariaT(String nombreTabla) {
        try {
            List<String> pk = new ArrayList<>();
            String sql = "SELECT cols.column_name FROM all_constraints cons, all_cons_columns cols WHERE cols.table_name = '" + nombreTabla.toUpperCase() + "' AND cons.constraint_type = 'P' AND cons.constraint_name = cols.constraint_name AND cons.owner = cols.owner ORDER BY cols.table_name, cols.position";
            try (Statement sentencia = conexion.createStatement()) {
                ResultSet resultado = sentencia.executeQuery(sql);
                while (resultado.next())
                    pk.add(resultado.getString(1).toLowerCase());
            }
            String [] clavePrimaria = pk.toArray(new String[pk.size()]);
            return clavePrimaria;
        } catch (SQLException e) {
            throw new RuntimeException("Error durante obtención de clave primaria: " + e.getMessage(), e);
        }
    }
    
    public void commitT() {
        try {
            conexion.setAutoCommit(false);
            conexion.commit();
        } catch (SQLException e) {
            String string = "Error durante Commit (Transacción): " + e.getMessage();
            throw new RuntimeException(string, e);
        }
    }
    
    public void rollbackT() {
        try {
            conexion.setAutoCommit(false);
            conexion.rollback();
        } catch (SQLException ex) {
            String string = "Error durante Rollback (Transacción): " + ex.getMessage();
            throw new RuntimeException(string, ex);
        }
    }
    
    public void inicioT() {
        conectarBD();
    }
    
    public void finT() {
        desconectarBD();
    }
    
    // No Genérico (Exclusivo de este programa)
    
    public int nextID (String nombreTabla, String where) {
        try {
            conectarBD();
            String sql = "SELECT MAX(id) from " + nombreTabla;
            if(!(where == null || where.isEmpty())) {
                sql += " WHERE " + where;
            }
            int max_id = 0;
            try (Statement sentencia = conexion.createStatement()) {
                ResultSet resultado = sentencia.executeQuery(sql);
                while(resultado.next()) {
                    String proximoId = resultado.getString(1);
                    if (!(proximoId == null || proximoId.isEmpty())) {
                        max_id = Integer.parseInt(proximoId);
                    }
                }
            }
            desconectarBD();
            return (max_id + 1);
        } catch (SQLException e) {
            throw new RuntimeException("Error durante obtención de útimo ID: " + e.getMessage(), e);
        }
    }

}