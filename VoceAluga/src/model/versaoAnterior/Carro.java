package model.versaoAnterior;

import model.Cliente;

public class Carro {
    private String marca;
    private int quilometragem;
    private boolean disponivel;
    private Cliente clienteQueAlugou;

    public Carro(String marcaDoCarro){
        this.marca = marcaDoCarro;
        this.disponivel = true;
    }

    
   public Carro(MarcaDeCarro marcaDoCarro){

    }

    public String getMarcaDoCarro() {
        return this.marca;
    }

    public int getQuilometragem() {
        return this.quilometragem;
    }

    public void aumentaQuilometragem(int quilometrosPercorridos){
        //Quilometragem e private, entao usa-se uma funcao para aumentar
        if(quilometrosPercorridos >= 0) {
            this.quilometragem += quilometrosPercorridos;
        }
    }

    public boolean disponivelParaAlugar() {
        return disponivel;
    }

    public void setDisponivelParaAlugar(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void carroFoiAlugado(Cliente cliente) {
        this.clienteQueAlugou = cliente;
        this.setDisponivelParaAlugar(false);
    }

    public void carroFoiRetornado() {
        this.clienteQueAlugou = null;
        this.setDisponivelParaAlugar(true);
    }

    public Cliente getClienteQueAlugou() {
        return this.clienteQueAlugou;
    }
}