package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.Habilitacao;
import model.server.CadastroCliente;

public class InformacoesDoClienteController {
	ScenesManager manager = new ScenesManager();
	
	@FXML
	Label labelNome;
	
	@FXML
	Label labelCPF;
	
	@FXML
	Label labelPais;
	
	@FXML
	Label labelEstado;
	
	@FXML
	Label labelDataDeNascimento;
	
	@FXML
	Label labelCidade;
	
	@FXML
	Label labelPassaporte;
	
	@FXML
	Label labelBairro;
	
	@FXML
	Label labelTelefone;
	
	@FXML
	Label labelRua;
	
	@FXML
	Label labelEmail;
	
	@FXML
	Label labelNumero;
	
	@FXML
	Label labelCEP;
	
	@FXML
	Label labelComplemento;
	
	@FXML
	Label labelNumeroDeRegistro;
	
	@FXML
	Label labelValidade;
	
	@FXML
	Label labelCategoria;
	
	@FXML
	Label labelDataDeEmissao;
	
	@FXML
	public void processarBotaoVoltar(MouseEvent e) throws IOException {
		manager.mostrarTelaPrincipal();
	}
	
	@FXML
	public void processarBotaoEditar(MouseEvent e) throws IOException {
		manager.mostrarTelaCadastroDeCliente();
	}
	
	@FXML 
	public void processarBotaoDeletar(MouseEvent e) throws IOException{
		
	}
	
	public void initialize() {
	 	Cliente cliente = CadastroCliente.getClienteAtual();
	 	
	 	preencherLabelsComDadosDoCliente(cliente);	
		 
	}
	
	private void preencherLabelsComDadosDoCliente(Cliente cliente) {
		Habilitacao habilitacao = cliente.getHabilitacao();
		
		
		labelNome.setText(cliente.getNome());
	 	labelCPF.setText(cliente.getCpf());
	 	labelPais.setText(cliente.getPais());
	 	labelEstado.setText(cliente.getEstado());
	 	labelCidade.setText(cliente.getCidade());
	 	labelPassaporte.setText(cliente.getPassaporte());
	 	labelBairro.setText(cliente.getBairro());
	 	labelTelefone.setText(cliente.getTelefone());
	 	labelRua.setText(cliente.getRua());
	 	labelEmail.setText(cliente.getEmail());
	 	labelNumero.setText(cliente.getNumero());
	 	labelCEP.setText(cliente.getCEP());
	 	labelComplemento.setText(cliente.getComplemento());
	 	
	 	if(cliente.getDataDeNascimento()!=null)
	 		labelDataDeNascimento.setText(cliente.getDataDeNascimento().toString());

	 	labelNumeroDeRegistro.setText(habilitacao.getNumeroDeRegistro());
	 	labelCategoria.setText(habilitacao.getCategoria());
	 	
	 	if(habilitacao.getDataDeEmissao()!=null)
	 		labelDataDeEmissao.setText(habilitacao.getDataDeEmissao().toString());
	 	if(habilitacao.getValidade()!=null)
	 		labelValidade.setText(habilitacao.getValidade().toString());
	}
	

}
