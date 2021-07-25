package br.edu.ifsp.arq.dmos5_2021.meutreino.model;

import java.util.List;

public class Treino {

    private Atividade atividade;
    private Usuario usuario;

    public Treino(Atividade atividade, Usuario usuario) {
        this.atividade = atividade;
        this.usuario = usuario;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Treino{" +
                "atividade=" + atividade +
                ", usuario=" + usuario +
                '}';
    }
}
