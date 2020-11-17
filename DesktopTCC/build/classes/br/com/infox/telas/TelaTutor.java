package br.com.infox.telas;

import java.awt.Color;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaTutor extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;
Image icono;
String nomeImagem = "";

    public TelaTutor() {
        initComponents();
        btnUserAdd.setBackground(new Color (0,0,0,0));
        btnUserUpdate.setBackground(new Color (0,0,0,0));
        btnUserDelete.setBackground(new Color (0,0,0,0)); 
        conexao = ModuloConexao.conector();
    }
    
    private void pesquisar_tutor(){
        String sql = "select nometutor as Nome, login as Login, senha as Senha, email as Email, telefone as Telefone, rg as RG, foto as Foto, caminhoFoto as Caminho,idtutor as ID from tbtutor where nometutor like ?";
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
        nome.setText(tblTutores.getModel().getValueAt(setar,6).toString().replace(".jpg","").replace(".png",""));
        arquivo.setText(tblTutores.getModel().getValueAt(setar,7).toString());
        txtTutId.setText(tblTutores.getModel().getValueAt(setar, 8).toString());
        carregaImagem(btnImg, tblTutores.getModel().getValueAt(setar,7).toString());
    }
    
    private void adicionar(){
        String sql = "insert into tbtutor (nometutor,login,senha,email,telefone,rg,foto,caminhoFoto) values(?,?,?,?,?,?,?,?)";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtTutNome.getText());
            pst.setString(2,txtTutLogin.getText());
            pst.setString(3,txtTutSenha.getText());
            pst.setString(4,txtTutEmail.getText());
            pst.setString(5,txtTutFone.getText());
            pst.setString(6,txtTutRg.getText().replace(",", "."));
            pst.setString(7,nome.getText() + ".png");
            pst.setString(8,arquivo.getText());
  
            int verificarCampos = camposObrigatorios();
            
            if (verificarCampos != 1){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            }else{
                int adicionado =  pst.executeUpdate();
                if(adicionado > 0){
                    JOptionPane.showMessageDialog(null,"Tutor cadastrado com sucesso!");
                    txtTutEmail.setText(null);
                    pesquisar_tutor();
                    copiarImagem();
                    clear();
                }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir tutor! Verifique se já não existe um tutor com o RG informado.");
        }
        
    }
    
    private void alterar(){
    String sql = "update tbtutor set nometutor=?,login=?,senha=?,email=?,telefone=?,rg=?,foto=?,caminhoFoto=? where idtutor=?";
        try {
            int verificarCampos = camposObrigatorios();
                if (verificarCampos != 1 || txtTutId.getText().isEmpty() == true){
                    if(txtTutId.getText().isEmpty() == true) JOptionPane.showMessageDialog(null,"Selecione um tutor para atualizar!");
                    else JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                }else{
                    nomeImagem = nome.getText();
                    boolean verificarImg = consultarImagem();
                    int verificar = 0;
                    
                    if(verificarImg){
                    verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);}
                    
                    if(verificar == 0){
                        //Deleta a imagem que tem o mesmo nome da atual
                        deletarImagem();
                        
                        pst = conexao.prepareStatement(sql);
                        pst.setString(1,txtTutNome.getText());
                        pst.setString(2,txtTutLogin.getText());
                        pst.setString(3,txtTutSenha.getText());
                        pst.setString(4,txtTutEmail.getText());
                        pst.setString(5,txtTutFone.getText());
                        pst.setString(6,txtTutRg.getText());
                        pst.setString(7,nome.getText() + ".png");
                        pst.setString(8,"C:\\xampp\\htdocs\\myTCC\\site\\img-professores\\" + nome.getText().replace(".jpg","").replace(".png","") + ".png");
                        pst.setString(9,txtTutId.getText());

                        int atualizado = pst.executeUpdate();
                        if (atualizado > 0){
                            JOptionPane.showMessageDialog(null,"Dados do tutor alterados com sucesso!");
                            copiarImagem();
                            pesquisar_tutor();
                            clear();
                        }
                }
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao tentar atualizar tutor!");
        }
    }
    
    public boolean consultarImagem(){
         String sql = "select foto from tbtutor";
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                while(rs.next()){
                    if(rs.getString(1).equals(nomeImagem + ".png")){
                        return true;
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
            return false;
         }
     
        public void deletarImagem(){
            String sql = "select caminhoFoto from tbtutor where idtutor=?";

            try{
             pst = conexao.prepareStatement(sql);
             pst.setString(1,txtTutId.getText());
             rs = pst.executeQuery();
                    if(rs.next()){
                        File imagem = new File(rs.getString(1));
                        imagem.delete();
                    }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        
        public void copiarImagem(){
                         
            FileInputStream origem = null;
            FileOutputStream destino = null;
            FileChannel fcOrigem;
            FileChannel fcDestino;

            try {
                origem = new FileInputStream(arquivo.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TelaEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                destino = new FileOutputStream("C:\\xampp\\htdocs\\myTCC\\site\\img-professores\\" + nome.getText() + ".png");
            } catch (FileNotFoundException ex) {
                    Logger.getLogger(TelaEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            fcOrigem = origem.getChannel();
            fcDestino = destino.getChannel();
            
                try {
                    fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
                } catch (IOException ex) {
                    Logger.getLogger(TelaEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    origem.close();
                } catch (IOException ex) {
                    Logger.getLogger(TelaEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    destino.close();
                } catch (IOException ex) {
                    Logger.getLogger(TelaEventos.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void clear(){
        txtTutNome.setText(null);
        txtTutLogin.setText(null);
        txtTutSenha.setText(null);
        txtTutFone.setText(null);
        txtTutEmail.setText(null);
        txtTutRg.setText(null);
        txtTutId.setText(null);
        btnImg.setIcon(null);
        nome.setText(null);
        arquivo.setText(null);
        nomeImagem = "";
    }
    
    private int camposObrigatorios(){
         if (txtTutEmail.getText().isEmpty() || txtTutNome.getText().isEmpty()  || txtTutLogin.getText().isEmpty() || txtTutSenha.getText().isEmpty() || txtTutRg.getText().isEmpty() | arquivo.getText().isEmpty() || nome.getText().isEmpty())
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
        jPanel1 = new javax.swing.JPanel();
        txtTutPesquisar = new javax.swing.JTextField();
        txtTutId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnImg = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        arquivo = new javax.swing.JTextField();

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

        btnImg.setBorder(null);
        btnImg.setBorderPainted(false);
        btnImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImgMouseClicked(evt);
            }
        });
        btnImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("* Nome:");

        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("* Arquivo:");

        arquivo.setEnabled(false);
        arquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arquivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(234, 234, 234)
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTutNome)
                            .addComponent(txtTutEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtTutLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTutFone, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTutSenha)
                                    .addComponent(txtTutRg, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTutNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTutEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTutLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txtTutSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTutFone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtTutRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUserAdd, btnUserDelete, btnUserUpdate});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtTutEmail, txtTutFone, txtTutLogin, txtTutNome, txtTutRg, txtTutSenha});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {arquivo, nome});

        setBounds(0, 0, 988, 550);
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
    String sql = "select nometutor as nome, login as Login, senha as Senha, email as Email, telefone as Telefone, rg as RG, foto as Foto, caminhoFoto as Caminho, idtutor as ID from tbtutor";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tblTutores.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formInternalFrameOpened

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

    private void btnImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImgMouseClicked
            try {
            FileDialog fileDialog = new FileDialog((Frame)null);
            fileDialog.setVisible(true);
            if(fileDialog.getDirectory() != null){
                arquivo.setText(fileDialog.getDirectory() + fileDialog.getFile());
                nome.setText(fileDialog.getFile().replace(".jpg","").replace(".png",""));
                carregaImagem(btnImg,fileDialog.getDirectory() + fileDialog.getFile());
            }
        }catch(Exception e) {
               JOptionPane.showMessageDialog(null,"Erro ao tentar carregar o arquivo! Verifique se o arquivo selecionado é uma imagem.");
            }
    }//GEN-LAST:event_btnImgMouseClicked

     public void carregaImagem(JButton botao, String arquivo){
            try {
                File f = new File(arquivo);
                BufferedImage bufi = ImageIO.read(f);
                ImageIcon ico = new ImageIcon(bufi);
                ico.setImage(ico.getImage().getScaledInstance(329, 220, java.awt.Image.SCALE_SMOOTH));
                botao.setIcon(ico);
            } 
            catch(Exception e) {
               JOptionPane.showMessageDialog(null,"Erro ao tentar carregar o arquivo! Verifique se o arquivo selecionado é uma imagem.");
            }
     }
    private void btnImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImgActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void arquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arquivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arquivo;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnUserAdd;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JButton btnUserUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nome;
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