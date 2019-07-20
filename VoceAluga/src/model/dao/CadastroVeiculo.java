package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


import model.Veiculo;



public class CadastroVeiculo {
	private static ArrayList<Veiculo> VeiculosBuscados;
	private static Veiculo veiculoAtual;

	// CODIGO DE TESTE
	public static void buscarVeiculos(Veiculo veiculo){
		VeiculosBuscados = new ArrayList<Veiculo>();
		//VeiculosBuscados.add(new Veiculo(123456L,"Fiat","Uno","ABC-1234","123456",30000));
		//VeiculosBuscados.add(new Veiculo(456789L,"Ford","Ka","DEF-5678","456789",0));

		Map<String,String> campos = obterValoresDosCamposDoVeiculo(veiculo);
		String query = "SELECT * FROM Veiculos";
		query = adicionarParametrosQueryDeBusca(query,campos);

		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs    = stmt.executeQuery(query);

			while((rs.next())){
				Veiculo veiculoEncontrado = new Veiculo(rs.getInt("id"), rs.getString("filial"),
						rs.getString("chassi"), rs.getString("RENAVAM"),
						rs.getString("placa"), rs.getString("marca"),
						rs.getString("modelo"), rs.getInt("ano"),
						rs.getInt("quilometragem"),rs.getString("classe"),
						rs.getString("cor"), rs.getString("manutencaoEmDia"),
						rs.getString("infoAdicional"));
				VeiculosBuscados.add(veiculoEncontrado);
			}

		} catch (SQLException e) {
			System.err.println("model.dao.CadastroVeiculo: método buscarVeiculos");
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

	public static Map<String,String> obterValoresDosCamposDoVeiculo(Veiculo veiculo) {
		Map<String, String> campos = new TreeMap<>();
		if (veiculo.getFilial() != null) campos.put("filial", veiculo.getFilial());
		if (veiculo.getChassi() != null) campos.put("chassi", veiculo.getChassi());
		if (veiculo.getRENAVAM() != null) campos.put("RENAVAM", veiculo.getRENAVAM());
		if (veiculo.getPlaca() != null) campos.put("placa", veiculo.getPlaca());
		if (veiculo.getMarca() != null) campos.put("marca", veiculo.getMarca());
		if (veiculo.getModelo() != null) campos.put("modelo", veiculo.getModelo());
		if (veiculo.getAnoDeFabricacao() != null) campos.put("ano", String.valueOf(veiculo.getAnoDeFabricacao()));
		if (veiculo.getQuilometragem() != null) campos.put("quilometragem", String.valueOf(veiculo.getQuilometragem()));
		if (veiculo.getInformacoesAdicionais() != null) campos.put("infoAdicional", veiculo.getInformacoesAdicionais());

		return campos;
	}

	public static boolean alterarVeiculo(TreeMap<String,String> campos){

		buscarPorChassi(campos.get("chassi"));


		String query = "UPDATE Veiculos SET ";
		query = adicionarParametrosQueryUpdate(query, campos);

		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroVeiculo: método alterarVeiculo");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static String adicionarParametrosQueryUpdate(String query,TreeMap<String,String> campos){
		boolean algumParametroAdicionado = false;

		for(String key : campos.keySet()){

			if((key.equals("chassi"))&& campos.get(key).equals("")){ //nao permite que Chassi vazio seja adicionado
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

		if(campos.containsKey("chassi")){
			query = query+"chassi=\""+ campos.get("chassi")+"\";";
		}

		return query;
	}

	public static boolean cadastrarVeiculo(TreeMap<String,String> campos){
		String query = "INSERT INTO Veiculos ";
		query = adicionarParametrosQueryDeInsercao(query,campos);

		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroVeiculo: método cadastrarVeiculo");
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
			if((key.equals("chassi"))&& campos.get(key).equals("")){
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

	public static boolean deletarVeiculo(String Chassi){
		String query = "DELETE FROM Veiculos WHERE chassi=\""+Chassi+"\";";

		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroVeiculo: método deletarVeiculo");
			System.err.println(e.getMessage());

			return false;
		}
	}

	public static ArrayList<Veiculo> getVeiculosBuscados() {
		return VeiculosBuscados;
	}

	public static boolean buscarPorMarca(String marca) {
		veiculoAtual = null;

		String query = "SELECT * FROM Veiculos WHERE marca=\""+marca+"\";";

		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				Veiculo veiculoEncontrado = new Veiculo(rs.getInt("id"), rs.getString("filial"),
						rs.getString("chassi"), rs.getString("RENAVAM"),
						rs.getString("placa"), rs.getString("marca"),
						rs.getString("modelo"), rs.getInt("ano"),
						rs.getInt("quilometragem"),rs.getString("classe"),
						rs.getString("cor"), rs.getString("manutencaoEmDia"),
						rs.getString("infoAdicional"));

				veiculoAtual = veiculoEncontrado;
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroVeiculo: método buscarPorID");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static boolean buscarPorModelo(String modelo) {
		veiculoAtual = null;

		String query = "SELECT * FROM Veiculos WHERE modelo=\""+modelo+"\";";

		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				Veiculo veiculoEncontrado = new Veiculo(rs.getInt("id"), rs.getString("filial"),
						rs.getString("chassi"), rs.getString("RENAVAM"),
						rs.getString("placa"), rs.getString("marca"),
						rs.getString("modelo"), rs.getInt("ano"),
						rs.getInt("quilometragem"),rs.getString("classe"),
						rs.getString("cor"), rs.getString("manutencaoEmDia"),
						rs.getString("infoAdicional"));

				veiculoAtual = veiculoEncontrado;
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroVeiculo: método buscarPorID");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static boolean buscarPorChassi(String Chassi) {
		veiculoAtual = null;

		String query = "SELECT * FROM Veiculos WHERE chassi=\""+Chassi+"\";";


		try {
			Statement stmt  = MySQLConnector.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				Veiculo veiculoEncontrado = new Veiculo(rs.getInt("id"), rs.getString("filial"),
						rs.getString("chassi"), rs.getString("RENAVAM"),
						rs.getString("placa"), rs.getString("marca"),
						rs.getString("modelo"), rs.getInt("ano"),
						rs.getInt("quilometragem"),rs.getString("classe"),
						rs.getString("cor"), rs.getString("manutencaoEmDia"),
						rs.getString("infoAdicional"));

				veiculoAtual = veiculoEncontrado;
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("model.dao.CadastroVeiculo: método buscarPorChassi");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static Veiculo getVeiculoAtual() {
		return veiculoAtual;
	}

	public static void setVeiculoAtual(Veiculo veiculo) {
		veiculoAtual = veiculo;
	}

}
