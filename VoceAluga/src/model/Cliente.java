package model;
import java.util.Date;
import model.Habilitacao;

public class Cliente {
  private String nome;
  private String cpf; 
  private String passaporte;
  private String email;
  private Date dataDeNascimento;
  private Habilitacao habilitacao; 
  
  private String telefone;
  
  
  private String CEP;
  private String pais;
  private String estado;
  private String cidade;
  private String bairro;
  private String rua;
  private String numero;
  private String complemento;
  


  public Cliente(String nome, String cpf) {
      this.nome = nome;
      this.cpf = cpf;
      this.habilitacao= new Habilitacao();
  }
  
  public Cliente() {
	  this.habilitacao= new Habilitacao();
  }

  // ====getters====
  public String getNome() {
      return this.nome;
  }

  public Date getDataDeNascimento() {
      return this.dataDeNascimento;
  }
 
  public String getCpf() {
      return this.cpf;
  }
  
 

  
  public String getPassaporte() {
	  return this.passaporte;
  }
  
  public String getCEP() {
	  return this.CEP;
  }
  
  public String getEmail() {
	  return this.email;
  }
  
  public Habilitacao getHabilitacao() {
	  return this.habilitacao;
  }
  
  public String getPais() {
	  return this.pais;
  }
  
  public String getCidade() {
	  return this.cidade;
  }
  
  public String getEstado() {
	  return this.estado;
  }
  
  public String getBairro() {
	  return this.bairro;
  }
  
  public String getRua() {
	  return this.rua;
  }
  
  public String getNumero() {
	  return this.numero;
  }
  
  public String getComplemento() {
	  return this.complemento;
  }
  
  public String getTelefone() {
	  return this.telefone;
  }
  
  //====setters====
  public void setNome(String nome) {
      this.nome = nome;
  }

  public void setDataDeNascimento(Date data) {
      this.dataDeNascimento = data;
  }
 
  public void setCpf(String cpf) {
      this.cpf = cpf;
  }
  
  public void setPassaporte(String passaporte) {
	  this.passaporte = passaporte;
  }
  
  public void setCEP(String CEP) {
	  this.CEP = CEP;
  }
  
  public void setEmail(String email) {
	  this.email = email;
  }
  
  public void setHabilitacao(String numeroDeRegistro,String categoria, Date validade, Date dataDeEmissao) {
	  this.habilitacao = new Habilitacao(numeroDeRegistro,categoria,validade,dataDeEmissao); 
  }
  
  public void setTelefone(String telefone) {
	  this.telefone = telefone;
  }

  
//  =====================
  
  
  
}