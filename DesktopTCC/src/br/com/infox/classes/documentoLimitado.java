package br.com.infox.classes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class documentoLimitado extends PlainDocument{
    private int tamanhoMax = 10;
         
    public documentoLimitado(int tamanhoMax){
        this.tamanhoMax = tamanhoMax;
    }
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
 
            if (str == null) return;  
                    
             String stringAntiga = getText (0, getLength() );  
             int tamanhoNovo = stringAntiga.length() + str.length(); 
                        
             if (tamanhoNovo <= tamanhoMax) {  
                 super.insertString(offset, str , attr);  
             } else {    
                 super.insertString(offset, "", attr); 
             }  
    }
}
