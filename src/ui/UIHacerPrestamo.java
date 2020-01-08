/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.toedter.components.JSpinField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.CapaNegocio;
import negocio.Edicion;
import negocio.Ejemplar;
import negocio.Usuario;

/**
 *
 * @author USUARIO
 */
public class UIHacerPrestamo extends javax.swing.JFrame {

    /**
     * Creates new form UIHacerPrestamo
     */
    
    private ArrayList<Ejemplar> ejemplares;
    private List<Ejemplar> ejemplaresCombo;
    private Usuario usuario;
    private CapaNegocio negocio;
    private DefaultTableModel modeloTabla;
    
    public UIHacerPrestamo() {
        initComponents();
    }
    
    public void iniciar() {
        this.setTitle("Hacer Préstamo");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jSpinFieldHora.setMaximum(23);
        jSpinFieldHora.setMinimum(0);
        jSpinFieldMinuto.setMaximum(59);
        jSpinFieldMinuto.setMinimum(0);
        jSpinFieldSegundo.setMaximum(59);
        jSpinFieldSegundo.setMinimum(0);
        mostrarTabla();
        negocio = new CapaNegocio();
        ejemplares = new ArrayList();
        activarControlesCedula(false);
        activarControlesEjemplar(false);
        jButtonEliminar.setEnabled(false);
        this.jDateChooser.setDateFormatString("yyyy-MM-dd");
    }
    
    private void activarControlesCedula(boolean estado) {
        jComboId.setEnabled(estado);
        jTextFieldIsbn.setEditable(estado);
    }
    
    private void activarControlesEjemplar(boolean estado) {
        jButtonHacerPrestamo.setEnabled(estado);
        jDateChooser.setEnabled(estado);
        jSpinFieldHora.setEnabled(estado);
        jSpinFieldMinuto.setEnabled(estado);
        jSpinFieldSegundo.setEnabled(estado);
    }
    
