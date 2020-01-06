/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import negocio.CapaNegocio;
import negocio.Edicion;
import negocio.Ejemplar;
import negocio.Estante;
import negocio.Planta;

/**
 *
 * @author USUARIO
 */
public class UIEjemplares extends javax.swing.JFrame {

    /**
     * Creates new form UIUsuarios
     */
    
    private DefaultTableModel modeloTabla;
    private List<Ejemplar> ejemplares;
    private List<Planta> plantas;
    private List<Estante> estantes;
    private Ejemplar ejemplar;
    private boolean opcion;
    private CapaNegocio negocio;
    
    public UIEjemplares() {
        initComponents();
    }
    
    public void iniciar() {
        this.setTitle("Gestión de Ejemplares");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        activarTextos(false);
        activarBotones(true);
        mostrarTabla();
        initCombo();
        negocio = new CapaNegocio();
        this.jTableEdiciones.setEnabled(false);
        this.jTextFieldIsbn.setEditable(false);
        this.jTextFieldId.setEditable(false);
    }
    
    private void activarTextos(Boolean estado) {
        this.jComboPlanta.setEditable(estado);
        this.jComboEstante.setEditable(estado);
        this.jTextFieldObservaciones.setEditable(estado);
    }
    
    private void activarBotones(Boolean estado) {
        jButtonNuevo.setEnabled(estado);
        jButtonActualizar.setEnabled(estado);
        jButtonEliminar.setEnabled(estado);
        jButtonGrabar.setEnabled(!estado);
        jButtonCancelar.setEnabled(!estado);
    }
    
    private void vaciarTextos() {
        jTextFieldIsbn.setText("");
        jTextFieldId.setText("");
        this.jComboPlanta.removeAllItems();
        this.jComboEstante.removeAllItems();
        this.jTextFieldObservaciones.setText("");
        this.jLabelInformeIsbn.setText("");
    }
    
    public void initCombo() {
        jComboOrden.addItem("ISBN");
        jComboOrden.addItem("Id");
        jComboOrden.addItem("Planta (Id)");
        jComboOrden.addItem("Estante (Id)");
        jComboOrden.addItem("Prestado");
        jComboOrden.addItem("Observaciones");
    }
    
    public void cargarComboPlanta() {
        jComboPlanta.removeAllItems();
        plantas = negocio.todasPlantas(0);
        plantas.forEach((plantaCombo) -> {
            jComboPlanta.addItem(String.valueOf(plantaCombo.getId()));
        });
        plantas.clear();
    }
    
    public void cargarComboEstante() {
        jComboEstante.removeAllItems();
        estantes = negocio.consultarEstantes(Integer.parseInt(jComboPlanta.getSelectedItem().toString()));
        estantes.forEach((estanteCombo) -> {
            jComboEstante.addItem(String.valueOf(estanteCombo.getId()));
        });
        estantes.clear();
    }
    
    private void mostrarTabla() {
        String [][] roster = {};
        String [] columnas = {"ISBN", "Id", "Planta (Id)", "Estante (Id)", "Prestado", "Observaciones"};
        this.modeloTabla = new DefaultTableModel(roster, columnas);
        jTableEdiciones.setModel(modeloTabla);
    }
    
