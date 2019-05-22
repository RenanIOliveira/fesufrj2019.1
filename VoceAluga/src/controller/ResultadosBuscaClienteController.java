package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;

import controller.ScenesManager;
import model.Cliente;
import model.server.CadastroCliente;




public class ResultadosBuscaClienteController {
	
	ScenesManager manager = new ScenesManager();
	
	
	ObservableList<Cliente> listaDeClientesBuscados = FXCollections.observableArrayList();
	
	@FXML
	ListView<String> listaExibidaNaTela;
	
	@FXML
	void processarBotaoConfirmar(MouseEvent e) throws IOException {
		int indexDoClienteSelecionado = listaExibidaNaTela.getSelectionModel().getSelectedIndex();
		
		if(indexDoClienteSelecionado!= -1) {
			CadastroCliente.setClienteAtual(listaDeClientesBuscados.get(indexDoClienteSelecionado));
			manager.mostrarTelaInformacoesDoCliente();
		}
	}

	@FXML
	void processarBotaoCancelar(MouseEvent e) throws IOException {
		manager.mostrarTelaPrincipal();
	}

	public void initialize() {
		
		//gera lista de clientes com nome e cpf e exibe no elemento listaExibidaNaTela
		
		listaDeClientesBuscados.addAll(CadastroCliente.getClientesBuscados());
		ObservableList <String> listaDeNomesDeClientesBuscados = FXCollections.observableArrayList();
		
		listaDeClientesBuscados.forEach(cliente ->{
			listaDeNomesDeClientesBuscados.add(cliente.getNome() +"   "+cliente.getCpf());
		});


		listaExibidaNaTela.setItems(listaDeNomesDeClientesBuscados);
		
	}

	
}
