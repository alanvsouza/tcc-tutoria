package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
//a linha abaio importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

public class TelaEventos extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;
Image icono;
    public TelaEventos() {
        initComponents();
        btnAdicionar.setBackground(new Color (0,0,0,0));
        btnAtualizar.setBackground(new Color (0,0,0,0));
        btnRemover.setBackground(new Color (0,0,0,0));
        
        conexao = ModuloConexao.conector();
    }
    
    private void pesquisar_evento(){
    String sql = "select nome as Nome,dataevento as Data, inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,idevento as ID from tbeventos where nome like ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEvtPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tblEventos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
       //metodo para setar os campos do formulário com o conteúdo da tabela
        public void setar_campos(){
        // pegando a linha selecionada
        int setar = tblEventos.getSelectedRow();
        txtEvtNome.setText(tblEventos.getModel().getValueAt(setar,0).toString());
        txtEvtData.setText(tblEventos.getModel().getValueAt(setar,1).toString());
        txtEvtInicio.setText(tblEventos.getModel().getValueAt(setar,2).toString());
        txtEvtTermino.setText(tblEventos.getModel().getValueAt(setar,3).toString());
        taEvtDescricao.setText(tblEventos.getModel().getValueAt(setar,4).toString());
        txtEvtLocal.setText(tblEventos.getModel().getValueAt(setar,5).toString());
        txtEvtId.setText(tblEventos.getModel().getValueAt(setar,6).toString());
    }
    
     private void adicionarEvento(){
        String sql = "insert into tbeventos (nome,dataevento,inicio,termino,descricao,localevento) values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtEvtNome.getText());
            pst.setString(2,txtEvtData.getText().replace("/","-"));
            pst.setString(3,txtEvtInicio.getText());
            pst.setString(4,txtEvtTermino.getText());
            pst.setString(5,taEvtDescricao.getText());
            pst.setString(6,txtEvtLocal.getText());
            
            int verificarCampos = camposObrigatorios();
            
            if (verificarCampos != 1){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            }else{
                int adicionado =  pst.executeUpdate();
                if(adicionado > 0){
                    JOptionPane.showMessageDialog(null,"Evento cadastrado com sucesso!");
                    pesquisar_evento();
                    clear();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Falha ao inserir o evento");
        }
    }
    
     private void alterar(){
     String sql = "update tbeventos set nome=?,inicio=?,termino=?,dataevento=?,descricao=?,localevento=? where idevento=?";
        try {
            //int setar = tblEventos.getSelectedRow();
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1,txtEvtNome.getText());
            pst.setString(2,txtEvtInicio.getText());
            pst.setString(3,txtEvtTermino.getText());
            pst.setString(4,txtEvtData.getText().replace("/","-"));
            pst.setString(5,taEvtDescricao.getText());
            pst.setString(6,txtEvtLocal.getText());
            pst.setString(7,txtEvtId.getText());


            int verificarCampos = camposObrigatorios();
            
                if (verificarCampos != 1){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                }else{
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0){
                        JOptionPane.showMessageDialog(null,"Dados do evento alterados com sucesso!");
                        pesquisar_evento();
                        clear();
                    }else{
                    JOptionPane.showMessageDialog(null,"Evento não encontrado! Selecione um evento na tabela.");
                    }
                }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Falha ao tentar atualizar evento!");
        }
    }
     
    private void remover_cliente(){
    String sql= "delete from tbeventos where idevento=?";
    int verificar = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir este evento?","AVISO",JOptionPane.YES_NO_OPTION);
    int camposObritagotrios = camposObrigatorios();
        if(verificar == JOptionPane.YES_OPTION){
            if (camposObritagotrios == 1){
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtEvtId.getText());
                int apagado = pst.executeUpdate();
                  if (apagado > 0) {
                      JOptionPane.showMessageDialog(null,"Evento apagado com sucesso!");
                      pesquisar_evento();
                      clear();
                      btnAdicionar.setEnabled(true);
                  } 
            } catch (Exception e){
                JOptionPane.showMessageDialog(null,"Falha ao tentar remover evento!");
            }
          }
          else{
            JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
          }
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
     }
   
     private int camposObrigatorios(){
         if (txtEvtNome.getText().isEmpty() || txtEvtData.getText().isEmpty() || txtEvtTermino.getText().isEmpty() || txtEvtInicio.getText().isEmpty() || txtEvtLocal.getText().isEmpty()) 
            return 0;
          else 
            return 1;
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
                icono = ImageIO.read(foto.getSelectedFile()).getScaledInstance(lblEvtFoto.getWidth(), lblEvtFoto.getHeight(), Image.SCALE_DEFAULT);
                
                lblEvtFoto.setIcon(new ImageIcon(icono));
                lblEvtFoto.updateUI();

                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
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
        lblEvtFoto = new javax.swing.JLabel();
        btnExcluirImg = new javax.swing.JButton();
        btnAdicionarImg = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtEvtPesquisar = new javax.swing.JTextField();
        txtEvtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Eventos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(894, 550));
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
        taEvtDescricao.setRows(5);
        jScrollPane2.setViewportView(taEvtDescricao);

        lblEvtFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/adduser.png"))); // NOI18N

        btnExcluirImg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcluirImg.setText("Excluir Imagem");
        btnExcluirImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcluirImgMouseClicked(evt);
            }
        });

        btnAdicionarImg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdicionarImg.setText("Escolher Imagem");
        btnAdicionarImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdicionarImgMouseClicked(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtEvtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtEvtData, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEvtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEvtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(107, 107, 107)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEvtTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnExcluirImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdicionarImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEvtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addGap(37, 37, 37)
                        .addComponent(btnAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemover))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEvtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdicionarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluirImg))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEvtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEvtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEvtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEvtTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEvtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtEvtData, txtEvtInicio, txtEvtLocal, txtEvtNome, txtEvtTermino});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdicionarImg, btnExcluirImg});

        setBounds(0, 0, 895, 521);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEvtLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEvtLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEvtLocalActionPerformed

    private void txtEvtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEvtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEvtDataActionPerformed

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
        alterar();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        remover_cliente();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void txtEvtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEvtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEvtIdActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
      String sql = "select nome as Nome,dataevento as Data, inicio as Inicio,termino as Término,descricao as Descrição,localevento as Local,idevento as ID from tbeventos";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            tblEventos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
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

    private void btnAdicionarImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarImgMouseClicked
        procurar_foto();
    }//GEN-LAST:event_btnAdicionarImgMouseClicked

    private void btnExcluirImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirImgMouseClicked
        lblEvtFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/addUser.png")));
    }//GEN-LAST:event_btnExcluirImgMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAdicionarImg;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluirImg;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEvtFoto;
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
