package model;
import java.time.LocalDate;
import model.Habilitacao;

public class Cliente {
  private String nome;
  private String cpf; 
  private String passaporte;
  private String email;
  private LocalDate dataDeNascimento;
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

// construtor contendo todos os campos
  public Cliente(String nome,String cpf,String passaporte,String email,String telefone,String CEP,
		         String pais, String estado, String cidade, String bairro, String rua, String numero, String complemento, LocalDate dataDeNascimento){

	  this(nome,cpf,passaporte,CEP,telefone,email);
	  this.pais = pais;
	  this.estado = estado;
	  this.cidade = cidade;
	  this.bairro = bairro;
	  this.rua = rua;
	  this.numero = numero;
	  this.complemento = complemento;
	  this.dataDeNascimento = dataDeNascimento;

  }

  // Esse construtor contem os elementos exatos da tela de busca
  public Cliente(String nome,String cpf,String passaporte,String CEP, String telefone,String email){
	  this(nome,cpf);
	  this.passaporte = passaporte;
	  this.CEP = CEP;
	  this.telefone = telefone;
	  this.email=email;
  }

  public Cliente(String nome, String cpf) {
	  this();
      this.nome = nome;
      this.cpf = cpf;
  }

  public Cliente() {
	  this.habilitacao= new Habilitacao();
  }

  // ====getters====
  
  public String getNome() {
      return this.nome;
  }

  public LocalDate getDataDeNascimento() {
      return this.dataDeNascimento;
  }
 
  public String getCPF() {
      return this.cpf;
  }
  
  public String getCPFFormatado(){
	  	String CPFFormatado = null;
	  try{
		  CPFFormatado = this.cpf.substring(0,3) +"." + this.cpf.substring(3,6)+ "."
				  		+this.cpf.substring(6,9)+ "-" + this.cpf.substring(9,11);	
	  }catch(IndexOutOfBoundsException e){
		  System.err.println("Cliente.getCPFFormatado");
		  System.err.println(e.getMessage());
	  }
	  return CPFFormatado;
  }
  
  
  public String getCEPFormatado(){
		String CEPFormatado = null;
		  try{
			  CEPFormatado = this.CEP.substring(0,5) + "-" + this.CEP.substring(5);	
		  }catch(IndexOutOfBoundsException e){
			  System.err.println("Cliente.getCEPFormatado");
			  System.err.println(e.getMessage());
		  }
		  return CEPFormatado;
  }
  
  public String getTelefoneFormatado(){
		String telefoneFormatado = null;
		  try{
			  telefoneFormatado = "+" + this.telefone.substring(0,2) + " (" +this.telefone.substring(2, 4)+ ") " 
					  				  + this.telefone.substring(4);	
		  }catch(IndexOutOfBoundsException e){
			  System.err.println("Cliente.getCEPFormatado");
			  System.err.println(e.getMessage());
		  }
		  return telefoneFormatado;
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
  
  // ====setters====
  public void setNome(String nome) {
      this.nome = nome;
  }

  public void setDataDeNascimento(LocalDate data) {
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
  
  public void setHabilitacao(String numeroDeRegistro,String categoria, LocalDate validade, LocalDate dataDeEmissao) {
	  this.habilitacao = new Habilitacao(numeroDeRegistro,categoria,validade,dataDeEmissao); 
  }
  
  public void setHabilitacao(Habilitacao h){
	  setHabilitacao(h.getNumeroDeRegistro(),h.getCategoria(),h.getValidade(),h.getDataDeEmissao());
  }
  
  public void setTelefone(String telefone) {
	  this.telefone = telefone;
  }

  
//  =====================
  
  public static boolean ValidarCPF(String cpf){
	  int digito1 = 0;
	  int digito2 =0;
	  int[] CPFComoArrayDeInt = new int[11];
	  char[] CPFSeparado;
	  
	  //transforma o CPF em um array de int
	  if (cpf.length() != 11) return false;
	  CPFSeparado = cpf.toCharArray();
	  for(int i=0; i<11; i++) CPFComoArrayDeInt[i] =  (char) (CPFSeparado[i]- '0');
	  
	  //confere se o primeiro digito de validacao esta correto
	  for(int i=0; i<9 ;i++) digito1 += CPFComoArrayDeInt[i]*(10-i);
	  digito1 = (digito1*10)%11;
	  if(digito1!= CPFComoArrayDeInt[9]) return false;
	  
	  //confere se todos os digitos sao iguais
	  int cont=0;
	  for(int i=0;i<11;i++) if(digito1==CPFComoArrayDeInt[i]) cont++;
	  if(cont==11) return false;
	  
	  //confere se o segundo digito de validacao esta correto
	  for(int i=0; i<10; i++)  digito2+=CPFComoArrayDeInt[i]*(11-i);
	  digito2 = (digito2*10)%11;
	  if(digito2!= CPFComoArrayDeInt[10]) return false;
		
	  
	  return true;
  }
  
}