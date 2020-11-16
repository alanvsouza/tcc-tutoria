package br.com.infox.classes;

import br.com.infox.dal.ModuloConexao;
import br.com.infox.telas.TelaEventos;
import br.com.infox.telas.TelaTutor;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Imagem {
   
    private final Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public Imagem(){
        this.conexao =  ModuloConexao.conector();
    }
    
    public boolean consultarImagem(String sql, String nomeImagem, String extensao){
        try{
            pst = conexao.prepareStatement(sql);
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
     
    public void deletarImagem(String sql, String txtEvtId, String excecao, String caminhoImg){
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtEvtId);
            rs = pst.executeQuery();
                if(rs.next()){
                   File imagem = new File(caminhoImg + rs.getString(1));
                   imagem.delete();
                }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,excecao);
        }
    }
    
    public void copiarImagem(String tela,String caminhoOrigem, String caminhoDestino, String nomeEvt, String extensao){
            
            FileInputStream origem = null;
            FileOutputStream destino = null;
            FileChannel fcOrigem;
            FileChannel fcDestino;

            try {
                origem = new FileInputStream(caminhoOrigem);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(tela).log(Level.SEVERE, null, ex);
            }
            try {
                destino = new FileOutputStream(caminhoDestino + nomeEvt + extensao);
            } catch (FileNotFoundException ex) {
                    Logger.getLogger(tela).log(Level.SEVERE, null, ex);
            }
            
            fcOrigem = origem.getChannel();
            fcDestino = destino.getChannel();
            
                try {
                    fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
                } catch (IOException ex) {
                    Logger.getLogger(tela).log(Level.SEVERE, null, ex);
                }
                try {
                    origem.close();
                } catch (IOException ex) {
                    Logger.getLogger(tela).log(Level.SEVERE, null, ex);
                }
                try {
                    destino.close();
                } catch (IOException ex) {
                    Logger.getLogger(tela).log(Level.SEVERE, null, ex);
                }
     }
    
    public void selecionarImagem(JTextField arquivo,JTextField nome, JButton btnImg,int largura,int altura){
     try {
            FileDialog fileDialog = new FileDialog((Frame)null);
            fileDialog.setVisible(true);
            if(fileDialog.getDirectory() != null){
                arquivo.setText(fileDialog.getDirectory() + fileDialog.getFile());
                nome.setText(fileDialog.getFile().replace(".jpg","").replace(".png",""));
                carregaImagem(btnImg,fileDialog.getDirectory() + fileDialog.getFile(), largura, altura);
            }
        }catch(Exception e) {
           JOptionPane.showMessageDialog(null,"Erro ao tentar carregar o arquivo! Verifique se o arquivo selecionado é uma imagem.");
        }   
    }
    
     public void carregaImagem(JButton botao, String arquivo, int largura, int altura){
            try {
                File f = new File(arquivo);
                BufferedImage bufi = ImageIO.read(f);
                ImageIcon ico = new ImageIcon(bufi);
                ico.setImage(ico.getImage().getScaledInstance(largura, altura, java.awt.Image.SCALE_SMOOTH));
                botao.setIcon(ico);
            } 
            catch(Exception e) {
               JOptionPane.showMessageDialog(null,"Erro ao tentar carregar o arquivo! Verifique se o arquivo selecionado é uma imagem.");
            }
     }

}
