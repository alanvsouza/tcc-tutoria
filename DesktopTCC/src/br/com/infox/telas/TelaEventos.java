package br.com.infox.telas;

import br.com.infox.classes.documentoLimitado;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.Color;
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
import javax.swing.JOptionPane;
//a linha abaio importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

public class TelaEventos extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;

String nomeEvento = "";
Image icono;
String nomeImagem = "";
boolean camposObrigatorios = false;



    public TelaEventos() {
        initComponents();
        //Colocando um máximo de caracteres para os campos
        txtEvtNome.setDocument( new documentoLimitado(60));
        txtEvtData.setDocument( new documentoLimitado(10));
        txtEvtInicio.setDocument( new documentoLimitado(5));
        txtEvtTermino.setDocument( new documentoLimitado(5));
        taEvtDescricao.setDocument( new documentoLimitado(218));
        txtEvtLocal.setDocument( new documentoLimitado(50));
        nome.setDocument( new documentoLimitado(120));

        btnAdicionar.setBackground(new Color (0,0,0,0));
        btnAtualizar.setBackground(new Color (0,0,0,0));
        btnRemover.setBackground(new Color (0,0,0,0));
        conexao = ModuloConexao.conector();
    }

    public void pesquisar_evento(){
    String sql = "select nome as Nome,dataevento as Data, inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,image as Imagem, caminhoImg as Caminho, idevento as ID from tbeventos where nome like ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEvtPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tblEventos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void setar_campos(){
        int setar = tblEventos.getSelectedRow();
        txtEvtNome.setText(tblEventos.getModel().getValueAt(setar,0).toString());
        txtEvtData.setText(tblEventos.getModel().getValueAt(setar,1).toString());
        txtEvtInicio.setText(tblEventos.getModel().getValueAt(setar,2).toString());
        txtEvtTermino.setText(tblEventos.getModel().getValueAt(setar,3).toString());
        taEvtDescricao.setText(tblEventos.getModel().getValueAt(setar,4).toString());
        txtEvtLocal.setText(tblEventos.getModel().getValueAt(setar,5).toString());
        nome.setText(tblEventos.getModel().getValueAt(setar,6).toString().replace(".jpg","").replace(".png",""));
        arquivo.setText(tblEventos.getModel().getValueAt(setar,7).toString());
        txtEvtId.setText(tblEventos.getModel().getValueAt(setar,8).toString());
        carregaImagem(btnImg, tblEventos.getModel().getValueAt(setar,7).toString());
    }
    
     private void adicionarEvento(){
        try {            
            boolean verificarCampos = verificarCamposEvento();
            if (verificarCampos != true || txtEvtId.getText().isEmpty() == false){
               if(!txtEvtId.getText().isEmpty()) JOptionPane.showMessageDialog(null, "É necessáio limpar os campos antes de criar um novo evento!");
               else if(camposObrigatorios){ 
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!"); 
                camposObrigatorios = false;
               }
            }else{
                    nomeImagem = nome.getText();
                    boolean verificarImg = consultarImagem();
                    int verificar = 0;
                    
                    if(verificarImg){
                        verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);}
                            //Deleta a antiga imagem e a imagem com o nome atual
                            
                            if(verificar == 0){
                                deletarImagem();
                                copiarImagem();
                                
                                String sql = "insert into tbeventos (nome,dataevento,inicio,termino,descricao,localevento,caminhoImg,image) values(?,?,?,?,?,?,?,?)";
                                pst = conexao.prepareStatement(sql);
                                pst.setString(1,nomeEvento);
                                pst.setString(2,txtEvtData.getText().replace("-","/"));
                                pst.setString(3,txtEvtInicio.getText());
                                pst.setString(4,txtEvtTermino.getText());
                                pst.setString(5,taEvtDescricao.getText());
                                pst.setString(6,txtEvtLocal.getText());
                                pst.setString(7,"C:\\xampp\\htdocs\\myTCC\\site\\img-eventos\\" + nome.getText().replace(".jpg","").replace(".png","") + ".jpg");
                                pst.setString(8,nome.getText() + ".jpg");
                            int adicionado =  pst.executeUpdate();

                            if(adicionado > 0){
                                JOptionPane.showMessageDialog(null,"Evento cadastrado com sucesso!");
                                pesquisar_evento();
                                clear();
                            }
                        }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Erro ao tentar inserir evento!");
        }
    }
    
     private void alterarEvento(){
        try {
            boolean verificarCampos = verificarCamposEvento();
                if (verificarCampos != true || txtEvtId.getText().isEmpty() == true){
                    if(txtEvtId.getText().isEmpty() == true) JOptionPane.showMessageDialog(null,"Selecione um evento para atualizar!");
                    else if(camposObrigatorios){ 
                        camposObrigatorios = false;
                        JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                    }
                }else{
                        nomeImagem = nome.getText();
                        boolean verificarImg = consultarImagem();
                        int verificar = 0;

                        if(verificarImg){
                        verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);}

                        if(verificar == 0){

                            String sql = "update tbeventos set nome=?,inicio=?,termino=?,dataevento=?,descricao=?,localevento=?,image=?,caminhoImg=? where idevento=?";
                            pst = conexao.prepareStatement(sql);
                            pst.setString(1,txtEvtNome.getText());
                            pst.setString(2,txtEvtInicio.getText());
                            pst.setString(3,txtEvtTermino.getText());
                            pst.setString(4,txtEvtData.getText().replace("-","/"));
                            pst.setString(5,taEvtDescricao.getText());
                            pst.setString(6,txtEvtLocal.getText());
                            pst.setString(7,nome.getText() + ".jpg");
                            pst.setString(8,"C:\\xampp\\htdocs\\myTCC\\site\\img-eventos\\" + nome.getText().replace(".jpg","").replace(".png","") + ".jpg");
                            pst.setString(9,txtEvtId.getText());

                            int adicionado = pst.executeUpdate();
                            if (adicionado > 0){
                                JOptionPane.showMessageDialog(null,"Dados do evento alterados com sucesso!");
                                pesquisar_evento();
                                clear();
                                
                                if(!arquivo.getText().equals("C:\\xampp\\htdocs\\myTCC\\site\\img-eventos\\" + nome.getText().replace(".jpg","").replace(".png","") + ".jpg")){
                                    deletarImagem();
                                    copiarImagem();   
                                }  
                            }else{
                            JOptionPane.showMessageDialog(null,"Evento não encontrado! Selecione um evento na tabela.");
                            }
                         }
                }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    private void deletarEvento(){
    boolean verificarCampos = verificarCamposEvento();
    if (verificarCampos == true || txtEvtId.getText().isEmpty() == false){
        int verificar = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir este evento?","AVISO",JOptionPane.YES_NO_OPTION);
            if(verificar == JOptionPane.YES_OPTION){
               String sql= "delete from tbeventos where idevento=?";
                    try{
                         deletarImagem();
                         pst = conexao.prepareStatement(sql);
                         pst.setString(1, txtEvtId.getText());
                         int apagado = pst.executeUpdate();
                            if (apagado > 0) {
                                JOptionPane.showMessageDialog(null,"Evento apagado com sucesso!");
                                pesquisar_evento();
                                clear();
                            } 
                    } catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Falha ao tentar remover evento!");
                    }
                 }   
        }else {
            if(txtEvtId.getText().isEmpty() == true)JOptionPane.showMessageDialog(null,"Selecione um evento para excluir!!"); 
            else if(camposObrigatorios){ 
                camposObrigatorios = false;
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");}
        }
    }

     private void clear(){
        txtEvtNome.setText(null);
        txtEvtData.setText(null);
        txtEvtInicio.setText(null);
        txtEvtTermino.setText(null);
        txtEvtLocal.setText(null);
        txtEvtId.setText(null);
        taEvtDescricao.setText(null);
        btnImg.setIcon(null);
        nome.setText(null);
        arquivo.setText(null);
        txtEvtPesquisar.setText(null);
        nomeImagem = "";
     }
   
    private boolean verificarCamposEvento(){
         if (txtEvtNome.getText().isEmpty() || txtEvtData.getText().isEmpty() || txtEvtTermino.getText().isEmpty() || txtEvtInicio.getText().isEmpty() || txtEvtLocal.getText().isEmpty() || arquivo.getText().isEmpty() || nome.getText().isEmpty()){ 
             camposObrigatorios = true;
             return false;}
         else{
            int count = 0;
            char caractere = ' ';
            
            //Nome do evento
            nomeEvento = txtEvtNome.getText().trim();
            String[] partes = nomeEvento.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < partes.length; i++) {
                String word = partes[i];
                word = word.substring(0, 1).toUpperCase() + word.substring(1);
                sb.append(" ").append(word);
            }
            nomeEvento = sb.toString().replaceFirst(" ", "");
            
            //Data do evento
            String dataEvento = txtEvtData.getText().replace("/","-"); //<= formatar a data
            
            if(dataEvento.length() < 10){
                JOptionPane.showMessageDialog(null,"O campo de DATA do evento deve ter 10 caracteres e deve estar no seguinte formato: dd/MM/yyyy");
                return false;
            }
            
            for(int i = 0; i < dataEvento.length(); i++){
               caractere = dataEvento.charAt(i);
               if(caractere == '-') count++;
            }
 
            if(count != 2){
                JOptionPane.showMessageDialog(null,"O campo de DATA do evento deve ter 2 caracteres de barra (/) como no seguinte exemplo: 12/12/2020");
                return false;
            }
            
            //Início e término
            if(txtEvtInicio.getText().length() != 5){
                JOptionPane.showMessageDialog(null,"O campo de INÍCIO deve ter 5 caracteres e deve estar no seguinte formato: hh:mm. Exemplo: 12:10");
                return false;
            }
            
             if(txtEvtTermino.getText().length() != 5){
                JOptionPane.showMessageDialog(null,"O campo de TÉRMINO deve ter 5 caracteres e deve estar no seguinte formato: hh:mm. Exemplo: 13:05");
                return false;
            }

            return true;
         }
     }
     
     private boolean consultarImagem(){
         String sql = "select image from tbeventos";
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                while(rs.next()){
                    if(rs.getString(1).equals(nomeImagem + ".jpg")){
                        return true;
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
            return false;
         }
     
        private void deletarImagem(){
            String sql = "select caminhoImg from tbeventos where idevento=?";

            try{
             pst = conexao.prepareStatement(sql);
             pst.setString(1,txtEvtId.getText());
             rs = pst.executeQuery();
                    if(rs.next()){
                        File imagem = new File(rs.getString(1));
                        imagem.delete();
                    }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"Falha ao tentar excluir a imagem");
            }
        }
        
        private void copiarImagem(){
                         
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
                destino = new FileOutputStream("C:\\xampp\\htdocs\\myTCC\\site\\img-eventos\\" + nome.getText() + ".jpg");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEventos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEvtLocal = new javax.swing.JTextField();
        txtEvtTermino = new javax.swing.JTextField();
        txtEvtInicio = new javax.swing.JTextField();
        txtEvtData = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtEvtNome = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taEvtDescricao = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        txtEvtPesquisar = new javax.swing.JTextField();
        txtEvtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnImg = new javax.swing.JButton();
        nome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        arquivo = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Eventos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1000, 550));
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
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });

        tblEventos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEventosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEventos);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("* Campos obrigatórios");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("* data:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("* Inicio:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("* Término:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("* Local:");

        txtEvtLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEvtLocalActionPerformed(evt);
            }
        });
        txtEvtLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEvtLocalKeyPressed(evt);
            }
        });

        txtEvtTermino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEvtTerminoKeyPressed(evt);
            }
        });

        txtEvtInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEvtInicioKeyPressed(evt);
            }
        });

        txtEvtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEvtDataActionPerformed(evt);
            }
        });
        txtEvtData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEvtDataKeyPressed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/maisBlue.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/atualizarBlue.png"))); // NOI18N
        btnAtualizar.setToolTipText("Atualizar");
        btnAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/fecharRed.png"))); // NOI18N
        btnRemover.setToolTipText("Remover");
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("* Nome:");

        txtEvtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEvtNomeKeyPressed(evt);
            }
        });

        taEvtDescricao.setColumns(20);
        taEvtDescricao.setLineWrap(true);
        taEvtDescricao.setRows(5);
        taEvtDescricao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(taEvtDescricao);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Evento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtEvtPesquisar.setNextFocusableComponent(txtEvtNome);
        txtEvtPesquisar.setPreferredSize(new java.awt.Dimension(6, 25));
        txtEvtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEvtPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEvtPesquisarKeyReleased(evt);
            }
        });

        txtEvtId.setEnabled(false);
        txtEvtId.setPreferredSize(new java.awt.Dimension(6, 25));
        txtEvtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEvtIdActionPerformed(evt);
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
                .addComponent(txtEvtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEvtId, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEvtPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEvtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(3, 3, 3))
        );

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

        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("* Nome:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("* Arquivo:");

        arquivo.setEnabled(false);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtEvtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtEvtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtEvtData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEvtLocal, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                            .addComponent(txtEvtTermino))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {arquivo, nome});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnAdicionar)
                                .addGap(1, 1, 1)
                                .addComponent(btnAtualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemover))
                            .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEvtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEvtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtEvtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEvtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEvtTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtEvtData, txtEvtInicio, txtEvtLocal, txtEvtNome, txtEvtTermino});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {arquivo, nome});

        setBounds(0, 0, 988, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEvtLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEvtLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEvtLocalActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
       adicionarEvento();
    }//GEN-LAST:event_btnAdicionarActionPerformed
    // o evento abaixo é do tipo "enquando for digitando"
    private void txtEvtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvtPesquisarKeyReleased
       pesquisar_evento();
    }//GEN-LAST:event_txtEvtPesquisarKeyReleased
    // Evento que será usado para setar os capso da tabela ao clicar na linha
    private void tblEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEventosMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblEventosMouseClicked

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        alterarEvento();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        deletarEvento();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void txtEvtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEvtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEvtIdActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
      pesquisar_evento();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
       this.setLocation(0,0);
    }//GEN-LAST:event_formComponentMoved

    private void txtEvtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvtPesquisarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtEvtNome.requestFocus();
        }
    }//GEN-LAST:event_txtEvtPesquisarKeyPressed

    private void txtEvtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvtNomeKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER){
            txtEvtData.requestFocus();
        }
    }//GEN-LAST:event_txtEvtNomeKeyPressed

    private void txtEvtDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvtDataKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtEvtInicio.requestFocus();
        }
    }//GEN-LAST:event_txtEvtDataKeyPressed

    private void txtEvtInicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvtInicioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtEvtTermino.requestFocus();
        }
    }//GEN-LAST:event_txtEvtInicioKeyPressed

    private void txtEvtLocalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvtLocalKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            taEvtDescricao.requestFocus();
        }
    }//GEN-LAST:event_txtEvtLocalKeyPressed

    private void txtEvtTerminoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvtTerminoKeyPressed
         if (evt.getKeyCode() == evt.VK_ENTER) {
            txtEvtLocal.requestFocus();
        }
    }//GEN-LAST:event_txtEvtTerminoKeyPressed

    private void txtEvtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEvtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEvtDataActionPerformed

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
                ico.setImage(ico.getImage().getScaledInstance(333, 226, java.awt.Image.SCALE_SMOOTH));
                botao.setIcon(ico);
            } 
            catch(Exception e) {
               JOptionPane.showMessageDialog(null,"Erro ao tentar carregar o arquivo! Verifique se o arquivo selecionado é uma imagem.");
            }
     }
    
    private void arquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arquivoActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void btnImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImgActionPerformed

    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        clear();
    }//GEN-LAST:event_btnClearMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arquivo;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nome;
    private javax.swing.JTextArea taEvtDescricao;
    private javax.swing.JTable tblEventos;
    private javax.swing.JTextField txtEvtData;
    private javax.swing.JTextField txtEvtId;
    private javax.swing.JTextField txtEvtInicio;
    private javax.swing.JTextField txtEvtLocal;
    private javax.swing.JTextField txtEvtNome;
    private javax.swing.JTextField txtEvtPesquisar;
    private javax.swing.JTextField txtEvtTermino;
    // End of variables declaration//GEN-END:variables
}
