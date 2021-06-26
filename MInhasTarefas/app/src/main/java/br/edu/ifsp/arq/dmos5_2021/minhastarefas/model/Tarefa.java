package br.edu.ifsp.arq.dmos5_2021.minhastarefas.model;

import java.util.Date;

import br.edu.ifsp.arq.dmos5_2021.minhastarefas.constantes.Constantes;

public class Tarefa implements Comparable<Tarefa>{

    private String descricao;
    private String prioridade;
    private String data;
    //private String telefone;
    private boolean concluida;

    public Tarefa() {
    }

    public Tarefa(String descricao, String prioridade, String data) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.data = data;
        concluida = false;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

   /* public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }*/

    @Override
    public String toString() {
        return "Tarefa{" +
                "descrição=" + descricao  +
                ", prioridade=" + prioridade +
                ", data=" + data +
                //", telefone=" + telefone +
                '}';
    }

    @Override
    public int compareTo(Tarefa t) {
        return this.getData().compareTo(t.getData());
    }

    /*public int compareToData(Tarefa t) {
        return this.getData().compareTo(t.getData());
    }*/

}
