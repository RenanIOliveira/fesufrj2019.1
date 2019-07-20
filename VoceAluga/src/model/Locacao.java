package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Locacao {
    private int id;
    private int id_cliente;
    private int id_veiculo;
    private Date dataLocacao;
    private Date dataDevolucao;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Locacao(int id, int id_cliente, int id_veiculo) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_veiculo = id_veiculo;
        this.dataLocacao = new Date();
    }

    public Locacao() {
        this.dataLocacao = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String getDataLocacaoFormatada() {
        return this.dateFormat.format(this.dataLocacao);
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getDataDevolucaoFormatada() {
        return this.dateFormat.format(this.dataDevolucao);
    }
}
