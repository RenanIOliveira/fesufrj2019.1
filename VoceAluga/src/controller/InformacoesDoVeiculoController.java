package controller;


import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import controller.ScenesManager;
import model.Veiculo;
import model.dao.CadastroVeiculo;

public class InformacoesDoVeiculoController {

    ScenesManager manager = new ScenesManager();

    @FXML
    public Label labelFilial;

    @FXML
    public Label labelMarca;

    @FXML
    public Label labelModelo;

    @FXML
    public Label labelAno;

    @FXML
    public Label labelQuilometragem;

    @FXML
    public Label labelPlaca;

    @FXML
    public Label labelClasse;

    @FXML
    public Label labelChassi;

    @FXML
    public Label labelRENAVAM;

    @FXML
    public Label labelManutencao;

    @FXML
    public Label labelCor;

    @FXML
    public Label labelInformacoesAdicionais;

    // === Aluguel ===
    @FXML
    public Label labelAlugado;

    @FXML
    public Label labelLocatarioNome;

    @FXML
    public Label labelLocatarioCPF;

    @FXML
    public Label labelLocatarioHabilitacao;

    @FXML
    public Label labelDataLocacao;

    @FXML
    public Label labelDataDevolucao;

    @FXML
    public void processarBotaoVoltar(MouseEvent e) throws IOException {
        manager.mostrarTelaPrincipal();
    }

    @FXML
    public void processarBotaoEditar(MouseEvent e) throws IOException {
        manager.mostrarTelaCadastroDeVeiculo();
    }

    @FXML
    void processarBotaoDeletar(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deletar Dados Do Veiculo");
        alert.setHeaderText("Atenção, essa operação é irreversível!");
        alert.setContentText("Deseja Continuar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(CadastroVeiculo.deletarVeiculo(CadastroVeiculo.getVeiculoAtual().getChassi())){;
                mostrarMensagemDeSucesso("Veículo deletado com sucesso");
                manager.mostrarTelaPrincipal();
            }
            else{
                mostrarMensagemDeErro("Não foi possível deletar o veiculo");
            }

        }
    }

    public void mostrarMensagemDeSucesso(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(mensagem);
        alert.setContentText("");
        alert.showAndWait();
    }
    public void mostrarMensagemDeErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText(mensagem);
        alert.setContentText("");
        alert.showAndWait();
    }

    public void initialize() {
        Veiculo veiculo = CadastroVeiculo.getVeiculoAtual();

        preencherLabelsComDadosDoVeiculo(veiculo);
    }

    public void preencherLabelsComDadosDoVeiculo(Veiculo veiculo) {

        if(veiculo.getFilial() != null)labelFilial.setText(veiculo.getFilial());
        if(veiculo.getChassi() != null)labelChassi.setText(veiculo.getChassi());

        if(veiculo.getRENAVAM() != null)labelRENAVAM.setText(veiculo.getRENAVAM());
        if(veiculo.getPlaca() != null)labelPlaca.setText(veiculo.getPlaca());
        if(veiculo.getClasse() != null)labelClasse.setText(veiculo.getClasse());
        if(veiculo.getMarca() != null)labelMarca.setText(veiculo.getMarca());
        if(veiculo.getModelo() != null)labelModelo.setText(veiculo.getModelo());
        if(veiculo.getAnoDeFabricacao() != null)labelAno.setText(String.valueOf(veiculo.getAnoDeFabricacao()));
        if(veiculo.getQuilometragem() != null)labelQuilometragem.setText(String.valueOf(veiculo.getQuilometragem()));
        if(veiculo.getCor() != null) labelCor.setText(veiculo.getCor());
        if(veiculo.getManutencaoEmDia() != null) labelManutencao.setText(veiculo.getManutencaoEmDia());
        if(veiculo.getInformacoesAdicionais() != null)labelInformacoesAdicionais.setText(veiculo.getInformacoesAdicionais());


        if(veiculo.isAlugado() == true){
            labelAlugado.setText("SIM");
        }
        else {
            labelAlugado.setText("NÂO");
        }

        if(veiculo.getLocatario() != null) {
            labelLocatarioNome.setText(veiculo.getLocatarioNome());
            labelLocatarioCPF.setText(veiculo.getLocatarioCPF());
            labelLocatarioHabilitacao.setText(veiculo.getLocatarioHabilitacao());
        }
    }
}
