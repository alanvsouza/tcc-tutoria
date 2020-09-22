package br.com.infox.telas;

import java.awt.Color;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaTutor extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;
Image icono;

    public TelaTutor() {
        initComponents();
        btnUserAdd.setBackground(new Color (0,0,0,0));
        btnUserUpdate.setBackground(new Color (0,0,0,0));
        btnUserDelete.setBackground(new Color (0,0,0,0)); 
        conexao = ModuloConexao.conector();
    }
    
    private void pesquisar_tutor(){
        String sql = "select nometutor as Nome, login as Login, senha as Senha, email as Email, telefone as Telefone, rg as RG, idtutor as ID from tbtutor where nometutor like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTutPesquisar.getText()+"%");
            rs = pst.executeQuery();
           
            tblTutores.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    private void setar_campos(){
    int setar = tblTutores.getSelectedRow();
    txtTutNome.setText(tblTutores.getModel().getValueAt(setar, 0).toString());
    txtTutLogin.setText(tblTutores.getModel().getValueAt(setar, 1).toString());
    txtTutSenha.setText(tblTutores.getModel().getValueAt(setar, 2).toString());
    txtTutEmail.setText(tblTutores.getModel().getValueAt(setar, 3).toString());
    txtTutFone.setText(tblTutores.getModel().getValueAt(setar, 4).toString());
    txtTutRg.setText(tblTutores.getModel().getValueAt(setar, 5).toString());
    txtTutId.setText(tblTutores.getModel().getValueAt(setar, 6).toString());
    }
    
    private void adicionar(){
        String sql = "insert into tbtutor (nometutor,login,senha,email,telefone,rg) values(?,?,?,?,?,?)";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtTutNome.getText());
            pst.setString(2,txtTutLogin.getText());
            pst.setString(3,txtTutSenha.getText());
            pst.setString(4,txtTutEmail.getText());
            pst.setString(5,txtTutFone.getText());
            pst.setString(6,txtTutRg.getText().replace(",", "."));
  
            int verificarCampos = camposObrigatorios();
            
            if (verificarCampos != 1){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            }else{
                int adicionado =  pst.executeUpdate();
                if(adicionado > 0){
                    JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso!");
                    txtTutEmail.setText(null);
                    clear();
                }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir tutor! Verifique se já não existe um tutor com o RG informado.");
        }
        
    }
    
    private void alterar(){
    String sql = "update tbtutor set nometutor=?,login=?,senha=?,email=?,telefone=?,rg=? where idtutor=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtTutNome.getText());
            pst.setString(2,txtTutLogin.getText());
            pst.setString(3,txtTutSenha.getText());
            pst.setString(4,txtTutEmail.getText());
            pst.setString(5,txtTutFone.getText());
            pst.setString(6,txtTutRg.getText());
            pst.setString(7,txtTutId.getText());
            
            int verificarCampos = camposObrigatorios();
            
                if (verificarCampos != 1){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                }else{
                    int atualizado = pst.executeUpdate();
                    if (atualizado > 0){
                        JOptionPane.showMessageDialog(null,"Dados do usuário alterados com sucesso!");
                        clear();
                    }
                }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao tentar atualizar tutor!");
        }
    }
    
    private void deletar(){  
    int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse tutor?","AVISO",JOptionPane.YES_NO_OPTION);
        if(confirmar == JOptionPane.YES_OPTION){
            String sql = "delete from tbtutor where idtutor=?";
                
                    try {
                        pst = conexao.prepareStatement(sql);
                        pst.setString(1,txtTutId.getText());
                        int apagado =  pst.executeUpdate();
                            if (apagado > 0) {
                                JOptionPane.showMessageDialog(null,"Usuário apagado com sucesso!");
                                txtTutEmail.setText(null);
                                clear();
                            }else {
                               JOptionPane.showMessageDialog(null,"Tutor não encontrado! Exclusão não realizada!"); 
                            }
                    } catch (Exception e) {
                     JOptionPane.showMessageDialog(null,"Erro ao tentar remover tutor!");
                    } 
        }
    }
    private void procurar_foto(){
    FileInputStream fis;
    int longitudBytes;
 
    JFileChooser foto = new JFileChooser();
        foto.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = foto.showOpenDialog(null);

        if(estado == JFileChooser.APPROVE_OPTION){
            try {
                longitudBytes = (int) foto.getSelectedFile().length();
                icono = ImageIO.read(foto.getSelectedFile()).getScaledInstance(lblTutFoto.getWidth(), lblTutFoto.getHeight(), Image.SCALE_DEFAULT);
                
                lblTutFoto.setIcon(new ImageIcon(icono));
                lblTutFoto.updateUI();

                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void clear(){
        txtTutNome.setText(null);
        txtTutLogin.setText(null);
        txtTutSenha.setText(null);
        txtTutFone.setText(null);
        txtTutEmail.setText(null);
        txtTutRg.setText(null);
        txtTutId.setText(null);
        lblTutFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/addUser.png")));
    }
    
    private int camposObrigatorios(){
         if (txtTutEmail.getText().isEmpty() || txtTutNome.getText().isEmpty()  || txtTutLogin.getText().isEmpty() || txtTutSenha.getText().isEmpty() || txtTutRg.getText().isEmpty())
         return 0;
         else
         return 1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTutEmail = new javax.swing.JTextField();
        txtTutNome = new javax.swing.JTextField();
        txtTutFone = new javax.swing.JTextField();
        txtTutLogin = new javax.swing.JTextField();
        txtTutSenha = new javax.swing.JTextField();
        btnUserAdd = new javax.swing.JButton();
        btnUserUpdate = new javax.swing.JButton();
        btnUserDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTutRg = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTutores = new javax.swing.JTable();
        lblTutFoto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTutPesquisar = new javax.swing.JTextField();
        txtTutId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnTutFoto = new javax.swing.JButton();
        btnTutExcluirFoto = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Tutor");
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("* Email:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("* Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("* Login:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Telefone:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("* Senha:");

        txtTutEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTutEmailKeyPressed(evt);
            }
        });

        txtTutNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTutNomeKeyPressed(evt);
            }
        });

        txtTutFone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTutFoneKeyPressed(evt);
            }
        });

        txtTutLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTutLoginKeyPressed(evt);
            }
        });

        txtTutSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTutSenhaKeyPressed(evt);
            }
        });

        btnUserAdd.setBackground(java.awt.SystemColor.window);
        btnUserAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/maisBlue.png"))); // NOI18N
        btnUserAdd.setToolTipText("Adicionar");
        btnUserAdd.setBorder(null);
        btnUserAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserAdd.setOpaque(false);
        btnUserAdd.setPreferredSize(new java.awt.Dimension(70, 70));
        btnUserAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserAddActionPerformed(evt);
            }
        });

        btnUserUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/atualizarBlue.png"))); // NOI18N
        btnUserUpdate.setToolTipText("Atualizar");
        btnUserUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserUpdateActionPerformed(evt);
            }
        });

        btnUserDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/fecharRed.png"))); // NOI18N
        btnUserDelete.setToolTipText("Remover");
        btnUserDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDeleteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("* Campos Obrigatórios");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("* RG:");

        tblTutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5", "Title 1", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTutores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTutoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTutores);

        lblTutFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/adduser.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tutor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtTutPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTutPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTutPesquisarKeyReleased(evt);
            }
        });

        txtTutId.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTutPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTutId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTutPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTutId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtTutId, txtTutPesquisar});

        btnTutFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTutFoto.setText("Escolher Foto");
        btnTutFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTutFotoMouseClicked(evt);
            }
        });

        btnTutExcluirFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTutExcluirFoto.setText("Excluir Foto");
        btnTutExcluirFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTutExcluirFotoMouseClicked(evt);
            }
        });
        btnTutExcluirFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutExcluirFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(234, 234, 234)
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtTutFone, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTutLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(120, 120, 120)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTutSenha)
                                            .addComponent(txtTutRg, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTutNome, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                                            .addComponent(txtTutEmail))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTutFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTutExcluirFoto))
                            .addComponent(lblTutFoto))
                        .addGap(18, 18, 18))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTutFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTutFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTutExcluirFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTutNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTutEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTutLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(txtTutSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTutFone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtTutRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUserUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUserDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUserAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUserAdd, btnUserDelete, btnUserUpdate});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtTutEmail, txtTutFone, txtTutLogin, txtTutNome, txtTutRg, txtTutSenha});

        setBounds(0, 0, 883, 534);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUserAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserAddActionPerformed
        adicionar();
    }//GEN-LAST:event_btnUserAddActionPerformed

    private void btnUserUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btnUserUpdateActionPerformed

    private void btnUserDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDeleteActionPerformed
        deletar();
    }//GEN-LAST:event_btnUserDeleteActionPerformed

    private void txtTutPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTutPesquisarKeyReleased
       pesquisar_tutor();
    }//GEN-LAST:event_txtTutPesquisarKeyReleased

    private void tblTutoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTutoresMouseClicked
       setar_campos();
    }//GEN-LAST:event_tblTutoresMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
    String sql = "select nometutor as nome, login as Login, senha as Senha, email as Email, telefone as Telefone, rg as RG,idtutor as ID from tbtutor";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tblTutores.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnTutFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTutFotoMouseClicked
        procurar_foto();
    }//GEN-LAST:event_btnTutFotoMouseClicked

    private void btnTutExcluirFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTutExcluirFotoMouseClicked
       lblTutFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/addUser.png")));
    }//GEN-LAST:event_btnTutExcluirFotoMouseClicked

    private void txtTutPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTutPesquisarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTutNome.requestFocus();
        }
    }//GEN-LAST:event_txtTutPesquisarKeyPressed

    private void txtTutNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTutNomeKeyPressed
       if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTutEmail.requestFocus();
        }
    }//GEN-LAST:event_txtTutNomeKeyPressed

    private void txtTutEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTutEmailKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTutLogin.requestFocus();
        }
    }//GEN-LAST:event_txtTutEmailKeyPressed

    private void txtTutLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTutLoginKeyPressed
       if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTutSenha.requestFocus();
        }
    }//GEN-LAST:event_txtTutLoginKeyPressed

    private void txtTutFoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTutFoneKeyPressed
       if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTutRg.requestFocus();
        }
    }//GEN-LAST:event_txtTutFoneKeyPressed

    private void txtTutSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTutSenhaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtTutFone.requestFocus();
        }
    }//GEN-LAST:event_txtTutSenhaKeyPressed

    private void btnTutExcluirFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutExcluirFotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTutExcluirFotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTutExcluirFoto;
    private javax.swing.JButton btnTutFoto;
    private javax.swing.JButton btnUserAdd;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JButton btnUserUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTutFoto;
    private javax.swing.JTable tblTutores;
    private javax.swing.JTextField txtTutEmail;
    private javax.swing.JTextField txtTutFone;
    private javax.swing.JTextField txtTutId;
    private javax.swing.JTextField txtTutLogin;
    private javax.swing.JTextField txtTutNome;
    private javax.swing.JTextField txtTutPesquisar;
    private javax.swing.JTextField txtTutRg;
    private javax.swing.JTextField txtTutSenha;
    // End of variables declaration//GEN-END:variables
}
