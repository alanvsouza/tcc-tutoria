package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaGaleriaEventos extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;
Image icono;

    public TelaGaleriaEventos() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtGaleriaEventoPesquisar = new javax.swing.JTextField();
        txtGaleriaEventoId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGaleriaEventos = new javax.swing.JTable();
        fotosGaleria = new javax.swing.JLabel();
        excluirFoto = new javax.swing.JButton();
        btnNextFoto = new javax.swing.JButton();
        btnPrevFoto = new javax.swing.JButton();
        lblGaleriaFoto = new javax.swing.JLabel();
        btnAdicionarFoto = new javax.swing.JButton();
        btnExcluirFoto = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Evento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtGaleriaEventoPesquisar.setPreferredSize(new java.awt.Dimension(6, 25));
        txtGaleriaEventoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGaleriaEventoPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGaleriaEventoPesquisarKeyReleased(evt);
            }
        });

        txtGaleriaEventoId.setEnabled(false);
        txtGaleriaEventoId.setPreferredSize(new java.awt.Dimension(6, 25));
        txtGaleriaEventoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGaleriaEventoIdActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtGaleriaEventoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGaleriaEventoId, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGaleriaEventoPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGaleriaEventoId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(3, 3, 3))
        );

        tblGaleriaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Título 4", "Título 5", "Título 6", "Title 4"
            }
        ));
        tblGaleriaEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGaleriaEventosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGaleriaEventos);

        excluirFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        excluirFoto.setText("Excluir");

        btnNextFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNextFoto.setText("Próximo");

        btnPrevFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPrevFoto.setText("Anterior");
        btnPrevFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevFotoActionPerformed(evt);
            }
        });

        lblGaleriaFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/adduser.png"))); // NOI18N

        btnAdicionarFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdicionarFoto.setText("Escolher Imagem");
        btnAdicionarFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdicionarFotoMouseClicked(evt);
            }
        });

        btnExcluirFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcluirFoto.setText("Excluir Imagem");
        btnExcluirFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcluirFotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnExcluirFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdicionarFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGaleriaFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnPrevFoto)
                                .addGap(157, 157, 157)
                                .addComponent(excluirFoto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNextFoto))
                            .addComponent(fotosGaleria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNextFoto, btnPrevFoto, excluirFoto});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fotosGaleria, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNextFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(excluirFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGaleriaFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdicionarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluirFoto)
                            .addComponent(btnPrevFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(32, 32, 32))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdicionarFoto, btnExcluirFoto});

        setBounds(0, 0, 879, 521);
    }// </editor-fold>//GEN-END:initComponents

    private void txtGaleriaEventoPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGaleriaEventoPesquisarKeyPressed
      
    }//GEN-LAST:event_txtGaleriaEventoPesquisarKeyPressed

    private void txtGaleriaEventoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGaleriaEventoPesquisarKeyReleased

    }//GEN-LAST:event_txtGaleriaEventoPesquisarKeyReleased

    private void txtGaleriaEventoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGaleriaEventoIdActionPerformed
       
    }//GEN-LAST:event_txtGaleriaEventoIdActionPerformed

    private void tblGaleriaEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGaleriaEventosMouseClicked

    }//GEN-LAST:event_tblGaleriaEventosMouseClicked

    private void btnPrevFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevFotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevFotoActionPerformed

    private void btnAdicionarFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarFotoMouseClicked
   
    }//GEN-LAST:event_btnAdicionarFotoMouseClicked

    private void btnExcluirFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirFotoMouseClicked
        lblGaleriaFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/addUser.png")));
    }//GEN-LAST:event_btnExcluirFotoMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        String sql = "select nome as Nome,replace(DATE_FORMAT(dataevento, '%d-%m-%Y'),'-','/')as Data, inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,idevento as "
        + "ID from tbeventos WHERE DATE_FORMAT(NOW(), '%Y-%m-%d') >= dataevento";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tblGaleriaEventos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarFoto;
    private javax.swing.JButton btnExcluirFoto;
    private javax.swing.JButton btnNextFoto;
    private javax.swing.JButton btnPrevFoto;
    private javax.swing.JButton excluirFoto;
    private javax.swing.JLabel fotosGaleria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGaleriaFoto;
    private javax.swing.JTable tblGaleriaEventos;
    private javax.swing.JTextField txtEvtId7;
    private javax.swing.JTextField txtEvtPesquisar7;
    private javax.swing.JTextField txtGaleriaEventoId;
    private javax.swing.JTextField txtGaleriaEventoPesquisar;
    // End of variables declaration//GEN-END:variables
}
