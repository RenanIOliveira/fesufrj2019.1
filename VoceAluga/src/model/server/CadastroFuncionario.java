package model.server;

import java.util.ArrayList;

import model.Funcionario;

public class CadastroFuncionario {
	private static ArrayList<Funcionario> FuncionariosBuscados;
	private static Funcionario FuncionarioAtual;
	
	// CODIGO DE TESTE
	public static void buscarFuncionarios(Funcionario Funcionario){
		FuncionariosBuscados = new ArrayList<Funcionario>();
		FuncionariosBuscados.add(new Funcionario("Funcionario1","123456789"));
		FuncionariosBuscados.add(new Funcionario("Funcionario2","987654321"));
	}
	
	public static ArrayList<Funcionario> getFuncionariosBuscados() {
		return FuncionariosBuscados;
	}
	
	public static void buscarPorCPF(String CPF) {
		FuncionarioAtual = new Funcionario();
	}
	
	public static void buscarPorLogin(String login) {
		FuncionarioAtual = new Funcionario();
	}
	
	public static Funcionario getFuncionarioAtual() {
		
		return FuncionarioAtual;
	}
	
	public static void setFuncionarioAtual(Funcionario Funcionario) {
		FuncionarioAtual = Funcionario;
	}


}
