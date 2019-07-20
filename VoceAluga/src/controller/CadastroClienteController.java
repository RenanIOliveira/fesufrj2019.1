package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.Habilitacao;
import model.dao.CadastroCliente;

import java.io.IOException;
import java.util.TreeMap;

import controller.ScenesManager;

public class CadastroClienteController {
	
	ScenesManager manager = new ScenesManager();
	
	@FXML
	TextField campoNome;
	
	@FXML
	MaskField campoCPF;
	
	@FXML
	TextField campoPais;
	
	@FXML
	TextField campoEstado;
	
	@FXML
	DatePicker campoDataDeNascimento;
	
	@FXML
	TextField campoCidade;
	
	@FXML
	TextField campoPassaporte;
	
	@FXML
	TextField campoBairro;
	
	@FXML
	MaskField campoTelefone;
	
	@FXML
	TextField campoRua;
	
	@FXML
	TextField campoEmail;
	
	@FXML
	TextField campoNumero;
	
	@FXML
	MaskField campoCEP;
	
	@FXML
	TextField campoComplemento;
	
	@FXML
	MaskField campoNumeroDeRegistro;
	
	@FXML
	DatePicker campoValidade;
	
	@FXML
	MaskField campoCategoria;
	
	@FXML
	DatePicker campoDataDeEmissao;
	
	
	public void initialize() {
		if(CadastroCliente.getClienteAtual()!=null){
			Cliente cliente = CadastroCliente.getClienteAtual();
	 		preencherCamposComDadosDoCliente(cliente);	
	 		campoCPF.setDisable(true);
		}
	}
	
	@FXML
	void processarBotaoCancelar(MouseEvent e) throws IOException {
		manager.mostrarTelaPrincipal();
	}
	
	TreeMap<String,String> gerarMapAPartirDoFormularioCliente(){
		TreeMap<String,String> campos = new TreeMap<>();
		
		if(campoNome.getText() != null) campos.put("nome",campoNome.getText());
		if(!campoCPF.getPlainText().equals("")) campos.put("CPF", campoCPF.getPlainText());
		
		if(campoPais.getText() != null) campos.put("pais", campoPais.getText());
		if(campoEstado.getText() != null) campos.put("estado", campoEstado.getText());
		if(campoDataDeNascimento.getValue()!=null) campos.put("dataDeNascimento", campoDataDeNascimento.getValue().toString());
		if(campoCidade.getText() != null) campos.put("cidade", campoCidade.getText());
		if(campoPassaporte.getText() !=null) campos.put("passaporte", campoPassaporte.getText());
		if(campoBairro.getText() != null) campos.put("bairro", campoBairro.getText());
		if(!campoTelefone.getPlainText().equals("")) campos.put("telefone", campoTelefone.getPlainText());
		if(campoRua.getText() != null) campos.put("rua", campoRua.getText());
		if(campoEmail.getText() != null) campos.put("email", campoEmail.getText());
		if(campoNumero.getText() != null) campos.put("numero", campoNumero.getText());
		if(!campoCEP.getPlainText().equals("")) campos.put("CEP", campoCEP.getPlainText());
		if(campoComplemento.getText() !=null) campos.put("complemento", campoComplemento.getText());
		
		return campos;
	}
	 
	TreeMap<String,String> gerarMapAPartirDoFormularioHabilitacao(){
		 	TreeMap<String,String> campos = new TreeMap<>();
			if(!campoNumeroDeRegistro.getPlainText().equals("")) campos.put("numeroDeRegistro", campoNumeroDeRegistro.getPlainText());
			if(campoValidade.getValue()!=null) campos.put("validade", campoValidade.getValue().toString());
			if(!campoCategoria.getPlainText().equals("")) campos.put("categoria", campoCategoria.getPlainText());
			if(campoDataDeEmissao.getValue()!=null) campos.put("dataDeEmissao", campoDataDeEmissao.getValue().toString());
			
			return campos;
		
	 }