    private void cargarDatos() {
        int rows = this.modeloTabla.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            this.modeloTabla.removeRow(i);
        }
        for (int j = 0; j < ejemplares.size(); j++) {
            this.modeloTabla.insertRow(this.modeloTabla.getRowCount(), new Object[]{});
            this.modeloTabla.setValueAt(ejemplares.get(j).getEdicion_isbn(), this.modeloTabla.getRowCount() - 1, 0);
            this.modeloTabla.setValueAt(String.valueOf(ejemplares.get(j).getId()), this.modeloTabla.getRowCount() - 1, 1);
            this.modeloTabla.setValueAt(String.valueOf(ejemplares.get(j).getPlanta_id()), this.modeloTabla.getRowCount() - 1, 2);
            this.modeloTabla.setValueAt(String.valueOf(ejemplares.get(j).getEstante_id()), this.modeloTabla.getRowCount() - 1, 3);
            this.modeloTabla.setValueAt(String.valueOf(ejemplares.get(j).getPrestado()), this.modeloTabla.getRowCount() - 1, 4);
            this.modeloTabla.setValueAt(ejemplares.get(j).getObservaciones(), this.modeloTabla.getRowCount() - 1, 5);
        }
    }
    
    private boolean esAlfaNumerico(String cadena) {
        char[] charArray = cadena.toCharArray();
        for(char c : charArray) {
            if (!(Character.isLetterOrDigit(c) || c == ' ' || c == '-'))
                return false;
        }
        return true;
    }
    
    private boolean validar(String cadena) {
        char[] charArray = cadena.toCharArray();
        if (charArray.length != 13) {
            return false;
        }
        for(char c : charArray) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
    
    private boolean entero(String cadena) {
        try {
            int parseInt = Integer.parseInt(cadena);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldIsbn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jButtonNuevo = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonGrabar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEdiciones = new javax.swing.JTable();
        jButtonBuscar = new javax.swing.JButton();
        jTextFieldBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonMostrarTodos = new javax.swing.JButton();
        jComboOrden = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButtonVerEdiciones = new javax.swing.JButton();
        jTextFieldObservaciones = new javax.swing.JTextField();
        jComboPlanta = new javax.swing.JComboBox<>();
        jComboEstante = new javax.swing.JComboBox<>();
        jButtonVerLogisitca = new javax.swing.JButton();
        jLabelInformeIsbn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Gestión de Ejemplares");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ISBN:");

        jTextFieldIsbn.setToolTipText("");
        jTextFieldIsbn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFielIsbnKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Id:");

        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonGrabar.setText("Grabar");
        jButtonGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGrabarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTableEdiciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableEdiciones);

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel6.setText("Buscar por ISBN, Id de Ejemplar, Id de Planta, Id de Estante u Observaciones");

        jButtonMostrarTodos.setText("Mostrar Todos");
        jButtonMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarTodosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel7.setText("Ordenar por");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Planta (Id):");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Estante (Id):");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("*Observaciones:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel10.setText("(*) Indica un campo opcional.");

        jButtonVerEdiciones.setText("Ver Ediciones");
        jButtonVerEdiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerEdicionesActionPerformed(evt);
            }
        });

        jComboPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPlantaActionPerformed(evt);
            }
        });

        jButtonVerLogisitca.setText("Ver Logística");
        jButtonVerLogisitca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerLogisitcaActionPerformed(evt);
            }
        });

        jLabelInformeIsbn.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabelInformeIsbn.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldBuscar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 151, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jComboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(216, 216, 216)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelInformeIsbn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldObservaciones, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonVerEdiciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jTextFieldId)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jComboEstante, javax.swing.GroupLayout.Alignment.LEADING, 0, 458, Short.MAX_VALUE)
                                            .addComponent(jComboPlanta, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonVerLogisitca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonGrabar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonMostrarTodos)
                        .addGap(347, 347, 347))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(330, 330, 330))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNuevo)
                    .addComponent(jButtonVerEdiciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelInformeIsbn)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonVerLogisitca))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboEstante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonGrabar)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jLabel10))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonMostrarTodos)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        this.activarBotones(false);
        this.activarTextos(true);
        opcion = true;
        jTextFieldIsbn.setEditable(true);
        cargarComboPlanta();
        cargarComboEstante();
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        try {
            JTextField campoISBN = new JTextField(13);
            JTextField campoId = new JTextField(4);
            JPanel panel = new JPanel();
            panel.add(new JLabel("ISBN: "));
            panel.add(campoISBN);
            panel.add(Box.createHorizontalStrut(15)); 
            panel.add(new JLabel("Id: "));
            panel.add(campoId);
            int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese ISBN e Id", JOptionPane.OK_CANCEL_OPTION);
            String isbnActualizar = "";
            String id = "";
            if (result == JOptionPane.OK_OPTION) {
               isbnActualizar = campoISBN.getText();
               id = campoId.getText();
            }
            if (validar(isbnActualizar) && entero(id)) {
                ejemplares = negocio.consultarEjemplar(isbnActualizar, Integer.parseInt(id));
                if (!ejemplares.isEmpty()) {
                    ejemplar = ejemplares.get(0);
                    ejemplares.clear();
                    JOptionPane.showMessageDialog(this, "Modifique los datos de la edición en la ventana principal y luego presione Grabar.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                    this.activarBotones(false);
                    this.activarTextos(true);
                    jTextFieldIsbn.setEditable(false);
                    jTextFieldIsbn.setText(ejemplar.getEdicion_isbn());
                    jTextFieldId.setText(String.valueOf(ejemplar.getId()));
                    cargarComboPlanta();
                    this.jComboPlanta.setSelectedItem(String.valueOf(ejemplar.getPlanta_id()));
                    this.cargarComboEstante();
                    this.jComboEstante.setSelectedItem(String.valueOf(ejemplar.getEstante_id()));
                    this.jTextFieldObservaciones.setText(ejemplar.getObservaciones());
                    opcion = false;
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un ejemplar con el ISBN e Id ingresados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El ISBN debe tener 13 dígitos y no puede contener caracteres especiales. El Id debe ser un entero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la edición: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        try {
            JTextField campoISBN = new JTextField(13);
            JTextField campoId = new JTextField(4);
            JPanel panel = new JPanel();
            panel.add(new JLabel("ISBN: "));
            panel.add(campoISBN);
            panel.add(Box.createHorizontalStrut(15)); 
            panel.add(new JLabel("Id: "));
            panel.add(campoId);
            int result = JOptionPane.showConfirmDialog(this, panel, "Ingrese ISBN e Id", JOptionPane.OK_CANCEL_OPTION);
            String isbnEliminar = "";
            String id = "";
            if (result == JOptionPane.OK_OPTION) {
               isbnEliminar = campoISBN.getText();
               id = campoId.getText();
            }
            if (validar(isbnEliminar) && entero(id)) {
                ejemplares = negocio.consultarEjemplar(isbnEliminar, Integer.parseInt(id));
                if (!ejemplares.isEmpty()) {
                    ejemplar = ejemplares.get(0);
                    ejemplares.clear();
                    negocio.eliminar(ejemplar);
                    JOptionPane.showMessageDialog(this, "Ejemplar eliminado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                    this.vaciarTextos();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un ejemplar con el ISBN e Id ingresados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El ISBN debe tener 13 dígitos y no puede contener caracteres especiales. El Id debe ser un entero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar el ejemplar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGrabarActionPerformed
        try {
            if (validar(this.jTextFieldIsbn.getText()) && esAlfaNumerico(jTextFieldObservaciones.getText())) {
                if (opcion) {
                    negocio.insertar(new Ejemplar(jTextFieldIsbn.getText(), Integer.parseInt(this.jTextFieldId.getText()), Integer.parseInt(this.jComboPlanta.getSelectedItem().toString()), Integer.parseInt(this.jComboEstante.getSelectedItem().toString()), 0, this.jTextFieldObservaciones.getText()));
                    JOptionPane.showMessageDialog(this, "Ejemplar ingresado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ejemplar.setPlanta_id(Integer.parseInt(this.jComboPlanta.getSelectedItem().toString()));
                    ejemplar.setEstante_id(Integer.parseInt(this.jComboEstante.getSelectedItem().toString()));
                    ejemplar.setObservaciones(this.jTextFieldObservaciones.getText());
                    negocio.actualizar(ejemplar);
                    JOptionPane.showMessageDialog(this, "Ejemplar actualizado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                }
                this.activarBotones(true);
                this.activarTextos(false);
                this.vaciarTextos();
                this.jTextFieldIsbn.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, "El ISBN debe tener 13 dígitos. Los datos no deben contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "El ejemplar no fue grabado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGrabarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.activarBotones(true);
        this.activarTextos(false);
        this.vaciarTextos();
        this.jTextFieldIsbn.setEditable(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            String busqueda = jTextFieldBuscar.getText();
            if (esAlfaNumerico(busqueda) && !busqueda.isEmpty()) {
                ejemplares = negocio.buscarEjemplares(busqueda, jComboOrden.getSelectedIndex());
                if (!ejemplares.isEmpty()) {
                    this.cargarDatos();
                    ejemplares.clear();
                } else {
                    JOptionPane.showMessageDialog(this, "No existen resultados para la búsqueda.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                }   
            } else {
                JOptionPane.showMessageDialog(this, "La búsqueda no debe contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }       
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de ejemplares: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonMostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarTodosActionPerformed
        try {
            ejemplares = negocio.todosEjemplares(jComboOrden.getSelectedIndex());
            this.cargarDatos();
            ejemplares.clear();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de ejemplares: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonMostrarTodosActionPerformed

    private void jButtonVerEdicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerEdicionesActionPerformed
        UIEdicionesAlternativa ui = new UIEdicionesAlternativa();
        ui.iniciar();
        /*UIEdiciones ui = new UIEdiciones();
        ui.iniciar();*/
    }//GEN-LAST:event_jButtonVerEdicionesActionPerformed

    private void jButtonVerLogisitcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerLogisitcaActionPerformed
        UILogistica ui = new UILogistica();
        ui.iniciar();
    }//GEN-LAST:event_jButtonVerLogisitcaActionPerformed

    private void jComboPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPlantaActionPerformed
        cargarComboEstante();
    }//GEN-LAST:event_jComboPlantaActionPerformed

    private void jTextFielIsbnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFielIsbnKeyReleased
        if(this.jTextFieldIsbn.getText().length() == 13) {
            List<Edicion> ediciones = negocio.consultarEdicion(this.jTextFieldIsbn.getText());
            if (ediciones.isEmpty()) {
                this.jLabelInformeIsbn.setText("* Ingrese un ISBN válido.");
                this.jTextFieldId.setText("");
            } else {
                this.jLabelInformeIsbn.setText("");
                this.jTextFieldId.setText(String.valueOf(negocio.proximoID(Ejemplar.class, ediciones.get(0).getIsbn())));
            }
        } else {
            this.jLabelInformeIsbn.setText("* Un ISBN tiene 13 dígitos.");
            this.jTextFieldId.setText("");
        }
    }//GEN-LAST:event_jTextFielIsbnKeyReleased

    /**
     * @param args the command line arguments
     */
    //public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(new Runnable() {
            /*public void run() {
                new UIUsuarios().setVisible(true);
           }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGrabar;
    private javax.swing.JButton jButtonMostrarTodos;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonVerEdiciones;
    private javax.swing.JButton jButtonVerLogisitca;
    private javax.swing.JComboBox<String> jComboEstante;
    private javax.swing.JComboBox<String> jComboOrden;
    private javax.swing.JComboBox<String> jComboPlanta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelInformeIsbn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEdiciones;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldIsbn;
    private javax.swing.JTextField jTextFieldObservaciones;
    // End of variables declaration//GEN-END:variables
}