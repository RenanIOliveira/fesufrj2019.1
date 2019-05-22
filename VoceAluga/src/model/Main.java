package model;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import controller.ScenesManager;

public class Main extends Application {
	
	
	ScenesManager manager = new ScenesManager();
	
	@Override
    public void start(Stage primaryStage) throws IOException{
    	manager.mostrarTelaDeLogin();
//		manager.mostrarResultadosBuscaCliente();
        ScenesManager.primaryStage.show();
    }
	
	

    public static void main(String[] args) {
        launch(args);
    }
}