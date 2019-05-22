package model.server;

import java.util.ArrayList;

import model.Veiculo;



public class CadastroVeiculo {
	private static ArrayList<Veiculo> VeiculosBuscados;
	private static Veiculo VeiculoAtual;
	
	// CODIGO DE TESTE
	public static void buscarVeiculos(Veiculo Veiculo){
		VeiculosBuscados = new ArrayList<Veiculo>();
		VeiculosBuscados.add(new Veiculo(123456L,"Fiat","Uno","ABC-1234","123456",30000));
		VeiculosBuscados.add(new Veiculo(456789L,"Ford","Ka","DEF-5678","456789",0));
	}
	
	public static ArrayList<Veiculo> getVeiculosBuscados() {
		return VeiculosBuscados;
	}
	
	public static void buscarPorID(Long id) {
		VeiculoAtual = new Veiculo();
	}
	
	public static void buscarPorChassi(String Chassi) {
		VeiculoAtual = new Veiculo();
	}
	
	public static Veiculo getVeiculoAtual() {		
		return VeiculoAtual;
	}
	
	public static void setVeiculoAtual(Veiculo Veiculo) {
		VeiculoAtual = Veiculo;
	}

}
