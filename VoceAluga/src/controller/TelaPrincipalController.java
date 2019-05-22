package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
	 void processarBotaoCadastrarNovoCliente(MouseEvent e) throws IOException {
		 CadastroCliente.setClienteAtual(new Cliente());
		 manager.mostrarTelaCadastroDeCliente();
	 }
	 
	 //NAOIMPLEMENTADO
	 @FXML
	 void processarBotaoBuscarCliente(MouseEvent e) throws IOException{
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
