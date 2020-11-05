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
    Imagem img = new Imagem();
     
    public GaleriaEventos(){
        this.conexao =  ModuloConexao.conector();
    }
    
    public void setCamposGaleria(JTable tbl, JTextField[] campos, JTextArea descricao){
        int setar = tbl.getSelectedRow();
        for(int i = 0; i < campos.length; i++){
            if(campos[i] != null) campos[i].setText(tbl.getModel().getValueAt(setar, i).toString()); 
            else descricao.setText(tbl.getModel().getValueAt(setar, i).toString());
            campos[6].setText(tbl.getModel().getValueAt(setar, 6).toString().replace(".jpg","").replace(".png", ""));
        }
    }
    
    
    public void pesquisarGaleriaEventos(String sql, JTable tblEventos, String txtEvtPesquisar){
        try{
            pst = conexao.prepareStatement(sql);
            if(txtEvtPesquisar != null) pst.setString(1, txtEvtPesquisar + "%");
            rs = pst.executeQuery();
            tblEventos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e);
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
        indexFoto.setText("0 / 0");
        descricao.setText(null);
    }
    
    public boolean consultarImagem(String sql, String nomeImagem, String extensao, String txtGaleriaEventoId){
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtGaleriaEventoId);
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
    
    public String[] adquirirImagens(String txtGaleriaEventoId){
        String sql = "select imagem from tbimgeventos where idevento = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtGaleriaEventoId);
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

}
