/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Aplicacion.ServicioTelefono;
import Aplicacion.ServicioUsuario;

/**
 *
 * @author CCNAR
 */
public class ModuloTelefono extends javax.swing.JFrame {

    /**
     * Creates new form ModuloTelefono
     */
    public ModuloTelefono() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.btn_eliminar_tel.setVisible(false);
        this.btn_modificar_tel.setVisible(false);
        this.btn_nuevo_tel.setVisible(false);
        this.setTitle("Usuarios");
        ServicioTelefono servUser = new ServicioTelefono(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_telefono = new javax.swing.JTable();
        btn_crear_tel = new javax.swing.JButton();
        btn_modificar_tel = new javax.swing.JButton();
        btn_eliminar_tel = new javax.swing.JButton();
        btn_nuevo_tel = new javax.swing.JButton();
        btn_atras = new javax.swing.JButton();
        txt_cant = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Constantia", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Números Teléfonicos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 290, -1));

        jLabel3.setFont(new java.awt.Font("Constantia", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Teléfono:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 120, 30));

        txt_telefono.setFont(new java.awt.Font("Constantia", 2, 18)); // NOI18N
        jPanel1.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 170, 30));

        tb_telefono.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Teléfono"
            }
        ));
        jScrollPane2.setViewportView(tb_telefono);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 430, 240));

        btn_crear_tel.setBackground(new java.awt.Color(51, 51, 51));
        btn_crear_tel.setFont(new java.awt.Font("Constantia", 2, 12)); // NOI18N
        btn_crear_tel.setForeground(new java.awt.Color(255, 255, 255));
        btn_crear_tel.setText("Crear");
        jPanel1.add(btn_crear_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 100, -1));

        btn_modificar_tel.setBackground(new java.awt.Color(51, 51, 51));
        btn_modificar_tel.setFont(new java.awt.Font("Constantia", 2, 12)); // NOI18N
        btn_modificar_tel.setForeground(new java.awt.Color(255, 255, 255));
        btn_modificar_tel.setText("Modificar");
        jPanel1.add(btn_modificar_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 100, -1));

        btn_eliminar_tel.setBackground(new java.awt.Color(51, 51, 51));
        btn_eliminar_tel.setFont(new java.awt.Font("Constantia", 2, 12)); // NOI18N
        btn_eliminar_tel.setForeground(new java.awt.Color(255, 255, 255));
        btn_eliminar_tel.setText("Eliminar");
        jPanel1.add(btn_eliminar_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 100, -1));

        btn_nuevo_tel.setBackground(new java.awt.Color(51, 51, 51));
        btn_nuevo_tel.setFont(new java.awt.Font("Constantia", 2, 12)); // NOI18N
        btn_nuevo_tel.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevo_tel.setText("Nuevo");
        jPanel1.add(btn_nuevo_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 100, -1));

        btn_atras.setBackground(new java.awt.Color(51, 51, 51));
        btn_atras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/izquierda.png"))); // NOI18N
        jPanel1.add(btn_atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 30));

        txt_cant.setEditable(false);
        txt_cant.setFont(new java.awt.Font("Constantia", 2, 18)); // NOI18N
        jPanel1.add(txt_cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 40, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ModuloTelefono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloTelefono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloTelefono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloTelefono.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloTelefono().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_atras;
    public javax.swing.JButton btn_crear_tel;
    public javax.swing.JButton btn_eliminar_tel;
    public javax.swing.JButton btn_modificar_tel;
    public javax.swing.JButton btn_nuevo_tel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tb_telefono;
    public javax.swing.JTextField txt_cant;
    public javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}