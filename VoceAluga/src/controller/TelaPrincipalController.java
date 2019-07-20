package controller;

import java.io.IOException;
import java.util.TreeMap;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import model.Veiculo;
import model.dao.CadastroCliente;
import model.dao.CadastroFuncionario;
import model.dao.CadastroVeiculo;
import model.dao.ValidadorDeLogin;
import model.Cliente;
import model.Funcionario;
import controller.ScenesManager;

public class TelaPrincipalController {
	ScenesManager manager = new ScenesManager();
	
	public void initialize() {
		Funcionario funcionarioLogado = ValidadorDeLogin.getFuncionarioLogado();
		
		int nivel = funcionarioLogado.getNivelDeAcesso();
		usuario.setText(funcionarioLogado.getNome());
		cargo.setText(funcionarioLogado.getCargo());
		NivelDeAcesso.setText(Integer.toString(nivel));
		
		tabClientes.setDisable(false);
		
		
		
		if (nivel  <= 1) {
			
			tabVeiculos.setDisable(true);;
			tabFuncionarios.setDisable(true);
		}
		else {
			tabVeiculos.setDisable(false);;
			tabFuncionarios.setDisable(false);
		}
		
	}
	
	
	
	/// ==========Cliente =============
	
	
		
	 @FXML
	 private Label usuario;
	 
	 @FXML
	 private Label cargo;
	 
	 @FXML
	 private Label NivelDeAcesso;
	 
	 @FXML
	 private TabPane painel;
	 
	 @FXML
	 private Tab tabClientes;
	 
	 @FXML
	 private Tab tabVeiculos;
	 
	 @FXML
	 private Tab tabFuncionarios;
	 
	 @FXML
	 private TextField CampoNome;
	 
	 @FXML
	 private TextField CampoCPF;
	 
	 @FXML
	 private TextField CampoPassaporte;
	 
	 @FXML
	 private TextField CampoCEP;
	 
	 @FXML
	 private TextField CampoTelefone;
	 
	 @FXML
	 private TextField CampoEmail;


	@FXML
	void processarBotaoCadastrarNovoCliente(MouseEvent e) throws IOException {
	 	CadastroCliente.setClienteAtual(null);
		manager.mostrarTelaCadastroDeCliente();
	}
	 
	 
	@FXML
	void processarBotaoBuscarCliente(MouseEvent e) throws IOException{
		String nome = CampoNome.getText();
		String CPF = CampoCPF.getText().trim();
		String passaporte = CampoPassaporte.getText().trim();
		String CEP = CampoCEP.getText().trim();
		String email = CampoEmail.getText().trim();
		String telefone = CampoTelefone.getText();

		Cliente cliente= new Cliente();

		if(!telefone.equals("")) cliente.setTelefone(telefone);
		if(!CPF.equals("")) cliente.setCpf(CPF);
		if(!nome.equals("")) cliente.setNome(nome);
		if(!passaporte.equals("")) cliente.setPassaporte(passaporte);
		if(!CEP.equals("")) cliente.setCEP(CEP);
		if(!email.equals("")) cliente.setEmail(email);

		CadastroCliente.buscarClientes(cliente);


		if(CadastroCliente.getClientesBuscados().size()>0)
			manager.mostrarTelaResultadosBuscaCliente();
		else
			exibirErroNenhumClienteEncontrado();
	}
	 
	private void exibirErroNenhumClienteEncontrado() {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Erro");
    	alert.setHeaderText("Nenhum cliente corresponde aos campos informados.");
    	alert.setContentText("");
    	alert.showAndWait();
    	
    	CampoNome.setText("");
    	CampoCPF.setText("");
    	CampoPassaporte.setText("");
    	CampoCEP.setText("");
    	CampoTelefone.setText("");
    	CampoEmail.setText("");
    	
		
	}

//============Veículo===============

	@FXML
	private TextField CampoFilial;
	
	@FXML
	private TextField CampoChassi;
	
	@FXML
	private TextField CampoPlaca;
	