	@FXML
	void processarBotaoConfirmar(MouseEvent e) throws IOException{
		 if(CadastroCliente.getClienteAtual()==null){
			 System.out.println("Cadastrando cliente");
			 adicionarCliente();
		 }else{
			 System.out.println("alterando cliente");
			 alterarCliente();
		 }
	 }

	@FXML
	void adicionarCliente() throws IOException{
		if(campoPassaporte.getText().equals("") && campoCPF.getPlainText().equals("")){
			mostrarErroDeCadastro("Preencha o CPF ou Passaporte");
			return;
		}
		
		
		if(!campoCPF.getText().equals("") &&CadastroCliente.buscarPorCPF(campoCPF.getPlainText())){
			mostrarErroDeCadastro("CPF já cadastrado");
			return;
		}
		if(!campoPassaporte.getText().equals("") && CadastroCliente.buscarPorPassaporte(campoPassaporte.getText())){
			mostrarErroDeCadastro("Passaporte já cadastrado");
			return;
		}
		
		if(validarCampos() == false) return;
		
		TreeMap<String,String> camposCliente = gerarMapAPartirDoFormularioCliente();
		
		if(CadastroCliente.cadastrarCliente(camposCliente)){
			if(!campoNumeroDeRegistro.getPlainText().equals("")){
				adicionarHabilitacao();
			}
			mostrarMensagemDeSucesso("Cliente cadastrado com sucesso");
			manager.mostrarTelaPrincipal();
		}
		else{
			mostrarErroDeCadastro("Não foi possível cadastrar o cliente");
		}

	}
	
	void adicionarHabilitacao(){
		TreeMap<String,String> camposHabilitacao = gerarMapAPartirDoFormularioHabilitacao();
		if(!campoCPF.getPlainText().equals("")){
			if(!CadastroCliente.cadastrarHabilitacaoPorCPF(campoCPF.getPlainText(), camposHabilitacao)){
				mostrarErroDeCadastro("Erro ao cadastrar a habilitação");
			}
		}
		
		if(!campoPassaporte.getText().equals("")){
			if(!CadastroCliente.cadastrarHabilitacaoPorPassaporte(campoPassaporte.getText(), camposHabilitacao)){
				mostrarErroDeCadastro("Erro ao cadastrar a habilitação");
			}
		}
	}
	
	void alterarHabilitacao(){
		TreeMap<String,String> camposHabilitacao = gerarMapAPartirDoFormularioHabilitacao();
		if(!campoCPF.getPlainText().equals("")){
			if(!CadastroCliente.deletarHabilitacaoPorCPF(campoCPF.getPlainText())) mostrarErroDeCadastro("Erro ao cadastrar a habilitação");
			if(!CadastroCliente.cadastrarHabilitacaoPorCPF(campoCPF.getPlainText(), camposHabilitacao)) mostrarErroDeCadastro("Erro ao cadastrar a habilitação");	
		}
		else if(!campoPassaporte.getText().equals("")){
			if(!CadastroCliente.deletarHabilitacaoPorPassaporte(campoPassaporte.getText())) mostrarErroDeCadastro("Erro ao cadastrar a habilitação");
			if(!CadastroCliente.cadastrarHabilitacaoPorPassaporte(campoPassaporte.getText(), camposHabilitacao)) mostrarErroDeCadastro("Erro ao cadastrar a habilitação");	
		}
	}
	
	void deletarHabilitacao(){
		if(!campoCPF.getPlainText().equals("")){
			if(!CadastroCliente.deletarHabilitacaoPorCPF(campoCPF.getPlainText())) mostrarErroDeCadastro("Erro ao remover a habilitação");
		}
		else if(!campoPassaporte.getText().equals("")){
			if(!CadastroCliente.deletarHabilitacaoPorPassaporte(campoPassaporte.getText())) mostrarErroDeCadastro("Erro ao remover a habilitação");
		}
	}
	
