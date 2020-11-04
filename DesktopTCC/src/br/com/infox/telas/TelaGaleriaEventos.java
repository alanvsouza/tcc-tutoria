package br.com.infox.telas;

import br.com.infox.classes.GaleriaEventos;
import br.com.infox.classes.Imagem;
import br.com.infox.dal.ModuloConexao;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class TelaGaleriaEventos extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;

GaleriaEventos glEventos = new GaleriaEventos();
Imagem img = new Imagem();

String nomeImagem = "";
String fotos[];
int index = 0;
int count = 1;

    public TelaGaleriaEventos() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void adicionarFoto(){
        try{
            String pasta = "C:\\xampp\\htdocs\\myTCC\\site\\img-galeria-eventos\\" + txtGalNome.getText().replace(" ","").trim() + "_" 
            + txtGalData.getText().replace("/","-").trim() + "_" + txtGaleriaEventoId.getText();
            File pastaNova;
            boolean camposObrigatorios = verificarCamposGaleria();

            if(camposObrigatorios == true || btnImg.getIcon() == null){
                if(txtGaleriaEventoId.getText().isEmpty() == true) JOptionPane.showMessageDialog(null,"Escolha uma galeria de eventos para inserir uma imagem");
                else if(btnImg.getIcon() == null) JOptionPane.showMessageDialog(null,"É necessário escolher uma imagem!");
                else JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
            }else{
                
                if(!glEventos.existeDir(pasta)){
                    pastaNova = new File(pasta);
                    pastaNova.mkdir();
                }
                
                nomeImagem = nome.getText().replace(".jpg","").replace(".png","").replace(".","");
                boolean verificarImg = consultarImagem("select imagem from tbimgeventos where idevento=?", nomeImagem, ".jpg");
                int verificar = 0;
                
                if(verificarImg){
                    verificar = JOptionPane.showConfirmDialog(null,"Já existe uma imagem cadastrada com esse nome. "
                    + "Deseja sobrescrevê-la com a nova imagem?","AVISO",JOptionPane.YES_NO_OPTION);
                }
                
                if(verificar == 0){
                    boolean imgExiste = consultarImagem("select imagem from tbimgeventos where idevento=?", nomeImagem, ".jpg");
                    if(!arquivo.getText().equals(pasta + "\\" + nomeImagem + ".jpg")){
                       img.copiarImagem("br.com.infox.telas.TelaGaleriaEventos", arquivo.getText(),pasta,nomeImagem,".jpg");
                        fotos = consultarImagens();
                        count = 1;
                        index = 0;
                        lbGaleriaEventos.setText(count + " / " + fotos.length);
                    }
                    
                    if(!imgExiste){
                    String sql = "insert into tbimgeventos (idevento,imagem) values (?,?)";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1,txtGaleriaEventoId.getText());
                    pst.setString(2,nome.getText() + ".jpg");
                    
                    int adicionado =  pst.executeUpdate();

                        if(adicionado > 0){
                            JOptionPane.showMessageDialog(null,"Evento cadastrado com sucesso!");
                            fotos = consultarImagens();
                            count = 1;
                            index = 0;
                            lbGaleriaEventos.setText(count + " / " + fotos.length);
                            img.carregaImagem(btnGaleriaFotos,"C:\\xampp\\htdocs\\myTCC\\site\\img-galeria-eventos\\" + fotos[0], 294, 235);
                            glEventos.pesquisarGaleriaEventos("select nome as Nome,replace(DATE_FORMAT(dataevento, '%d-%m-%Y'),'-','/')as Data, "
                            + "inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,idevento as ID from tbeventos WHERE "
                            + "DATE_FORMAT(NOW(), '%Y/%m/%d') >= dataevento",tblGaleriaEventos,null);
                        }
                    }
                }
            }
        
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean consultarImagem(String sql, String nomeImagem, String extensao){
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtGaleriaEventoId.getText());
            rs = pst.executeQuery();

            while(rs.next()){
                if(rs.getString(1).equals(nomeImagem + extensao)){
                   return true;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public String[] consultarImagens(){
        String sql = "select imagem from tbimgeventos where idevento = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtGaleriaEventoId.getText());
            rs = pst.executeQuery();
            
            int i = 0;
            int linhas = getRows(rs);
            String[] fotos = new String[linhas];
            
            while(rs.next()){
                fotos[i] = rs.getString(1);
                i++;
            }
            
            return fotos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        return null;
    }
    
    public int getRows(ResultSet res){
        int totalRows = 0;
        try {
            res.last();
            totalRows = res.getRow();
            res.beforeFirst();
        } 
        catch(Exception ex)  {
            return 0;
        }
    return totalRows ;    
    }
    
    public boolean verificarCamposGaleria(){
        String[] camposObri = {nome.getText(),txtGaleriaEventoId.getText(),taGalDescricao.getText()};
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
        jLabel10 = new javax.swing.JLabel();
        arquivo = new javax.swing.JTextField();
        btnAtualizar = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnAdcFoto = new javax.swing.JButton();
        btnGaleriaFotos = new javax.swing.JButton();
        lbGaleriaEventos = new javax.swing.JLabel();

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("* Arquivo:");

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
        excluirFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirFotoActionPerformed(evt);
            }
        });

        btnNextFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNextFoto.setText("Próximo");
        btnNextFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextFotoMouseClicked(evt);
            }
        });
        btnNextFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextFotoActionPerformed(evt);
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

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("* Arquivo:");

        arquivo.setEnabled(false);

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/atualizarBlue.png"))); // NOI18N
        btnAtualizar.setToolTipText("Atualizar");
        btnAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnAdcFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdcFoto.setText("Adicionar Foto");
        btnAdcFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdcFotoActionPerformed(evt);
            }
        });

        lbGaleriaEventos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbGaleriaEventos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGaleriaEventos.setText("X / Y");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))
                            .addComponent(btnAdcFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGalData))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGalTermino))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGalLocal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGalNome, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(lbGaleriaEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGaleriaFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnPrevFoto)
                                        .addGap(27, 27, 27)
                                        .addComponent(excluirFoto)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNextFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel6, jLabel7});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtGalData, txtGalLocal, txtGalNome});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtGalInicio, txtGalTermino});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {arquivo, nome});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPrevFoto, excluirFoto});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdcFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbGaleriaEventos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtGalNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAtualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtGalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(txtGalLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGalInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtGalTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)))
                                    .addGap(14, 14, 14)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnGaleriaFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(excluirFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                                .addComponent(btnPrevFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addComponent(btnNextFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                .addGap(34, 34, 34))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnNextFoto, btnPrevFoto, excluirFoto});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtGalData, txtGalInicio, txtGalLocal, txtGalNome, txtGalTermino});

        setBounds(0, 0, 988, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void txtGaleriaEventoPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGaleriaEventoPesquisarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER){
            txtGalNome.requestFocus();
        }
    }//GEN-LAST:event_txtGaleriaEventoPesquisarKeyPressed

    private void txtGaleriaEventoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGaleriaEventoPesquisarKeyReleased
        glEventos.pesquisarGaleriaEventos("select nome as Nome,replace(DATE_FORMAT(dataevento, '%d-%m-%Y'),'-','/')as Data, inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,idevento as "
        + "ID from tbeventos WHERE DATE_FORMAT(NOW(), '%Y/%m/%d') >= dataevento and where like=?",tblGaleriaEventos,txtGaleriaEventoPesquisar.getText());
    }//GEN-LAST:event_txtGaleriaEventoPesquisarKeyReleased

    private void tblGaleriaEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGaleriaEventosMouseClicked
        try{
            JTextField[] campos = {txtGalNome, txtGalData, txtGalInicio,txtGalTermino, null, txtGalLocal,txtGaleriaEventoId};
            int setar = tblGaleriaEventos.getSelectedRow();
            for(int i = 0; i < campos.length; i++){
                if(campos[i] != null) campos[i].setText(tblGaleriaEventos.getModel().getValueAt(setar, i).toString()); 
                else taGalDescricao.setText(tblGaleriaEventos.getModel().getValueAt(setar, i).toString());
            }
            
           fotos = consultarImagens();
           count = 1;
           index = 0;
           
           if(fotos.length != 0){ 
            img.carregaImagem(btnGaleriaFotos,"C:\\xampp\\htdocs\\myTCC\\site\\img-galeria-eventos\\" + fotos[0], 294, 235);
            lbGaleriaEventos.setText(count + " / " + fotos.length);
           }
        } catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tblGaleriaEventosMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        glEventos.pesquisarGaleriaEventos("select nome as Nome,replace(DATE_FORMAT(dataevento, '%d-%m-%Y'),'-','/')as Data, inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,idevento as "
        + "ID from tbeventos WHERE DATE_FORMAT(NOW(), '%Y/%m/%d') >= dataevento",tblGaleriaEventos,null);
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnNextFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextFotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextFotoActionPerformed

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

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
     if(verificarCamposGaleria()) JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void excluirFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirFotoActionPerformed
        
    }//GEN-LAST:event_excluirFotoActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAdcFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdcFotoActionPerformed
        adicionarFoto();
    }//GEN-LAST:event_btnAdcFotoActionPerformed

    private void btnNextFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextFotoMouseClicked
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
            img.carregaImagem(btnGaleriaFotos,"C:\\xampp\\htdocs\\myTCC\\site\\img-galeria-eventos\\" + fotos[index] , 294, 232);
        }
    }//GEN-LAST:event_btnNextFotoMouseClicked

    private void btnPrevFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevFotoMouseClicked
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
            img.carregaImagem(btnGaleriaFotos,"C:\\xampp\\htdocs\\myTCC\\site\\img-galeria-eventos\\" + fotos[index] , 294, 232);
        }
    }//GEN-LAST:event_btnPrevFotoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arquivo;
    private javax.swing.JButton btnAdcFoto;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnGaleriaFotos;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnNextFoto;
    private javax.swing.JButton btnPrevFoto;
    private javax.swing.JButton excluirFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
