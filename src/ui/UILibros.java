/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.Autor;
import negocio.CapaNegocio;
import negocio.Libro;

/**
 *
 * @author USUARIO
 */
public class UILibros extends javax.swing.JFrame {

    /**
     * Creates new form UIUsuarios
     */
    
    private DefaultTableModel modeloTabla;
    private List<Libro> libros;
    private List<Autor> autores;
    private Libro libro;
    private boolean opcion;
    private CapaNegocio negocio;
    private TextAutoCompleter textAutoCompleter;
    
    
    public UILibros(CapaNegocio capaNegocio) {
        initComponents();
        this.negocio = capaNegocio;
    }
    
    public void iniciar() {
        this.setTitle("Gestión de Libros");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        activarTextos(false);
        activarBotones(true);
        mostrarTabla();
        initCombo();
        this.jTableLibros.setEnabled(false);
        textAutoCompleter = new TextAutoCompleter(jTextFieldAutor);
        jTextFieldId.setEditable(false);
    }
    
    private void activarTextos(Boolean estado) {
        jTextFieldTitulo.setEditable(estado);
        jTextFieldAutor.setEditable(estado);
    }
    
    private void activarBotones(Boolean estado) {
        jButtonNuevo.setEnabled(estado);
        jButtonActualizar.setEnabled(estado);
        jButtonEliminar.setEnabled(estado);
        jButtonGrabar.setEnabled(!estado);
        jButtonCancelar.setEnabled(!estado);
    }
    
    private void vaciarTextos() {
        jTextFieldId.setText("");
        jTextFieldTitulo.setText("");
        jTextFieldAutor.setText("");
    }
    
    public void initCombo() {
        jComboOrden.addItem("Id");
        jComboOrden.addItem("Título");
        jComboOrden.addItem("Autor (Id)");
    }
    
    private void mostrarTabla() {
        String [][] roster = {};
        String [] columnas = {"Id", "Título", "Autor (Id)"};
        this.modeloTabla = new DefaultTableModel(roster, columnas);
        jTableLibros.setModel(modeloTabla);
    }
    
