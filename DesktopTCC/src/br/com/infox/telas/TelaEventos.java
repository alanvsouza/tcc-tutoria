package br.com.infox.telas;

import br.com.infox.classes.Caminho;
import br.com.infox.classes.LimitarCampos;
import br.com.infox.classes.Eventos;
import br.com.infox.classes.Imagem;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaEventos extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;
String nomeEvento = "";
String nomeImagem = "";
String pastaEvento = "";
boolean camposObrigatorios = false;

Caminho caminho = new Caminho();
Eventos evt = new Eventos();
Imagem img = new Imagem();



    public TelaEventos() {
        initComponents();
        
        //Colocando um máximo de caracteres para os campos
        txtEvtNome.setDocument(new LimitarCampos(60));
        txtEvtData.setDocument(new LimitarCampos(10));
        txtEvtInicio.setDocument(new LimitarCampos(5));
        txtEvtTermino.setDocument(new LimitarCampos(5));
        taEvtDescricao.setDocument(new LimitarCampos(218));
        txtEvtLocal.setDocument(new LimitarCampos(50));
        nome.setDocument(new LimitarCampos(120));
        
        //setando o estilo em alguns componentes
        btnAdicionar.setBackground(new Color (0,0,0,0));
        btnAtualizar.setBackground(new Color (0,0,0,0));
        btnRemover.setBackground(new Color (0,0,0,0));
        btnClear.setBackground(new Color (0,0,0,0));
        
        //Mudar a pasta de eventos
        caminho.setPastaEventos("C:\\xampp\\htdocs\\myTCC\\site\\img-eventos\\");
        pastaEvento = caminho.getPastaEventos();
        
        conexao = ModuloConexao.conector();
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
                    boolean verificarImg = img.consultarImagem("select image from tbeventos", nomeImagem, ".jpg");
                    int verificar = 0;
                    
                    if(verificarImg){
                        verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);}
                            
                            if(verificar == 0){
                                if(!arquivo.getText().equals(pastaEvento + nome.getText().replace(".jpg","").replace(".png","") + ".jpg")){
                                    img.deletarImagem("select caminhoImg from tbeventos where idevento=?", txtEvtId.getText(),"Falha ao tentar excluir a imagem");
                                    img.copiarImagem("br.com.infox.telas.TelaEventos",arquivo.getText(),"C:\\\\xampp\\\\htdocs\\\\myTCC\\\\site\\\\img-eventos\\\\",nome.getText(),".jpg");
                                }
                                
                                String sql = "insert into tbeventos (nome,dataevento,inicio,termino,descricao,localevento,caminhoImg,image) values(?,?,?,?,?,?,?,?)";   
                                String dataFormatada = evt.formatarData_ValidarData(txtEvtData.getText().replace("-","/"));
        
                                    if(dataFormatada != null){
                                    pst = conexao.prepareStatement(sql);
                                    pst.setString(1,nomeEvento);
                                    pst.setString(2,dataFormatada.replace("-","/"));
                                    pst.setString(3,txtEvtInicio.getText());
                                    pst.setString(4,txtEvtTermino.getText());
                                    pst.setString(5,taEvtDescricao.getText());
                                    pst.setString(6,txtEvtLocal.getText());
                                    pst.setString(7,pastaEvento + nome.getText().replace(".jpg","").replace(".png","") + ".jpg");
                                    pst.setString(8,nome.getText().replace(".jpg","").replace(".png","") + ".jpg");
                                    int adicionado = pst.executeUpdate();

                                    if(adicionado > 0){
                                        JOptionPane.showMessageDialog(null,"Evento cadastrado com sucesso!");
                                        clear();
                                        pesquisar_evento();
                                    }
                                }
                        }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e); 
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
                        boolean verificarImg = img.consultarImagem("select image from tbeventos", nomeImagem, ".jpg");
                        int verificar = 0;

                        if(verificarImg){
                        verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);}

                        if(verificar == 0){
                            if(!arquivo.getText().equals(pastaEvento + nome.getText().replace(".jpg","").replace(".png","") + ".jpg")){
                                img.deletarImagem("select caminhoImg from tbeventos where idevento=?", txtEvtId.getText(),"Falha ao tentar excluir a imagem");
                                img.copiarImagem("br.com.infox.telas.TelaEventos",arquivo.getText(),pastaEvento,nome.getText(),".jpg");   
                            } 
                            String sql = "update tbeventos set nome=?,inicio=?,termino=?,dataevento=?,descricao=?,localevento=?,image=?,caminhoImg=? where idevento=?";
                            String dataFormatada = evt.formatarData_ValidarData(txtEvtData.getText().replace("-","/"));
                            
                            if(dataFormatada != null){
                                pst = conexao.prepareStatement(sql);
                                pst.setString(1,txtEvtNome.getText());
                                pst.setString(2,txtEvtInicio.getText());
                                pst.setString(3,txtEvtTermino.getText());
                                pst.setString(4,dataFormatada.replace("-","/"));
                                pst.setString(5,taEvtDescricao.getText());
                                pst.setString(6,txtEvtLocal.getText());
                                pst.setString(7,nome.getText().replace(".jpg","").replace(".png","") + ".jpg");
                                pst.setString(8,pastaEvento + nome.getText().replace(".jpg","").replace(".png","") + ".jpg");
                                pst.setString(9,txtEvtId.getText());

                                int adicionado = pst.executeUpdate();
                                if (adicionado > 0){
                                    JOptionPane.showMessageDialog(null,"Dados do evento alterados com sucesso!");
                                    clear();
                                    pesquisar_evento();
                                }else{
                                JOptionPane.showMessageDialog(null,"Evento não encontrado! Selecione um evento na tabela.");
                                }
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
                         img.deletarImagem("select caminhoImg from tbeventos where idevento=?", txtEvtId.getText(),"Falha ao tentar excluir a imagem");
                         pst = conexao.prepareStatement(sql);
                         pst.setString(1, txtEvtId.getText());
                         int apagado = pst.executeUpdate();
                            if (apagado > 0) {
                                JOptionPane.showMessageDialog(null,"Evento apagado com sucesso!");
                                clear();
                                pesquisar_evento();
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

    private void pesquisar_evento(){
        evt.pesquisarEventos("select nome as Nome,DATE_FORMAT(dataevento, '%d/%m/%Y') as Data, inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,"
        + "image as Imagem," + "caminhoImg as Caminho, idevento as ID from tbeventos where DATE_FORMAT(NOW(), '%Y/%m/%d') <= DATE_FORMAT(dataevento, '%Y/%m/%d') and nome like ?", tblEventos, txtEvtPesquisar);
    }
    
    private void setar_campos(){
        int setar = tblEventos.getSelectedRow();
        JTextField[] camposEvento = {txtEvtNome,txtEvtData,txtEvtInicio,txtEvtTermino,null,txtEvtLocal,nome,arquivo,txtEvtId};
        evt.setCamposEvento(tblEventos,camposEvento,taEvtDescricao);
        img.carregaImagem(btnImg, tblEventos.getModel().getValueAt(setar,7).toString(),374,220);
    }
    
     private void clear(){
        JTextField[] campos = {txtEvtNome,txtEvtData,txtEvtInicio,txtEvtTermino,txtEvtLocal,txtEvtId,nome,arquivo,txtEvtPesquisar};
        evt.clearCamposEvento(campos, taEvtDescricao, btnImg);
        nomeImagem = "";
     }
   
    private boolean verificarCamposEvento(){
        String[] camposObri = {txtEvtNome.getText(),txtEvtData.getText(),txtEvtInicio.getText(),txtEvtTermino.getText(),txtEvtLocal.getText()
        ,nome.getText(),arquivo.getText(),taEvtDescricao.getText()};
        
        int verificacao = evt.verificarCamposEvento(camposObri, txtEvtData.getText(), txtEvtInicio.getText(), txtEvtTermino.getText());
        
         if (verificacao == 1 || verificacao == 2){ 
             if(verificacao == 1) camposObrigatorios = true;
             return false;
         }
         
        char[] letras = txtEvtNome.getText().toCharArray();
        for (int i = 0; i < letras.length; ++i) {
            if (i == 0 || !Character.isLetterOrDigit (letras[i-1])) {
                letras[i] = Character.toUpperCase (letras[i]);
            }
        }
        
        nomeEvento = new String (letras);
        return true;
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
                formInternalFrameClosed(evt);
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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("* Nome:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("* Arquivo:");

        arquivo.setEnabled(false);

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/claro.png"))); // NOI18N
        btnClear.setToolTipText("Limpar campos");
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
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
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nome))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(arquivo))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdicionar, btnAtualizar, btnClear, btnRemover});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdicionar)
                                .addGap(1, 1, 1)
                                .addComponent(btnAtualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemover))
                            .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtEvtData, txtEvtInicio, txtEvtLocal, txtEvtNome, txtEvtTermino});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {arquivo, nome});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdicionar, btnAtualizar, btnRemover});

        setBounds(0, 0, 988, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        adicionarEvento();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtEvtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEvtPesquisarKeyReleased
        pesquisar_evento();
    }//GEN-LAST:event_txtEvtPesquisarKeyReleased
 
    private void tblEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEventosMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblEventosMouseClicked

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        alterarEvento();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        deletarEvento();
    }//GEN-LAST:event_btnRemoverActionPerformed

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

    private void btnImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImgMouseClicked
        img.selecionarImagem(arquivo, nome, btnImg, 374,220);
    }//GEN-LAST:event_btnImgMouseClicked
   
    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        clear();
        pesquisar_evento();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
        pesquisar_evento();
    }//GEN-LAST:event_btnClearActionPerformed

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
