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
import negocio.Planta;
import negocio.Estante;

/**
 *
 * @author USUARIO
 */
public class UILogistica extends javax.swing.JFrame {

    /**
     * Creates new form UIUsuarios
     */
    
    private DefaultTableModel modeloTablaPlanta;
    private List<Planta> plantas;
    private Planta planta;
    private boolean opcionPlanta;
    private DefaultTableModel modeloTablaEstante;
    private List<Estante> estantes;
    private Estante estante;
    private boolean opcionEstante;
    private CapaNegocio negocio;
    
    public UILogistica() {
        initComponents();
    }
    
    public void iniciar() {
        this.setTitle("Gestión de Logística");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        activarTextosPlanta(false);
        activarBotonesPlanta(true);
        activarTextosEstante(false);
        activarBotonesEstante(true);
        mostrarTablas();
        initCombos();
        negocio = new CapaNegocio();
        jTextFieldIdPlanta.setEditable(false);
        jTextFieldIdEstante.setEditable(false);
        this.jTableEstantes.setEnabled(false);
        this.jTablePlantas.setEnabled(false);
        cargarComboPlanta();
    }
    
    private void activarTextosPlanta(Boolean estado) {
        jTextFieldNombrePlanta.setEditable(estado);
    }
    
    private void activarTextosEstante(Boolean estado) {
        jTextFieldNombreEstante.setEditable(estado);
    }
    
    private void activarBotonesPlanta(Boolean estado) {
        jButtonNuevoPlanta.setEnabled(estado);
        jButtonActualizarPlanta.setEnabled(estado);
        jButtonEliminarPlanta.setEnabled(estado);
        jButtonGrabarPlanta.setEnabled(!estado);
        jButtonCancelarPlanta.setEnabled(!estado);
    }
    
    private void activarBotonesEstante(Boolean estado) {
        jButtonNuevoEstante.setEnabled(estado);
        jButtonActualizarEstante.setEnabled(estado);
        jButtonEliminarEstante.setEnabled(estado);
        jButtonGrabarEstante.setEnabled(!estado);
        jButtonCancelarEstante.setEnabled(!estado);
    }
    
    private void vaciarTextosPlanta() {
        jTextFieldIdPlanta.setText("");
        jTextFieldNombrePlanta.setText("");
    }
    
    private void vaciarTextosEstante() {
        jTextFieldIdEstante.setText("");
        jTextFieldNombreEstante.setText("");
        jComboPlanta.setSelectedItem(jComboPlanta.getItemAt(0));
    }
    
    public void initCombos() {
        jComboOrdenPlanta.addItem("Id");
        jComboOrdenPlanta.addItem("Nombre");
        jComboOrdenEstante.addItem("Planta (Id)");
        jComboOrdenEstante.addItem("Id");
        jComboOrdenEstante.addItem("Nombre");
    }
    
    public void cargarComboPlanta() {
        jComboPlanta.removeAllItems();
        plantas = negocio.todasPlantas(0);
        plantas.forEach((plantaCombo) -> {
            jComboPlanta.addItem(String.valueOf(plantaCombo.getId()));
        });
        plantas.clear();
    }
    
    private void mostrarTablas() {
        String [][] roster = {};
        String [] columnasPlanta = {"Id", "Nombre"};
        this.modeloTablaPlanta = new DefaultTableModel(roster, columnasPlanta);
        jTablePlantas.setModel(modeloTablaPlanta);
        String [] columnasEstante = {"Planta (Id)", "Id", "Nombre"};
        this.modeloTablaEstante = new DefaultTableModel(roster, columnasEstante);
        jTableEstantes.setModel(modeloTablaEstante);
    }
    
