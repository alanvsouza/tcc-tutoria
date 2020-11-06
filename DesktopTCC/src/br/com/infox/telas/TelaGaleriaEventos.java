package br.com.infox.telas;

import br.com.infox.classes.Caminho;
import br.com.infox.classes.GaleriaEventos;
import br.com.infox.classes.Imagem;
import br.com.infox.classes.LimitarCampos;
import br.com.infox.dal.ModuloConexao;
import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaGaleriaEventos extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
PreparedStatement pst2 = null;
ResultSet rs = null;

Caminho caminho = new Caminho();
GaleriaEventos glEventos = new GaleriaEventos();
Imagem img = new Imagem();

String nomeImagem = "";
String pasta;
String caminhoPasta;
String fotos[] = null;

int index = 0;
int count = 1;

    public TelaGaleriaEventos() {
        initComponents();
        taGalDescricao.setDocument(new LimitarCampos(218));
        btnRemover.setBackground(new Color (0,0,0,0));
        btnClear.setBackground(new Color (0,0,0,0));
        
        caminho.setPastaGaleria("C:\\xampp\\htdocs\\myTCC\\site\\img-galeria-eventos\\");
        caminhoPasta = caminho.getPastaGaleria();
        
        conexao = ModuloConexao.conector();
        arquivo.setVisible(false);
        lblArquivo.setVisible(false);
    }
    
    private void adicionarFoto(){
        try{
            boolean camposObrigatorios = verificarCamposGaleria();
            if(camposObrigatorios == true || btnImg.getIcon() == null){
                if(txtGaleriaEventoId.getText().isEmpty() == true) JOptionPane.showMessageDialog(null,"Escolha uma galeria de eventos para inserir uma imagem");
                else if(btnImg.getIcon() == null) JOptionPane.showMessageDialog(null,"É necessário escolher uma imagem!");
                else JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
            }else{
                
                File pastaNova;
                if(!glEventos.existeDir(pasta)){
                    pastaNova = new File(pasta);
                    pastaNova.mkdir();
                }
                
                nomeImagem = nome.getText().replace(".jpg","").replace(".png","").replace(".","");
                boolean verificarImg = glEventos.consultarImagem("select imagem from tbimgeventos where idevento=?", nomeImagem, ".jpg", txtGaleriaEventoId.getText());
                int verificar = 0;
                
                if(verificarImg){
                    verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. "
                    + "Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);
                }
                
                if(verificar == 0){
                    //Se a origem da imagem for igual ao destino da imagem -> false -> não preciso recriar a imagem
                    if(!arquivo.getText().equals(pasta + nomeImagem + ".jpg")){
                        img.copiarImagem("br.com.infox.telas.TelaGaleriaEventos", arquivo.getText(),pasta,nomeImagem,".jpg");
                    }
                    
                    //Se a imagem tiver o mesmo nome de uma cadastradaa, então eu não quero adicionar outra linha na tabela
                    //só preciso substituir a antiga imagem pela nova
                    if(!verificarImg){
                    String sql = "insert into tbimgeventos (idevento,imagem) values (?,?)";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1,txtGaleriaEventoId.getText());
                    pst.setString(2,nome.getText() + ".jpg");
                    
                    int adicionado =  pst.executeUpdate();

                        if(adicionado > 0){
                            JOptionPane.showMessageDialog(null,"Evento cadastrado com sucesso!");
                            pesquisarGaleria();
                        }
                    }
                    
                    btnImg.setIcon(null);
                    nome.setText(null);
                    fotos = glEventos.adquirirImagens(txtGaleriaEventoId.getText());
                    count = 1;
                    index = 0;
                    lbGaleriaEventos.setText(count + " / " + fotos.length);
                    img.carregaImagem(btnGaleriaFotos,pasta + fotos[0], 316, 237);
                }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Falha ao tentar adicionar a foto! Verifique sua conexão com a internet!");
        }
    }
    
    public void  alterarDescricao(){
        if(!taGalDescricao.getText().isEmpty() && !txtGaleriaEventoId.getText().isEmpty()){
            try{
                String sql = "update tbeventos set descricao=? where idevento=?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1,taGalDescricao.getText());
                pst.setString(2,txtGaleriaEventoId.getText());
                
                int adicionado = pst.executeUpdate();
                
                if(adicionado > 0){
                     JOptionPane.showMessageDialog(null,"Dados do evento alterados com sucesso!");
                     pesquisarGaleria();
                     clear();
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(null,"Falha ao tentar atualizar a descrição da galeria");
            }
        }else {
            if(taGalDescricao.getText().isEmpty()) JOptionPane.showMessageDialog(null, "É necessário preencher o campo de descrição para atualizá-lo");
            else  JOptionPane.showMessageDialog(null, "É necessário escolher uma galeria de eventos para atualizar sua descrição!");
        }
    }
    
    public void excluirFoto(){
        try{
            if(fotos.length != 0 && btnGaleriaFotos.getIcon() != null){
            int verificar = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir esta imagem?","AVISO",JOptionPane.YES_NO_OPTION);
                if(verificar == JOptionPane.YES_OPTION){
                    String sql = "delete from tbimgeventos where idevento=? and imagem=?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1,txtGaleriaEventoId.getText());
                    pst.setString(2,fotos[index]);

                    int adicionado = pst.executeUpdate();

                    if(adicionado > 0){
                        File imagem = new File(pasta + fotos[index]);
                        imagem.delete();

                        fotos = glEventos.adquirirImagens(txtGaleriaEventoId.getText());
                        count = 1;
                        index = 0;
                        
                        if(fotos.length != 0){
                            lbGaleriaEventos.setText(count + " / " + fotos.length);
                            img.carregaImagem(btnGaleriaFotos,pasta + fotos[0], 316, 237);
                        }else{
                            lbGaleriaEventos.setText(0 + " / " + fotos.length);
                            btnGaleriaFotos.setIcon(null);
                        }

                        JOptionPane.showMessageDialog(null, "Foto excluida com sucesso!");
                    }
                }
            }else JOptionPane.showMessageDialog(null, "Nenhuma foto para excluir!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Falha ao tentar excluir foto!");
        }
    }
    
    public void removerGaleria(){
        if(!txtGaleriaEventoId.getText().isEmpty()){
            try{
            int verificar = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir esta galeria? Todas as fotos "
            + "incluidas nela serão excluídas também!","AVISO",JOptionPane.YES_NO_OPTION);
        
            if(verificar == 0){
                String sql = "delete from tbimgeventos where idevento =?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1,txtGaleriaEventoId.getText());
                int adicionado = pst.executeUpdate();
                
                if(adicionado > 0){
                    String sql2 = "delete from tbeventos where idevento =?";
                    pst2 = conexao.prepareStatement(sql2);
                    pst2.setString(1,txtGaleriaEventoId.getText());
                    int adicionado2 = pst2.executeUpdate();
 
                    if(adicionado2 > 0){  
                        JOptionPane.showMessageDialog(null, "Galeria excluida com sucesso!");
                        pesquisarGaleria();
                        clear();
                        
                        removerArquivo (new File (pasta));
                        count = 1;
                        index = 0;
                        lbGaleriaEventos.setText(0 + " / " + 0);
                        btnGaleriaFotos.setIcon(null);
                    }
                }
            } 
            } catch(Exception e){
            
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma galeria para excluir!");
        }
    }
    
     public void removerArquivo (File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; ++i) {
                removerArquivo (files[i]);
            }
        }
        f.delete();
    }
    
    public void nextFoto(){
        try{
            if(fotos.length != 0){
                index += 1;
                if(index > fotos.length - 1){
                    index = 0; 
                    count = 1;
                    lbGaleriaEventos.setText(count + " / " + fotos.length);
                }else {
                    count += 1;
                    lbGaleriaEventos.setText(count + " / " + fotos.length);
                }
                img.carregaImagem(btnGaleriaFotos,pasta + fotos[index] , 316, 237);
            }
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Selecione uma galeria de eventos para ver suas fotos!");
        } 
    }
    
    public void prevFoto(){
        try{
            if(fotos.length != 0){
                index -= 1;
                if(index < 0){
                    index = fotos.length - 1; 
                    count = fotos.length;
                    lbGaleriaEventos.setText(fotos.length + " / " + fotos.length);
                }else {
                    count -= 1;
                    lbGaleriaEventos.setText(count + " / " + fotos.length);
                }
                img.carregaImagem(btnGaleriaFotos,pasta + fotos[index] , 310, 235);
            }
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Selecione uma galeria de eventos para ver suas fotos!");
        } 
    }
    
    public void pesquisarGaleria(){
        glEventos.pesquisarGaleriaEventos("select nome as Nome,replace(DATE_FORMAT(dataevento, '%d-%m-%Y'),'-','/')as Data, "
        + "inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,idevento as ID from tbeventos WHERE "
        + "DATE_FORMAT(NOW(), '%Y/%m/%d') >= dataevento",tblGaleriaEventos,null);
    }
    
    public void setCampos(){
        try{
           JTextField[] campos = {txtGalNome, txtGalData, txtGalInicio,txtGalTermino, null, txtGalLocal,txtGaleriaEventoId};
           glEventos.setCamposGaleria(tblGaleriaEventos, campos, taGalDescricao);
            
           pasta = caminhoPasta + 
           txtGalNome.getText().replace("^",".").replace("|",".").replace("(",".").replace(")",".").replace("[",".").replace("]",".").replace("\\",".")
           .replace("$",".").replace("+",".").replace(" ","").trim() + "_"  + txtGalData.getText().replace("/","-").trim() + "_" 
           + txtGaleriaEventoId.getText() + "\\"; 
           
           fotos = glEventos.adquirirImagens(txtGaleriaEventoId.getText());
           count = 1;
           index = 0; 
           
           if(fotos.length != 0 && fotos != null){ 
                img.carregaImagem(btnGaleriaFotos,pasta + fotos[0], 310, 235);
                lbGaleriaEventos.setText(count + " / " + fotos.length);
           } else {
                lbGaleriaEventos.setText(0 + " / " + 0);
                btnGaleriaFotos.setIcon(null);
           }
           
        } catch(Exception e){
             JOptionPane.showMessageDialog(null, "Erro ao tentar selecionar a galeria de eventos");
        }
    }
    
    public boolean verificarCamposGaleria(){
        String[] camposObri = {nome.getText(),txtGaleriaEventoId.getText()};
        boolean verificacao = glEventos.verificarCamposGaleria(camposObri);
        if(verificacao) return true;
        return false;
    }
    
    public void clear(){
        JTextField[] campos = {txtGalNome,txtGalData,txtGalInicio,txtGalTermino,txtGalLocal,txtGaleriaEventoId,nome,txtGaleriaEventoPesquisar};
        glEventos.clearCamposGaleria(campos, taGalDescricao, btnImg, btnGaleriaFotos,lbGaleriaEventos);
        nomeImagem = "";
        fotos = null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtGaleriaEventoPesquisar = new javax.swing.JTextField();
        txtGaleriaEventoId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGaleriaEventos = new javax.swing.JTable();
        excluirFoto = new javax.swing.JButton();
        btnNextFoto = new javax.swing.JButton();
        btnPrevFoto = new javax.swing.JButton();
        txtGalNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGalData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGalLocal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGalInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGalTermino = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taGalDescricao = new javax.swing.JTextArea();
        btnImg = new javax.swing.JButton();
        nome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblArquivo = new javax.swing.JLabel();
        arquivo = new javax.swing.JTextField();
        btnAdcFoto = new javax.swing.JButton();
        btnGaleriaFotos = new javax.swing.JButton();
        lbGaleriaEventos = new javax.swing.JLabel();
        btnRemover = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("* Arquivo:");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Galeria de Eventos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtGaleriaEventoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
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
        excluirFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirFotoMouseClicked(evt);
            }
        });

        btnNextFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNextFoto.setText("Próximo");
        btnNextFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextFotoMouseClicked(evt);
            }
        });

        btnPrevFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPrevFoto.setText("Anterior");
        btnPrevFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevFotoMouseClicked(evt);
            }
        });

        txtGalNome.setEnabled(false);
        txtGalNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGalNomeKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nome:");

        txtGalData.setEnabled(false);
        txtGalData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGalDataKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("data:");

        txtGalLocal.setEnabled(false);
        txtGalLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGalLocalKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Local:");

        txtGalInicio.setEnabled(false);
        txtGalInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGalInicioKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Inicio:");

        txtGalTermino.setEnabled(false);
        txtGalTermino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGalTerminoKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Término:");

        taGalDescricao.setColumns(20);
        taGalDescricao.setLineWrap(true);
        taGalDescricao.setRows(5);
        taGalDescricao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(taGalDescricao);

        btnImg.setBorder(null);
        btnImg.setBorderPainted(false);
        btnImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImgMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("* Nome:");

        lblArquivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblArquivo.setText("* Arquivo:");

        arquivo.setEnabled(false);

        btnAdcFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdcFoto.setText("Adicionar Foto");
        btnAdcFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdcFotoActionPerformed(evt);
            }
        });

        lbGaleriaEventos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbGaleriaEventos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGaleriaEventos.setText("0 / 0");

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/fecharRed.png"))); // NOI18N
        btnRemover.setToolTipText("Remover");
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoverMouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Atualizar Descrição");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nome:");

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
                        .addComponent(lblArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAdcFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nome)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGalData))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGalLocal))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGalNome, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGalTermino))
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnPrevFoto)
                                                .addGap(18, 18, 18)
                                                .addComponent(excluirFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnNextFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnGaleriaFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(114, 114, 114)
                                        .addComponent(lbGaleriaEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel6, jLabel7});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtGalData, txtGalLocal, txtGalNome});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtGalInicio, txtGalTermino});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNextFoto, btnPrevFoto});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnRemover});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txtGalNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbGaleriaEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGaleriaFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel3)
                                                .addComponent(txtGalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6)
                                                .addComponent(txtGalLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(txtGalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtGalTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRemover)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdcFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPrevFoto)
                                        .addComponent(excluirFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNextFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtGalData, txtGalInicio, txtGalLocal, txtGalNome, txtGalTermino});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdcFoto, btnNextFoto, btnPrevFoto, excluirFoto, jButton1});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnRemover});

        setBounds(0, 0, 988, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void txtGaleriaEventoPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGaleriaEventoPesquisarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER){
            txtGalNome.requestFocus();
        }
    }//GEN-LAST:event_txtGaleriaEventoPesquisarKeyPressed

    private void txtGaleriaEventoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGaleriaEventoPesquisarKeyReleased
        glEventos.pesquisarGaleriaEventos("select nome as Nome,replace(DATE_FORMAT(dataevento, '%d-%m-%Y'),'-','/')as Data, "
        + "inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,idevento as ID from tbeventos WHERE "
        + "DATE_FORMAT(NOW(), '%Y/%m/%d') >= dataevento and nome like ?",tblGaleriaEventos, txtGaleriaEventoPesquisar.getText());
    }//GEN-LAST:event_txtGaleriaEventoPesquisarKeyReleased

    private void tblGaleriaEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGaleriaEventosMouseClicked
       setCampos();
    }//GEN-LAST:event_tblGaleriaEventosMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
       pesquisarGaleria();
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtGalNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGalNomeKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER){
            txtGalData.requestFocus();
        }
    }//GEN-LAST:event_txtGalNomeKeyPressed

    private void txtGalDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGalDataKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtGalLocal.requestFocus();
        }
    }//GEN-LAST:event_txtGalDataKeyPressed

    private void txtGalLocalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGalLocalKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtGalInicio.requestFocus();
        }
    }//GEN-LAST:event_txtGalLocalKeyPressed

    private void txtGalInicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGalInicioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtGalTermino.requestFocus();
        }
    }//GEN-LAST:event_txtGalInicioKeyPressed

    private void txtGalTerminoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGalTerminoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            taGalDescricao.requestFocus();
        }
    }//GEN-LAST:event_txtGalTerminoKeyPressed

    private void btnImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImgMouseClicked
        img.selecionarImagem(arquivo,nome,btnImg,230, 234);
    }//GEN-LAST:event_btnImgMouseClicked

    private void btnAdcFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdcFotoActionPerformed
       adicionarFoto();
    }//GEN-LAST:event_btnAdcFotoActionPerformed

    private void btnNextFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextFotoMouseClicked
       nextFoto();
    }//GEN-LAST:event_btnNextFotoMouseClicked

    private void btnPrevFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevFotoMouseClicked
       prevFoto();
    }//GEN-LAST:event_btnPrevFotoMouseClicked

    private void excluirFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirFotoMouseClicked
       excluirFoto();
    }//GEN-LAST:event_excluirFotoMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       alterarDescricao();
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnRemoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseClicked
        removerGaleria();
    }//GEN-LAST:event_btnRemoverMouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
       clear();
       pesquisarGaleria();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
        pesquisarGaleria();
    }//GEN-LAST:event_btnClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arquivo;
    private javax.swing.JButton btnAdcFoto;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnGaleriaFotos;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnNextFoto;
    private javax.swing.JButton btnPrevFoto;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton excluirFoto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel lbGaleriaEventos;
    private javax.swing.JLabel lblArquivo;
    private javax.swing.JTextField nome;
    private javax.swing.JTextArea taGalDescricao;
    private javax.swing.JTable tblGaleriaEventos;
    private javax.swing.JTextField txtGalData;
    private javax.swing.JTextField txtGalInicio;
    private javax.swing.JTextField txtGalLocal;
    private javax.swing.JTextField txtGalNome;
    private javax.swing.JTextField txtGalTermino;
    private javax.swing.JTextField txtGaleriaEventoId;
    private javax.swing.JTextField txtGaleriaEventoPesquisar;
    // End of variables declaration//GEN-END:variables
}