	@FXML
	void alterarCliente(){
		if(validarCampos() == false) return;
		
		
		TreeMap<String,String> camposCliente = gerarMapAPartirDoFormularioCliente();
		
		if(CadastroCliente.alterarCliente(camposCliente)){
			if(!campoNumeroDeRegistro.getPlainText().equals("")){
				alterarHabilitacao();
			}
			
			if(camposDaHabilitacaoVazios() && CadastroCliente.getClienteAtual().getHabilitacao()!=null){
				deletarHabilitacao();
			}
			mostrarMensagemDeSucesso("Cadastro alterado com sucesso");
			manager.mostrarTelaPrincipal();
		}
		else{
			mostrarErroDeCadastro("Não foi possível alterar o cadastro do cliente");
		}
	}
	
	boolean camposDaHabilitacaoVazios(){
		if(!campoNumeroDeRegistro.getPlainText().equals("") || campoValidade.getValue() != null || campoDataDeEmissao.getValue() !=null ||
				   !campoCategoria.getPlainText().equals("")){
			return false;
		}
		return true;
	}
	
	boolean validarCampos(){
		
		if(campoNome.getText()== null || campoCPF.getPlainText().equals("") || campoDataDeNascimento == null
				|| campoCEP.getPlainText().equals("") || campoPais.getText() == null || campoEstado.getText() == null
				|| campoCidade.getText()== null || campoBairro.getText() == null || campoRua.getText() == null
				|| campoNumero.getText()==null){
			mostrarErroDeCadastro("Preencha todos os campos obrigatórios");
			return false;
		}
		if(campoCPF.getPlainText() == "" && campoPassaporte.getText() == null){
			return false;
		}
		if(!Cliente.ValidarCPF(campoCPF.getPlainText())){
			mostrarErroDeCadastro("CPF inválido");
			return false;
		}
		
		if(!camposDaHabilitacaoVazios()){
			if(campoNumeroDeRegistro.getPlainText().equals("") || campoValidade.getValue() == null || campoDataDeEmissao.getValue() ==null ||
		       campoCategoria.getPlainText().equals("")){
				mostrarErroDeCadastro("preencha todos os campos da habilitação");
				return false;
			}
			
			
		}
		return true;
	}
	
	public void mostrarMensagemDeSucesso(String mensagem) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Cadastro bem sucedido");
    	alert.setHeaderText(mensagem);
    	alert.setContentText("");
    	alert.showAndWait();
    }
	
    public void mostrarErroDeCadastro(String mensagem) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Erro no Cadastro");
    	alert.setHeaderText("");
    	alert.setContentText( mensagem);
    	alert.showAndWait();
    }

	private void preencherCamposComDadosDoCliente(Cliente cliente) {
		Habilitacao habilitacao = cliente.getHabilitacao();
		
		
		campoNome.setText(cliente.getNome());
	 	campoCPF.setPlainText(cliente.getCPF());
	 	campoPais.setText(cliente.getPais());
	 	campoEstado.setText(cliente.getEstado());
	 	campoCidade.setText(cliente.getCidade());
	 	campoPassaporte.setText(cliente.getPassaporte());
	 	campoBairro.setText(cliente.getBairro());
	 	campoTelefone.setPlainText(cliente.getTelefone());
	 	campoRua.setText(cliente.getRua());
	 	campoEmail.setText(cliente.getEmail());
	 	campoNumero.setText(cliente.getNumero());
	 	campoCEP.setPlainText(cliente.getCEP());
	 	campoComplemento.setText(cliente.getComplemento());
	 	
	 	if(cliente.getDataDeNascimento()!=null)
	 		campoDataDeNascimento.setValue(cliente.getDataDeNascimento());

	 	campoNumeroDeRegistro.setPlainText(habilitacao.getNumeroDeRegistro());
	 	campoCategoria.setPlainText(habilitacao.getCategoria());
	 	
	 	if(habilitacao.getDataDeEmissao()!=null)
	 		campoDataDeEmissao.setValue(habilitacao.getDataDeEmissao());
	 	if(habilitacao.getValidade()!=null)
	 		campoValidade.setValue(habilitacao.getValidade());
	}
	
}
