package model.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Funcionario;
import model.Gerente;

public class ValidadorDeLogin {
	
	static public Funcionario FuncionarioLogado;
	
	   	final private static String driver = "com.mysql.cj.jdbc.Driver";
	    final private static String enderecoServidor = "remotemysql.com"; 
	    final private static String usuario = "V0Q2C5knzI"; 
	    final private static String senha = "MO7nFZAUQX";
	    final private static String nomeDoBancoDeDados = "V0Q2C5knzI";
//	    final private static String porta = "3306";
//	    
	    private static Connection connection = null;
	    private Statement statement = null;
//	    private ResultSet result = null;
	
	
	  public static void abrirConexao(){
	        try {
	            Class.forName(driver).newInstance();
	            System.out.println("Driver carregado");
	        } catch (Exception e) {
	            System.err.println("Nao foi possivel carregar o driver do mysql");
	            System.err.println(e);
	        }

	        try {
	            connection = DriverManager.getConnection( 
	            		"jdbc:mysql://"+enderecoServidor+"/"+nomeDoBancoDeDados , usuario, senha );
	            System.out.println("Conexao com o banco de dados estabelecida successfully");   
	        } catch (Exception e) {
	            System.err.println("Nao foi possivel estabelecer a conexao");
	            System.err.println(e);
	        }
	}
	  
	 public static void fecharConexao(){
		try {
			connection.close();
			System.out.println("Conexao Fechada");
		} catch (SQLException e) {
			 System.err.println("Nao foi possivel fechar a conexao");
	         
		}
	 }
	  

	public static boolean  validarLogin(String login,String senha) {
		   FuncionarioLogado = new Funcionario();
	        
	        try {
	        	abrirConexao();
	             Statement stmt  = connection.createStatement();
	             ResultSet rs    = stmt.executeQuery("SELECT * FROM Funcionarios  WHERE login = \""+login+"\" AND senha = \""+senha+"\";");
	             
	             //se algum funcionario foi encontrado instancie e retorne
	             if((rs.next())){
	            	 if(rs.getInt("nivelDeAcesso")==1)
	            		 FuncionarioLogado = new Funcionario(rs.getString("nome"),rs.getString("CPF"),rs.getString("cargo"),rs.getString("login"),rs.getString("senha"));
	            	 else if(rs.getInt("nivelDeAcesso")==2)
	            		 FuncionarioLogado = new Gerente(rs.getString("nome"),rs.getString("CPF"),rs.getString("cargo"),rs.getString("login"),rs.getString("senha"));
		            
	            	return true;
	             }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        finally{
	        	fecharConexao();
	        }
			return false;
	
		
		
		
	}
	
	

}
