package model;

import java.util.Date;

public class Habilitacao {
  private String numeroDeRegistro;
  private Date validade;
  private String categoria;
  private Date dataDeEmissao;
  
  Habilitacao(){
	  
  }
  
  Habilitacao(String numeroDeRegistro,String categoria,Date validade,Date dataDeEmissao){
	  this.numeroDeRegistro = numeroDeRegistro;
	  this.categoria = categoria;
	  this.validade = validade;
	  this.dataDeEmissao = dataDeEmissao;
  }
  
  
//  ---getters---
  public String getNumeroDeRegistro() {
	  return this.numeroDeRegistro;
  }
  
  public Date getValidade() {
	  return this.validade;
  }
  
  public String getCategoria() {
	  return this.categoria;
  }
  
  public Date getDataDeEmissao() {
	  return this.dataDeEmissao;
  }
  

}