	@FXML
	private TextField CampoMarca;
	
	@FXML
	private TextField CampoModelo;
	
	@FXML
	private TextField CampoAnoDeFabricacao;
	
	@FXML
	private TextField CampoClasse;
	
	@FXML
	public void processarBotaoBuscarVeiculo(MouseEvent e) throws IOException {
		String filial = CampoFilial.getText();
		String chassi = CampoChassi.getText();
		String placa = CampoPlaca.getText();
		String marca = CampoMarca.getText();
		String ano = CampoAnoDeFabricacao.getText();
		String classe = CampoClasse.getText();
		String modelo = CampoModelo.getText();
		
		Veiculo veiculo = new Veiculo();
		
		
		if(!filial.equals("")) veiculo.setFilial(filial);
		if(!chassi.equals("")) veiculo.setChassi(chassi);
		if(!placa.equals("")) veiculo.setPlaca(placa);
		if(!marca.equals("")) veiculo.setMarca(marca);
		if(!modelo.equals("")) veiculo.setModelo(modelo);
		if(!ano.equals("")) {
			try {
				veiculo.setAnoDeFabricacao(Integer.parseInt(ano));
			} catch (NumberFormatException nfe) {
				System.err.println("Erro na formatacao do Ano de Fabricacao do Veiculo");
			}
		}
		if(!classe.equals("")) veiculo.setClasse(classe);
		

		CadastroVeiculo.buscarVeiculos(veiculo);
		manager.mostrarTelaResultadosBuscaVeiculo();
	}
	
	@FXML
	void processarBotaoCadastrarNovoVeiculo(MouseEvent e) throws IOException {
		CadastroVeiculo.setVeiculoAtual(null);
		manager.mostrarTelaCadastroDeVeiculo();
	}

//============Funcionario==============

	@FXML
	private TextField CampoNomeFuncionario;
	
	@FXML
	private TextField CampoCPFFuncionario;
	
	@FXML 
	private TextField CampoNivelDeAcessoFuncionario;
	
	@FXML
	private TextField CampoCargoFuncionario;
	
	@FXML
	private TextField CampoLoginFuncionario;

	@FXML
	void processarBotaoBuscarFuncionario(MouseEvent e) throws IOException{
		String nome = CampoNomeFuncionario.getText();
		String CPF = CampoCPFFuncionario.getText().trim();
		String nivelDeAcesso = CampoNivelDeAcessoFuncionario.getText().trim();
		String cargo = CampoCargoFuncionario.getText().trim();
		String login = CampoLoginFuncionario.getText().trim();

		TreeMap<String,String> campos = new TreeMap<>();
		
		if(!nome.equals("")) campos.put("nome", nome);
		if(!CPF.equals("")) campos.put("CPF", CPF);
		if(!nivelDeAcesso.equals("")) campos.put("nivelDeAcesso", nivelDeAcesso);
		if(!cargo.equals("")) campos.put("cargo", cargo);
		if(!login.equals("")) campos.put("login", login);
		
		
		if(CadastroFuncionario.buscarFuncionarios(campos))
			manager.mostrarTelaResultadosBuscaFuncionario();
		else
			exibirErroNenhumFuncionarioEncontrado();
	}
	

	private void exibirErroNenhumFuncionarioEncontrado() {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Erro");
    	alert.setHeaderText("Nenhum funcionário corresponde aos campos informados.");
    	alert.setContentText("");
    	alert.showAndWait();
    	
    	CampoNomeFuncionario.setText("");
    	CampoCPFFuncionario.setText("");
    	CampoNivelDeAcessoFuncionario.setText("");
    	CampoCargoFuncionario.setText("");
    	CampoLoginFuncionario.setText("");
    	
	}
	
	@FXML
	void processarBotaoCadastrarFuncionario(MouseEvent e) throws IOException{
		CadastroFuncionario.setFuncionarioAtual(null);
		manager.mostrarTelaCadastroDeFuncionario();
	}
	
	
	 

}


