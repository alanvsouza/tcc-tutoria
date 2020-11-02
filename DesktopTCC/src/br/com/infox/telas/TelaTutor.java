package br.com.infox.telas;

import br.com.infox.classes.documentoLimitado;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import net.proteanit.sql.DbUtils;

public class TelaTutor extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;

Image icono;
String origem = "";
String nomeImagem = "";
boolean camposObrigatorios = false;
 
    public TelaTutor() {
        initComponents();
        txtTutNome.setDocument(new documentoLimitado(80));
        txtTutEmail.setDocument(new documentoLimitado(60));
        txtTutLogin.setDocument(new documentoLimitado(30));
        txtTutSenha.setDocument(new documentoLimitado(15));
        txtTutFone.setDocument( new documentoLimitado(20));
        taTutDescricao.setDocument( new documentoLimitado(600));
        txtTutDisciplinas.setDocument( new documentoLimitado(150));
        arquivo.setVisible(false);
        lblArquivo.setVisible(false);
        btnUserAdd.setBackground(new Color (0,0,0,0));
        btnUserUpdate.setBackground(new Color (0,0,0,0));
        btnUserDelete.setBackground(new Color (0,0,0,0)); 
        conexao = ModuloConexao.conector();
    }
    
    private void pesquisar_tutor(){
        String sql = "select nometutor as Nome, descricao as Descrição,login as Login, senha as Senha, email as Email, telefone as Telefone, disciplinas as Disciplinas,foto as Foto, caminhoFoto as Caminho,idtutor as ID, facebook as Facebook, instagram as Instagram, linkedin as LinkedIn, twitter as Twitter, youtube as Youtube from tbtutor where nometutor like ?";
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
        taTutDescricao.setText(tblTutores.getModel().getValueAt(setar, 1).toString());
        txtTutLogin.setText(tblTutores.getModel().getValueAt(setar, 2).toString());
        txtTutSenha.setText(tblTutores.getModel().getValueAt(setar, 3).toString());
        txtTutEmail.setText(tblTutores.getModel().getValueAt(setar, 4).toString());
        txtTutFone.setText(tblTutores.getModel().getValueAt(setar, 5).toString());
        txtTutDisciplinas.setText(tblTutores.getModel().getValueAt(setar, 6).toString());
        nome.setText(tblTutores.getModel().getValueAt(setar,7).toString().replace(".jpg","").replace(".png",""));
        arquivo.setText(tblTutores.getModel().getValueAt(setar,8).toString());
        txtTutId.setText(tblTutores.getModel().getValueAt(setar, 9).toString());       
        txtFacebook.setText(tblTutores.getModel().getValueAt(setar, 10).toString());
        txtInstagram.setText(tblTutores.getModel().getValueAt(setar, 11).toString());
        txtLinkedin.setText(tblTutores.getModel().getValueAt(setar, 12).toString());
        txtTwitter.setText(tblTutores.getModel().getValueAt(setar, 13).toString());
        txtYoutube.setText(tblTutores.getModel().getValueAt(setar, 14).toString());
        carregaImagem(btnImg, tblTutores.getModel().getValueAt(setar,8).toString());
    }
    
    private void adicionar(){
        try{
            boolean verificarCampos = verificarCamposTutor();
            if (verificarCampos != true  || txtTutId.getText().isEmpty() == false){
                if(!txtTutId.getText().isEmpty()) JOptionPane.showMessageDialog(null, "É necessáio limpar os campos antes de adicionar um novo evento!");
                else if(camposObrigatorios){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
                    camposObrigatorios = false;
                }
            }else{
                nomeImagem = nome.getText();
                boolean verificarImg = consultarImagem();
                int verificar = 0;
                
                if(verificarImg){
                    verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);
                }
                if(verificar == 0){
                    deletarImagem();
                    copiarImagem();
                    
                    String sql = "insert into tbtutor (nometutor,login,senha,email,telefone,foto,caminhoFoto,facebook,linkedin,instagram,twitter,youtube,descricao,disciplinas) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1,txtTutNome.getText().trim());
                    pst.setString(2,txtTutLogin.getText().trim());
                    pst.setString(3,txtTutSenha.getText().trim());
                    pst.setString(4,txtTutEmail.getText().trim());
                    pst.setString(5,txtTutFone.getText().trim());
                    pst.setString(6,nome.getText().trim() + ".png");
                    pst.setString(7,"C:\\xampp\\htdocs\\myTCC\\site\\img-eventos\\" + nome.getText().trim().replace(".jpg","").replace(".png","") + ".png");
                    pst.setString(8,txtFacebook.getText().trim());
                    pst.setString(9,txtLinkedin.getText().trim());
                    pst.setString(10,txtInstagram.getText().trim());
                    pst.setString(11,txtTwitter.getText().trim());
                    pst.setString(12,txtYoutube.getText().trim());    
                    pst.setString(13,taTutDescricao.getText().trim());
                    pst.setString(14,txtTutDisciplinas.getText().trim()); 
                    int adicionado =  pst.executeUpdate();

                    if(adicionado > 0){
                        JOptionPane.showMessageDialog(null,"Tutor cadastrado com sucesso!");
                        txtTutEmail.setText(null);
                        pesquisar_tutor();
                        clear();
                    }
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar adicionar tutor");
        }
    }
    
    private void alterar(){
        try {
            boolean verificarCampos = verificarCamposTutor();
                if (verificarCampos != true || txtTutId.getText().isEmpty() == true){
                    if(txtTutId.getText().isEmpty() == true) JOptionPane.showMessageDialog(null,"Selecione um tutor para atualizar!");
                    else if(camposObrigatorios){
                        JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                        camposObrigatorios = false;
                    }
                }else{
                    nomeImagem = nome.getText();
                    boolean verificarImg = consultarImagem();
                    int verificar = 0;
                    
                    if(verificarImg){
                    verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);
                    }
                    
                    if(verificar == 0){
                        if(!arquivo.getText().equals("C:\\xampp\\htdocs\\myTCC\\site\\img-professores\\" + nome.getText().replace(".jpg","").replace(".png","") + ".png")){
                            deletarImagem();
                            copiarImagem();   
                        }
                        
                        String sql = "update tbtutor set nometutor=?,login=?,senha=?,email=?,telefone=?,foto=?,caminhoFoto=?,facebook=?,instagram=?,twitter=?,linkedin=?,youtube=?,descricao=?,disciplinas=? where idtutor=?";
                        pst = conexao.prepareStatement(sql);
                        pst.setString(1,txtTutNome.getText().trim());
                        pst.setString(2,txtTutLogin.getText().trim());
                        pst.setString(3,txtTutSenha.getText().trim());
                        pst.setString(4,txtTutEmail.getText().trim());
                        pst.setString(5,txtTutFone.getText().trim());
                        pst.setString(6,nome.getText().trim() + ".png");
                        pst.setString(7,"C:\\xampp\\htdocs\\myTCC\\site\\img-professores\\" + nome.getText().replace(".jpg","").replace(".png","") + ".png");
                        pst.setString(8,txtFacebook.getText().trim());
                        pst.setString(9,txtInstagram.getText().trim());
                        pst.setString(10,txtTwitter.getText().trim());
                        pst.setString(11,txtLinkedin.getText().trim());
                        pst.setString(12,txtYoutube.getText().trim());    
                        pst.setString(13,taTutDescricao.getText().trim());
                        pst.setString(14,txtTutDisciplinas.getText().trim()); 
                        pst.setString(15,txtTutId.getText());

                        int atualizado = pst.executeUpdate();
                        if (atualizado > 0){
                            JOptionPane.showMessageDialog(null,"Dados do tutor alterados com sucesso!");
                            pesquisar_tutor();
                            clear();
                        }
                }
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao tentar atualizar tutor!");
        }
    }

    private void deletar(){  
    boolean verificarCampos = verificarCamposTutor();
    
    if(verificarCampos == true || txtTutId.getText().isEmpty() == false){
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse tutor?","AVISO",JOptionPane.YES_NO_OPTION);
            if(confirmar == JOptionPane.YES_OPTION){
                String sql = "delete from tbtutor where idtutor=?";
                        try {
                            deletarImagem();
                            pst = conexao.prepareStatement(sql);
                            pst.setString(1,txtTutId.getText());
                            int apagado =  pst.executeUpdate();
                                if (apagado > 0) {
                                    JOptionPane.showMessageDialog(null,"Usuário apagado com sucesso!");
                                    pesquisar_tutor();
                                    clear();
                                }else {
                                   JOptionPane.showMessageDialog(null,"Tutor não encontrado! Exclusão não realizada!"); 
                                }
                        } catch (Exception e) {
                         JOptionPane.showMessageDialog(null,"Erro ao tentar remover tutor!");
                        } 
            }
        }
        else{
            if(txtTutId.getText().isEmpty() == true) JOptionPane.showMessageDialog(null,"Selecione um tutor para excluir!!"); 
            else if(camposObrigatorios){ 
                camposObrigatorios = false;
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
            }
        }
    }
    
    private void clear(){
        txtTutNome.setText(null);
        txtTutLogin.setText(null);
        txtTutSenha.setText(null);
        txtTutFone.setText(null);
        txtTutEmail.setText(null);
        txtTutId.setText(null);
        taTutDescricao.setText(null);
        txtTutDisciplinas.setText(null);
        btnImg.setIcon(null);
        nome.setText(null);
        arquivo.setText(null);
        txtFacebook.setText(null);
        txtInstagram.setText(null);
        txtTwitter.setText(null);
        txtLinkedin.setText(null);
        txtYoutube.setText(null);;
        cbFacebook.setSelected(false);
        cbInstagram.setSelected(false);
        cbTwitter.setSelected(false);
        cbLinkedin.setSelected(false);
        cbYoutube.setSelected(false);
        nomeImagem = "";
    }
    
    private boolean verificarCamposTutor(){
         if (txtTutEmail.getText().isEmpty() || txtTutNome.getText().isEmpty()  || txtTutLogin.getText().isEmpty() || txtTutSenha.getText().isEmpty() || arquivo.getText().isEmpty() || nome.getText().isEmpty() || txtTutDisciplinas.getText().isEmpty() || taTutDescricao.getText().isEmpty()){
             camposObrigatorios = true;
             return false;
         }else{
            //E-mail
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(txtTutEmail.getText());
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(null,"O formato de e-mail é inválido!");
                    return false;
                }
            //Telefone
                String expression2 = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})";
                Pattern pattern2 = Pattern.compile(expression2, Pattern.CASE_INSENSITIVE);
                Matcher matcher2 = pattern2.matcher(txtTutFone.getText());
                if (!matcher2.matches()) {
                    JOptionPane.showMessageDialog(null,"O formato do telefone é inválido!");
                    return false;
                }
         }
        return true;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTutores = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtTutPesquisar = new javax.swing.JTextField();
        txtTutId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnImg = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        lblArquivo = new javax.swing.JLabel();
        arquivo = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taTutDescricao = new javax.swing.JTextArea();
        cbFacebook = new javax.swing.JCheckBox();
        cbInstagram = new javax.swing.JCheckBox();
        cbLinkedin = new javax.swing.JCheckBox();
        cbTwitter = new javax.swing.JCheckBox();
        cbYoutube = new javax.swing.JCheckBox();
        txtInstagram = new javax.swing.JTextField();
        txtTwitter = new javax.swing.JTextField();
        txtLinkedin = new javax.swing.JTextField();
        txtYoutube = new javax.swing.JTextField();
        txtFacebook = new javax.swing.JTextField();
        txtTutDisciplinas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

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

        txtTutFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTutFoneActionPerformed(evt);
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

        lblArquivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblArquivo.setText("* Arquivo:");

        arquivo.setEnabled(false);
        arquivo.setOpaque(false);
        arquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arquivoActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearMouseClicked(evt);
            }
        });

        taTutDescricao.setColumns(20);
        taTutDescricao.setLineWrap(true);
        taTutDescricao.setRows(5);
        taTutDescricao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(taTutDescricao);

        cbFacebook.setText("Facebook");
        cbFacebook.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFacebookItemStateChanged(evt);
            }
        });

        cbInstagram.setText("Instagram");
        cbInstagram.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbInstagramItemStateChanged(evt);
            }
        });

        cbLinkedin.setText("LinkedIn");
        cbLinkedin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLinkedinItemStateChanged(evt);
            }
        });

        cbTwitter.setText("Twitter");
        cbTwitter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTwitterItemStateChanged(evt);
            }
        });

        cbYoutube.setText("YouTube");
        cbYoutube.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbYoutubeItemStateChanged(evt);
            }
        });

        txtInstagram.setForeground(new java.awt.Color(51, 51, 51));
        txtInstagram.setEnabled(false);
        txtInstagram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInstagramActionPerformed(evt);
            }
        });

        txtTwitter.setForeground(new java.awt.Color(51, 51, 51));
        txtTwitter.setEnabled(false);

        txtLinkedin.setForeground(new java.awt.Color(51, 51, 51));
        txtLinkedin.setEnabled(false);

        txtYoutube.setForeground(new java.awt.Color(51, 51, 51));
        txtYoutube.setEnabled(false);

        txtFacebook.setForeground(new java.awt.Color(51, 51, 51));
        txtFacebook.setEnabled(false);

        txtTutDisciplinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTutDisciplinasActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("*Disciplinas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblArquivo)
                                .addGap(18, 18, 18)
                                .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(45, 45, 45))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTutNome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTutLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTutEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTutSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbFacebook)
                                            .addComponent(cbLinkedin))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cbInstagram)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbTwitter))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbYoutube)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addComponent(txtYoutube, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLinkedin, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtInstagram, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTutDisciplinas, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                                    .addComponent(txtTutFone))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtInstagram, txtLinkedin, txtTwitter, txtYoutube});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTutDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTutNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTutFone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTutEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtTutLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTutSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbInstagram)
                                                .addComponent(cbTwitter))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbYoutube)
                                                .addComponent(cbLinkedin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(cbFacebook))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtInstagram, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtLinkedin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtYoutube, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUserAdd, btnUserDelete, btnUserUpdate});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {arquivo, nome});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtFacebook, txtInstagram, txtLinkedin, txtTwitter, txtYoutube});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtTutEmail, txtTutLogin, txtTutNome, txtTutSenha});

        setBounds(0, 0, 988, 552);
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
    String sql = "select nometutor as Nome, descricao as Descrição, login as Login, senha as Senha, email as Email, telefone as Telefone, disciplinas as Disciplinas, foto as Foto, caminhoFoto as Caminho,idtutor as ID, facebook as Facebook, instagram as Instagram, linkedin as LinkedIn, twitter as Twitter, youtube as Youtube from tbtutor";
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
            txtTutDisciplinas.requestFocus();
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
                ico.setImage(ico.getImage().getScaledInstance(200, 175, java.awt.Image.SCALE_SMOOTH));
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

    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        clear();
    }//GEN-LAST:event_btnClearMouseClicked

    private void txtTutFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTutFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTutFoneActionPerformed

    private void txtInstagramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInstagramActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInstagramActionPerformed

    private void cbFacebookItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFacebookItemStateChanged
        if(txtFacebook.isEnabled() == false && cbFacebook.isSelected() == true){
            txtFacebook.setEnabled(true);
        }else if(txtFacebook.isEnabled() == true && cbFacebook.isSelected() == false){
            txtFacebook.setEnabled(false);
        }
    }//GEN-LAST:event_cbFacebookItemStateChanged

    private void cbInstagramItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbInstagramItemStateChanged
        if(txtInstagram.isEnabled() == false && cbInstagram.isSelected() == true){
            txtInstagram.setEnabled(true);
        }else if(txtInstagram.isEnabled() == true && cbInstagram.isSelected() == false){
            txtInstagram.setEnabled(false);
        }
    }//GEN-LAST:event_cbInstagramItemStateChanged

    private void cbTwitterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTwitterItemStateChanged
       if(txtTwitter.isEnabled() == false && cbTwitter.isSelected() == true){
            txtTwitter.setEnabled(true);  
        }else if(txtTwitter.isEnabled() == true && cbTwitter.isSelected() == false){
            txtTwitter.setEnabled(false);
        }
    }//GEN-LAST:event_cbTwitterItemStateChanged

    private void cbLinkedinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLinkedinItemStateChanged
        if(txtLinkedin.isEnabled() == false && cbLinkedin.isSelected() == true){
            txtLinkedin.setEnabled(true);
        }else if(txtLinkedin.isEnabled() == true && cbLinkedin.isSelected() == false){
            txtLinkedin.setEnabled(false);
        }
    }//GEN-LAST:event_cbLinkedinItemStateChanged

    private void cbYoutubeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbYoutubeItemStateChanged
        if(txtYoutube.isEnabled() == false && cbYoutube.isSelected() == true){
            txtYoutube.setEnabled(true);
        }else if(txtYoutube.isEnabled() == true && cbYoutube.isSelected() == false){
            txtYoutube.setEnabled(false);
        }
    }//GEN-LAST:event_cbYoutubeItemStateChanged

    private void txtTutDisciplinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTutDisciplinasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTutDisciplinasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arquivo;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnUserAdd;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JButton btnUserUpdate;
    private javax.swing.JCheckBox cbFacebook;
    private javax.swing.JCheckBox cbInstagram;
    private javax.swing.JCheckBox cbLinkedin;
    private javax.swing.JCheckBox cbTwitter;
    private javax.swing.JCheckBox cbYoutube;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblArquivo;
    private javax.swing.JTextField nome;
    private javax.swing.JTextArea taTutDescricao;
    private javax.swing.JTable tblTutores;
    private javax.swing.JTextField txtFacebook;
    private javax.swing.JTextField txtInstagram;
    private javax.swing.JTextField txtLinkedin;
    private javax.swing.JTextField txtTutDisciplinas;
    private javax.swing.JTextField txtTutEmail;
    private javax.swing.JTextField txtTutFone;
    private javax.swing.JTextField txtTutId;
    private javax.swing.JTextField txtTutLogin;
    private javax.swing.JTextField txtTutNome;
    private javax.swing.JTextField txtTutPesquisar;
    private javax.swing.JTextField txtTutSenha;
    private javax.swing.JTextField txtTwitter;
    private javax.swing.JTextField txtYoutube;
    // End of variables declaration//GEN-END:variables
}
