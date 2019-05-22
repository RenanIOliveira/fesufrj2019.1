package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.Habilitacao;
import model.server.CadastroCliente;

import java.io.IOException;

import controller.ScenesManager;

public class CadastroClienteController {
	
	
	ScenesManager manager = new ScenesManager();
	
	@FXML
	TextField campoNome;
	
	@FXML
	TextField campoCPF;
	
	@FXML
	TextField campoPais;
	
	@FXML
	TextField campoEstado;
	
	@FXML
	TextField campoDataDeNascimento;
	
	@FXML
	TextField campoCidade;
	
	@FXML
	TextField campoPassaporte;
	
	@FXML
	TextField campoBairro;
	
	@FXML
	TextField campoTelefone;
	
	@FXML
	TextField campoRua;
	
	@FXML
	TextField campoEmail;
	
	@FXML
	TextField campoNumero;
	
	@FXML
	TextField campoCEP;
	
	@FXML
	TextField campoComplemento;
	
	@FXML
	TextField campoNumeroDeRegistro;
	
	@FXML
	TextField campoValidade;
	
	@FXML
	TextField campoCategoria;
	
	@FXML
	TextField campoDataDeEmissao;

	@FXML
	Button BotaoCancelar;

	@FXML
	Button BotaoConfirmar;

	@FXML
	void processarBotaoCancelar(MouseEvent e) throws IOException {
		manager.mostrarTelaPrincipal();
	}
	
	@FXML
	void processarBotaoConfirmar(MouseEvent e) throws IOException{
		
	}
	
	public void initialize() {
	 	Cliente cliente = CadastroCliente.getClienteAtual();
	 	
	 	preencherCamposComDadosDoCliente(cliente);	
	}
	
	private void preencherCamposComDadosDoCliente(Cliente cliente) {
		Habilitacao habilitacao = cliente.getHabilitacao();
		
		
		campoNome.setText(cliente.getNome());
	 	campoCPF.setText(cliente.getCpf());
	 	campoPais.setText(cliente.getPais());
	 	campoEstado.setText(cliente.getEstado());
	 	campoCidade.setText(cliente.getCidade());
	 	campoPassaporte.setText(cliente.getPassaporte());
	 	campoBairro.setText(cliente.getBairro());
	 	campoTelefone.setText(cliente.getTelefone());
	 	campoRua.setText(cliente.getRua());
	 	campoEmail.setText(cliente.getEmail());
	 	campoNumero.setText(cliente.getNumero());
	 	campoCEP.setText(cliente.getCEP());
	 	campoComplemento.setText(cliente.getComplemento());
	 	
	 	if(cliente.getDataDeNascimento()!= null) {
			campoDataDeNascimento.setText(cliente.getDataDeNascimento().toString());
		}

	 	campoNumeroDeRegistro.setText(habilitacao.getNumeroDeRegistro());
	 	campoCategoria.setText(habilitacao.getCategoria());
	 	
	 	if(habilitacao.getDataDeEmissao()!= null) {
			campoDataDeEmissao.setText(habilitacao.getDataDeEmissao().toString());
		}
	 	if(habilitacao.getValidade()!= null) {
			campoValidade.setText(habilitacao.getValidade().toString());
		}
	}
	
}
