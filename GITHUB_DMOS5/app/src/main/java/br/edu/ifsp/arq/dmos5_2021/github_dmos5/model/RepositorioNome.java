package br.edu.ifsp.arq.dmos5_2021.github_dmos5.model;

import java.io.Serializable;

public class RepositorioNome implements Serializable {

    private String name;

    public RepositorioNome(String nome) {
        this.name = nome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Repositorio{" +
                "name='" + name + '\'' +
                '}';
    }
}
