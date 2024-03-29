/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.CapaNegocio;
import negocio.Alquiler;

/**
 *
 * @author USUARIO
 */
public class UIPrestamos extends javax.swing.JFrame {

    /**
     * Creates new form UIUsuarios
     */
    
    private DefaultTableModel modeloTabla;
    private List<Alquiler> alquileres;
    private CapaNegocio negocio;
    
    
    
    public UIPrestamos(CapaNegocio capaNegocio) {
        initComponents();
        this.negocio = capaNegocio;
    }
    
    public void iniciar() {
        this.setTitle("Gestión de Préstamos");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        mostrarTabla();
        initCombo();
        this.jTableEdiciones.setEnabled(false);
    }
    
    public void initCombo() {
        jComboOrden.addItem("Cédula");
        jComboOrden.addItem("ISBN");
        jComboOrden.addItem("Ejemplar (Id)");
        jComboOrden.addItem("FH Préstamo");
        jComboOrden.addItem("FH Estimada");
        jComboOrden.addItem("FH Entrega");
    }
    
    private void mostrarTabla() {
        String [][] roster = {};
        String [] columnas = {"Cédula", "ISBN", "Ejemplar (Id)", "FH Préstamo", "FH Estimada", "FH Entrega"};
        this.modeloTabla = new DefaultTableModel(roster, columnas);
        jTableEdiciones.setModel(modeloTabla);
    }
    
    private void cargarDatos() {
        int rows = this.modeloTabla.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            this.modeloTabla.removeRow(i);
        }
        for (int j = 0; j < alquileres.size(); j++) {
            this.modeloTabla.insertRow(this.modeloTabla.getRowCount(), new Object[]{});
            this.modeloTabla.setValueAt(alquileres.get(j).getUsuario_cedula(), this.modeloTabla.getRowCount() - 1, 0);
            this.modeloTabla.setValueAt(alquileres.get(j).getEdicion_isbn(), this.modeloTabla.getRowCount() - 1, 1);
            this.modeloTabla.setValueAt(String.valueOf(alquileres.get(j).getEjemplar_id()), this.modeloTabla.getRowCount() - 1, 2);
            this.modeloTabla.setValueAt(alquileres.get(j).getFecha_hora_prestamo(), this.modeloTabla.getRowCount() - 1, 3);
            this.modeloTabla.setValueAt(alquileres.get(j).getFecha_hora_estimada_entrega(), this.modeloTabla.getRowCount() - 1, 4);
            this.modeloTabla.setValueAt(alquileres.get(j).getFecha_hora_entrega(), this.modeloTabla.getRowCount() - 1, 5);
        }
    }
    
    private boolean esAlfaNumerico(String cadena) {
        int tipo = negocio.tipoDato(cadena);
        if (tipo == 1 ){
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEdiciones = new javax.swing.JTable();
        jButtonBuscar = new javax.swing.JButton();
        jTextFieldBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonMostrarTodos = new javax.swing.JButton();
        jComboOrden = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButtonDevolverPrestamo = new javax.swing.JButton();
        jButtonHacerPrestamo = new javax.swing.JButton();
        jButtonVerUsuarios = new javax.swing.JButton();
        jButtonVerEjemplares = new javax.swing.JButton();
        jButtonBorrarPrestamo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

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
        jLabel6.setText("Buscar por Cédula, ISBN o Fecha");

        jButtonMostrarTodos.setText("Mostrar Todos");
        jButtonMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarTodosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel7.setText("Ordenar por");

        jButtonDevolverPrestamo.setText("Devolver Préstamo");
        jButtonDevolverPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDevolverPrestamoActionPerformed(evt);
            }
        });

        jButtonHacerPrestamo.setText("Hacer Préstamo");
        jButtonHacerPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHacerPrestamoActionPerformed(evt);
            }
        });

        jButtonVerUsuarios.setText("Ver Usuarios");
        jButtonVerUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerUsuariosActionPerformed(evt);
            }
        });

        jButtonVerEjemplares.setText("Ver Ejemplares");
        jButtonVerEjemplares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerEjemplaresActionPerformed(evt);
            }
        });

        jButtonBorrarPrestamo.setText("Borrar Préstamo");
        jButtonBorrarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarPrestamoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldBuscar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jComboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButtonHacerPrestamo)
                        .addGap(115, 115, 115)
                        .addComponent(jButtonBorrarPrestamo)
                        .addGap(120, 120, 120)
                        .addComponent(jButtonDevolverPrestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                        .addGap(85, 85, 85))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jButtonVerUsuarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVerEjemplares)
                        .addGap(108, 108, 108)))
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jButtonMostrarTodos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDevolverPrestamo)
                    .addComponent(jButtonHacerPrestamo)
                    .addComponent(jButtonBorrarPrestamo))
                .addGap(48, 48, 48)
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
                .addGap(28, 28, 28)
                .addComponent(jButtonMostrarTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVerUsuarios)
                    .addComponent(jButtonVerEjemplares))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            String busqueda = jTextFieldBuscar.getText();
            if (esAlfaNumerico(busqueda) && !busqueda.isEmpty()) {
                alquileres = negocio.buscarAlquileres(busqueda, jComboOrden.getSelectedIndex());
                if (!alquileres.isEmpty()) {
                    this.cargarDatos();
                    alquileres.clear();
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
            alquileres = negocio.todosAlquileres(jComboOrden.getSelectedIndex());
            this.cargarDatos();
            alquileres.clear();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de ejemplares: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonMostrarTodosActionPerformed
/*
    private void jButtonVerEdicjButtonHacerPrestamod(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerEdicionesActionPerformed

    }//GEN-LAST:event_jButtonVerEdicionesActionPerformed
*/
    private void jButtonDevolverPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDevolverPrestamoActionPerformed
        UIDevolverPrestamo ui = new UIDevolverPrestamo(negocio);
        ui.iniciar();
    }//GEN-LAST:event_jButtonDevolverPrestamoActionPerformed

    private void jButtonHacerPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHacerPrestamoActionPerformed
        UIHacerPrestamo ui = new UIHacerPrestamo(negocio);
        ui.iniciar();
    }//GEN-LAST:event_jButtonHacerPrestamoActionPerformed

    private void jButtonVerUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerUsuariosActionPerformed
        UIUsuarios ui = new UIUsuarios(negocio);
        ui.iniciar();
    }//GEN-LAST:event_jButtonVerUsuariosActionPerformed

    private void jButtonVerEjemplaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerEjemplaresActionPerformed
        UIEjemplares ui = new UIEjemplares(negocio);
        ui.iniciar();
        /*UIEjemplaresAlternativa ui = new UIEjemplaresAlternativa();
        ui.iniciar();*/
    }//GEN-LAST:event_jButtonVerEjemplaresActionPerformed

    private void jButtonBorrarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarPrestamoActionPerformed
        UIBorrarPrestamo ui = new UIBorrarPrestamo(negocio);
        ui.iniciar();
    }//GEN-LAST:event_jButtonBorrarPrestamoActionPerformed

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
    private javax.swing.JButton jButtonBorrarPrestamo;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonDevolverPrestamo;
    private javax.swing.JButton jButtonHacerPrestamo;
    private javax.swing.JButton jButtonMostrarTodos;
    private javax.swing.JButton jButtonVerEjemplares;
    private javax.swing.JButton jButtonVerUsuarios;
    private javax.swing.JComboBox<String> jComboOrden;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEdiciones;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
