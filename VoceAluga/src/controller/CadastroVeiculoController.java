package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Veiculo;
import model.dao.AlugaVeiculo;
import model.dao.CadastroVeiculo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TreeMap;

public class CadastroVeiculoController {

    ScenesManager manager = new ScenesManager();

    ObservableList<String> listaOpcoesSIMeNAO = FXCollections.observableArrayList("SIM","NÂO");

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @FXML
    TextField campoFilial;

    @FXML
    TextField campoMarca;

    @FXML
    TextField campoAno;

    @FXML
    TextField campoChassi;

    @FXML
    TextField campoRENAVAM;

    @FXML
    TextField campoClasse;

    @FXML
    TextField campoPlaca;

    @FXML
    TextField campoModelo;

    @FXML
    TextField campoQuilometragem;

    @FXML
    TextField campoCor;

    @FXML
    ChoiceBox caixaManutencao;

    @FXML
    TextField campoInformacoesAdicionais;

    // === Aluguel ===
    @FXML
    TextField campoLocatarioNome;

    @FXML
    TextField campoLocatarioCPF;

    @FXML
    TextField campoLocatarioHabilitacao;

    @FXML
    DatePicker campoDataLocacao;

    @FXML
    DatePicker campoDataDevolucao;

    @FXML
    Label labelAlugado;

    // === Botoes ===
    @FXML
    Button BotaoCancelar;

    @FXML
    Button BotaoConfirmar;

    @FXML
    Button BotaoAlugar;

    @FXML
    Button BotaoRetornar;

    public void initialize() {
        caixaManutencao.setItems(listaOpcoesSIMeNAO);

        if(CadastroVeiculo.getVeiculoAtual()!=null){
            Veiculo veiculo = CadastroVeiculo.getVeiculoAtual();
            preencherCamposComDadosDoVeiculo(veiculo);
        }
    }

    @FXML
    void processarBotaoCancelar(MouseEvent event) throws IOException{
        manager.mostrarTelaPrincipal();
    }

    @FXML
    void processarBotaoConfirmar(MouseEvent e) throws IOException{
        if(CadastroVeiculo.getVeiculoAtual()==null){
            System.out.println("Cadastrando veiculo");
            adicionarVeiculo();
        }else{
            System.out.println("Alterando veiculo");
            alterarVeiculo();
        }
    }

    TreeMap<String,String> gerarMapAPartirDoFormularioVeiculo(){
        TreeMap<String,String> campos = new TreeMap<>();

        if(!campoChassi.equals("")) campos.put("chassi", campoChassi.getText());
        if(!campoRENAVAM.equals("")) campos.put("RENAVAM", campoRENAVAM.getText());
        if(campoFilial.getText() != null) campos.put("filial", campoFilial.getText());
        if(campoPlaca.getText() != null) campos.put("placa", campoPlaca.getText());
        if(campoMarca.getText() != null) campos.put("marca", campoMarca.getText());
        if(campoModelo.getText()!= null) campos.put("modelo", campoModelo.getText());
        if(campoAno.getText() != null) campos.put("ano", campoAno.getText());
        if(campoClasse.getText() != null) campos.put("classe", campoClasse.getText());
        if(campoQuilometragem.getText() != null) campos.put("quilometragem", campoQuilometragem.getText());
        if(campoCor.getText() != null) campos.put("cor", campoCor.getText());
        if(caixaManutencao.getValue() != null) campos.put("manutencaoEmDia", String.valueOf(caixaManutencao.getValue()));
        if(campoInformacoesAdicionais.getText() != null) campos.put("infoAdicional", campoInformacoesAdicionais.getText());

        return campos;
    }
    //Correto?
    @FXML
    void adicionarVeiculo() throws IOException{
        if(!campoChassi.getText().equals("") && CadastroVeiculo.buscarPorChassi(campoChassi.getText())){
            mostrarErroDeCadastro("Chassi já cadastrado");
            return;
        }

        if(validarCamposCadastro() == false) return;

        TreeMap<String,String> camposVeiculo = gerarMapAPartirDoFormularioVeiculo();
        //Correto?
        if(CadastroVeiculo.cadastrarVeiculo(camposVeiculo)){
            mostrarMensagemDeSucessoDeCadastro("Veículo cadastrado com sucesso");
            manager.mostrarTelaPrincipal();
        }
        else{
            mostrarErroDeCadastro("Não foi possível cadastrar o veiculo");
        }

    }
    //Correto?
    @FXML
    void alterarVeiculo(){
        if(validarCamposCadastro() == false) return;


        TreeMap<String,String> camposVeiculo = gerarMapAPartirDoFormularioVeiculo();

        //Correto?
        if(CadastroVeiculo.alterarVeiculo(camposVeiculo)){
            mostrarMensagemDeSucessoDeCadastro("Cadastro alterado com sucesso");
            manager.mostrarTelaPrincipal();
        }
        else{
            mostrarErroDeCadastro("Não foi possível alterar o cadastro do veículo");
        }
    }
    //ToDo?
    boolean validarCamposCadastro(){

        if(campoFilial.getText()== null || campoMarca.getText()== null || campoModelo.getText().equals("")
                || campoAno.getText() == null || campoQuilometragem.getText() == null || campoPlaca.getText()== null
                || campoClasse.getText() == null || campoCor == null || campoChassi == null
                || campoRENAVAM == null || caixaManutencao.getSelectionModel().isEmpty()){
            mostrarErroDeCadastro("Preencha todos os campos obrigatórios");
            return false;
        }
        if(campoChassi.getText() == ""){
            return false;
        }
        //ToDo?
        if(!Veiculo.ValidarChassi(campoChassi.getText())){
            mostrarErroDeCadastro("Chassi inválido");
            return false;
        }

        return true;
    }

