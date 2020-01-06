//Añade INNER JOIN y ORDER BY y ON
//no hagas que el * se añada en una sentencia que no sea SELECT
//make yNOToNOT_ParamOpcionales unnecessary

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import datos.CapaDatos;
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
 

public class Querys extends JFrame {
    
    Icon addIcon = new ImageIcon("src/Vista/botonAnadir.png");
    Icon addIconPressed = new ImageIcon("src/Vista/botonAnadirPressed.png");
    Icon removeIcon = new ImageIcon("src/Vista/botonQuitar.png");
    Icon removeIconPressed = new ImageIcon("src/Vista/botonQuitarPressed.png");
    
    static Querys myFrame;
    static int countMe = 0;
    JPanel mainPanel;
    //static Connection con;
    JComboBox fromTable;
    
    static CapaDatos capaDatos = null;
    JTextArea consola = new JTextArea();
    JScrollPane sp = new JScrollPane();
    JTable tabla = new JTable();
    
    
    ArrayList<subPanel> listElementsStatement = new ArrayList<subPanel>();
    ArrayList<JComboBox> comboBoxesParam = new ArrayList<JComboBox>();
    ArrayList<JComboBox> comboBoxesSELECT = new ArrayList<JComboBox>();
    
    
    public static void main(String[] args) {
        
        capaDatos = new CapaDatos();
        //capaDatos.conectarBD();
        //con = capaDatos.getConexion();
        
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (SQLException ex) {
                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
 
    private static void createAndShowGUI() throws SQLException {
        myFrame = new Querys();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.prepareUI();
        //myFrame.setRelativeTo(null);
        myFrame.setVisible(true);
    }
 
    private void prepareUI() throws SQLException {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        
        JComboBox combo = new JComboBox();
        
         
        JButton buttonRemoveAll = new JButton("Remove All");
        buttonRemoveAll.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                countMe=0;
                mainPanel.removeAll();
                myFrame.pack();
            }
        });
 
        
                
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(new subPanel(1, -1), BorderLayout.PAGE_START);
        getContentPane().add(new subPanel(0, -1), BorderLayout.PAGE_END);
        