    private void mostrarTabla() {
        String [][] roster = {};
        String [] columnas = {"ISBN", "Id", "Observaciones"};
        this.modeloTabla = new DefaultTableModel(roster, columnas);
        this.jTableEjemplares.setModel(modeloTabla);
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
            this.modeloTabla.setValueAt(ejemplares.get(j).getObservaciones(), this.modeloTabla.getRowCount() - 1, 2);
        }
    }
    
    private boolean validarIsbn(String cadena) {
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
    
    private boolean validarCedula(String cadena) {
        char[] charArray = cadena.toCharArray();
        if (charArray.length != 10) {
            return false;
        }
        for(char c : charArray) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
    
    private void cargarComboId() {
        ejemplaresCombo = negocio.consultarEjemplares(this.jTextFieldIsbn.getText());
        if (!ejemplaresCombo.isEmpty()) {
            this.jComboId.removeAllItems();
            ejemplaresCombo.stream().filter((ejemplar) -> (ejemplar.getPrestado() == 0)).forEachOrdered((ejemplar) -> {
                this.jComboId.addItem(String.valueOf(ejemplar.getId()));
            });
            ejemplaresCombo.clear(); 
            jButtonAgregar.setEnabled(true);
        } 
        if (this.jComboId.getItemCount() == 0) { 
            this.jLabelIsbn.setText("* No hay ejemplares disponibles para esta edición.");
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

        jSpinField3 = new com.toedter.components.JSpinField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIsbn = new javax.swing.JTextField();
        jComboId = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabelIsbn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEjemplares = new javax.swing.JTable();
        jButtonEliminar = new javax.swing.JButton();
        jButtonHacerPrestamo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCedula = new javax.swing.JTextField();
        jButtonNuevoUsuario = new javax.swing.JButton();
        jLabelCedula = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jSpinFieldHora = new com.toedter.components.JSpinField();
        jSpinFieldMinuto = new com.toedter.components.JSpinField();
        jSpinFieldSegundo = new com.toedter.components.JSpinField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("ISBN:");

        jTextFieldIsbn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldIsbnKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Id:");

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Ingrese los ejemplares a prestar:");

        jLabelIsbn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelIsbn.setForeground(new java.awt.Color(255, 0, 51));

        jTableEjemplares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableEjemplares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEjemplaresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEjemplares);

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonHacerPrestamo.setText("Realizar Préstamo");
        jButtonHacerPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHacerPrestamoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Cédula de Usuario:");

        jTextFieldCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCedulaKeyReleased(evt);
            }
        });

        jButtonNuevoUsuario.setText("Nuevo Usuario");
        jButtonNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoUsuarioActionPerformed(evt);
            }
        });

        jLabelCedula.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelCedula.setForeground(new java.awt.Color(255, 0, 51));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Hora de Entrega Estimada:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Fecha de Entrega Estimada:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText(":");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinFieldMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinFieldSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonHacerPrestamo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelCedula)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextFieldCedula)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelIsbn)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 2, Short.MAX_VALUE)))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonAgregar)
                            .addComponent(jButtonNuevoUsuario)))
                    .addComponent(jScrollPane1))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNuevoUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCedula)
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAgregar)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelIsbn)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jSpinFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinFieldMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinFieldSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonHacerPrestamo))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCedulaKeyReleased
        if(validarCedula(this.jTextFieldCedula.getText())) {
            List<Usuario> usuarios = negocio.consultarUsuario(jTextFieldCedula.getText());
            if (usuarios.isEmpty()) {
                this.jLabelCedula.setText("* Ingrese una cédula válida.");
                activarControlesCedula(false);
            } else {
                if (usuarios.get(0).getPuede_prestamo() == 1) {
                    usuario = usuarios.get(0);
                    this.jLabelCedula.setText("");
                    activarControlesCedula(true);
                } else {
                    this.jLabelCedula.setText("* El usuario ya tiene un préstamo pendiente.");
                    activarControlesCedula(false);
                } 
            }
        } else {
            this.jLabelCedula.setText("* Una cédula tiene 10 dígitos.");
            activarControlesCedula(false);
        }
    }//GEN-LAST:event_jTextFieldCedulaKeyReleased

    private void jButtonNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoUsuarioActionPerformed
        UIUsuarios ui = new UIUsuarios();
        ui.iniciar();
    }//GEN-LAST:event_jButtonNuevoUsuarioActionPerformed

    private void jTextFieldIsbnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIsbnKeyReleased
        if(validarIsbn(this.jTextFieldIsbn.getText())) {
            List<Edicion> ediciones = negocio.consultarEdicion(this.jTextFieldIsbn.getText());
            if (ediciones.isEmpty()) {
                this.jLabelIsbn.setText("* Ingrese un ISBN válido.");
                this.jComboId.removeAllItems();
                jButtonAgregar.setEnabled(false);
            } else {
                this.jLabelIsbn.setText("");
                this.cargarComboId();
            }
        } else {
            this.jLabelIsbn.setText("* Un ISBN tiene 13 dígitos.");
            this.jComboId.removeAllItems();
            jButtonAgregar.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldIsbnKeyReleased

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        if (ejemplares.isEmpty()) {
            activarControlesEjemplar(true);
        }
        try {
            List<Ejemplar> ejemplar = negocio.consultarEjemplar(this.jTextFieldIsbn.getText(), Integer.parseInt(this.jComboId.getSelectedItem().toString()));
            ejemplares.add(ejemplar.get(0));
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el ejemplar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.cargarDatos();
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        ejemplares.remove(this.jTableEjemplares.getSelectedRow());
        jButtonEliminar.setEnabled(false);
        if (ejemplares.isEmpty()) {
            activarControlesEjemplar(false);
        }
        this.cargarDatos();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonHacerPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHacerPrestamoActionPerformed
        try {
            GregorianCalendar calendar = (GregorianCalendar) this.jDateChooser.getCalendar();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            fmt.setCalendar(calendar);
            String fecha = fmt.format(calendar.getTime());
            List<JSpinField> arregloHora = new ArrayList();
            arregloHora.add(this.jSpinFieldHora);
            arregloHora.add(this.jSpinFieldMinuto);
            arregloHora.add(this.jSpinFieldSegundo);
            List<String> hora = new ArrayList();
            arregloHora.stream().map((spin) -> spin.getValue()).map((valor) -> {
                String hhmmss;
                if (valor < 10) {
                    hhmmss = "0" + String.valueOf(valor);
                } else {
                    hhmmss = String.valueOf(valor);
                }
                return hhmmss;
            }).forEachOrdered((hhmmss) -> {
                hora.add(hhmmss);
            });
            fecha += " " + hora.get(0) + ":" + hora.get(1) + ":" + hora.get(2);
            String resultado = negocio.hacerPrestamo(usuario, ejemplares, fecha);
            switch (resultado) {
                case "OK":
                    JOptionPane.showMessageDialog(this, "Préstamo efectuado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    break;
                case "U":
                    JOptionPane.showMessageDialog(this, "No se pudo hacer el préstamo. El usuario tiene un préstamo pendiente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "No se pudo hacer el préstamo. Hay un libro que ya está prestado: " + resultado, "Advertencia", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo efectuar el préstamo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonHacerPrestamoActionPerformed

    private void jTableEjemplaresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEjemplaresMouseClicked
        jButtonEliminar.setEnabled(true);
    }//GEN-LAST:event_jTableEjemplaresMouseClicked

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
            java.util.logging.Logger.getLogger(UIHacerPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIHacerPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIHacerPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIHacerPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIHacerPrestamo().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonHacerPrestamo;
    private javax.swing.JButton jButtonNuevoUsuario;
    private javax.swing.JComboBox<String> jComboId;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCedula;
    private javax.swing.JLabel jLabelIsbn;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.components.JSpinField jSpinField3;
    private com.toedter.components.JSpinField jSpinFieldHora;
    private com.toedter.components.JSpinField jSpinFieldMinuto;
    private com.toedter.components.JSpinField jSpinFieldSegundo;
    private javax.swing.JTable jTableEjemplares;
    private javax.swing.JTextField jTextFieldCedula;
    private javax.swing.JTextField jTextFieldIsbn;
    // End of variables declaration//GEN-END:variables
}
