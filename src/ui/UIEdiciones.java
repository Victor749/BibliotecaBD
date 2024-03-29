/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.CapaNegocio;
import negocio.Edicion;
import negocio.Editorial;
import negocio.Libro;

/**
 *
 * @author USUARIO
 */
public class UIEdiciones extends javax.swing.JFrame {

    /**
     * Creates new form UIUsuarios
     */
    
    private DefaultTableModel modeloTabla;
    private List<Edicion> ediciones;
    private List<Libro> libros;
    private List<Editorial> editoriales;
    private Edicion edicion;
    private boolean opcion;
    private CapaNegocio negocio;
    private TextAutoCompleter textAutoCompleterLibro;
    private TextAutoCompleter textAutoCompleterEditorial;
    

    
    public UIEdiciones(CapaNegocio capaNegocio) {
        initComponents();
        this.negocio = capaNegocio;
    }
    
    public void iniciar() {
        this.setTitle("Gestión de Ediciones");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        activarTextos(false);
        activarBotones(true);
        mostrarTabla();
        initCombo();
        this.jTableEdiciones.setEnabled(false);
        textAutoCompleterLibro = new TextAutoCompleter(jTextFieldLibro);
        textAutoCompleterEditorial = new TextAutoCompleter(jTextFieldEditorial);
        this.jDateChooserFecha.setDateFormatString("yyyy-MM-dd");
        this.jTextFieldIsbn.setEditable(false);
    }
    
    private void activarTextos(Boolean estado) {
        jTextFieldLibro.setEditable(estado);
        jTextFieldEditorial.setEditable(estado);
        this.jTextFieldNumero.setEditable(estado);
        this.jDateChooserFecha.setEnabled(estado);
        this.jTextFieldDescripcion.setEditable(estado);
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
        jTextFieldLibro.setText("");
        jTextFieldEditorial.setText("");
        this.jTextFieldNumero.setText("");
        this.jDateChooserFecha.setToolTipText("");
        jDateChooserFecha.setDate(null);
        this.jTextFieldDescripcion.setText("");
    }
    
    public void initCombo() {
        jComboOrden.addItem("ISBN");
        jComboOrden.addItem("Libro (Id)");
        jComboOrden.addItem("Editorial (Id)");
        jComboOrden.addItem("Número");
        jComboOrden.addItem("Fecha");
    }
    
    private void mostrarTabla() {
        String [][] roster = {};
        String [] columnas = {"ISBN", "Libro (Id)", "Editorial (Id)", "Número", "Fecha", "Descripción"};
        this.modeloTabla = new DefaultTableModel(roster, columnas);
        jTableEdiciones.setModel(modeloTabla);
    }
    
