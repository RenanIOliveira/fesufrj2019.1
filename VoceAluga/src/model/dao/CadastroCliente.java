package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

import model.Cliente;

public class CadastroCliente {
	private static ArrayList<Cliente> clientesBuscados;
	private static Cliente clienteAtual;


	public  static void buscarClientes(Cliente cliente){
		clientesBuscados = new ArrayList<Cliente>();
		
		Map<String,String> campos = obterValoresDosCamposDoCliente(cliente); 	
		String query = "SELECT * FROM Clientes";
		query = adicionarParametrosQueryDeBusca(query,campos);
		
		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs    = stmt.executeQuery(query);
			
			while((rs.next())){
				Cliente clienteEncontrado= new Cliente(rs.getString("nome"),
						rs.getString("CPF"), rs.getString("passaporte"),
   			 			rs.getString("email"), rs.getString("telefone"),
						rs.getString("CEP"), rs.getString("pais"),
   			 			rs.getString("estado"), rs.getString("cidade"),
						rs.getString("bairro"), rs.getString("rua"),
   			 			rs.getString("numero"), rs.getString("complemento"),
						rs.getDate("dataDeNascimento").toLocalDate());


		       	 if(buscarHabilitacaoPorCPF(clienteEncontrado.getCPF())){
		       		 clienteEncontrado.setHabilitacao(CadastroHabilitacao.habilitacaoAtual);
		       	 }
		       	 else if(buscarHabilitacaoPorPassaporte(clienteEncontrado.getPassaporte())){
		       		clienteEncontrado.setHabilitacao(CadastroHabilitacao.habilitacaoAtual);
		       	 }
		       	 
		       	clientesBuscados.add(clienteEncontrado);
			}
			
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método buscarClientes");
			System.err.println(e.getMessage());
		}
		
	}
	
	public static String adicionarParametrosQueryDeBusca(String query, Map<String,String> campos){
		boolean algumParametroAdicionado = false; // indica se algum parametro ja foi adicionado apos WHERE
		String[] nomeDosCampos = campos.keySet().toArray(new String[campos.size()]);

		
		if(campos.size()!=0){
			query = query+" WHERE";
			
			for(int i=0; i<campos.size(); i++){
					query= query + gerarParametroParaQueryDeBusca(nomeDosCampos[i], campos, algumParametroAdicionado);
					algumParametroAdicionado = true;
				}
		}
		return query;
	}

	public static String gerarParametroParaQueryDeBusca(String nomeDoCampo,Map<String,String> campos,boolean algumParametroAdicionado){
		String parametro = " ";
		if(algumParametroAdicionado) parametro = "AND ";
		parametro = parametro + nomeDoCampo+"=\""+campos.get(nomeDoCampo)+"\" ";
		return parametro;
	}
	
	public static Map<String,String> obterValoresDosCamposDoCliente(Cliente cliente){
		Map<String, String> campos = new TreeMap<>();
		if(cliente.getNome() != null) campos.put("nome", cliente.getNome());
		if(cliente.getCPF() != null) campos.put("CPF", cliente.getCPF());
		if(cliente.getPassaporte() != null) campos.put("passaporte",cliente.getPassaporte());
		if(cliente.getCEP()!= null) campos.put("CEP", cliente.getCEP());
		if(cliente.getTelefone()!= null) campos.put("telefone", cliente.getTelefone());
		if(cliente.getEmail()!= null) campos.put("email", cliente.getEmail());

		
		return campos;
	}
	
	public static boolean alterarCliente(TreeMap<String,String> campos){

		buscarPorCPF(campos.get("CPF"));
		
		
		String query = "UPDATE Clientes SET ";
		query = adicionarParametrosQueryUpdate(query, campos);
		
		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método alterarCliente");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public static String adicionarParametrosQueryUpdate(String query,TreeMap<String,String> campos){
		boolean algumParametroAdicionado = false;
		
		
		for(String key : campos.keySet()){
			
			if((key.equals("CPF")||key.equals("passaporte"))&& campos.get(key).equals("")){ //nao permite que CPF ou passaporte vazio seja adicionado
				if(algumParametroAdicionado){
					query = query + ","+key+"=NULL ";
				}
				else{
					query = query +key+"=NULL ";
					algumParametroAdicionado=true;
				}
			}
			else{
				if(algumParametroAdicionado){
					query = query + ","+key+"=\""+campos.get(key)+"\" ";
				}
				else{
					query = query +key+"=\""+campos.get(key)+"\" ";
					algumParametroAdicionado=true;
				}
			}
		}
		
		query = query + " WHERE ";
		
		if(campos.containsKey("CPF")){
			query = query+"CPF=\""+ campos.get("CPF")+"\";";
		}
		else if(campos.containsKey("passaporte")){
			query = query +"passaporte=\""+ campos.get("passaporte")+"\";";
		}

		return query;
	}
	
	public static boolean cadastrarCliente(TreeMap<String,String> campos){
		String query = "INSERT INTO Clientes ";
		query = adicionarParametrosQueryDeInsercao(query,campos);

		
		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método cadastrarCliente");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public static String adicionarParametrosQueryDeInsercao(String query,TreeMap<String,String> campos){
		boolean algumParametroAdicionado = false; // indica se algum parametro ja foi adicionado apos WHERE
		
		query = query+ " (";
		for(String key : campos.keySet()){
			if(!algumParametroAdicionado){
				query= query + key;
				algumParametroAdicionado = true;
			}
			else{
				query = query +","+key;
			}
		}
		query = query +") VALUES ("; 
		algumParametroAdicionado=false;
		
		for(String key: campos.keySet()){
			if((key.equals("CPF")||key.equals("passaporte"))&& campos.get(key).equals("")){
				if(!algumParametroAdicionado){
					query= query + "NULL";
					algumParametroAdicionado = true;
				}
				else{
					query = query +","+"NULL";
				}
			}
			else{
				if(!algumParametroAdicionado){
					query= query + "\""+campos.get(key)+"\"";
					algumParametroAdicionado = true;
				}
				else{
					query = query +","+"\""+campos.get(key)+"\"";
				}
			}
		}
		
		query = query + ");";
		return query;
	}

	public static boolean deletarCliente(String CPF){
		String query = "DELETE FROM Clientes WHERE CPF=\""+CPF+"\";";

		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método deletarCliente");
			System.err.println(e.getMessage());

			return false;
		}
	}

	public static ArrayList<Cliente> getClientesBuscados() {
		return clientesBuscados;
	}
	
	public static boolean buscarPorCPF(String CPF) {
		clienteAtual = null;
		
		String query = "SELECT * FROM Clientes WHERE CPF=\""+CPF+"\";";
		
		
		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				Cliente clienteEncontrado = new Cliente(rs.getString("nome"),
						rs.getString("CPF"), rs.getString("passaporte"),
						rs.getString("email"), rs.getString("telefone"),
						rs.getString("CEP"),rs.getString("pais"),
						rs.getString("estado"), rs.getString("cidade"),
						rs.getString("bairro"), rs.getString("rua"),
						rs.getString("numero"), rs.getString("complemento"),
						rs.getDate("dataDeNascimento").toLocalDate());
				
				 if(buscarHabilitacaoPorCPF(clienteEncontrado.getCPF())){
		       		 clienteEncontrado.setHabilitacao(CadastroHabilitacao.habilitacaoAtual);
		       	 }
		    	
				clienteAtual = clienteEncontrado;
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método buscarPorCPF");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean buscarPorPassaporte(String passaporte) {
		clienteAtual = null;
		
		String query = "SELECT * FROM Clientes WHERE passaporte=\""+passaporte+"\";";

		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				Cliente clienteEncontrado = new Cliente(rs.getString("nome"),
						rs.getString("CPF"), rs.getString("passaporte"),
						rs.getString("email"), rs.getString("telefone"),
						rs.getString("CEP"),rs.getString("pais"),
						rs.getString("estado"), rs.getString("cidade"),
						rs.getString("bairro"), rs.getString("rua"),
						rs.getString("numero"), rs.getString("complemento"),
						rs.getDate("dataDeNascimento").toLocalDate());
				
				
				
				 if(buscarHabilitacaoPorPassaporte(clienteEncontrado.getPassaporte())){
		       		 clienteEncontrado.setHabilitacao(CadastroHabilitacao.habilitacaoAtual);
		       	 }
		    
		       	 
		       	clientesBuscados.add(clienteEncontrado);
				
				
				clienteAtual = clienteEncontrado;
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método buscarPorPassaporte");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static Cliente getClienteAtual() {
		return clienteAtual;
	}

	public static void setClienteAtual(Cliente cliente) {
		clienteAtual = cliente;
	}

	public static boolean cadastrarHabilitacaoPorCPF(String CPF,Map<String,String> camposHabilitacao){
		TreeMap<String,String> camposCliente = new TreeMap<>();
		camposCliente.put("CPF", CPF);
		camposCliente.put("habilitacaoNumeroDeRegistro", camposHabilitacao.get("numeroDeRegistro"));
		if(buscarPorCPF(CPF))
			if(alterarCliente(camposCliente))
				if(CadastroHabilitacao.cadastrarHabilitacao(camposHabilitacao))
					return true;				

		return false;
	}
	
	public static boolean cadastrarHabilitacaoPorPassaporte(String passaporte,Map<String,String> camposHabilitacao){
		TreeMap<String,String> camposCliente = new TreeMap<>();
		camposCliente.put("passaporte", passaporte);
		camposCliente.put("habilitacaoNumeroDeRegistro", camposHabilitacao.get("numeroDeRegistro"));
		if(buscarPorPassaporte(passaporte))
			if(alterarCliente(camposCliente))
				if(CadastroHabilitacao.cadastrarHabilitacao(camposHabilitacao))
					return true;				
			
		return false;
	}
	
	public static boolean deletarHabilitacaoPorCPF(String CPF){
		
		String query = "SELECT * FROM Clientes WHERE CPF=\""+CPF+"\";";
		String numeroDeRegistro;
		
		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				numeroDeRegistro = rs.getString("habilitacaoNumeroDeRegistro");
				if(CadastroHabilitacao.deletarHabilitacao(numeroDeRegistro))
					return true;	
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método deletarHabilitacaoPorCPF");
			System.err.println(e.getMessage());
			return false;
		}
			
	}
	
	public static boolean deletarHabilitacaoPorPassaporte(String passaporte){
		
		String query = "SELECT * FROM Clientes WHERE passaporte=\""+passaporte+"\";";
		String numeroDeRegistro;
		
		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				numeroDeRegistro = rs.getString("habilitacaoNumeroDeRegistro");
				if(CadastroHabilitacao.deletarHabilitacao(numeroDeRegistro))
					return true;	
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método deletarHabilitacaoPorPassaporte");
			System.err.println(e.getMessage());
			return false;
		}
			
	}
	
	public static boolean buscarHabilitacaoPorCPF(String CPF){
		String query = "SELECT * FROM Clientes WHERE CPF=\""+CPF+"\";";
		String numeroDeRegistro;
		
		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				numeroDeRegistro = rs.getString("habilitacaoNumeroDeRegistro");
				if(CadastroHabilitacao.buscarHabilitacaoPorNumeroDeRegistro(numeroDeRegistro)){
					return true;
				}
						
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método buscarHabilitacaoPorCPF");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static boolean buscarHabilitacaoPorPassaporte(String passaporte){
		String query = "SELECT * FROM Clientes WHERE passaporte=\""+passaporte+"\";";
		String numeroDeRegistro;
		
		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				numeroDeRegistro = rs.getString("habilitacaoNumeroDeRegistro");
				if(CadastroHabilitacao.buscarHabilitacaoPorNumeroDeRegistro(numeroDeRegistro)){
					return true;
				}
						
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroCliente: método buscarHabilitacaoPorPassaporte");
			System.err.println(e.getMessage());
			return false;
		}
	}
}













