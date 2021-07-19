package br.edu.ifsp.arq.dmos5_2021.meutreino.model;

import java.util.ArrayList;
import java.util.List;

public class Exercicio {

        private String nome;

    public Exercicio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Exercicio{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
