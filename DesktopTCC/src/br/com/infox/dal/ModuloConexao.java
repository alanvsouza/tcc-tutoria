package br.com.infox.dal;
import java.sql.*;//Importando tudo o que existe dentro do pacote sql

//método responsável por estabalecer a conexão com o banco
public class ModuloConexao {
    public static Connection conector(){
    java.sql.Connection conexao = null;
    //a linha abaixo "chama" o driver através do caminho dele
    String driver = "com.mysql.jdbc.Driver";
    //armazenando informações referentes ao banco
    String url = "jdbc:mysql://143.106.241.3:3306/cl18152";
    //jdbc:mysql:// - referência ao driver |143.106.241.3 - informando que o IP do BD/cl18152 - noome do BD
    String user = "cl18152";
    String password = "cl*07062002";
    //estabelecendo a conexão com o BD
        try {
            Class.forName(driver);//executar o arquivo do driver
            conexao = DriverManager.getConnection(url,user,password);//obtendo a conexão com o DB
            return conexao;
        } catch (Exception e){
            return null;
        }
    }
}

