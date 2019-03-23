package com.company;

public class Contrato {
    private Cliente assinante;
    private boolean validez;

    public Contrato(Cliente cliente){
        this.assinante = cliente;
    }

    public void setValidez() {
        this.validez = true;
    }

    public void quebraDeContrato(){
        this.validez = false;
    }

    public String getAssinante() {
        return this.assinante.getCpf();
    }

    public void mostrarAssinante() {
        this.assinante.printCpfFormatado();
    }
}