
package br.com.infox.classes;

import br.com.infox.dal.ModuloConexao;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;


public class Eventos {
    private final Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    

    public Eventos(){
        this.conexao =  ModuloConexao.conector();
    }
    
     public void setCamposEvento(JTable tbl, JTextField[] campos, JTextArea descricao){
        int setar = tbl.getSelectedRow();
        for(int i = 0; i < campos.length; i++){
            if(campos[i] != null) campos[i].setText(tbl.getModel().getValueAt(setar, i).toString()); 
            else descricao.setText(tbl.getModel().getValueAt(setar, i).toString());
            campos[6].setText(tbl.getModel().getValueAt(setar, 6).toString().replace(".jpg","").replace(".png", ""));
        }
    }
     
     public int verificarCamposEvento(String[] camposObrigatorios, String data, String inicio, String termino){
        for(int i = 0; i < camposObrigatorios.length; i++){
            if(camposObrigatorios[i].length() == 0){
                return 1;
            }
        }
        
        int count = 0;
        char caractere = ' ';
        
        if(data.length() < 10){
           JOptionPane.showMessageDialog(null,"O campo de DATA do evento deve ter 10 caracteres e deve estar no seguinte formato: dd/MM/yyyy");
           return 2;
         }
        
        for(int i = 0; i < data.length(); i++){
           caractere = data.replace("/","-").charAt(i);
           if(caractere == '-') count++;
        }
        
        if(count != 2){
            JOptionPane.showMessageDialog(null,"O campo de DATA do evento deve ter 2 caracteres de barra (/) como no seguinte exemplo: 12/12/2020");
            return 2;
        }
        
        if(inicio.length() != 5){
           JOptionPane.showMessageDialog(null,"O campo de INÍCIO deve ter 5 caracteres e deve estar no seguinte formato: hh:mm. Exemplo: 12:10");
           return 2;
        }
         
        if(termino.length() != 5){
            JOptionPane.showMessageDialog(null,"O campo de TÉRMINO deve ter 5 caracteres e deve estar no seguinte formato: hh:mm. Exemplo: 13:05");
            return 2;
        }
        
        return 0;
    }

     public void clearCamposEvento(JTextField[] campos, JTextArea descricao, JButton btnIcon){
        for(int i = 0; i < campos.length; i++){
            campos[i].setText(null);
        }
        btnIcon.setIcon(null);
        descricao.setText(null);
    }
    
     public void pesquisarEventos(String sql, JTable tblEventos, JTextField txtEvtPesquisar){
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEvtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblEventos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e);
        }
    }
}
