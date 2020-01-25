/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import negocio.CapaNegocio;

/**
 *
 * @author USUARIO
 */
public class UIMenu extends javax.swing.JFrame {
    
    private CapaNegocio capaNegocio;
    String usuario;
    String contrasena;
    
    
    /**
     * Creates new form UIMenu
     */
    public UIMenu(String usuario, String contrasena) {
        initComponents();
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    UIMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void iniciar(UI_Login parentView) throws SQLException {
        capaNegocio  = new CapaNegocio(usuario,contrasena);
        try {
            capaNegocio.iniciar();
            this.setTitle("Gestión de Biblioteca");
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            JOptionPane.showMessageDialog(this, "¡Conexión Exitosa con la BD!", "OK", JOptionPane.INFORMATION_MESSAGE);
            parentView.dispose();
            String saludo = capaNegocio.mostarMensajePendiente(usuario);
            if(saludo == null){saludo = "Bienvenido SUPERUSER";}
            JOptionPane.showMessageDialog(this, saludo, "HI THERE", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException E) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con la BD.", "OK", JOptionPane.ERROR_MESSAGE);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        jMenuItemGestor = new javax.swing.JMenuItem();
        jMenuItemUsuarios = new javax.swing.JMenuItem();
        jMenuItemAutores = new javax.swing.JMenuItem();
        jMenuItemEditoriales = new javax.swing.JMenuItem();
        jMenuItemLibros = new javax.swing.JMenuItem();
        jMenuItemEdiciones = new javax.swing.JMenuItem();
        jMenuItemEjemplares = new javax.swing.JMenuItem();
        jMenuItemLogistica = new javax.swing.JMenuItem();
        jMenuItemPrestamos = new javax.swing.JMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuItemAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        jLabel1.setText("BIBLIOTECARIA");

        jLabel3.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        jLabel3.setText("SISTEMA DE GESTIÓN");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/logobib.png"))); // NOI18N

        jMenu.setText("Menú");

        jMenuItemGestor.setText("Gestor de Consultas");
        jMenuItemGestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestorActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemGestor);

        jMenuItemUsuarios.setText("Usuarios");
        jMenuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUsuariosActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemUsuarios);

        jMenuItemAutores.setText("Autores");
        jMenuItemAutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAutoresActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemAutores);

        jMenuItemEditoriales.setText("Editoriales");
        jMenuItemEditoriales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditorialesActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemEditoriales);

        jMenuItemLibros.setText("Libros");
        jMenuItemLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLibrosActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemLibros);

        jMenuItemEdiciones.setText("Ediciones");
        jMenuItemEdiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEdicionesActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemEdiciones);

        jMenuItemEjemplares.setText("Ejemplares");
        jMenuItemEjemplares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEjemplaresActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemEjemplares);

        jMenuItemLogistica.setText("Logística");
        jMenuItemLogistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogisticaActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemLogistica);

        jMenuItemPrestamos.setText("Préstamos");
        jMenuItemPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrestamosActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemPrestamos);

        jMenuBar1.add(jMenu);

        jMenuAyuda.setText("Ayuda");

        jMenuItemAcercaDe.setText("Acerca De");
        jMenuItemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAcercaDeActionPerformed(evt);
            }
        });
        jMenuAyuda.add(jMenuItemAcercaDe);

        jMenuBar1.add(jMenuAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel1)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuariosActionPerformed
        UIUsuarios ui = new UIUsuarios(capaNegocio);
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemUsuariosActionPerformed

    private void jMenuItemLogisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogisticaActionPerformed
        UILogistica ui = new UILogistica(capaNegocio);
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemLogisticaActionPerformed

    private void jMenuItemAutoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAutoresActionPerformed
        UIAutores ui = new UIAutores(capaNegocio);
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemAutoresActionPerformed

    private void jMenuItemEditorialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditorialesActionPerformed
        UIEditoriales ui = new UIEditoriales(capaNegocio);
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemEditorialesActionPerformed

    private void jMenuItemLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLibrosActionPerformed
        UILibros ui = new UILibros(capaNegocio);
        ui.iniciar();
       /*UILibrosAlternativa ui = new UILibrosAlternativa();
        ui.iniciar();*/
    }//GEN-LAST:event_jMenuItemLibrosActionPerformed

    private void jMenuItemGestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestorActionPerformed
        // TODO add your handling code here:
        CapaNegocio capaNegocioQuerys = new CapaNegocio("admin3", "1234");
        Querys gestor = new Querys(capaNegocioQuerys);
        gestor.iniciar();
    }//GEN-LAST:event_jMenuItemGestorActionPerformed

    private void jMenuItemPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrestamosActionPerformed
        UIPrestamos ui = new UIPrestamos(capaNegocio);
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemPrestamosActionPerformed

    private void jMenuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAcercaDeActionPerformed
        UIAcercaDe ui = new UIAcercaDe();
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemAcercaDeActionPerformed

    private void jMenuItemEjemplaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEjemplaresActionPerformed
        UIEjemplares ui = new UIEjemplares(capaNegocio);
        ui.iniciar();
        /*UIEjemplaresAlternativa ui = new UIEjemplaresAlternativa();
        ui.iniciar();*/
    }//GEN-LAST:event_jMenuItemEjemplaresActionPerformed

    private void jMenuItemEdicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEdicionesActionPerformed
        UIEdiciones ui = new UIEdiciones(capaNegocio);
        ui.iniciar();
        /*UIEdicionesAlternativa ui = new UIEdicionesAlternativa();
        ui.iniciar();*/
    }//GEN-LAST:event_jMenuItemEdicionesActionPerformed

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
            java.util.logging.Logger.getLogger(UIMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIMenu().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAcercaDe;
    private javax.swing.JMenuItem jMenuItemAutores;
    private javax.swing.JMenuItem jMenuItemEdiciones;
    private javax.swing.JMenuItem jMenuItemEditoriales;
    private javax.swing.JMenuItem jMenuItemEjemplares;
    private javax.swing.JMenuItem jMenuItemGestor;
    private javax.swing.JMenuItem jMenuItemLibros;
    private javax.swing.JMenuItem jMenuItemLogistica;
    private javax.swing.JMenuItem jMenuItemPrestamos;
    private javax.swing.JMenuItem jMenuItemUsuarios;
    // End of variables declaration//GEN-END:variables
}
