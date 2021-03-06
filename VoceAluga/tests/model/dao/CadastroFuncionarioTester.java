package model.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeMap;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Funcionario;

public class CadastroFuncionarioTester {
	
	@BeforeClass
	public static void before(){
		MySQLConnector.abrirConexao();
	}

	@Test
	public void testBuscarFuncionario() {
		TreeMap<String,String> camposFuncionario = new TreeMap<String,String>();
		camposFuncionario.put("nome", "João Almeida");
		
		
		
		assertTrue(CadastroFuncionario.buscarFuncionarios(camposFuncionario));
		ArrayList<Funcionario> funcionarios = CadastroFuncionario.getFuncionariosBuscados();
		assertEquals(2,funcionarios.get(0).getNivelDeAcesso());
		assertEquals("Gerente",funcionarios.get(0).getCargo());
		assertEquals(1,funcionarios.size());
		assertEquals("João Almeida",funcionarios.get(0).getNome());
	}
	
	@Test
	public void testBuscarPorCPF1(){
		assertTrue(CadastroFuncionario.buscarPorCPF("19876543210"));
		Funcionario funcionario = CadastroFuncionario.getFuncionarioAtual();
		
		assertEquals(1,funcionario.getNivelDeAcesso());
		assertEquals("Vendedor",funcionario.getCargo());
		
	}
	
	
	@Test
	public void testBuscarPorCPF2(){
		assertTrue(CadastroFuncionario.buscarPorCPF("01234567891"));
		Funcionario funcionario = CadastroFuncionario.getFuncionarioAtual();
		
		assertEquals(2,funcionario.getNivelDeAcesso());
		assertEquals("Gerente",funcionario.getCargo());
		
	}
	
	@Test 
	public void testBuscarFuncionarios(){
		TreeMap<String,String> campos = new TreeMap<>(); 
		
		assertTrue(CadastroFuncionario.buscarFuncionarios(campos));
		assertTrue(CadastroFuncionario.getFuncionariosBuscados().size()>0);
	}

	@Test
	public void testInsercaoBuscaRemocao(){
		TreeMap<String,String> campos = new TreeMap<>();
		campos.put("login", "abcdef");
		campos.put("senha","abcdef");
		campos.put("CPF", "11133344400");
		campos.put("nome", "Renan");
		campos.put("cargo", "Vendedor");
		campos.put("nivelDeAcesso", "1");
		assertTrue(CadastroFuncionario.cadastrarFuncionario(campos));
		
		assertTrue(CadastroFuncionario.buscarPorCPF("11133344400"));
		assertEquals("11133344400",CadastroFuncionario.getFuncionarioAtual().getCPF());
		assertEquals("Vendedor",CadastroFuncionario.getFuncionarioAtual().getCargo());	
		
		assertTrue(CadastroFuncionario.deletarFuncionario("11133344400"));
		assertTrue(!CadastroFuncionario.buscarPorCPF("11133344400"));
	}


	@AfterClass
	public static void after(){
		MySQLConnector.fecharConexao();
	}

}