    private void cargarDatosPlanta() {
        int rows = this.modeloTablaPlanta.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            this.modeloTablaPlanta.removeRow(i);
        }
        for (int j = 0; j < plantas.size(); j++) {
            this.modeloTablaPlanta.insertRow(this.modeloTablaPlanta.getRowCount(), new Object[]{});
            this.modeloTablaPlanta.setValueAt(String.valueOf(plantas.get(j).getId()), this.modeloTablaPlanta.getRowCount() - 1, 0);
            this.modeloTablaPlanta.setValueAt(plantas.get(j).getNombre(), this.modeloTablaPlanta.getRowCount() - 1, 1);
        }
    }
    
    private void cargarDatosEstante() {
        int rows = this.modeloTablaEstante.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            this.modeloTablaEstante.removeRow(i);
        }
        for (int j = 0; j < estantes.size(); j++) {
            this.modeloTablaEstante.insertRow(this.modeloTablaEstante.getRowCount(), new Object[]{});
            this.modeloTablaEstante.setValueAt(String.valueOf(estantes.get(j).getPlanta_id()), this.modeloTablaEstante.getRowCount() - 1, 0);
            this.modeloTablaEstante.setValueAt(String.valueOf(estantes.get(j).getId()), this.modeloTablaEstante.getRowCount() - 1, 1);
            this.modeloTablaEstante.setValueAt(estantes.get(j).getNombre(), this.modeloTablaEstante.getRowCount() - 1, 2);
        }
    }
    
    private boolean esAlfaNumerico(String cadena) {
        char[] charArray = cadena.toCharArray();
        for(char c : charArray) {
            if (!(Character.isLetterOrDigit(c) || c == ' '))
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
        jTextFieldIdPlanta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNombrePlanta = new javax.swing.JTextField();
        jButtonNuevoPlanta = new javax.swing.JButton();
        jButtonActualizarPlanta = new javax.swing.JButton();
        jButtonGrabarPlanta = new javax.swing.JButton();
        jButtonCancelarPlanta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePlantas = new javax.swing.JTable();
        jButtonBuscarPlanta = new javax.swing.JButton();
        jTextFieldBuscarPlanta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonMostrarTodosPlanta = new javax.swing.JButton();
        jComboOrdenPlanta = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButtonGrabarEstante = new javax.swing.JButton();
        jButtonCancelarEstante = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEstantes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButtonBuscarEstante = new javax.swing.JButton();
        jTextFieldIdEstante = new javax.swing.JTextField();
        jTextFieldBuscarEstante = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldNombreEstante = new javax.swing.JTextField();
        jButtonMostrarTodosEstante = new javax.swing.JButton();
        jButtonNuevoEstante = new javax.swing.JButton();
        jComboOrdenEstante = new javax.swing.JComboBox<>();
        jButtonActualizarEstante = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButtonEliminarEstante = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboPlanta = new javax.swing.JComboBox<>();
        jButtonEliminarPlanta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Plantas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Id:");

        jTextFieldIdPlanta.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nombre:");

        jButtonNuevoPlanta.setText("Nuevo");
        jButtonNuevoPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoPlantaActionPerformed(evt);
            }
        });

        jButtonActualizarPlanta.setText("Actualizar");
        jButtonActualizarPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarPlantaActionPerformed(evt);
            }
        });

        jButtonGrabarPlanta.setText("Grabar");
        jButtonGrabarPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGrabarPlantaActionPerformed(evt);
            }
        });

        jButtonCancelarPlanta.setText("Cancelar");
        jButtonCancelarPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarPlantaActionPerformed(evt);
            }
        });

        jTablePlantas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTablePlantas);

        jButtonBuscarPlanta.setText("Buscar");
        jButtonBuscarPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPlantaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel6.setText("Buscar por Id o Nombre");

        jButtonMostrarTodosPlanta.setText("Mostrar Todas");
        jButtonMostrarTodosPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarTodosPlantaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel7.setText("Ordenar por");

        jButtonGrabarEstante.setText("Grabar");
        jButtonGrabarEstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGrabarEstanteActionPerformed(evt);
            }
        });

        jButtonCancelarEstante.setText("Cancelar");
        jButtonCancelarEstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarEstanteActionPerformed(evt);
            }
        });

        jTableEstantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableEstantes);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Id:");

        jButtonBuscarEstante.setText("Buscar");
        jButtonBuscarEstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarEstanteActionPerformed(evt);
            }
        });

        jTextFieldIdEstante.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nombre:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel8.setText("Buscar por Id de Planta, Id o Nombre");

        jButtonMostrarTodosEstante.setText("Mostrar Todos");
        jButtonMostrarTodosEstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarTodosEstanteActionPerformed(evt);
            }
        });

        jButtonNuevoEstante.setText("Nuevo");
        jButtonNuevoEstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoEstanteActionPerformed(evt);
            }
        });

        jButtonActualizarEstante.setText("Actualizar");
        jButtonActualizarEstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarEstanteActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel9.setText("Ordenar por");

        jButtonEliminarEstante.setText("Eliminar");
        jButtonEliminarEstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarEstanteActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Estantes");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Planta (Id):");

        jButtonEliminarPlanta.setText("Eliminar");
        jButtonEliminarPlanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarPlantaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldNombreEstante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(jTextFieldIdEstante)
                                    .addComponent(jComboPlanta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonActualizarEstante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonNuevoEstante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonEliminarEstante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldBuscarEstante, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboOrdenEstante, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonBuscarEstante, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonCancelarEstante, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonGrabarEstante, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(202, 202, 202))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldBuscarPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboOrdenPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonBuscarPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButtonMostrarTodosPlanta)
                                        .addGap(226, 226, 226))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldIdPlanta)
                                            .addComponent(jTextFieldNombrePlanta))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButtonNuevoPlanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButtonActualizarPlanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jButtonEliminarPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButtonCancelarPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonGrabarPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(189, 189, 189)))))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonMostrarTodosEstante)
                        .addGap(253, 253, 253))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonBuscarPlanta)
                            .addComponent(jTextFieldBuscarPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboOrdenPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonMostrarTodosPlanta))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldIdPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonNuevoPlanta))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldNombrePlanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonActualizarPlanta))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEliminarPlanta)
                            .addComponent(jButtonCancelarPlanta)
                            .addComponent(jButtonGrabarPlanta))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jComboPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldIdEstante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldNombreEstante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCancelarEstante)
                            .addComponent(jButtonGrabarEstante))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNuevoEstante)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonActualizarEstante)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarEstante)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscarEstante)
                    .addComponent(jTextFieldBuscarEstante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboOrdenEstante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonMostrarTodosEstante)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoPlantaActionPerformed
        this.activarBotonesPlanta(false);
        this.activarTextosPlanta(true);
        opcionPlanta = true;
        this.jTextFieldIdPlanta.setText(String.valueOf(negocio.proximoID(Planta.class, null)));
    }//GEN-LAST:event_jButtonNuevoPlantaActionPerformed

    private void jButtonActualizarPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarPlantaActionPerformed
        try {
            int idActualizar = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el Id de la planta a mofificar: "));
            plantas = negocio.consultarPlanta(idActualizar);
            if (!plantas.isEmpty()) {
                planta = plantas.get(0);
                plantas.clear();
                JOptionPane.showMessageDialog(this, "Modifique los datos de la planta en la ventana principal y luego presione Grabar.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                this.activarBotonesPlanta(false);
                this.activarTextosPlanta(true);
                jTextFieldIdPlanta.setText(String.valueOf(planta.getId()));
                jTextFieldNombrePlanta.setText(planta.getNombre());
                opcionPlanta = false;
            } else {
                JOptionPane.showMessageDialog(this, "No existe una planta con el Id ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            if (e.getClass().equals(NumberFormatException.class)) {
                JOptionPane.showMessageDialog(this, "El Id debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo recuperar la planta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonActualizarPlantaActionPerformed

    private void jButtonGrabarPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGrabarPlantaActionPerformed
        try {
            if (esAlfaNumerico(jTextFieldNombrePlanta.getText())) {
                if (opcionPlanta) {
                    negocio.insertar(new Planta(Integer.parseInt(jTextFieldIdPlanta.getText()), jTextFieldNombrePlanta.getText()));
                    cargarComboPlanta();
                    JOptionPane.showMessageDialog(this, "Planta ingresada con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    planta.setNombre(jTextFieldNombrePlanta.getText());
                    negocio.actualizar(planta);
                    JOptionPane.showMessageDialog(this, "Planta actualizada con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                }
                this.activarBotonesPlanta(true);
                this.activarTextosPlanta(false);
                this.vaciarTextosPlanta();
            } else {
                JOptionPane.showMessageDialog(this, "Los datos no deben contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "La planta no fue grabada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGrabarPlantaActionPerformed

    private void jButtonCancelarPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarPlantaActionPerformed
        this.activarBotonesPlanta(true);
        this.activarTextosPlanta(false);
        this.vaciarTextosPlanta();
    }//GEN-LAST:event_jButtonCancelarPlantaActionPerformed

    private void jButtonBuscarPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPlantaActionPerformed
        try {
            String busqueda = jTextFieldBuscarPlanta.getText();
            if (esAlfaNumerico(busqueda) && !busqueda.isEmpty()) {
                plantas = negocio.buscarPlantas(busqueda, jComboOrdenPlanta.getSelectedIndex());
                if (!plantas.isEmpty()) {
                    this.cargarDatosPlanta();
                    plantas.clear();
                } else {
                    JOptionPane.showMessageDialog(this, "No existen resultados para la búsqueda.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                }   
            } else {
                JOptionPane.showMessageDialog(this, "La búsqueda no debe contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }       
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de plantas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonBuscarPlantaActionPerformed

    private void jButtonMostrarTodosPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarTodosPlantaActionPerformed
        try {
            plantas = negocio.todasPlantas(jComboOrdenPlanta.getSelectedIndex());
            this.cargarDatosPlanta();
            plantas.clear();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de plantas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonMostrarTodosPlantaActionPerformed

    private void jButtonGrabarEstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGrabarEstanteActionPerformed
        try {
            if (esAlfaNumerico(jTextFieldNombreEstante.getText())) {
                int id_planta = Integer.parseInt(jComboPlanta.getSelectedItem().toString());
                if (opcionEstante) {
                    negocio.insertar(new Estante(id_planta, Integer.parseInt(jTextFieldIdEstante.getText()), jTextFieldNombreEstante.getText()));
                    JOptionPane.showMessageDialog(this, "Estante ingresado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    estante.setNombre(jTextFieldNombreEstante.getText());
                    negocio.actualizar(estante);
                    JOptionPane.showMessageDialog(this, "Estante actualizado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                }
                this.activarBotonesEstante(true);
                this.activarTextosEstante(false);
                this.vaciarTextosEstante();
            } else {
                JOptionPane.showMessageDialog(this, "Los datos no deben contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "El estante no fue grabado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGrabarEstanteActionPerformed

    private void jButtonCancelarEstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarEstanteActionPerformed
        this.activarBotonesEstante(true);
        this.activarTextosEstante(false);
        this.vaciarTextosEstante();
    }//GEN-LAST:event_jButtonCancelarEstanteActionPerformed

    private void jButtonBuscarEstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarEstanteActionPerformed
        try {
            String busqueda = jTextFieldBuscarEstante.getText();
            if (esAlfaNumerico(busqueda) && !busqueda.isEmpty()) {
                estantes = negocio.buscarEstantes(busqueda, jComboOrdenEstante.getSelectedIndex());
                if (!estantes.isEmpty()) {
                    this.cargarDatosEstante();
                    estantes.clear();
                } else {
                    JOptionPane.showMessageDialog(this, "No existen resultados para la búsqueda.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                }   
            } else {
                JOptionPane.showMessageDialog(this, "La búsqueda no debe contener caracteres especiales.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }       
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de estantes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonBuscarEstanteActionPerformed

    private void jButtonMostrarTodosEstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarTodosEstanteActionPerformed
        try {
            estantes = negocio.todosEstantes(jComboOrdenEstante.getSelectedIndex());
            this.cargarDatosEstante();
            estantes.clear();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar la lista de estantes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonMostrarTodosEstanteActionPerformed

    private void jButtonEliminarEstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarEstanteActionPerformed
        try {
            int idEliminar = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el id del estante a eliminar: "));
            int idPlanta = Integer.parseInt(jComboPlanta.getSelectedItem().toString());
             estantes = negocio.consultarEstante(idPlanta, idEliminar);
            if (!estantes.isEmpty()) {
                estante = estantes.get(0);
                estantes.clear();
                negocio.eliminar(estante);
                JOptionPane.showMessageDialog(this, "Estante eliminado con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                this.vaciarTextosEstante();
            } else {
                JOptionPane.showMessageDialog(this, "En la planta indicada no existe un estante con el Id ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            if (e.getClass().equals(NumberFormatException.class)) {
                JOptionPane.showMessageDialog(this, "El Id debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo recuperar el estante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonEliminarEstanteActionPerformed

    private void jButtonActualizarEstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarEstanteActionPerformed
        try {
            int idActualizar = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el Id del estante a mofificar: "));
            int idPlanta = Integer.parseInt(jComboPlanta.getSelectedItem().toString());
            estantes = negocio.consultarEstante(idPlanta, idActualizar);
            if (!estantes.isEmpty()) {
                estante = estantes.get(0);
                estantes.clear();
                JOptionPane.showMessageDialog(this, "Modifique los datos del estante en la ventana principal y luego presione Grabar.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                this.activarBotonesEstante(false);
                this.activarTextosEstante(true);
                jTextFieldIdEstante.setText(String.valueOf(estante.getId()));
                jTextFieldNombreEstante.setText(estante.getNombre());
                opcionEstante = false;
            } else {
                JOptionPane.showMessageDialog(this, "En la planta indicada no existe un estante con el Id ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            if (e.getClass().equals(NumberFormatException.class)) {
                JOptionPane.showMessageDialog(this, "El Id debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo recuperar el estante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonActualizarEstanteActionPerformed

    private void jButtonNuevoEstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoEstanteActionPerformed
        int id_planta = Integer.parseInt(jComboPlanta.getSelectedItem().toString());
        this.activarBotonesEstante(false);
        this.activarTextosEstante(true);
        opcionEstante = true;
        this.jTextFieldIdEstante.setText(String.valueOf(negocio.proximoID(Estante.class, id_planta)));
    }//GEN-LAST:event_jButtonNuevoEstanteActionPerformed

    private void jButtonEliminarPlantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarPlantaActionPerformed
        try {
            int idEliminar = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el id de la planta a eliminar: "));
            plantas = negocio.consultarPlanta(idEliminar);
            if (!plantas.isEmpty()) {
                planta = plantas.get(0);
                plantas.clear();
                negocio.eliminar(planta);
                cargarComboPlanta();
                JOptionPane.showMessageDialog(this, "Planta eliminada con éxito.", "OK", JOptionPane.INFORMATION_MESSAGE);
                this.vaciarTextosPlanta();
            } else {
                JOptionPane.showMessageDialog(this, "No existe una planta con el Id ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            if (e.getClass().equals(NumberFormatException.class)) {
                JOptionPane.showMessageDialog(this, "El Id debe ser un entero", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo recuperar la planta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonEliminarPlantaActionPerformed

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
    private javax.swing.JButton jButtonActualizarEstante;
    private javax.swing.JButton jButtonActualizarPlanta;
    private javax.swing.JButton jButtonBuscarEstante;
    private javax.swing.JButton jButtonBuscarPlanta;
    private javax.swing.JButton jButtonCancelarEstante;
    private javax.swing.JButton jButtonCancelarPlanta;
    private javax.swing.JButton jButtonEliminarEstante;
    private javax.swing.JButton jButtonEliminarPlanta;
    private javax.swing.JButton jButtonGrabarEstante;
    private javax.swing.JButton jButtonGrabarPlanta;
    private javax.swing.JButton jButtonMostrarTodosEstante;
    private javax.swing.JButton jButtonMostrarTodosPlanta;
    private javax.swing.JButton jButtonNuevoEstante;
    private javax.swing.JButton jButtonNuevoPlanta;
    private javax.swing.JComboBox<String> jComboOrdenEstante;
    private javax.swing.JComboBox<String> jComboOrdenPlanta;
    private javax.swing.JComboBox<String> jComboPlanta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEstantes;
    private javax.swing.JTable jTablePlantas;
    private javax.swing.JTextField jTextFieldBuscarEstante;
    private javax.swing.JTextField jTextFieldBuscarPlanta;
    private javax.swing.JTextField jTextFieldIdEstante;
    private javax.swing.JTextField jTextFieldIdPlanta;
    private javax.swing.JTextField jTextFieldNombreEstante;
    private javax.swing.JTextField jTextFieldNombrePlanta;
    // End of variables declaration//GEN-END:variables
}
