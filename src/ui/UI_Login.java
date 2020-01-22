/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import negocio.CapaNegocio;
import static ui.Querys.myFrame;

/**
 *
 * @author Estudiante
 */
public class UI_Login extends javax.swing.JFrame {

    /**
     * Creates new form UI_Login
     */
    public UI_Login() {
        initComponents();
        this.jToggleButtonAPrivilegios.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldUsuario = new javax.swing.JTextField();
        jButtonIngresar = new javax.swing.JButton();
        jButtonRegistrarse = new javax.swing.JButton();
        jTextFieldContrasena = new javax.swing.JPasswordField();
        jToggleButtonAPrivilegios = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });

        jButtonIngresar.setText("INGRESAR");
        jButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarActionPerformed(evt);
            }
        });

        jButtonRegistrarse.setText("REGISTRARSE");
        jButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarseActionPerformed(evt);
            }
        });

        jTextFieldContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldContrasenaActionPerformed(evt);
            }
        });

        jToggleButtonAPrivilegios.setText("Administrar Privilegios");
        jToggleButtonAPrivilegios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonAPrivilegiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jButtonIngresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jButtonRegistrarse))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButtonAPrivilegios))))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTextFieldContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButtonIngresar)
                .addGap(18, 18, 18)
                .addComponent(jButtonRegistrarse)
                .addGap(18, 18, 18)
                .addComponent(jToggleButtonAPrivilegios)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void iniciar() {
        this.setTitle("LOGIN");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        this.jTextFieldUsuario.addKeyListener(new KeyListener(){
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
                                if(jTextFieldUsuario.getText().equals("admin3")){
                                    jToggleButtonAPrivilegios.setEnabled(true);
                                }else{
                                    jToggleButtonAPrivilegios.setEnabled(false);
                                }
                            }
                        });
                        
    }
    
    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

    private void jButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarActionPerformed
        
    }//GEN-LAST:event_jButtonIngresarActionPerformed

    private void jButtonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarseActionPerformed
        // TODO add your handling code here:
        UI_RegistrarUsuario ui = new UI_RegistrarUsuario();
        ui.iniciar();
        
    }//GEN-LAST:event_jButtonRegistrarseActionPerformed

    private void jTextFieldContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldContrasenaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextFieldContrasenaActionPerformed

    private void jToggleButtonAPrivilegiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAPrivilegiosActionPerformed
        // TODO add your handling code here:
        String nombreUsuario = jTextFieldUsuario.getText() ;
        String contrasena = jTextFieldContrasena.getText() ;
        //UIAsignarPrivilegios ui = new UIAsignarPrivilegios(nombreUsuario,contrasena);
        //ui.iniciar();
    }//GEN-LAST:event_jToggleButtonAPrivilegiosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonIngresar;
    public javax.swing.JButton jButtonRegistrarse;
    private javax.swing.JPasswordField jTextFieldContrasena;
    public javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JToggleButton jToggleButtonAPrivilegios;
    // End of variables declaration//GEN-END:variables
}
