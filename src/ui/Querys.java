
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import negocio.CapaNegocio;
import static ui.Querys.myFrame;

 

public  class Querys extends JFrame {


    // Se cargan los iconos para la interfaz de usuario en las siguientes variables

    Icon addIcon = new ImageIcon("src/ui/botonAnadir.png");
    Icon addIconPressed = new ImageIcon("src/ui/botonAnadirPressed.png");
    Icon removeIcon = new ImageIcon("src/ui/botonQuitar.png");
    Icon removeIconPressed = new ImageIcon("src/ui/botonQuitarPressed.png");
    Icon queryIcon = new ImageIcon("src/ui/query.png");
    Icon queryIconPressed = new ImageIcon("src/ui/queryPressed.png");
    Icon refreshIcon = new ImageIcon("src/ui/refresh.png");
    Icon refreshIconPressed = new ImageIcon("src/ui/refreshPressed.png");
    
    String nombreUsuario;
    String contrasena;
    
    // Se declara el JFrame que se utilizara como la interfaz de la vista
    static Querys myFrame;
    
    
    //Se inicializa la variable de la cantidad de sub-frames que se usara
    static int countMe = 0;
    JPanel mainPanel;
    //static Connection con;
    JComboBox fromTable;
    
    // Se inicia el enlace a la capa de negocio que hace referencia a la capa de datos
    CapaNegocio negocio;
    JTextField consola = new JTextField();
    JScrollPane sp = new JScrollPane();
    JTable tabla = new JTable();
    
    //Estas listas son las encargadas en mantener la concistencia de la interfaz a crear
    ArrayList<subPanel> listElementsStatement = new ArrayList<subPanel>();
    ArrayList<JComboBox> comboBoxesParam = new ArrayList<JComboBox>();
    ArrayList<JComboBox> comboBoxesSELECT = new ArrayList<JComboBox>();
    ArrayList<JComboBox> comboBoxesINNERJOIN = new ArrayList<JComboBox>();

    //
    JInternalFrame internalFrame = null;
    