    public void mostrarMensagemDeSucessoDeCadastro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro bem sucedido");
        alert.setHeaderText(mensagem);
        alert.setContentText("");
        alert.showAndWait();
    }

    public void mostrarErroDeCadastro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no Cadastro");
        alert.setHeaderText("");
        alert.setContentText( mensagem);
        alert.showAndWait();
    }

    public void mostrarMensagemDeErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText(mensagem);
        alert.setContentText("");
        alert.showAndWait();
    }

    public void preencherCamposComDadosDoVeiculo(Veiculo veiculo){
        campoFilial.setText(veiculo.getFilial());
        campoMarca.setText(veiculo.getMarca());
        campoAno.setText(String.valueOf(veiculo.getAnoDeFabricacao()));
        campoChassi.setText(veiculo.getChassi());
        campoRENAVAM.setText(veiculo.getRENAVAM());
        campoClasse.setText(veiculo.getClasse());
        campoPlaca.setText(veiculo.getPlaca());
        campoModelo.setText(veiculo.getModelo());
        campoFilial.setText(veiculo.getFilial());
        campoQuilometragem.setText(String.valueOf(veiculo.getQuilometragem()));
        campoCor.setText(veiculo.getCor());
        caixaManutencao.setValue(veiculo.getManutencaoEmDia());
        campoInformacoesAdicionais.setText(veiculo.getInformacoesAdicionais());


        if(veiculo.isAlugado() == true){
            labelAlugado.setText("SIM");
        }
        else {
            labelAlugado.setText("NÂO");
        }

        if(veiculo.getLocatario() != null) {
            campoLocatarioNome.setText(veiculo.getLocatarioNome());
            campoLocatarioCPF.setText(veiculo.getLocatarioCPF());
            campoLocatarioHabilitacao.setText(veiculo.getLocatarioHabilitacao());
        }
    }


    // === Aluguel ===

    @FXML
    void processarBotaoAlugar(MouseEvent event) throws IOException{

        if(CadastroVeiculo.getVeiculoAtual()==null){
            System.out.println("Erro");
            mostrarMensagemDeErro("Tentou alugar sem veículo definido");
        }
        else {
            System.out.println("Alugando veiculo");
            alugarVeiculo();
        }
        //ToDo

    }

    @FXML
    void processarBotaoRetornar(MouseEvent event) throws IOException{
        //ToDo
        Veiculo veiculo = CadastroVeiculo.getVeiculoAtual();
        System.out.println("ToDo: Retornar o veículo.");;
    }

    boolean validarCamposAluguel(){
        if (campoLocatarioNome.getText().equals("") || campoLocatarioCPF.getText().equals("")
                || campoLocatarioHabilitacao.getText().equals("")) {
            mostrarErroDeAluguel("Preencha os campos do aluguel");
            return false;
        }

        return true;
    }

    void alugarVeiculo() {
        if (validarCamposAluguel() == false) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alugar Veiculo");
        alert.setContentText("Deseja alugar o veículo?");

        TreeMap<String, String> camposLocacao = gerarMapAPartirDoFormularioLocacao();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (AlugaVeiculo.alugarVeiculo(camposLocacao)) {
                mostrarMensagemDeSucessoDeAluguel("Veículo alugado com sucesso");
                manager.mostrarTelaPrincipal();
            } else {
                mostrarMensagemDeErro("Não foi possível alugar o veiculo");
            }

        }
    }

    TreeMap<String,String> gerarMapAPartirDoFormularioLocacao(){
        TreeMap<String,String> campos = new TreeMap<>();

        Veiculo veiculo = CadastroVeiculo.getVeiculoAtual();

        if(veiculo.getLocatario() != null) {

            campos.put("nome_cliente", campoLocatarioNome.getText());
            campos.put("id_veiculo", String.valueOf(veiculo.getId()));
            campos.put("dataLocacao", campoDataLocacao.getValue().toString());
            campos.put("dataDevolucao", campoDataDevolucao.getValue().toString());
        }

        return campos;
    }

    public void mostrarMensagemDeSucessoDeAluguel(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aluguel bem sucedido");
        alert.setHeaderText(mensagem);
        alert.setContentText("");
        alert.showAndWait();
    }

    public void mostrarErroDeAluguel(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no Aluguel");
        alert.setHeaderText("");
        alert.setContentText( mensagem);
        alert.showAndWait();
    }
}