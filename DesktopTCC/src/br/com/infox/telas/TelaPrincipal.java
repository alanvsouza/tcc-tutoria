package br.com.infox.telas;
import javax.swing.JOptionPane;

public class TelaPrincipal extends javax.swing.JFrame {
TelaTutor telatutor;
TelaEventos telaeventos;

    public TelaPrincipal() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        Menu = new javax.swing.JMenuBar();
        MenEdit = new javax.swing.JMenu();
        MenEvent = new javax.swing.JMenuItem();
        MenCadTut = new javax.swing.JMenu();
        MenCadEvt = new javax.swing.JMenuItem();
        MenCadTutor = new javax.swing.JMenuItem();
        MenAjuSob = new javax.swing.JMenu();
        MenOpcSai = new javax.swing.JMenu();
        MenOpcSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gerenciamento");
        setPreferredSize(new java.awt.Dimension(879, 514));
        setResizable(false);

        desktop.setPreferredSize(new java.awt.Dimension(640, 507));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        MenEdit.setText("Editar");

        MenEvent.setText("Eventos");
        MenEdit.add(MenEvent);

        Menu.add(MenEdit);

        MenCadTut.setText("Cadastro");

        MenCadEvt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        MenCadEvt.setText("Cadastrar Evento");
        MenCadEvt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadEvtActionPerformed(evt);
            }
        });
        MenCadTut.add(MenCadEvt);

        MenCadTutor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        MenCadTutor.setText("Cadastrar Tutor");
        MenCadTutor.setBorderPainted(true);
        MenCadTutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadTutorActionPerformed(evt);
            }
        });
        MenCadTut.add(MenCadTutor);

        Menu.add(MenCadTut);

        MenAjuSob.setText("Ajuda");
        Menu.add(MenAjuSob);

        MenOpcSai.setText("Opções");

        MenOpcSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_MASK));
        MenOpcSair.setText("Sair");
        MenOpcSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenOpcSairActionPerformed(evt);
            }
        });
        MenOpcSai.add(MenOpcSair);

        Menu.add(MenOpcSai);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(895, 580));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenOpcSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenOpcSairActionPerformed
       int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?","Atenção",JOptionPane.YES_NO_OPTION);//
       if(sair == JOptionPane.YES_OPTION){
         System.exit(0);
       };
    }//GEN-LAST:event_MenOpcSairActionPerformed

    private void MenCadTutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadTutorActionPerformed
        if (telatutor == null) {
        telatutor = new TelaTutor();
        }
        
        if(!telatutor.isVisible()) {
        telatutor.setVisible(true);
        desktop.add(telatutor);
        }
    }//GEN-LAST:event_MenCadTutorActionPerformed

    private void MenCadEvtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadEvtActionPerformed
        if (telaeventos == null) {
           telaeventos= new TelaEventos();
        }
        if (!telaeventos.isVisible()) {
            telaeventos.setVisible(true);
            desktop.add(telaeventos); 
        }
    }//GEN-LAST:event_MenCadEvtActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenAjuSob;
    private javax.swing.JMenuItem MenCadEvt;
    private javax.swing.JMenu MenCadTut;
    public static javax.swing.JMenuItem MenCadTutor;
    private javax.swing.JMenu MenEdit;
    private javax.swing.JMenuItem MenEvent;
    private javax.swing.JMenu MenOpcSai;
    private javax.swing.JMenuItem MenOpcSair;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JDesktopPane desktop;
    // End of variables declaration//GEN-END:variables
}
