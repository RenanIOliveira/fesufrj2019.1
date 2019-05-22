package controller;

import model.server.ValidadorDeLogin;
import controller.ScenesManager;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class LoginController {
	ScenesManager manager = new ScenesManager();

    @FXML
    private TextField usuario;
    
    @FXML
    private PasswordField senha;
    
    @FXML
   	void processarLoginBotao(MouseEvent e) throws IOException {
       	if(ValidadorDeLogin.validarLogin(usuario.getText(),senha.getText())) {
       		manager.mostrarTelaPrincipal();
       	}
       	else {
       		mostrarErroDeLogin();
       	}
   }
    
    ///Exibe a caixa de texto contendo mensagem de erro
    public void mostrarErroDeLogin() {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Erro");
    	alert.setHeaderText("Erro de Login");
    	alert.setContentText("Login ou usuario invalido");
    	alert.showAndWait();
    	usuario.setText("");
    	senha.setText("");
    }

    public void initialize() {
    	
    }
    
    
}