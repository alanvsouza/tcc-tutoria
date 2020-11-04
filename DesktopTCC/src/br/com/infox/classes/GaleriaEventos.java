package br.com.infox.classes;

import br.com.infox.dal.ModuloConexao;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class GaleriaEventos {
    private final Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    private File diretorio;
     
    public GaleriaEventos(){
        this.conexao =  ModuloConexao.conector();
    }
    
    public void pesquisarGaleriaEventos(String sql, JTable tblEventos, String txtEvtPesquisar){
        try{
            pst = conexao.prepareStatement(sql);
            if(txtEvtPesquisar != null) pst.setString(1, txtEvtPesquisar + "%");
            rs = pst.executeQuery();
            tblEventos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao procurar galeria de eventos");
        }
    }
    
    public boolean  verificarCamposGaleria(String[] camposObrigatorios){
        for(int i = 0; i < camposObrigatorios.length; i++){
            if(camposObrigatorios[i].length() == 0){
                return true;
            }
        }
        return false;
    }
    
    public boolean existeDir(String caminho){
        diretorio = new File(caminho);
        if (diretorio.exists()){
            return true;
        }else{
            return false;
        }
    }
    
    public void clearCamposGaleria(JTextField[] campos, JTextArea descricao, JButton btnIcon, JButton btnFotos, JLabel indexFoto){
        for(int i = 0; i < campos.length; i++){
            campos[i].setText(null);
        }
        btnIcon.setIcon(null);
        btnFotos.setIcon(null);
        indexFoto.setText("x / y");
        descricao.setText(null);
    }

}
