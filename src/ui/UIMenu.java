/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author USUARIO
 */
public class UIMenu extends javax.swing.JFrame {

    /**
     * Creates new form UIMenu
     */
    public UIMenu() {
        initComponents();
    }
    
    public void iniciar() {
        this.setTitle("Gestión de Biblioteca");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        jMenuItemGestor = new javax.swing.JMenuItem();
        jMenuItemUsuarios = new javax.swing.JMenuItem();
        jMenuItemAutores = new javax.swing.JMenuItem();
        jMenuItemEditoriales = new javax.swing.JMenuItem();
        jMenuItemLibros = new javax.swing.JMenuItem();
        jMenuItemLogistica = new javax.swing.JMenuItem();
        jMenuItemPrestamos = new javax.swing.JMenuItem();
        jAcercaDe = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        jLabel1.setText("bibliotecaria");

        jLabel3.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        jLabel3.setText("SISTEMA DE GESTIÓN");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/logobib.png"))); // NOI18N

        jMenu.setText("Menú");

        jMenuItemGestor.setText("Gestor de Consultas");
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
        jMenu.add(jMenuItemLibros);

        jMenuItemLogistica.setText("Logística");
        jMenuItemLogistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogisticaActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItemLogistica);

        jMenuItemPrestamos.setText("Préstamos");
        jMenu.add(jMenuItemPrestamos);

        jMenuBar1.add(jMenu);

        jAcercaDe.setText("Acerca De");
        jMenuBar1.add(jAcercaDe);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuariosActionPerformed
        UIUsuarios ui = new UIUsuarios();
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemUsuariosActionPerformed

    private void jMenuItemLogisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogisticaActionPerformed
        UILogistica ui = new UILogistica();
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemLogisticaActionPerformed

    private void jMenuItemAutoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAutoresActionPerformed
        UIAutores ui = new UIAutores();
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemAutoresActionPerformed

    private void jMenuItemEditorialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditorialesActionPerformed
        UIEditoriales ui = new UIEditoriales();
        ui.iniciar();
    }//GEN-LAST:event_jMenuItemEditorialesActionPerformed

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
    private javax.swing.JMenu jAcercaDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAutores;
    private javax.swing.JMenuItem jMenuItemEditoriales;
    private javax.swing.JMenuItem jMenuItemGestor;
    private javax.swing.JMenuItem jMenuItemLibros;
    private javax.swing.JMenuItem jMenuItemLogistica;
    private javax.swing.JMenuItem jMenuItemPrestamos;
    private javax.swing.JMenuItem jMenuItemUsuarios;
    // End of variables declaration//GEN-END:variables
}
