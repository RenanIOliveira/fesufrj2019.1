package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import controller.ScenesManager;
import model.Veiculo;
import model.dao.CadastroVeiculo;

public class ResultadosBuscaVeiculoController {
	ScenesManager manager = new ScenesManager();
	
	
	ObservableList<Veiculo> listaDeVeiculosBuscados = FXCollections.observableArrayList();
	
	@FXML
	ListView<String> listaExibidaNaTela;
	
	@FXML
	void processarBotaoConfirmar(MouseEvent e) throws IOException {
		int indexDoVeiculoSelecionado = listaExibidaNaTela.getSelectionModel().getSelectedIndex();
		
		if(indexDoVeiculoSelecionado!= -1) {
			CadastroVeiculo.setVeiculoAtual(listaDeVeiculosBuscados.get(indexDoVeiculoSelecionado));
			manager.mostrarTelaInformacoesDoVeiculo();
		}
	}
	
	@FXML
	void processarBotaoCancelar(MouseEvent e) throws IOException {
		manager.mostrarTelaPrincipal();
	}
	
	public void initialize() {
		
		//gera lista de veiculos com Modelo, Ano e Placa e exibe no elemento listaExibidaNaTela
		
		listaDeVeiculosBuscados.addAll(CadastroVeiculo.getVeiculosBuscados());
		ObservableList <String> listaDeNomesDeVeiculosBuscados = FXCollections.observableArrayList();
		
		listaDeVeiculosBuscados.forEach(veiculo ->{
			listaDeNomesDeVeiculosBuscados.add(veiculo.getMarca() + "   " + veiculo.getModelo() + "   " +
					veiculo.getAnoDeFabricacao() + "   " + veiculo.getClasse() + "   " + veiculo.getPlaca());
		});


		listaExibidaNaTela.setItems(listaDeNomesDeVeiculosBuscados);
		
	}
}