    private void cargarDatos() {
        int rows = this.modeloTabla.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            this.modeloTabla.removeRow(i);
        }
        for (int j = 0; j < ediciones.size(); j++) {
            this.modeloTabla.insertRow(this.modeloTabla.getRowCount(), new Object[]{});
            this.modeloTabla.setValueAt(ediciones.get(j).getIsbn(), this.modeloTabla.getRowCount() - 1, 0);
            this.modeloTabla.setValueAt(String.valueOf(ediciones.get(j).getLibro_id()), this.modeloTabla.getRowCount() - 1, 1);
            this.modeloTabla.setValueAt(String.valueOf(ediciones.get(j).getEditorial_id()), this.modeloTabla.getRowCount() - 1, 2);
            this.modeloTabla.setValueAt(String.valueOf(ediciones.get(j).getNumero()), this.modeloTabla.getRowCount() - 1, 3);
            this.modeloTabla.setValueAt(ediciones.get(j).getFecha(), this.modeloTabla.getRowCount() - 1, 4);
            this.modeloTabla.setValueAt(ediciones.get(j).getDescripcion(), this.modeloTabla.getRowCount() - 1, 5);
        }
    }
    
    // Limita caracteres, para evitar Inyecciones SQL
    private boolean esAlfaNumerico(String cadena) {
        int tipo = negocio.tipoDato(cadena);
        if (tipo == 1 ){
            return true;
        }
        return false;
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
        int tipo = negocio.tipoDato(cadena);
        if (tipo == 1 ){
            return false;
        }
        return true;
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
        jTextFieldLibro = new javax.swing.JTextField();
        jTextFieldEditorial = new javax.swing.JTextField();
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
        jTextFieldNumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Gestión de Ediciones");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ISBN:");

        jTextFieldIsbn.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Libro:");

        jTextFieldLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldLibroKeyTyped(evt);
            }
        });

        jTextFieldEditorial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEditorialKeyTyped(evt);
            }
        });

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
        jLabel6.setText("Buscar por ISBN, Id de Libro, Id de Editorial, Número, Fecha o Descripción");

        jButtonMostrarTodos.setText("Mostrar Todos");
        jButtonMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarTodosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel7.setText("Ordenar por");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Editorial:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Número:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Fecha:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("*Descripción:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel10.setText("(*) Indica un campo opcional.");

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
                                .addGap(0, 162, Short.MAX_VALUE)))
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
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldDescripcion)
                            .addComponent(jTextFieldLibro)
                            .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEditorial, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNumero, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonGrabar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(jButtonNuevo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonActualizar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEliminar)
                    .addComponent(jTextFieldEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGrabar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
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
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        this.activarBotones(false);
        this.activarTextos(true);
        opcion = true;
        this.jTextFieldIsbn.setEditable(true);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        try {
            String isbnActualizar = JOptionPane.showInputDialog(this, "Ingrese el ISBN de la edición a mofificar: ");
            if (validar(isbnActualizar)) {
                ediciones = negocio.consultarEdicion(isbnActualizar);
                if (!ediciones.isEmpty()) {
                    edicion = ediciones.get(0);
                    ediciones.clear();
                    JOptionPane.showMessageDialog(this, "Modifique los datos de la edición en la ventana principal y luego presione Grabar.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                    this.activarBotones(false);
                    this.activarTextos(true);
                    jTextFieldIsbn.setText(edicion.getIsbn());
                    Libro libro = negocio.consultarLibro(edicion.getLibro_id()).get(0);
                    jTextFieldLibro.setText(libro.getTitulo());
                    Editorial editorial = negocio.consultarEditorial(edicion.getEditorial_id()).get(0);
                    jTextFieldEditorial.setText(editorial.getNombre());
                    this.jTextFieldNumero.setText(String.valueOf(edicion.getNumero()));
                    this.jTextFieldDescripcion.setText(edicion.getDescripcion());
                    int anio = Integer.parseInt(edicion.getFecha().substring(0, 4));
                    int mes = Integer.parseInt(edicion.getFecha().substring(5, 7));
                    int dia = Integer.parseInt(edicion.getFecha().substring(9));
                    this.jDateChooserFecha.setCalendar(new GregorianCalendar(anio, mes - 1, dia));
                    opcion = false;
                } else {
                    JOptionPane.showMessageDialog(this, "No existe una edición con el ISBN ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El ISBN debe tener 13 dígitos y no puede contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la edición: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        try {
            String isbnEliminar = JOptionPane.showInputDialog(this, "Ingrese el ISBN de la edición a eliminar: ");
            if (validar(isbnEliminar)) {
                ediciones = negocio.consultarEdicion(isbnEliminar);
                if (!ediciones.isEmpty()) {
                    edicion = ediciones.get(0);
                    ediciones.clear();
                    negocio.eliminar(edicion);
                    JOptionPane.showMessageDialog(this, "Edición eliminada con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                    this.vaciarTextos();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe una edición con el ISBN ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El ISBN debe tener 13 dígitos y no puede contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la edición: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGrabarActionPerformed
        try {
            if (esAlfaNumerico(jTextFieldLibro.getText()) && validar(this.jTextFieldIsbn.getText()) && esAlfaNumerico(jTextFieldEditorial.getText()) && entero(this.jTextFieldNumero.getText())) {
                List<Libro> librosTemp = negocio.consultarLibro(negocio.normalizar(jTextFieldLibro.getText()));
                if (!librosTemp.isEmpty()) {
                    List<Editorial> editorialesTemp = negocio.consultarEditorial(negocio.normalizar(jTextFieldEditorial.getText()));
                        if (!editorialesTemp.isEmpty()) {
                            Libro libro = librosTemp.get(0);
                            Editorial editorial = editorialesTemp.get(0);
                            GregorianCalendar calendar = (GregorianCalendar) this.jDateChooserFecha.getCalendar();
                            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            fmt.setCalendar(calendar);
                            String fecha = fmt.format(calendar.getTime());
                            if (opcion) {
                                negocio.insertar(new Edicion(jTextFieldIsbn.getText(), libro.getId(), editorial.getId(), Integer.parseInt(this.jTextFieldNumero.getText()), fecha, negocio.normalizar(this.jTextFieldDescripcion.getText())));
                                JOptionPane.showMessageDialog(this, "Edición ingresada con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                edicion.setLibro_id(libro.getId());
                                edicion.setEditorial_id(editorial.getId());
                                edicion.setNumero(Integer.parseInt(this.jTextFieldNumero.getText()));
                                edicion.setFecha(fecha);
                                edicion.setDescripcion(negocio.normalizar(this.jTextFieldDescripcion.getText()));
                                negocio.actualizar(edicion);
                                JOptionPane.showMessageDialog(this, "Edición actualizada con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                            }
                            this.activarBotones(true);
                            this.activarTextos(false);
                            this.vaciarTextos();
                            this.jTextFieldIsbn.setEditable(false);
                            textAutoCompleterLibro.removeAllItems();
                            textAutoCompleterEditorial.removeAllItems();
                        } else {
                            JOptionPane.showMessageDialog(this, "No existe una editorial con el nombre especificado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }  
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un libro con el título especificado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El ISBN debe tener 13 dígitos. El número de edición debe ser un entero. Los datos no deben contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "La edición no fue grabada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGrabarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.activarBotones(true);
        this.activarTextos(false);
        this.vaciarTextos();
        textAutoCompleterLibro.removeAllItems();
        textAutoCompleterEditorial.removeAllItems();
        this.jTextFieldIsbn.setEditable(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            String busqueda = jTextFieldBuscar.getText();
            if (!busqueda.isEmpty()) {
                ediciones = negocio.buscarEdiciones(negocio.normalizar(busqueda), jComboOrden.getSelectedIndex());
                if (!ediciones.isEmpty()) {
                    this.cargarDatos();
                    ediciones.clear();
                } else {
                    JOptionPane.showMessageDialog(this, "No existen resultados para la búsqueda.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                }   
            } else {
                JOptionPane.showMessageDialog(this, "La búsqueda no debe contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }       
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de ediciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonMostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarTodosActionPerformed
        try {
            ediciones = negocio.todasEdiciones(jComboOrden.getSelectedIndex());
            this.cargarDatos();
            ediciones.clear();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de ediciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonMostrarTodosActionPerformed

    private void jTextFieldEditorialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEditorialKeyTyped
        String busqueda = this.jTextFieldEditorial.getText();
        if (esAlfaNumerico(busqueda)) {
            editoriales = negocio.consultarEditoriales(negocio.normalizar(busqueda), 7);
            editoriales.forEach((editorial) -> {
                if (!textAutoCompleterEditorial.itemExists(editorial.getNombre())) {
                    textAutoCompleterEditorial.addItem(editorial.getNombre());
                }
            });
        }
    }//GEN-LAST:event_jTextFieldEditorialKeyTyped

    private void jTextFieldLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLibroKeyTyped
        String busqueda = this.jTextFieldLibro.getText();
        if (esAlfaNumerico(busqueda)) {
            libros = negocio.consultarLibros(negocio.normalizar(busqueda), 7);
            libros.forEach((libro) -> {
                if (!textAutoCompleterLibro.itemExists(libro.getTitulo())) {
                    textAutoCompleterLibro.addItem(libro.getTitulo());
                }
            });
        }
    }//GEN-LAST:event_jTextFieldLibroKeyTyped

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
    private javax.swing.JComboBox<String> jComboOrden;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEdiciones;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldEditorial;
    private javax.swing.JTextField jTextFieldIsbn;
    private javax.swing.JTextField jTextFieldLibro;
    private javax.swing.JTextField jTextFieldNumero;
    // End of variables declaration//GEN-END:variables
}
