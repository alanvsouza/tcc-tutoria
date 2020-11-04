package br.com.infox.classes;

import br.com.infox.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;


public class Tutor {
     
    private final Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public Tutor(){
        this.conexao =  ModuloConexao.conector();
    }
    
    public void setCamposTutor(JTable tbl, JTextField[] campos, JTextArea descricao){
        int setar = tbl.getSelectedRow();
        for(int i = 0; i < campos.length; i++){
            if(campos[i] != null) campos[i].setText(tbl.getModel().getValueAt(setar, i).toString()); 
            else descricao.setText(tbl.getModel().getValueAt(setar, i).toString());
            campos[6].setText(tbl.getModel().getValueAt(setar, 6).toString().replace(".jpg","").replace(".png", ""));
        }
    }
    
    public void pesquisarTutor(String sql, JTable tblEventos, JTextField txtEvtPesquisar){
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEvtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblEventos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void clearCamposTutor(JTextField[] campos, JTextArea descricao, JButton btnIcon, JCheckBox[] redesSociais){
        for(int i = 0; i < campos.length; i++){
            campos[i].setText(null);
        }
        
        for(int i = 0; i < redesSociais.length; i++){
            redesSociais[i].setSelected(false);
        }
        
        btnIcon.setIcon(null);
        descricao.setText(null);
    }
    
    public int verificarCamposTutor(String[] camposObrigatorios, String txtTutEmail, String txtTutFone){
        for(int i = 0; i < camposObrigatorios.length; i++){
            if(camposObrigatorios[i].length() == 0 ){
                return 1;
            }
            //E-mail
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(txtTutEmail);
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(null,"O formato de e-mail é inválido!");
                    return 2;
                }
            //Telefone
                String expression2 = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})";
                Pattern pattern2 = Pattern.compile(expression2, Pattern.CASE_INSENSITIVE);
                Matcher matcher2 = pattern2.matcher(txtTutFone);
                if (!matcher2.matches()) {
                    JOptionPane.showMessageDialog(null,"O formato do telefone é inválido!");
                    return 2;
                }
            
        }
        return 0;
    }
}