    public Querys(CapaNegocio capaNegocio){
        this.negocio=capaNegocio;
    }
    
    
    //metodo que se llama para inicializar la interfaz
    public void iniciar() {
        
        //capaDatos = new CapaDatos();
        //capaDatos.conectarBD();
        //con = capaDatos.getConexion();
        
        SwingUtilities.invokeLater(() -> {
            try {
                //Se llama al metodo para crear y mostrar la interfaz
                createAndShowGUI();
            } catch (SQLException ex) {
                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    private /*static*/ void createAndShowGUI() throws SQLException {
        myFrame = new Querys(negocio);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.prepareUI();
        //myFrame.setRelativeTo(null);
        myFrame.setVisible(true);
    }

    //Se coloca en el JFrame que es la interfaz de usuario las partes mas importantes
    //como el como el Panel que contendra los subpanels y la cabecera ademas de la
    // del text fiel que contendra la sentencia a crear y sus botones de actualizacion y de solicitud para mostrar el resultado
    private void prepareUI() throws SQLException {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        //combo principal de donde se muestra las sentencias a crear
        JComboBox combo = new JComboBox();
        
         
//        JButton buttonRemoveAll = new JButton("Remove All");
//        buttonRemoveAll.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                countMe=0;
//                mainPanel.removeAll();
myFrame.revalidate();//               myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
//            }
//        });
 


        //Se añade las partes aneteriormente menciaonados
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(new subPanel(1, -1), BorderLayout.PAGE_START);
        getContentPane().add(new subPanel(0, -1), BorderLayout.PAGE_END);
        
        //getContentPane().add(buttonRemoveAll, BorderLayout.PAGE_END);
       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
    }
   
   private void setRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   //Clase anidada que nos servira para crear subpaneles que podran anadirse al mainpanel ya anadido al Frame
   private class subPanel extends JPanel {


        //
        subPanel me;
        //index dependera de la variable countMe de la clase externa
        int index;
        //bandera para situaciones donde el mismo comportamiento olo debe suceder una vez
        int flag;
        String toSend = "";
        JComboBox INNERJOINTables;
        
        //Constructor de clase anidada
        public subPanel(int key, int index) throws SQLException {
            //se llama al consturctor de la clase padre
            super();
            me = this;
            this.index = index;
            System.out.println("Se ha introducido un nuevo panel: "+ String.valueOf(index));

            //si el constructor se manda con un cero es para crear el subpanel que sera
            //la linea mas baja donde nuestra sentencia aparecera actualizada y lista para enviar
            if(key == 0){
                
                JButton botonActualizar = createBotonActualizar();
                JButton botonSEND = createBotonSend();
                
                JTextArea areaTexto = new JTextArea();
                areaTexto.setText("<<Actualize una vez configurada su consulta>>");
                areaTexto.setBorder( BorderFactory.createLineBorder(Color.GRAY, 4));
                
                add(areaTexto);
                add(botonActualizar);
                add(botonSEND);
                
//                areaTexto.getDocument().addDocumentListener(new DocumentListener(){
//                    @Override
//                    public void insertUpdate(DocumentEvent de) {
myFrame.revalidate();//                       myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
//                    }
//
//                    @Override
//                    public void removeUpdate(DocumentEvent de) {
myFrame.revalidate();//                       myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
//                    }
//
//                    @Override
//                    public void changedUpdate(DocumentEvent de) {
//
//                    }
//
//                });
                
                //si se preciona el boton actualizar se actualiza la sentencia creada en el area de texo
                botonActualizar.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        toSend= "";
                        int size=listElementsStatement.size();
                        boolean bandera = true;
                        for(int i = 0; i<size; i++){
                            Container c = listElementsStatement.get(i);
                            int cantidadSentencias = c.getComponentCount();
                            System.out.println("Catidad de sentencias: " + cantidadSentencias);
                            for(int j=0; j < cantidadSentencias; j++){
                                if(String.valueOf(c.getComponent(j).getName()).equals("null")){
                                    if(bandera){
                                        System.out.println("NO seas tonto");
                                        System.out.println(c.getComponent(j).getName());
                                        toSend = toSend + "\n";
                                        bandera =false;
                                    }
                                    
                                }else{
                                    System.out.println(c.getComponent(j).getName());
                                    toSend = toSend + " " + c.getComponent(j).getName();
                                    bandera = true;
                                }
                            }
                        }
                        toSend = negocio.normalizar(toSend); //llamada a procedimiento remoto
                        areaTexto.setText(toSend);
                    }
                });

                //Se hace la consulta a la base de datos y se despliega el resultado
                //en una tabla

                botonSEND.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        String query = areaTexto.getText();
                        
                        try {
                            
                            myFrame.remove(sp);
                            
                            sp.removeAll();
                            tabla.removeAll();
                            
                            ArrayList<Object> response = negocio.solicitarConsultaParaTabla(query);
                            
                            
                            
                            tabla = new JTable((String[][])response.get(1), (String[])response.get(0));
                            sp = new JScrollPane(tabla);
                            //tabla.setPreferredScrollableViewportSize(new java.awt.Dimension(800, 300));
                            
                            
                            tabla.setPreferredSize(new java.awt.Dimension((((String[])response.get(0)).length)*125, 300));
                            sp.setPreferredSize(new java.awt.Dimension((((String[])response.get(0)).length)*125, 300));
                            
                            
                            consola.setText("El comando es válido, resultado mostrado");
                            myFrame.add(sp, BorderLayout.BEFORE_LINE_BEGINS);
                           myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            System.out.println("holaaaaaaaaaaaddsa tt");
                            
                        } catch (Exception ex) {
                            //Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                            sp.removeAll();
                            myFrame.remove(sp);
                            consola.setPreferredSize(new java.awt.Dimension(300, 200));
                            consola.setText(ex.toString());
                            consola.setBorder( BorderFactory.createLineBorder(Color.GRAY, 4));
                            myFrame.add(consola, BorderLayout.AFTER_LINE_ENDS );
                           myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                        }
                    }     
                });
                //Si se manda al constructor con llave 1 es para crear el subpanel que ira a la cabecera, donde se muestra
                // los comando posibles a insertar y la opcion de insertarlos
            }else if(key==1){
                JComboBox combo = new JComboBox();
                
                //Se anade comandos al combo para mostrarlos
                combo.addItem("WHERE");
                combo.addItem("WHERE NOT");
                combo.addItem("AND(...)");
                combo.addItem("AND NOT(...)");
                combo.addItem("OR(...)");
                combo.addItem("OR NOT(...)");
                combo.addItem("ORDER BY ___ [ASC|DESC]");
                combo.addItem("INNER JOIN ___ ON");
                combo.addItem("SELECT ___ FROM ___");
                combo.addItem("SELECT AVG(___) FROM ___");
                combo.addItem("SELECT COUNT(___) FROM ___");
                combo.addItem("SELECT SUM(___) FROM ___");
                combo.addItem("SELECT MIN(___) FROM ___");
                combo.addItem("SELECT MAX(___) FROM ___");
                combo.addItem("GROUP BY(____)");
                
                JButton boton = createBotonAnadir();
                
                subPanel nuevoSubPanel = new subPanel(2, countMe);
                // si se presiona el boton a anadir  se anade un subpanel dependiendo su llave
                //la llave depende de que comando esta ese ese momento en el combo de adicion
                boton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        String campoAnadir = String.valueOf(combo.getSelectedItem());
                        subPanel nuevoSubPanel = null; 
                        try {                            
                            // se manda claves diferentes al constructor dependiendo del comando
                            if(campoAnadir.equals("SELECT ___ FROM ___")){
                                nuevoSubPanel = new subPanel(2, countMe);
                            }else if(campoAnadir.equals("WHERE")){
                                 nuevoSubPanel = new subPanel(3, countMe);
                            }else if(campoAnadir.equals("WHERE NOT")){
                                 nuevoSubPanel = new subPanel(4, countMe);
                            }else if(campoAnadir.equals("AND(...)")){
                                 nuevoSubPanel = new subPanel(5, countMe);
                            }else if(campoAnadir.equals("AND NOT(...)")){
                                 nuevoSubPanel = new subPanel(6, countMe);
                            }else if(campoAnadir.equals("OR(...)")){
                                 nuevoSubPanel = new subPanel(7, countMe);
                            }else if(campoAnadir.equals("OR NOT(...)")){
                                 nuevoSubPanel = new subPanel(8, countMe);
                            }else if(campoAnadir.equals("ORDER BY ___ [ASC|DESC]") ){
                                 nuevoSubPanel = new subPanel(9, countMe);
                            }else if(campoAnadir.equals("INNER JOIN ___ ON")){
                                 nuevoSubPanel = new subPanel(10, countMe);
                            }else if(campoAnadir.equals("SELECT AVG(___) FROM ___")){
                                nuevoSubPanel = new subPanel(11, countMe);
                            }else if(campoAnadir.equals("SELECT COUNT(___) FROM ___")){
                                nuevoSubPanel = new subPanel(12, countMe);
                            }else if(campoAnadir.equals("SELECT SUM(___) FROM ___")){
                                nuevoSubPanel = new subPanel(13, countMe);
                            }else if(campoAnadir.equals("SELECT MIN(___) FROM ___")){
                                nuevoSubPanel = new subPanel(14, countMe);
                            }else if(campoAnadir.equals("SELECT MAX(___) FROM ___")){
                                nuevoSubPanel = new subPanel(15, countMe);
                            }else if(campoAnadir.equals("GROUP BY(____)")){
                                nuevoSubPanel = new subPanel(16, countMe);
                            }
                        } catch (SQLException ex) {
                                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        // se anade el nuevo panel a la lista donde se guarda todos los subpaneles activos y que se pueden borrar
                        listElementsStatement.add(nuevoSubPanel);
                        mainPanel.add(nuevoSubPanel);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                        countMe++;
                    }
                });
                
                listElementsStatement.add(nuevoSubPanel);
                mainPanel.add(nuevoSubPanel);
               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                countMe++;
                
                add(combo);
                add(boton);


                // Se crea el comando select donde su componente de columna se genera dependenido de la eleccion
                //de la tabla del FROM
            }else if(key == 2){

                JLabel selectLabel = new JLabel("SELECT");
                selectLabel.setName(selectLabel.getText());
                JLabel fromLabel = new JLabel("FROM");
                fromLabel.setName(fromLabel.getText());
                
                JComboBox selectTable = new JComboBox();
                comboBoxesSELECT.add(selectTable);
                fromTable = new JComboBox();
                
                JButton anadirSelectParam = createBotonAnadir();
                //JButton anadirFromParam = new JButton("Añadir");
                
                fillFromCombo(fromTable);
                try {
                    fillAllCombosSELECT(comboBoxesSELECT, fromTable);
                } catch (SQLException ex) {
                    Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                } 

                // Si se cambia la tabla del FROM se cambia las columnas de los SELECT y los campos que depende de estas
                fromTable.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        try {
                            fillAllCombos(comboBoxesParam, fromTable);
                            fillAllCombosSELECT(comboBoxesSELECT, fromTable);
                            fromTable.setName(String.valueOf(fromTable.getSelectedItem()));
                            System.out.println("El nombre ahora es " + fromTable.getName());
                           myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                        } catch (SQLException ex) {
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                //se actualiza el nombre del componente si se altera
                selectTable.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        selectTable.setName(String.valueOf(selectTable.getSelectedItem()));
                        System.out.println("El nombre ahora es " + selectTable.getName());
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                //se añade el boton para retirar este subpanel
                JButton myButtonRemoveMe = createBotonQuitar();
                //Se anade los componentes
                add(selectLabel);
                add(selectTable);
                add(anadirSelectParam);
                add(fromLabel);
                add(fromTable);
                //add(anadirFromParam);
                
                myButtonRemoveMe.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                //Se añade el boton para poder añadir nuevos parametros
                //cada vez que se de click en este el nuevo parametro añadido se carga con las columnas
                //de la tabla que se presenta como la eleccio de from
                anadirSelectParam.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int indiceBotonAnadir=1;
                        Container c = anadirSelectParam.getParent();
                        for (int i = 0; i < c.getComponentCount(); i++){
                            System.out.println("El Boton anadir esta en el indice: " + c.getComponent(i).getName() );
                        }
                       
                        //se añade comas como la verdadera sintaxis del lenguaje requiere
                        JComboBox nuevoParam = new JComboBox();
                        JLabel coma = new JLabel(",");
                        coma.setName(",");
                        
                        anadirSelectParam.getParent().add(nuevoParam,2);
                        anadirSelectParam.getParent().add(coma,2);
                        comboBoxesSELECT.add(nuevoParam);
                        
                        nuevoParam.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                nuevoParam.setName(String.valueOf(nuevoParam.getSelectedItem()));
                                System.out.println("El nombre ahora es " + nuevoParam.getName());
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            }
                        });
                        
                        try {
                            fillThisCombo(nuevoParam, fromTable);
                        } catch (SQLException ex) {
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                        
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                /*
                anadirFromParam.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                */
                
                //se añade el boton para remover el subpanel
                add(myButtonRemoveMe);
                
                //en esta pate del consturctor solo entra cuando se desea crear una sentencia con WHERE o WHERE NOT
            }else if(key == 3 || key == 4){
                JLabel whereLabel = null;
                if(key == 3){ whereLabel = new JLabel("WHERE");}
                else if(key == 4){ whereLabel = new JLabel("WHERE NOT");}
                
                //Se añade parestesis com parte de la correcta sintaxis
                // ademas se llena este subpanel con los parametros y sentencias necesarios
                whereLabel.setName(whereLabel.getText());
                JLabel openParenthesis = new JLabel("(");
                openParenthesis.setName(openParenthesis.getText());
                JLabel closeParenthesis = new JLabel(")");
                closeParenthesis.setName(closeParenthesis.getText());
                
                JComboBox firstParameter = new JComboBox();
                JComboBox operandos = new JComboBox();
                JTextField secondParameter = new JTextField();
                secondParameter.setText("<<parametro>>");
                
                JComboBox parametrosOpcionales = new JComboBox();
                JButton anadirParametros = createBotonAnadir();
                JButton myButtonRemoveMe = createBotonQuitar();
                
                
                //se añade cada uno dse los coponentes al subpanel
                add(whereLabel);
                add(openParenthesis);
                add(firstParameter);
                add(operandos);
                add(secondParameter);
                add(closeParenthesis);
                add(anadirParametros);
                add(parametrosOpcionales);
                add(myButtonRemoveMe);
                
                //cada uno de estos hilos listeners es para detectar cambios no solo individualmente 
                //del coponente sino tambien del efecto que la accion de uno de estos componentes 
                //tiene sobre el resto
                
                comboBoxesParam.add(firstParameter);
                
                //los operando tambien se da para la elecion del usuario
                fillWithOperands(operandos);
                fillWithAndOr(parametrosOpcionales);
                
                try {
                    fillThisCombo(firstParameter, fromTable);
                }catch(SQLException ex){
                    Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                firstParameter.setName(String.valueOf(firstParameter.getSelectedItem()));
                operandos.setName(String.valueOf(operandos.getSelectedItem()));
                secondParameter.setName(String.valueOf(secondParameter.getText()));
                
                //se puede añadir parametros al where coo AND, AND NOT, OR, OR NOT, etc.
                anadirParametros.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        JLabel nuevoOperando = new JLabel(String.valueOf(parametrosOpcionales.getSelectedItem()));
                        nuevoOperando.setName(nuevoOperando.getText());
                        //JLabel openParenthesis = new JLabel("(");
                        //openParenthesis.setName(openParenthesis.getText());
                        //JLabel closeParenthesis = new JLabel(")");
                        //closeParenthesis.setName(closeParenthesis.getText());
                        
                        JComboBox firstParameter2 = new JComboBox();
                        JComboBox operandos2 = new JComboBox();
                        JTextField secondParameter2 = new JTextField();
                        secondParameter2.setText("<<parametro>>");

                        comboBoxesParam.add(firstParameter2);
                        try {
                            fillThisCombo(firstParameter2, fromTable);
                        }catch(SQLException ex){
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        add(secondParameter2,5);
                        add(operandos2,5);
                        add(firstParameter2,5);
                        add(nuevoOperando,5);
                        
                        fillWithOperands(operandos2);
                        
                        firstParameter2.setName(String.valueOf(firstParameter2.getSelectedItem()));
                        operandos2.setName(String.valueOf(operandos2.getSelectedItem()));
                        secondParameter2.setName(String.valueOf(secondParameter2.getText()));
                        
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                        firstParameter2.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                firstParameter2.setName(String.valueOf(firstParameter2.getSelectedItem()));
                                System.out.println("El nombre ahora es " + firstParameter2.getName());
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            }
                        });
                
                        operandos2.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                operandos2.setName(String.valueOf(operandos2.getSelectedItem()));
                                System.out.println("El nombre ahora es " + operandos2.getName());
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            }
                        });
                        
                        // el segundo parametro es un text field donde se puede insertar cualquier tipo de parametro 
                        // ya que una consulta no solo puede ser de lo que hay sino de lo que no hay
                        secondParameter2.addKeyListener(new KeyListener(){
                            @Override
                            public void keyTyped(KeyEvent ke) {
                                //
                            }

                            @Override
                            public void keyPressed(KeyEvent ke) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void keyReleased(KeyEvent ke) {
                                secondParameter2.setName(secondParameter2.getText());
                                System.out.println("El nombre ahora es " + secondParameter2.getName());
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            }
                        });
                        
                        int indiceBotonAnadir=1;
                        Container c = anadirParametros.getParent();
                        for (int i = 0; i < c.getComponentCount(); i++) {
                            System.out.println("El Boton anadir esta en el indice: " + c.getComponent(i).getName() );
                        }
                    }
                });
                
                firstParameter.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        firstParameter.setName(String.valueOf(firstParameter.getSelectedItem()));
                        System.out.println("El nombre ahora es " + firstParameter.getName());
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                operandos.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        operandos.setName(String.valueOf(operandos.getSelectedItem()));
                        System.out.println("El nombre ahora es " + operandos.getName());
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                secondParameter.addKeyListener(new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent ke) {
                        //
                    }

                    @Override
                    public void keyPressed(KeyEvent ke) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void keyReleased(KeyEvent ke) {
                        secondParameter.setName(secondParameter.getText());
                        System.out.println("El nombre ahora es " + secondParameter.getName());
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                
                myButtonRemoveMe.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                // en este caso se maneja la cracion de una sentencia logica por parte del usuario,
                //se debe enteder que esto puede ser un agregado al comando where
            }else if(key == 5 || key == 6 || key == 7 || key == 8){
                flag = 0;
                JLabel ANDLabel = null;
                if(key == 5){ANDLabel = new JLabel("AND");}
                else if(key == 6){ANDLabel = new JLabel("AND NOT");}
                else if(key == 7){ANDLabel = new JLabel("OR");}
                else if(key == 8){ANDLabel = new JLabel("OR NOT");}
                
                
                ANDLabel.setName(ANDLabel.getText());
                JLabel openParenthesis = new JLabel("(");
                openParenthesis.setName(openParenthesis.getText());
                JLabel closeParenthesis = new JLabel(")");
                closeParenthesis.setName(closeParenthesis.getText());
                
                
                JComboBox parametrosOpcionales = new JComboBox();
                JButton anadirParametros = createBotonAnadir();
                JButton myButtonRemoveMe = createBotonQuitar();
                
                add(ANDLabel);
                add(openParenthesis);
                add(closeParenthesis);
                add(anadirParametros);
                add(parametrosOpcionales);
                add(myButtonRemoveMe);
                
                fillWithAndOr(parametrosOpcionales);
                
                myButtonRemoveMe.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                
                //Se pueden anadir mas paramterodentro de la sentencia logica ya creada
                anadirParametros.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        //la primera instancia de creacion  debe crear una instancia con dos parametros, cualquier posteror creacion de
                        // un parametro logico solo necesita un operador y un parametro
                        if(flag==0){
                            JComboBox primeroGrupo1 = new JComboBox();
                            JComboBox operandoGrupo1 = new JComboBox();
                            JTextField segundoGrupo1 = new JTextField();

                            JLabel operador = new JLabel(String.valueOf(parametrosOpcionales.getSelectedItem()));
                            
                            fillWithAndOr(parametrosOpcionales);
                            
                            JComboBox primeroGrupo2 = new JComboBox();
                            JComboBox operandoGrupo2 = new JComboBox();
                            JTextField segundoGrupo2 = new JTextField();
                            
                            fillWithOperands(operandoGrupo1);
                            fillWithOperands(operandoGrupo2);
                            
                            comboBoxesParam.add(primeroGrupo1);
                            comboBoxesParam.add(primeroGrupo2);
                            
                            segundoGrupo1.setText("<<parametro>>");
                            segundoGrupo2.setText("<<parametro>>");
                            
                            add(segundoGrupo2,2);
                            add(operandoGrupo2,2);
                            add(primeroGrupo2,2);
                            add(operador,2);
                            add(segundoGrupo1,2);
                            add(operandoGrupo1,2);
                            add(primeroGrupo1,2);
                           
                            
                            try {
                                fillThisCombo(primeroGrupo1, fromTable);
                                fillThisCombo(primeroGrupo2, fromTable);
                            } catch (SQLException ex) {
                                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            segundoGrupo2.setName(segundoGrupo2.getText());
                            operandoGrupo2.setName(String.valueOf(operandoGrupo2.getSelectedItem()));
                            primeroGrupo2.setName(String.valueOf(primeroGrupo2.getSelectedItem()));
                            operador.setName(operador.getText());
                            segundoGrupo1.setName(segundoGrupo1.getText());
                            operandoGrupo1.setName(String.valueOf(operandoGrupo1.getSelectedItem()));
                            primeroGrupo1.setName(String.valueOf(primeroGrupo1.getSelectedItem()));
                            
                            primeroGrupo1.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    primeroGrupo1.setName(String.valueOf(primeroGrupo1.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + primeroGrupo1.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });

                            operandoGrupo1.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    operandoGrupo1.setName(String.valueOf(operandoGrupo1.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + operandoGrupo1.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });

                            segundoGrupo1.addKeyListener(new KeyListener(){
                                @Override
                                public void keyTyped(KeyEvent ke) {
                                    //
                                }

                                @Override
                                public void keyPressed(KeyEvent ke) {
                                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }

                                @Override
                                public void keyReleased(KeyEvent ke) {
                                    segundoGrupo1.setName(segundoGrupo1.getText());
                                    System.out.println("El nombre ahora es " + segundoGrupo1.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });
                            
                            primeroGrupo2.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    primeroGrupo2.setName(String.valueOf(primeroGrupo2.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + primeroGrupo2.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });

                            operandoGrupo2.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    operandoGrupo2.setName(String.valueOf(operandoGrupo2.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + operandoGrupo2.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });

                            segundoGrupo2.addKeyListener(new KeyListener(){
                                @Override
                                public void keyTyped(KeyEvent ke) {
                                    //
                                }

                                @Override
                                public void keyPressed(KeyEvent ke) {
                                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }

                                @Override
                                public void keyReleased(KeyEvent ke) {
                                    segundoGrupo2.setName(segundoGrupo2.getText());
                                    System.out.println("El nombre ahora es " + segundoGrupo2.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });
                            
                           myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            flag=1;
                            
                        }else{
                            
                            JComboBox primeroGrupo1 = new JComboBox();
                            JComboBox operandoGrupo1 = new JComboBox();
                            JTextField segundoGrupo1 = new JTextField();

                            JLabel operador = new JLabel(String.valueOf(parametrosOpcionales.getSelectedItem()));
                            
                            Container c = anadirParametros.getParent();
                            int cantidad = c.getComponentCount();
                            
                            comboBoxesParam.add(primeroGrupo1);
                            fillWithOperands(operandoGrupo1);
                            segundoGrupo1.setText("<<parametro>>");
                            
                            try {
                                fillThisCombo(primeroGrupo1, fromTable);
                            } catch (SQLException ex) {
                                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            primeroGrupo1.setName(String.valueOf(primeroGrupo1.getSelectedItem()));
                            segundoGrupo1.setName(segundoGrupo1.getText());
                            operandoGrupo1.setName(String.valueOf(operandoGrupo1.getSelectedItem()));
                            operador.setName(operador.getText());
                            
                            
                            add(segundoGrupo1, cantidad-4);
                            add(operandoGrupo1, cantidad-4);
                            add(primeroGrupo1, cantidad-4);
                            add(operador, cantidad-4);
                            
                            primeroGrupo1.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    primeroGrupo1.setName(String.valueOf(primeroGrupo1.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + primeroGrupo1.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });

                            operandoGrupo1.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    operandoGrupo1.setName(String.valueOf(operandoGrupo1.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + operandoGrupo1.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });

                            segundoGrupo1.addKeyListener(new KeyListener(){
                                @Override
                                public void keyTyped(KeyEvent ke) {
                                    //
                                }

                                @Override
                                public void keyPressed(KeyEvent ke) {
                                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }

                                @Override
                                public void keyReleased(KeyEvent ke) {
                                    segundoGrupo1.setName(segundoGrupo1.getText());
                                    System.out.println("El nombre ahora es " + segundoGrupo1.getName());
                                   myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                }
                            });
                            
                           myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            
                        }
                        Container c = anadirParametros.getParent();
                        for (int i = 0; i < c.getComponentCount(); i++) {
                            System.out.println("El Boton anadir esta en el indice: " + c.getComponent(i).getName() );
                        }
                    } 
                });
               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                
                
                //el siguiente comando conciste de que ordenamiento se desea hacer
                //puede ser Ascendente o Descendente pero necesita de un parametro 
                //para realizar la ordenacion
            }else if(key == 9){
                flag = 0;
                JComboBox DESC_ASC_combo = new JComboBox();
                DESC_ASC_combo.addItem("ASC");
                DESC_ASC_combo.addItem("DESC");
                
                DESC_ASC_combo.setName(String.valueOf(DESC_ASC_combo.getSelectedItem()));
                
                JLabel orderByLabel = new JLabel("ORDER BY ");
                orderByLabel.setName(orderByLabel.getText());
                
                JLabel closeParenthesis = new JLabel(")");
                closeParenthesis.setName(closeParenthesis.getText());
                
                
                JComboBox comboParametro = new JComboBox();
                comboBoxesParam.add(comboParametro);
                
                fillThisCombo(comboParametro, fromTable);
                comboParametro.setName(String.valueOf(comboParametro.getSelectedItem()));
                
                JButton anadirSelectParam = createBotonAnadir();
                
                JButton myButtonRemoveMe = createBotonQuitar();
                               
                myButtonRemoveMe.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                comboParametro.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        comboParametro.setName(String.valueOf(comboParametro.getSelectedItem()));
                        System.out.println("El nombre ahora es " + comboParametro.getName());
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                DESC_ASC_combo.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        DESC_ASC_combo.setName(String.valueOf(DESC_ASC_combo.getSelectedItem()));
                        System.out.println("El nombre ahora es " + DESC_ASC_combo.getName());
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                // se puede añadir varios parametros dentro de nuestro ORDER BY
                anadirSelectParam.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int indiceBotonAnadir=1;
                        Container c = anadirSelectParam.getParent();
                        for (int i = 0; i < c.getComponentCount(); i++) {
                            System.out.println("El Boton anadir esta en el indice: " + c.getComponent(i).getName() );
                        }
                        
                       
                        JComboBox nuevoParam = new JComboBox();
                        JLabel coma = new JLabel(",");
                        coma.setName(",");
                        JComboBox nuevo_DESC_ASC_combo = new JComboBox();
                        nuevo_DESC_ASC_combo.addItem("ASC");
                        nuevo_DESC_ASC_combo.addItem("DESC");

                        nuevo_DESC_ASC_combo.setName(String.valueOf(nuevo_DESC_ASC_combo.getSelectedItem()));
                        
                        anadirSelectParam.getParent().add(nuevo_DESC_ASC_combo,3);        
                        anadirSelectParam.getParent().add(nuevoParam,3);
                        anadirSelectParam.getParent().add(coma,3);
                        comboBoxesParam.add(nuevoParam);
                        
                        nuevoParam.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                nuevoParam.setName(String.valueOf(nuevoParam.getSelectedItem()));
                                System.out.println("El nombre ahora es " + nuevoParam.getName());
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            }
                        });
                        
                        nuevo_DESC_ASC_combo.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                nuevo_DESC_ASC_combo.setName(String.valueOf(nuevo_DESC_ASC_combo.getSelectedItem()));
                                System.out.println("El nombre ahora es " + nuevo_DESC_ASC_combo.getName());
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            }
                        });
                        
                        try {
                            fillThisCombo(nuevoParam, fromTable);
                        } catch (SQLException ex) {
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                        
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                        
                    }
                });
                
                //Se añade los compnentes al subPanel
                add(orderByLabel);
                add(comboParametro);
                add(DESC_ASC_combo);
                //add(closeParenthesis);
                add(anadirSelectParam);
                add(myButtonRemoveMe);
                
               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                
                
                //en el siguiente condicion se hace que se acceda para crear el argumento INNER JOIN
            }else if(key == 10){
                JLabel INNERJOINLabel = new JLabel("INNER JOIN");
                INNERJOINLabel.setName(INNERJOINLabel.getText());
                
                INNERJOINTables = new JComboBox();
                fillThisComboINNERJOIN(INNERJOINTables, fromTable);
                INNERJOINTables.setName(String.valueOf(INNERJOINTables.getSelectedItem()));
                
                JLabel tablaOperador1 = new JLabel(INNERJOINTables.getName());
                tablaOperador1.setName(tablaOperador1.getText());
                
                
                JLabel tablaOperador2 = new JLabel(fromTable.getName());
                tablaOperador2.setName(tablaOperador2.getText());
                
                
                JComboBox columnOperator1 = new JComboBox();
                fillThisCombo(columnOperator1, fromTable);
                columnOperator1.setName(String.valueOf(columnOperator1.getSelectedItem()));
                
                JComboBox columnOperator2 = new JComboBox();
                fillThisCombo(columnOperator2, INNERJOINTables);
                comboBoxesParam.add(columnOperator2);
                columnOperator2.setName(String.valueOf(columnOperator2.getSelectedItem()));
                
                fromTable.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        tablaOperador2.setText(String.valueOf(fromTable.getSelectedItem()));
                        tablaOperador2.setName(String.valueOf(fromTable.getSelectedItem()));
                    }
                });
                
                INNERJOINTables.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        tablaOperador1.setText(String.valueOf(INNERJOINTables.getSelectedItem()));
                        tablaOperador1.setName(String.valueOf(INNERJOINTables.getSelectedItem()));
                        
                        try {
                            
                            fillThisCombo(columnOperator1,INNERJOINTables);
                            columnOperator2.setName(String.valueOf(columnOperator1.getSelectedItem()));
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        INNERJOINTables.setName(String.valueOf(INNERJOINTables.getSelectedItem()));
                        
                        
                    }
                });
                
                columnOperator2.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        columnOperator2.setName(String.valueOf(columnOperator2.getSelectedItem()));
                        
                    }
                });
                
                columnOperator1.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        columnOperator1.setName(String.valueOf(columnOperator1.getSelectedItem()));
                        
                    }
                });
                
               
                
                JComboBox parametrosOpcionales = new JComboBox();
                fillWithOperands(parametrosOpcionales);
                parametrosOpcionales.setName(String.valueOf(parametrosOpcionales.getSelectedItem()));
                
                parametrosOpcionales.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                       
                        parametrosOpcionales.setName(String.valueOf(parametrosOpcionales.getSelectedItem()));
                        
                    }
                });
                
                
                JLabel onLabel = new JLabel("ON");
                onLabel.setName(onLabel.getText());
                
                JLabel dotOperator1 = new JLabel(".");
                dotOperator1.setName(dotOperator1.getText());
                
                JLabel dotOperator2 = new JLabel(".");
                dotOperator2.setName(dotOperator2.getText());
                
                JButton myButtonRemoveMe = createBotonQuitar();
                
                myButtonRemoveMe.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                // INNER JOIN se base en dos cosas, el parametro del from en la consulta
                //que se esta haciendo y del INNER JOIN, de hay se da la comparacoion por medio de un operador 
                // entre una columna del uno contra una columna del otro
                
                add(INNERJOINLabel);
                add(INNERJOINTables);
                add(onLabel);
                add(tablaOperador1);
                add(dotOperator1);
                add(columnOperator1);
                add(parametrosOpcionales);
                add(tablaOperador2);
                add(dotOperator2);
                add(columnOperator2);
                add(myButtonRemoveMe);
                
               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
 
             // En esta seccion se añade las consultas de agregacion, de acuerdoa cada una de las llaves
             //si enmabrgo estas sguen una logica en comun, un argumento entre parentesis con la opcion de generar
             //mas parametros solo si se agraga un grou by con los mismo
            }else if(key == 11 || key == 12 || key == 13 || key == 14 || key == 15){
                flag = 0;
                JLabel SELECTLabel = null;
                if(key == 11){SELECTLabel = new JLabel("SELECT AVG (");}
                else if(key == 12){SELECTLabel = new JLabel("SELECT COUNT (");}
                else if(key == 13){SELECTLabel = new JLabel("SELECT SUM (");}
                else if(key == 14){SELECTLabel = new JLabel("SELECT MAX (");}
                else if(key == 15){SELECTLabel = new JLabel("SELECT MIN (");}
                
                SELECTLabel.setName(SELECTLabel.getText());
                
                JComboBox comboCualquierSELECT = new JComboBox(); 
                comboBoxesSELECT.add(comboCualquierSELECT);
                
                fillThisCombo(comboCualquierSELECT, fromTable);
                comboCualquierSELECT.setName(String.valueOf(comboCualquierSELECT.getSelectedItem()));
                
                JLabel closeParenthesis = new JLabel(")");
                closeParenthesis.setName(closeParenthesis.getText());
                
                JLabel fromLabel = new JLabel("FROM");
                fromLabel.setName(fromLabel.getText());
                
                JButton anadirSelectParam = createBotonAnadir();
                
                JButton myButtonRemoveMe = createBotonQuitar();
                
                
                
                
                myButtonRemoveMe.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                comboCualquierSELECT.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        comboCualquierSELECT.setName(String.valueOf(comboCualquierSELECT.getSelectedItem()));
                        System.out.println("El nombre ahora es " + comboCualquierSELECT.getName());
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                anadirSelectParam.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int indiceBotonAnadir=1;
                        Container c = anadirSelectParam.getParent();
                        for (int i = 0; i < c.getComponentCount(); i++) {
                            System.out.println("El Boton anadir esta en el indice: " + c.getComponent(i).getName() );
                        }
                        
                       
                        JComboBox nuevoParam = new JComboBox();
                        JLabel coma = new JLabel(",");
                        coma.setName(",");
                        
                        anadirSelectParam.getParent().add(nuevoParam,3);
                        anadirSelectParam.getParent().add(coma,3);
                        comboBoxesSELECT.add(nuevoParam);
                        
                        nuevoParam.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                nuevoParam.setName(String.valueOf(nuevoParam.getSelectedItem()));
                                System.out.println("El nombre ahora es " + nuevoParam.getName());
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            }
                        });
                        
                        try {
                            fillThisCombo(nuevoParam, fromTable);
                        } catch (SQLException ex) {
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }          
                        //se añade un group by solo si no se ha generado uno antes, si si se lo ha hecho y se desea crear uno nuevo
                        //si lo hay como hacer (revisar siguente parte).
                        if(flag==0){
                            try {
                                subPanel nuevoSubPanel = new subPanel(16, countMe);
                                listElementsStatement.add(nuevoSubPanel);
                                mainPanel.add(nuevoSubPanel);
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                                countMe++;
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            flag=1;
                        }
                        
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );                       
                    }
                });
                
                
                add(SELECTLabel);
                add(comboCualquierSELECT);
                add(closeParenthesis);
                add(anadirSelectParam);
                add(fromLabel);
                add(fromTable);
                add(myButtonRemoveMe);
 
               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                
                // para generar GROUP BYs se da un parametro de inmediato, se da la opcion de generar mas parametros
                //a voluntad, este comando es de gran ayuda para la agrgacion de parametros a una funcion de agragacion
            }else if(key == 16){
                
                JLabel groupByLabel = new JLabel("GROUP BY (");
                groupByLabel.setName(groupByLabel.getText());
                
                JComboBox comboParametro = new JComboBox();
                comboBoxesParam.add(comboParametro);
                
                fillThisCombo(comboParametro, fromTable);
                comboParametro.setName(String.valueOf(comboParametro.getSelectedItem()));
                
                JLabel closeParenthesis = new JLabel(")");
                closeParenthesis.setName(closeParenthesis.getText());
                
                JButton anadirSelectParam = createBotonAnadir();
                
                JButton myButtonRemoveMe = createBotonQuitar();
                               
                myButtonRemoveMe.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                comboParametro.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        comboParametro.setName(String.valueOf(comboParametro.getSelectedItem()));
                        System.out.println("El nombre ahora es " + comboParametro.getName());
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                    }
                });
                
                anadirSelectParam.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int indiceBotonAnadir=1;
                        Container c = anadirSelectParam.getParent();
                        for (int i = 0; i < c.getComponentCount(); i++) {
                            System.out.println("El Boton anadir esta en el indice: " + c.getComponent(i).getName() );
                        }
                        
                       
                        JComboBox nuevoParam = new JComboBox();
                        JLabel coma = new JLabel(",");
                        coma.setName(",");
                        
                        anadirSelectParam.getParent().add(nuevoParam,2);
                        anadirSelectParam.getParent().add(coma,2);
                        comboBoxesSELECT.add(nuevoParam);
                        
                        nuevoParam.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                nuevoParam.setName(String.valueOf(nuevoParam.getSelectedItem()));
                                System.out.println("El nombre ahora es " + nuevoParam.getName());
                               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                            }
                        });
                        
                        try {
                            fillThisCombo(nuevoParam, fromTable);
                        } catch (SQLException ex) {
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                        
                       myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                        
                    }
                });
                
                // se añade cada uno de los componentes al subPanel
                
                add(groupByLabel);
                add(comboParametro);
                add(closeParenthesis);
                add(anadirSelectParam);
                add(myButtonRemoveMe);
                
               
               myFrame.revalidate();myFrame.setExtendedState(myFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
                
                
            }
            
            
            
        }
        
        
        // cuando se preciona el boton de borrar en un sub pane este metodo se activa para quitarlo de la lista de
        //subpanels activos
        public void removeAndReorderFromList(int index){
            int size=listElementsStatement.size();
            
            for(int i = 0; i<size; i++){
                if(i>index){
                    listElementsStatement.get(i).index--;
                    System.out.println("Inidce se ha actualizado: " + String.valueOf(i-1));
                }else{
                    System.out.println("Inidce NO se ha actualizado: " + String.valueOf(i));
                }
            }
            listElementsStatement.remove(index);
        }
        
        //Se hace uso de la clase enlace para conectar a la capa de datos y hacer una consulta acerca
        //las tablas de la base de datos, se la coloca en el comboBox que corresponde a FROM del comando SELECT
        public void fillFromCombo(JComboBox combo) throws SQLException{
            String sql = negocio.getStringForTable();
            ArrayList<String> response = negocio.solicitarConsultaParaComboFOR(sql);
            
            
            for(String campo: response){
                    System.out.println("hey: " + campo);
                    combo.addItem(campo);
            }
            
            combo.setSelectedIndex(0);
            combo.setName(String.valueOf(combo.getSelectedItem()));
            System.out.println("El nombre ahora es " + combo.getName());
        }
        
        //CUando hay un cambio en el FROM de la consulta se debe cambiar todo los comboBox  segun el nuevo campo elegido
        public void fillAllCombos(ArrayList<JComboBox> arrayCombos, JComboBox comboFrom) throws SQLException{
            
            int size=arrayCombos.size();

            String sql = "SELECT * FROM " + String.valueOf(comboFrom.getSelectedItem());

            ArrayList<String> response = negocio.solicitarConsultaParaCombo(sql);
            
            for(int i = 0; i<size; i++){
                arrayCombos.get(i).removeAllItems();
                for (int j = 0; j < response.size(); j++) {
                    arrayCombos.get(i).addItem(response.get(j));
                }
                arrayCombos.get(i).setName(String.valueOf(arrayCombos.get(i).getSelectedItem()));
                System.out.println("El nombre ahora es " + arrayCombos.get(i).getName());
            }
        }
        
        //Complemento al metodo anterior solo que se lo hace a los parametros a los que corresponde 
        // de todos los cmandos SELECT existentes
        public void fillAllCombosSELECT(ArrayList<JComboBox> arrayCombos, JComboBox comboFrom) throws SQLException{
            
            int size=arrayCombos.size();
            
            for(int i = 0; i<size; i++){
                arrayCombos.get(i).removeAllItems();
                arrayCombos.get(i).addItem("*");
            }

            String sql = "SELECT * FROM " + String.valueOf(comboFrom.getSelectedItem());
            
            System.out.println("holaaaaaaaaaaaaaa" + sql);
            
            ArrayList<String> response = negocio.solicitarConsultaParaCombo(sql);

            for(int i = 0; i<size; i++){
                for (int j = 0; j < response.size(); j++) {
                    arrayCombos.get(i).addItem(response.get(j));
                }
                arrayCombos.get(i).setName(String.valueOf(arrayCombos.get(i).getSelectedItem()));
                System.out.println("El nombre ahora es " + arrayCombos.get(i).getName());
            }
            
            
        }
        
        //Cuando se añade un combo se usa este metodo para llenarlo correspondientemente
        public void fillThisCombo(JComboBox combo, JComboBox comboFrom) throws SQLException{
            
            combo.removeAllItems();
            
            String sql = "SELECT * FROM " + String.valueOf(comboFrom.getSelectedItem());

            ArrayList<String> response = negocio.solicitarConsultaParaCombo(sql);
            

            for (int j = 0; j < response.size(); j++) {
                combo.addItem(response.get(j));
            }
            
            
        }
        
        //Se usa para llenar el comboBox, siendo un parametro
        //independiente del combo FROM de la consulta en creacion
        public void fillThisComboINNERJOIN(JComboBox combo, JComboBox comboFrom) throws SQLException{
            

            String sql = negocio.getStringForTable();
            ArrayList<String> response = negocio.solicitarConsultaParaComboFOR(sql);
            
            
            for(String campo: response){
                    System.out.println("hey: " + campo);
                    combo.addItem(campo);
            }
            
            combo.setSelectedIndex(0);
            combo.setName(String.valueOf(combo.getSelectedItem()));
            System.out.println("El nombre ahora es " + combo.getName());
            
            
        }
        
        
        public void fillWithOperands(JComboBox combo){
            //Se llenaun comboBox con los operandos dentro del array en la linea siguiente
            String[] operandos= {"=", ">", "<", ">=","<=", "<>", "BETWEEN", "LIKE", "IN"};
            
            for(int i=0; i<operandos.length; i++){
                combo.addItem(operandos[i]);
            }
            
        }
        
        
        public void fillWithAndOr(JComboBox combo){
            // se llena un combo con los siguientes opérandos
            String[] AndOr= {"AND","OR","AND NOT","OR NOT"};
            for(int i=0; i<AndOr.length; i++){
                combo.addItem(AndOr[i]);
            }
        }
        
       /* public void yNOToNOT_ParamOpcionales(JComboBox combo, int flag){
            String[] AndOr= {"AND","OR","AND NOT","OR NOT"};
            if(flag==0){    
                for(int i=0; i<AndOr.length; i++){
                    combo.addItem(AndOr[i]);
                }
            }else{
                combo.removeAllItems();
                for(int i=0; i<AndOr.length; i++){
                    combo.addItem(AndOr[i]);
                }
            }
        } */
        
        // Se crea un boton para con el icono de adicion
        //se inserta el icono que aparece cuando se presiona 
        public JButton createBotonAnadir(){
            
            JButton botonAnadir = new JButton(addIcon);
            botonAnadir.setBorderPainted(false); 
            botonAnadir.setContentAreaFilled(false); 
            botonAnadir.setFocusPainted(false); 
            botonAnadir.setOpaque(false);
            botonAnadir.setPressedIcon(addIconPressed);
            
            return botonAnadir;
        }
        
        // Se crea un boton para con el icono de eliminar
        //se inserta el icono que aparece cuando se presiona 
        public JButton createBotonQuitar(){
            
            JButton botonAnadir = new JButton(removeIcon);
            botonAnadir.setBorderPainted(false); 
            botonAnadir.setContentAreaFilled(false); 
            botonAnadir.setFocusPainted(false); 
            botonAnadir.setOpaque(false);
            botonAnadir.setPressedIcon(removeIconPressed);
            
            return botonAnadir;
        }
        
        // Se crea un boton para con el icono de recargar para la actualizacion de la sentencia creada
        //se inserta el icono que aparece cuando se presiona 
        public JButton createBotonActualizar(){
            
            JButton botonAnadir = new JButton(refreshIcon);
            botonAnadir.setBorderPainted(false); 
            botonAnadir.setContentAreaFilled(false); 
            botonAnadir.setFocusPainted(false); 
            botonAnadir.setOpaque(false);
            botonAnadir.setPressedIcon(refreshIconPressed);
            
            return botonAnadir;
        }
        
        
        //Se crea el boton que sirve para hacer la consulta a la base de datos
        //se inserta el icono que aparece cuando se presiona 
        public JButton createBotonSend(){
            
            JButton botonAnadir = new JButton(queryIcon);
            botonAnadir.setBorderPainted(false); 
            botonAnadir.setContentAreaFilled(false); 
            botonAnadir.setFocusPainted(false); 
            botonAnadir.setOpaque(false);
            botonAnadir.setPressedIcon(queryIconPressed);
            
            return botonAnadir;
        }
        
    }
    
}
