package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Veiculo;
import model.server.CadastroVeiculo;

import java.io.IOException;

public class CadastroVeiculoController {

    ScenesManager manager = new ScenesManager();

    @FXML
    TextField campoFilial;

    @FXML
    TextField campoMarca;

    @FXML
    TextField campoAno;

    @FXML
    TextField campoChassi;

    @FXML
    TextField campoClasse;

    @FXML
    TextField campoPlaca;

    @FXML
    TextField campoModelo;

    @FXML
    Button BotaoCancelar;

    @FXML
    Button BotaoConfirmar;

    @FXML
    void processarBotaoCancelar(MouseEvent event) throws IOException{
        manager.mostrarTelaPrincipal();
    }

    @FXML
    void processarBotaoConfirmar(MouseEvent e) throws IOException{

    }

    public void initialize() {
        Veiculo veiculo = CadastroVeiculo.getVeiculoAtual();

        preencherCamposComDadosDoVeiculo(veiculo);
    }

    public void preencherCamposComDadosDoVeiculo(Veiculo veiculo){
        campoFilial.setText(veiculo.getFilial());
        campoMarca.setText(veiculo.getMarca());
        campoAno.setText(String.valueOf(veiculo.getAnoDeFabricacao()));
        campoChassi.setText(veiculo.getChassi());
        campoClasse.setText(String.valueOf(veiculo.getClasse()));
        campoPlaca.setText(veiculo.getPlaca());
        campoModelo.setText(veiculo.getModelo());
        campoFilial.setText(veiculo.getFilial());
    }
}
