package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import model.Veiculo;
import model.server.CadastroVeiculo;
import model.server.ValidadorDeLogin;
import model.server.CadastroCliente;
import model.Cliente;
import controller.ScenesManager;

public class TelaPrincipalController {
	
	ScenesManager manager = new ScenesManager();
		
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
	 private TextField CampoFilial;

	 @FXML
	 private TextField CampoMarca;

	 @FXML
	 private TextField CampoAno;

	 @FXML
	 private TextField CampoChassi;

	 @FXML
	 private TextField CampoClasse;

	 @FXML
	 private TextField CampoPlaca;

	 @FXML
	 private TextField CampoModelo;
	 
	 
	 
	 @FXML
	 void processarBotaoCadastrarNovoCliente(MouseEvent e) throws IOException {
	 	CadastroCliente.setClienteAtual(new Cliente());
		manager.mostrarTelaCadastroDeCliente();
	 }
	 
	 //NAOIMPLEMENTADO
	 @FXML
	 void processarBotaoBuscarCliente(MouseEvent e) throws IOException {
		String nome = CampoNome.getText();
		String CPF = CampoCPF.getText().trim();
		String passaporte = CampoPassaporte.getText().trim();
		String CEP = CampoCEP.getText().trim();
		String email = CampoEmail.getText().trim();

		Cliente cliente= new Cliente();

		if(!CPF.equals("")) cliente.setCpf(CPF);
		if(!nome.equals("")) cliente.setNome(nome);
		if(!passaporte.equals("")) cliente.setPassaporte(passaporte);
		if(!CEP.equals("")) cliente.setCEP(CEP);
		if(!email.equals("")) cliente.setEmail(email);

		CadastroCliente.buscarClientes(cliente);

		manager.mostrarTelaResultadosBuscaCliente();

	 }

	@FXML
	void processarBotaoCadastrarNovoVeiculo(MouseEvent e) throws IOException {
		CadastroVeiculo.setVeiculoAtual(new Veiculo());
		manager.mostrarTelaCadastroDeVeiculo();
	}

	//NAOIMPLEMENTADO
	@FXML
	void processarBotaoBuscarVeiculo(MouseEvent e) throws IOException {
		String filial = CampoFilial.getText();
		String marca = CampoMarca.getText();
		String modelo = CampoModelo.getText();
		String placa = CampoPlaca.getText();
		String chassi = CampoChassi.getText();
		String classe = CampoClasse.getText();
		String anoDeFabricacao = CampoAno.getText();

		Veiculo veiculo = new Veiculo();

		if(!filial.equals("")) veiculo.setFilial(filial);
		if(!marca.equals("")) veiculo.setMarca(marca);
		if(!modelo.equals("")) veiculo.setModelo(modelo);
		if(!placa.equals("")) veiculo.setPlaca(placa);
		if(!chassi.equals("")) veiculo.setChassi(chassi);
		if(!classe.equals("")) veiculo.setClasse(classe.charAt(0));
		if(!anoDeFabricacao.equals("")) veiculo.setAnoDeFabricacao(Integer.parseInt(anoDeFabricacao));

		CadastroVeiculo.buscarVeiculos(veiculo);

		manager.mostrarTelaResultadosBuscaVeiculo();

	}

	public void initialize() {

		int nivel = ValidadorDeLogin.FuncionarioLogado.getNivelDeAcesso();
		usuario.setText(ValidadorDeLogin.FuncionarioLogado.getNome());
		cargo.setText(ValidadorDeLogin.FuncionarioLogado.getCargo());
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
}
