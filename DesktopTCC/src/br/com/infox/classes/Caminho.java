package br.com.infox.classes;

public class Caminho {
    private String pastaEventos;
    private String pastaGaleria;
    private String pastaTutor;
    
    public void setPastaEventos(String pastaEvento){
        this.pastaEventos = pastaEvento;
    }
    
    public String getPastaEventos(){
        return this.pastaEventos;
    }
    
    public void setPastaGaleria(String pastaGaleria){
        this.pastaGaleria = pastaGaleria;
    }
    
    public String getPastaGaleria(){
        return this.pastaGaleria;
    }
    
    public void setPastaTutor(String pastaTutor){
        this.pastaTutor = pastaTutor;
    }
    
    public String getPastaTutor(){
        return this.pastaTutor;
    }
    
    
}