        //getContentPane().add(buttonRemoveAll, BorderLayout.PAGE_END);
        myFrame.pack();
    }
   
   private void setRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
 /*
    private class subPanel extends JPanel {
         
        subPanel me;
 
        public subPanel() {
            super();
            me = this;
            
            JLabel myLabel = new JLabel("Hello subPanel(): " + countMe++);
            add(myLabel);
            JButton myButtonRemoveMe = new JButton("remove me");
            myButtonRemoveMe.addActionListener(new ActionListener(){
 
                @Override
                public void actionPerformed(ActionEvent e) {
                    me.getParent().remove(me);
                    myFrame.pack();
                }
            });
            add(myButtonRemoveMe);
        }
    } */
   
   private class subPanel extends JPanel {
         
        subPanel me;
        int index;
        int flag;
        String toSend = "";
        
        
        public subPanel(int key, int index) throws SQLException {
            super();
            me = this;
            this.index = index;
            System.out.println("Se ha introducido un nuevo panel: "+ String.valueOf(index));
            
            if(key == 0){
                
                JButton botonActualizar = new JButton("Actualizar");
                JButton botonSEND = new JButton("SEND");
                
                JTextArea areaTexto = new JTextArea();
                areaTexto.setText("<<Actualize una vez configurada su consulta>>");
                areaTexto.setBorder( BorderFactory.createLineBorder(Color.GRAY, 4));
                
                add(areaTexto);
                add(botonActualizar);
                add(botonSEND);
                
                areaTexto.getDocument().addDocumentListener(new DocumentListener(){
                    @Override
                    public void insertUpdate(DocumentEvent de) {
                        myFrame.pack();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent de) {
                        myFrame.pack();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent de) {
                        
                    }
                    
                });
                
            
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
                        
                        areaTexto.setText(toSend);
                    }
                });
                
                botonSEND.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        String query = areaTexto.getText();
                        
                        try {
                            
                            ResultSet rs = capaDatos.makeQuery(query);
                            ResultSet rs2 = capaDatos.makeQuery(query);
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
                            
                            System.out.println("Hola estas en esta linea");
                            tabla = new JTable(matriz, columnas);
                            sp = new JScrollPane(tabla);
                            consola.setText("El comando es válido, resultado mostrado");
                            myFrame.add(sp, BorderLayout.BEFORE_LINE_BEGINS);
                            myFrame.pack();
                            
                        } catch (Exception ex) {
                            tabla.removeAll();
                            myFrame.remove(sp);
                            consola.setText(ex.toString());
                            consola.setBorder( BorderFactory.createLineBorder(Color.GRAY, 4));
                            myFrame.add(consola, BorderLayout.AFTER_LINE_ENDS );
                            myFrame.pack();
                        }
                        
                        
                        
                    }
                    
                });
                
            }else if(key==1){
                JComboBox combo = new JComboBox();
                
                
                combo.addItem("WHERE");
                combo.addItem("WHERE NOT");
                combo.addItem("AND(...)");
                combo.addItem("AND NOT(...)");
                combo.addItem("OR(...)");
                combo.addItem("OR NOT(...)");
                combo.addItem("ORDER BY ___ ASC");
                combo.addItem("ORDER BY ___ DESC");
                combo.addItem("SELECT ___ FROM ___");
                
                JButton boton = createBotonAnadir();
                
                subPanel nuevoSubPanel = new subPanel(2, countMe);
                
                boton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        String campoAnadir = String.valueOf(combo.getSelectedItem());
                        subPanel nuevoSubPanel = null; 
                        try {                            
                            
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
                            }else if(campoAnadir.equals("ORDER BY ___ ASC")){
                                 nuevoSubPanel = new subPanel(9, countMe);
                            }else if(campoAnadir.equals("ORDER BY ___ DESC")){
                                 nuevoSubPanel = new subPanel(10, countMe);
                            }
                        
                        } catch (SQLException ex) {
                                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        
                        listElementsStatement.add(nuevoSubPanel);
                        mainPanel.add(nuevoSubPanel);
                        myFrame.pack();
                        countMe++;
                    }
                });
                
                listElementsStatement.add(nuevoSubPanel);
                mainPanel.add(nuevoSubPanel);
                myFrame.pack();
                countMe++;
                
                add(combo);
                add(boton);
                
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
                
                fromTable.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        try {
                            fillAllCombos(comboBoxesParam, fromTable);
                            fillAllCombosSELECT(comboBoxesSELECT, fromTable);
                            fromTable.setName(String.valueOf(fromTable.getSelectedItem()));
                            System.out.println("El nombre ahora es " + fromTable.getName());
                            myFrame.pack();
                        } catch (SQLException ex) {
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                selectTable.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        selectTable.setName(String.valueOf(selectTable.getSelectedItem()));
                        System.out.println("El nombre ahora es " + selectTable.getName());
                        myFrame.pack();
                    }
                });
                
                
                JButton myButtonRemoveMe = createBotonQuitar();
                
                
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
                        myFrame.pack();
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
                                myFrame.pack();
                            }
                        });
                        
                        try {
                            fillAllCombosSELECT(comboBoxesSELECT, fromTable);
                        } catch (SQLException ex) {
                            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                        
                        myFrame.pack();
                    }
                });
                /*
                anadirFromParam.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                        myFrame.pack();
                    }
                });
                */
                
                add(myButtonRemoveMe);
                
            }else if(key == 3 || key == 4){
                JLabel whereLabel = null;
                if(key == 3){ whereLabel = new JLabel("WHERE");}
                else if(key == 4){ whereLabel = new JLabel("WHERE NOT");}
                
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
                
                add(whereLabel);
                add(openParenthesis);
                add(firstParameter);
                add(operandos);
                add(secondParameter);
                add(closeParenthesis);
                add(anadirParametros);
                add(parametrosOpcionales);
                add(myButtonRemoveMe);
                
                
                comboBoxesParam.add(firstParameter);
                
                fillWithOperands(operandos);
                fillWithAndOr(parametrosOpcionales);
                
                try {
                    fillAllCombos(comboBoxesParam, fromTable);
                }catch(SQLException ex){
                    Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                firstParameter.setName(String.valueOf(firstParameter.getSelectedItem()));
                operandos.setName(String.valueOf(operandos.getSelectedItem()));
                secondParameter.setName(String.valueOf(secondParameter.getText()));
                
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
                            fillAllCombos(comboBoxesParam, fromTable);
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
                        
                        myFrame.pack();
                        firstParameter2.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                firstParameter2.setName(String.valueOf(firstParameter2.getSelectedItem()));
                                System.out.println("El nombre ahora es " + firstParameter2.getName());
                                myFrame.pack();
                            }
                        });
                
                        operandos2.addItemListener(new ItemListener(){
                            @Override
                            public void itemStateChanged(ItemEvent ie) {
                                operandos2.setName(String.valueOf(operandos2.getSelectedItem()));
                                System.out.println("El nombre ahora es " + operandos2.getName());
                                myFrame.pack();
                            }
                        });

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
                                myFrame.pack();
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
                        myFrame.pack();
                    }
                });
                
                operandos.addItemListener(new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent ie) {
                        operandos.setName(String.valueOf(operandos.getSelectedItem()));
                        System.out.println("El nombre ahora es " + operandos.getName());
                        myFrame.pack();
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
                        myFrame.pack();
                    }
                });
                
                
                myButtonRemoveMe.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                        myFrame.pack();
                    }
                });
                
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
                
                yNOToNOT_ParamOpcionales(parametrosOpcionales,0);
                
                myButtonRemoveMe.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countMe = countMe - 1;
                        me.removeAndReorderFromList(me.index);
                        me.getParent().remove(me);
                        myFrame.pack();
                    }
                });
                
                anadirParametros.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if(flag==0){
                            JComboBox primeroGrupo1 = new JComboBox();
                            JComboBox operandoGrupo1 = new JComboBox();
                            JTextField segundoGrupo1 = new JTextField();

                            JLabel operador = new JLabel(String.valueOf(parametrosOpcionales.getSelectedItem()));
                            
                            yNOToNOT_ParamOpcionales(parametrosOpcionales,1);
                            
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
                                fillAllCombos(comboBoxesParam, fromTable);
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
                                    myFrame.pack();
                                }
                            });

                            operandoGrupo1.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    operandoGrupo1.setName(String.valueOf(operandoGrupo1.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + operandoGrupo1.getName());
                                    myFrame.pack();
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
                                    myFrame.pack();
                                }
                            });
                            
                            primeroGrupo2.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    primeroGrupo2.setName(String.valueOf(primeroGrupo2.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + primeroGrupo2.getName());
                                    myFrame.pack();
                                }
                            });

                            operandoGrupo2.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    operandoGrupo2.setName(String.valueOf(operandoGrupo2.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + operandoGrupo2.getName());
                                    myFrame.pack();
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
                                    myFrame.pack();
                                }
                            });
                            
                            myFrame.pack();
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
                                fillAllCombos(comboBoxesParam, fromTable);
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
                                    myFrame.pack();
                                }
                            });

                            operandoGrupo1.addItemListener(new ItemListener(){
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    operandoGrupo1.setName(String.valueOf(operandoGrupo1.getSelectedItem()));
                                    System.out.println("El nombre ahora es " + operandoGrupo1.getName());
                                    myFrame.pack();
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
                                    myFrame.pack();
                                }
                            });
                            
                            myFrame.pack();
                            
                        }
                        Container c = anadirParametros.getParent();
                        for (int i = 0; i < c.getComponentCount(); i++) {
                            System.out.println("El Boton anadir esta en el indice: " + c.getComponent(i).getName() );
                        }
                    } 
                });
                myFrame.pack();
            }else if(key == 9){
                
            }
            
        }
        
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
        
        public void fillFromCombo(JComboBox combo) throws SQLException{
            String sql = "SELECT table_name FROM user_tables ORDER BY table_name";
            ResultSet rs = capaDatos.makeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(rs.next()){
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    combo.addItem(columnValue);
                }
            }
            combo.setSelectedIndex(0);
            combo.setName(String.valueOf(combo.getSelectedItem()));
            System.out.println("El nombre ahora es " + combo.getName());
        }
        
        public void fillAllCombos(ArrayList<JComboBox> arrayCombos, JComboBox comboFrom) throws SQLException{
            
            int size=arrayCombos.size();

            String sql = "SELECT * FROM " + String.valueOf(comboFrom.getSelectedItem());

            ResultSet rs = capaDatos.makeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();


            for(int i = 0; i<size; i++){
                arrayCombos.get(i).removeAllItems();
                for (int j = 1; j <= columnsNumber; j++) {
                    arrayCombos.get(i).addItem(rsmd.getColumnName(j));
                }
                arrayCombos.get(i).setName(String.valueOf(arrayCombos.get(i).getSelectedItem()));
                System.out.println("El nombre ahora es " + arrayCombos.get(i).getName());
            }
        }
        
        public void fillAllCombosSELECT(ArrayList<JComboBox> arrayCombos, JComboBox comboFrom) throws SQLException{
            
            int size=arrayCombos.size();
            
            for(int i = 0; i<size; i++){
                arrayCombos.get(i).removeAllItems();
                arrayCombos.get(i).addItem("*");
            }

            String sql = "SELECT * FROM " + String.valueOf(comboFrom.getSelectedItem());

            ResultSet rs = capaDatos.makeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();


            for(int i = 0; i<size; i++){
                for (int j = 1; j <= columnsNumber; j++) {
                    arrayCombos.get(i).addItem(rsmd.getColumnName(j));
                }
                arrayCombos.get(i).setName(String.valueOf(arrayCombos.get(i).getSelectedItem()));
                System.out.println("El nombre ahora es " + arrayCombos.get(i).getName());
            }
            
        }
        
        public void fillWithOperands(JComboBox combo){
            String[] operandos= {"=", ">", "<", ">=","<=", "<>", "BETWEEN", "LIKE", "IN"};
            
            for(int i=0; i<operandos.length; i++){
                combo.addItem(operandos[i]);
            }
            
        }
        
        public void fillWithAndOr(JComboBox combo){
            String[] AndOr= {"AND","OR","AND NOT","OR NOT"};
            for(int i=0; i<AndOr.length; i++){
                combo.addItem(AndOr[i]);
            }
        }
        
        public void yNOToNOT_ParamOpcionales(JComboBox combo, int flag){
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
        }
        
        public JButton createBotonAnadir(){
            
            JButton botonAnadir = new JButton(addIcon);
            botonAnadir.setBorderPainted(false); 
            botonAnadir.setContentAreaFilled(false); 
            botonAnadir.setFocusPainted(false); 
            botonAnadir.setOpaque(false);
            botonAnadir.setPressedIcon(addIconPressed);
            
            return botonAnadir;
        }
        
        public JButton createBotonQuitar(){
            
            JButton botonAnadir = new JButton(removeIcon);
            botonAnadir.setBorderPainted(false); 
            botonAnadir.setContentAreaFilled(false); 
            botonAnadir.setFocusPainted(false); 
            botonAnadir.setOpaque(false);
            botonAnadir.setPressedIcon(removeIconPressed);
            
            return botonAnadir;
        }
        
    }
    
}