    private void cargarDatos() {
        int rows = this.modeloTabla.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            this.modeloTabla.removeRow(i);
        }
        for (int j = 0; j < libros.size(); j++) {
            this.modeloTabla.insertRow(this.modeloTabla.getRowCount(), new Object[]{});
            this.modeloTabla.setValueAt(String.valueOf(libros.get(j).getId()), this.modeloTabla.getRowCount() - 1, 0);
            this.modeloTabla.setValueAt(libros.get(j).getTitulo(), this.modeloTabla.getRowCount() - 1, 1);
            this.modeloTabla.setValueAt(String.valueOf(libros.get(j).getAutor_id()), this.modeloTabla.getRowCount() - 1, 2);
        }
    }
    
    // Limita caracteres, para evitar Inyecciones SQL
    private boolean esAlfaNumerico(String cadena) {
        char[] charArray = cadena.toCharArray();
        for(char c : charArray) {
            if (!(Character.isLetterOrDigit(c) || c == ' ' || c == '-' || c == ',' || c == ':'))
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
        jTextFieldId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTitulo = new javax.swing.JTextField();
        jTextFieldAutor = new javax.swing.JTextField();
        jButtonNuevo = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonGrabar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLibros = new javax.swing.JTable();
        jButtonBuscar = new javax.swing.JButton();
        jTextFieldBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonMostrarTodos = new javax.swing.JButton();
        jComboOrden = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Gestión de Libros");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Id:");

        jTextFieldId.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Título:");

        jTextFieldAutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldAutorKeyTyped(evt);
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

        jTableLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableLibros);

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel6.setText("Buscar por Id, Título o Id de Autor");

        jButtonMostrarTodos.setText("Mostrar Todos");
        jButtonMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarTodosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel7.setText("Ordenar por");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Autor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(247, 247, 247))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(37, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jComboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonMostrarTodos)
                                .addGap(223, 223, 223))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldId)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(144, 144, 144))
                            .addComponent(jTextFieldAutor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonGrabar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNuevo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonActualizar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEliminar)
                    .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGrabar)
                    .addComponent(jButtonCancelar))
                .addGap(28, 28, 28)
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
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        this.activarBotones(false);
        this.activarTextos(true);
        opcion = true;
        this.jTextFieldId.setText(String.valueOf(negocio.proximoID(Libro.class, null)));
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        try {
            int idActualizar = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el Id del libro a mofificar: "));
            libros = negocio.consultarLibro(idActualizar);
            if (!libros.isEmpty()) {
                libro = libros.get(0);
                libros.clear();
                JOptionPane.showMessageDialog(this, "Modifique los datos del libro en la ventana principal y luego presione Grabar.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                this.activarBotones(false);
                this.activarTextos(true);
                jTextFieldId.setText(String.valueOf(libro.getId()));
                jTextFieldTitulo.setText(libro.getTitulo());
                Autor autor = negocio.consultarAutor(libro.getAutor_id()).get(0);
                jTextFieldAutor.setText(autor.getNombre());
                opcion = false;
            } else {
                JOptionPane.showMessageDialog(this, "No existe un libro con el id ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            if (e.getClass().equals(NumberFormatException.class)) {
                JOptionPane.showMessageDialog(this, "El Id debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo recuperar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        try {
            int idEliminar = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el id del libro a eliminar: "));
            libros = negocio.consultarLibro(idEliminar);
            if (!libros.isEmpty()) {
                libro = libros.get(0);
                libros.clear();
                negocio.eliminar(libro);
                JOptionPane.showMessageDialog(this, "Libro eliminado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                this.vaciarTextos();
            } else {
                JOptionPane.showMessageDialog(this, "No existe un libro con el id ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            if (e.getClass().equals(NumberFormatException.class)) {
                JOptionPane.showMessageDialog(this, "El Id debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo recuperar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGrabarActionPerformed
        try {
            if (esAlfaNumerico(jTextFieldTitulo.getText()) && esAlfaNumerico(jTextFieldAutor.getText())) {
                List<Autor> autoresTemp = negocio.consultarAutor(jTextFieldAutor.getText());
                if (!autoresTemp.isEmpty()) {
                    Autor autor = autoresTemp.get(0);
                    if (opcion) {
                    negocio.insertar(new Libro(Integer.parseInt(jTextFieldId.getText()), jTextFieldTitulo.getText(), autor.getId()));
                    JOptionPane.showMessageDialog(this, "Libro ingresado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        libro.setTitulo(jTextFieldTitulo.getText());
                        libro.setAutor_id(autor.getId());
                        negocio.actualizar(libro);
                        JOptionPane.showMessageDialog(this, "Libro actualizado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                    }
                    this.activarBotones(true);
                    this.activarTextos(false);
                    this.vaciarTextos();
                    textAutoCompleter.removeAllItems();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un autor con el nombre especificado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Los datos no deben contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "El libro no fue grabado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGrabarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.activarBotones(true);
        this.activarTextos(false);
        this.vaciarTextos();
        textAutoCompleter.removeAllItems();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            String busqueda = jTextFieldBuscar.getText();
            if (esAlfaNumerico(busqueda) && !busqueda.isEmpty()) {
                libros = negocio.buscarLibros(busqueda, jComboOrden.getSelectedIndex());
                if (!libros.isEmpty()) {
                    this.cargarDatos();
                    libros.clear();
                } else {
                    JOptionPane.showMessageDialog(this, "No existen resultados para la búsqueda.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                }   
            } else {
                JOptionPane.showMessageDialog(this, "La búsqueda no debe contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }       
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de libros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonMostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarTodosActionPerformed
        try {
            libros = negocio.todosLibros(jComboOrden.getSelectedIndex());
            this.cargarDatos();
            libros.clear();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de libros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonMostrarTodosActionPerformed

    private void jTextFieldAutorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAutorKeyTyped
        String busqueda = this.jTextFieldAutor.getText();
        if (esAlfaNumerico(busqueda)) {
            autores = negocio.consultarAutores(busqueda, 7);
            autores.forEach((autor) -> {
                if (!textAutoCompleter.itemExists(autor.getNombre())) {
                    textAutoCompleter.addItem(autor.getNombre());
                }
            });
        }
    }//GEN-LAST:event_jTextFieldAutorKeyTyped

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLibros;
    private javax.swing.JTextField jTextFieldAutor;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables
}
