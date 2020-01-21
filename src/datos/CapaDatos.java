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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import negocio.CapaNegocio;

/**
 *
 * @author USUARIO
 */
public class CapaDatos {
    
    // Capa de Datos Genérica
    
    // Objeto de conexion con la base de datos
    private static Connection conexion;
    private String usuario;
    private String contrasena;
    
    public CapaDatos(String usuario, String contrasena){
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    // Conectar con la base de datos
    private void conectarBD() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",this.usuario,this.contrasena);
            
        } catch(SQLException | ClassNotFoundException | IllegalArgumentException e) {
            String mensaje = "No se puede conectar a la BD: " + e.getMessage();
            // El manejo de excepciones en todo el programa se hace de esta forma
            throw new RuntimeException(mensaje, e);
            // Permite mostrar las excepciones entre las capas a través de las capas
        }
    }
    
    // Desconectar de la base de datos
    private void desconectarBD() {
        try {
            conexion.close();
        } catch(SQLException | IllegalArgumentException e) {
            String mensaje = "Error al desconectar de la BD: " + e.getMessage();
            throw new RuntimeException(mensaje, e);
        }
    }   
    
    // Método para formar una sentencia Insert Genérica
    // Se utiliza el comodín ? para luego  agregar el nombre y valor de los campos
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
    
    // Método que prepara un PreparedStatement para hacer una inserción con los nombres y valores de los campos de un objeto
    // Se utiliza la clase java.lang.reflection.Field para hacer una reflexion del objeto en tiempo de ejecución
    // El nombre de los atributos de los objetos se deben corresponder con los nombres de las columnas de las tablas en la BD
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
    
    // Los métodos que terminen con BD se conectan y desconectan de la base da datos
    // Además hacen un commit por una única operación
    
    // Método genérico para inserción de un objeto
    // Accesible desde la capa de negocio
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
                desconectarBD();
                throw new RuntimeException(string, ex);
            }
            String string = "Error durante ejecución de sentencia INSERT SQL: " + e.getMessage();
            desconectarBD();
            throw new RuntimeException(string, e);
        }
    }
    
    // Método que comprueba si una cadena esta dentro de un arreglo
    // Se usa para determinar si un campo es parte de la clave primaria de una tabla
    private boolean compCadenaArreglo(String cadena, String [] arreglo) {
        for (String partePK : arreglo) {
            if (cadena.equals(partePK)) {
                return true;
            }
        }
        return false;
    }
    
    // Método para formar una sentencia Update Genérica
    // Cada update se realiza indicando el valor de la clave primaria de cada objeto (Where)
    // Esto permite actualizar cada objeto de manera individual
    // Más abajo se describe el método para obtener la clave primaria
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
    
    // Método que prepara un PreparedStatement para hacer una actualización con los nombres y valores de los campos de un objeto
    // Se utiliza la clase Field (Reflection)
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
    
    // Método genérico para actualización de un objeto
    // Accesible desde la capa de negocio
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
                desconectarBD();
                throw new RuntimeException(string, ex);
            }
            String string = "Error durante ejecución de sentencia UPDATE SQL: " + e.getMessage();
            desconectarBD();
            throw new RuntimeException(string, e);
        }
    }
    
    // Método para formar una sentencia Delete Genérica
    // Se utiliza la clase Field (Reflection)
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
    
    // Método que prepara un PreparedStatement para hacer una eliminación con los nombres y valores de los campos de un objeto
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
    
    // Método genérico para eliminación de un objeto
    // Accesible desde la capa de negocio
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
                desconectarBD();
                throw new RuntimeException(string, ex);
            }
            String string = "Error durante ejecución de sentencia DELETE SQL: " + e.getMessage();
            desconectarBD();
            throw new RuntimeException(string, e);
        }
    }
    
    // Consulta genérica que devuelve una lista de objetos genéricos
    // Se le indica una condición (where) y el orden
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
            desconectarBD();
            throw new RuntimeException("Error durante generación de consulta: " + e.getMessage(), e);
        }
        desconectarBD();
        return lista;
    }
    
    // Método extra que permite limitar el número de tuplas que devuelva la consulta
    public <T> List <T> selectBD(Class<T> tipo, String nombreTabla, String where, String orderBy, int limit) {
        List<T> lista = new ArrayList<>();
        conectarBD();
        String sql = "SELECT * FROM " + nombreTabla;
        if(!(where == null || where.isEmpty())) {
             sql += " WHERE " + where;
        }
        if(!(orderBy == null || orderBy.isEmpty())) {
             sql += " ORDER BY " + orderBy;
        }
        if(limit != 0) {
             sql +=  " FETCH NEXT " + String.valueOf(limit) + " ROWS ONLY" ;
        }
        try (Statement sentencia = conexion.createStatement()) {
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()) {
                T t = tipo.newInstance();
                cargarResultadoObjeto(resultado, t);
                lista.add(t);
            }
        } catch(InstantiationException | IllegalAccessException | SQLException e) {
            desconectarBD();
            throw new RuntimeException("Error durante generación de consulta: " + e.getMessage(), e);
        }
        desconectarBD();
        return lista;
    }
    
    // Arma la condición Where para una búsqueda en una tabla
    public String searchWhere(Class<?> clase, String busqueda) {
        StringBuilder where = new StringBuilder();
        int searchInt;
        try {
            searchInt = Integer.parseInt(busqueda);
        } catch (NumberFormatException e) {
            searchInt = -1;
        }
        for(Field campo : clase.getDeclaredFields()) {
            String nombre = campo.getName();
            String par;
            if (campo.getType().equals(String.class)) {
                par = "UPPER(" + nombre + ") LIKE UPPER('%" + busqueda + "%')";
            } else {
                if (searchInt != -1) {
                    par = nombre + " = " + searchInt;
                }  else {
                    par = "";
                }  
            }
            if(where.length() > 1 && !par.equals("")) {
                where.append(" OR ");
            }
            where.append(par);
        }
        return where.toString();
    }
    
    // Arma la condición Where para obtener un objeto específico
    public String queryOneWhereString(Class<?> clase, String [] clavePrimaria, String [] clavePrimariaValues) {
        StringBuilder where = new StringBuilder();
        int i = 0;
        for(Field campo : clase.getDeclaredFields()) {
            String nombre = campo.getName();
            String par;
            if(compCadenaArreglo(nombre, clavePrimaria)) {
                if (campo.getType().equals(String.class)) {
                    par = nombre + " = '" + clavePrimariaValues[i] + "'";
                } else {
                    par = nombre + " = " + clavePrimariaValues[i];
                }
                if(where.length() > 1) {
                    where.append(" AND ");
                }
                where.append(par);
                i++;
            }
        }
        return where.toString();
    }
    
    // Arma la condición Where una consulta con una condición simple =
    public String queryWhereString(Class<?> clase, String condicionName, String condicionValue) {
        try {
            Field campo = clase.getDeclaredField(condicionName);
            String where;
            if (campo.getType().equals(String.class)) {
                where = condicionName + " = '" + condicionValue + "'";
            } else {
                where = condicionName + " = " + condicionValue;
            }
            return where;
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("Campo con nombre no existente: " + e.getMessage(), e);
        }
    }
    
    // Arma la condición Where una consulta con una condición simple Like
    public String queryWhereLikeString(Class<?> clase, String condicionName, String condicionValue) {
        return "UPPER(" + condicionName + ") LIKE UPPER('%" + condicionValue + "%')";
    }
    
    // Carga los campos de un ResultSet en un objeto
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
    
    // Determina si un objeto es de tipo primitivo
    private boolean esTipoPrimitivo(Class<?> tipo) {
        return (tipo == int.class || tipo == long.class || tipo == double.class  || tipo == float.class || tipo == boolean.class || tipo == byte.class || tipo == char.class || tipo == short.class);
    }
 
    // Empaqueta un valor de tipo primitivo en un objeto
    // La clase Field requiere que todo sea un objeto
    // Por eso se requiere este empaquetamiento
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
    
    // Método que consulta la clave primaria de una tabla
    // como un arreglo de String
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
            desconectarBD();
            throw new RuntimeException("Error durante obtención de clave primaria: " + e.getMessage(), e);
        }
    }
    
    // Operaciones para Transacciones
    
    // Se distinguen de las operaciones anteriores debido a que el commit no debe hacerse automaticamente en cada operación
    // Se usa el método setAutoCommit()
    // Para poder controlar la atomicidad de la transacción desde la capa de negocio se deben usar estos métodos
    // Tienen una T al final

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
    
    // Métodos accesibles desde la capa de negocio
    // para poder controlar el flujo de una transacción
    
    // Commit accesible desde capa de negocio
    public void commitT() {
        try {
            conexion.setAutoCommit(false);
            conexion.commit();
        } catch (SQLException e) {
            String string = "Error durante Commit (Transacción): " + e.getMessage();
            throw new RuntimeException(string, e);
        }
    }
    
    // Rollback accesible desde capa de negocio
    public void rollbackT() {
        try {
            conexion.setAutoCommit(false);
            conexion.rollback();
        } catch (SQLException ex) {
            String string = "Error durante Rollback (Transacción): " + ex.getMessage();
            throw new RuntimeException(string, ex);
        }
    }
    
    // Conectar al inicio de transaccion
    public void inicioT() {
        conectarBD();
    }
    
    // Desconectar al fin de transaccion
    public void finT() {
        desconectarBD();
    }
    
    
    // No Genérico (Exclusivo de este programa)
    
    
    // Método para obtener el próximo Id de una entidad específica
    // Realiza una consulta del máximo Id presente en la tabla de dicha entidad
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
            desconectarBD();
            throw new RuntimeException("Error durante obtención de útimo ID: " + e.getMessage(), e);
        }
    }
    
    // Dada dos columnas, forma una consulta para ver si el valor de la segunda columna es nulo
    // para cierto valor de la primera columna
    public String queryGivenOneOtherNull(String columna1, String columna2, String valorColumna1) {
        return columna1 + " = '" + valorColumna1 + "' AND " + columna2 + " IS NULL";
    }
    
    
    
       //se regresa la la informacion necesaria para la creacion de una tabla
    public ArrayList<Object> makeQueryForTable(String sqlStatement) throws SQLException{ //This method should go in CapaDatos
            
            this.conectarBD();
            
            Statement st = conexion.createStatement();
            Statement st2 = conexion.createStatement();
            
            
            ResultSet rs = st.executeQuery(sqlStatement);
            ResultSet rs2 = st2.executeQuery(sqlStatement);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String[] columnas = new String[columnsNumber];
            for (int i = 1; i <= columnsNumber; i++) {
                    columnas[i-1] = rsmd.getColumnName(i) ;
            }

            int rowsNumber=0;
            while(rs2.next()){
                String[] arreglo = new String[columnsNumber];
                for (int i = 1; i <= columnsNumber; i++) {
                    rs2.getString(i);
                }   
                rowsNumber++;
            }
            String[][] matriz = new String[rowsNumber][columnsNumber];
            int i=0;
            while(rs.next()){
                String[] arreglo = new String[columnsNumber];
                for (int j = 1; j <= columnsNumber; j++) {
                    matriz[i][j-1] = rs.getString(j);
                }  
                i++;
            }

            ArrayList<Object> response = new ArrayList<Object>();
            response.add(columnas);
            response.add(matriz);            
            
            this.desconectarBD();
            
            return response;
    }
    
    //se regresa la  informacion necesaria para llenar un combo 
    public ArrayList<String> makeQueryForCombo(String sqlStatement) throws SQLException{ //This method should go in CapaDatos
            
            this.conectarBD();
            
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sqlStatement);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            ArrayList<String> response = new ArrayList<String>();
            
            for (int i = 1; i <= columnsNumber; i++) {
                    response.add(rsmd.getColumnName(i));
            }

            this.desconectarBD();
            
            return response;
    }
    
    //se regresa la  informacion necesaria para llenar un comboEL combo FOR de la interfaz de usuario
    public ArrayList<String> makeQueryForComboFOR(String sqlStatement) throws SQLException{ //This method should go in CapaDatos
            
            this.conectarBD();
            
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sqlStatement);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            ArrayList<String> response = new ArrayList<String>();
            
            while(rs.next()){
                response.add(rs.getString(1));
            }

            this.desconectarBD();
            
            return response;
    }
    
    public void iniciar() {
        this.conectarBD();
        this.desconectarBD();
    }
    
    
    public void crearUsuario(String nuevoUsuario, String contrasenaNuevo, String tipo) throws SQLException{
        this.conectarBD();
        String comando1 ="alter session set \"_ORACLE_SCRIPT\"=true";
        System.out.println(comando1);
        String comando2 ="CREATE USER " + nuevoUsuario + " IDENTIFIED BY " + contrasenaNuevo +"  DEFAULT TABLESPACE biblioteca3  QUOTA 20M on biblioteca3";
        System.out.println(comando2);
        String comando3 ="GRANT " + toLowerCase(tipo) + " TO  " + nuevoUsuario;
        System.out.println(comando3);
            
        Statement stmt = conexion.createStatement();
        
        boolean st = stmt.execute(comando1);
        st = stmt.execute(comando2);
        st = stmt.execute(comando3);

        this.desconectarBD();
        
    }

    
    
}
