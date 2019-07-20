package controller;


import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/***
 * 
 * Essa Classe e responsavel por exibir todas as telas do sistema
 *
 */


public class ScenesManager {
	
	public static Stage primaryStage =  new Stage();
	
	public void mostrarTelaDeLogin(){		 
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			primaryStage.setResizable(false);
	    	primaryStage.initStyle(StageStyle.UNIFIED);
	        primaryStage.setTitle("Voce Aluga - Login");
	        primaryStage.setScene(new Scene(root));
		} catch (IOException e) {
			System.err.println("Nao foi possivel exibir a tela de login");
			System.err.println(e.getMessage());
		}
    	
	}
	
 
	public void mostrarTelaPrincipal(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/TelaPrincipal.fxml"));
			primaryStage.setTitle("Voce Aluga - Principal");
	        primaryStage.setScene(new Scene(root));
		} catch (IOException e) {
			System.err.println("Nao foi possivel exibir a tela principal");
			System.err.println(e.getMessage());
		}	        
	 }
	 
	 public void mostrarTelaCadastroDeCliente() throws IOException {
		 try{
			 Parent root = FXMLLoader.load(getClass().getResource("../view/CadastroCliente.fxml"));
			 primaryStage.setTitle("Voce Aluga - Cadastrar Cliente");
			 primaryStage.setScene(new Scene(root));
		 }
		 	catch( IOException e){
		 		System.err.println("Nao foi possivel exibir a tela de cadastro de cliente");
		 		System.err.println(e.getMessage());
		 }
	 }
	 
	 public void mostrarTelaResultadosBuscaCliente(){
		 Parent root;
		 try {
			 root = FXMLLoader.load(getClass().getResource("../view/ResultadosBuscaCliente.fxml"));
			 primaryStage.setTitle("Voce Aluga - Busca de Clientes");
			 primaryStage.setScene(new Scene(root));
		 } catch (IOException e) {
			 System.err.println("Nao foi possivel exibir a telade resultados de busca de cliente");
			 System.err.println(e.getMessage());
		 }
	        
	 }
	 
	 public void mostrarTelaInformacoesDoCliente(){
		 Parent root;
		 try {
			 root = FXMLLoader.load(getClass().getResource("../view/InformacoesDoCliente.fxml"));
			 primaryStage.setTitle("Voce Aluga - Dados do Cliente");
			 primaryStage.setScene(new Scene(root));
		 } catch (IOException e) {
			 System.err.println("Nao foi possivel exibir a tela de informacoes do cliente");
			 System.err.println(e.getMessage());
		 }
		 	
	 }
	 
	 public void mostrarTelaCadastroDeVeiculo(){
		 Parent root;
		 try {
			 root = FXMLLoader.load(getClass().getResource("../view/CadastroVeiculo.fxml"));
			 primaryStage.setTitle("Voce Aluga - Cadastrar Veiculo");
			 primaryStage.setScene(new Scene(root));
		 } catch (IOException e) {
			 System.err.println("Nao foi possivel exibir a tela de cadastro do veiculo");
			 System.err.println(e.getMessage());
		 }
	 
	 }
	 
	 public void mostrarTelaResultadosBuscaVeiculo(){		
		 Parent root;
		 try {
			 root = FXMLLoader.load(getClass().getResource("../view/ResultadosBuscaVeiculo.fxml"));
			 primaryStage.setTitle("Voce Aluga - Busca de Veiculo");
			 primaryStage.setScene(new Scene(root));
		 } catch (IOException e) {
			 System.err.println("Nao foi possivel exibir a tela de resultados busca de veiculo");
			 System.err.println(e.getMessage());
		 }
    
	 }

	public void mostrarTelaInformacoesDoVeiculo(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/InformacoesDoVeiculo.fxml"));
			primaryStage.setTitle("Voce Aluga - Dados do Veiculo");
			primaryStage.setScene(new Scene(root));
		} catch (IOException e) {
			System.err.println("Nao foi possivel exibir a tela de informacoes do veiculo");
			System.err.println(e.getMessage());
		}

	}


	public void mostrarTelaInformacoesDoFuncionario() {
		Parent root;
		 try {
			 
			 root = FXMLLoader.load(getClass().getResource("../view/InformacoesDoFuncionario.fxml"));
			 primaryStage.setTitle("Voce Aluga - Informações do Funcionário");
			 primaryStage.setScene(new Scene(root));
		 } catch (IOException e) {
			 System.err.println("Nao foi possivel exibir a telade resultados de informações do funcionário");
			 System.err.println(e.getMessage());
		 }
	}


	public void mostrarTelaResultadosBuscaFuncionario() {
		 Parent root;
		 try {
			 
			 root = FXMLLoader.load(getClass().getResource("../view/ResultadosBuscaFuncionario.fxml"));
			 primaryStage.setTitle("Voce Aluga - Busca de Funcionarios");
			 primaryStage.setScene(new Scene(root));
		 } catch (IOException e) {
			 System.err.println("Nao foi possivel exibir a telade resultados de busca de Funcionarios");
			 System.err.println(e.getMessage());
		 }
	}


	public void mostrarTelaCadastroDeFuncionario() {
		 Parent root;
		 try {
			 root = FXMLLoader.load(getClass().getResource("../view/CadastroFuncionario.fxml"));
			 primaryStage.setTitle("Voce Aluga - Cadastrar Funcionario");
			 primaryStage.setScene(new Scene(root));
		 } catch (IOException e) {
			 System.err.println("Nao foi possivel exibir a tela de cadastro do Funcionario");
			 System.err.println(e.getMessage());
		 }
	 
	}
	 
	 
	 
}
