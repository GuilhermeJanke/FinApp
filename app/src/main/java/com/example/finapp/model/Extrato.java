package com.example.finapp.model;

public class Extrato {

    private String classificacao;
    private String data;
    private String valor;

    public Extrato(){}

    public Extrato(String classificacao, String data, String valor) {
        this.classificacao = classificacao;
        this.data = data;
        this.valor = valor;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
