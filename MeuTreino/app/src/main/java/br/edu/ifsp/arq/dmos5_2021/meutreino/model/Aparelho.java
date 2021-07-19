package br.edu.ifsp.arq.dmos5_2021.meutreino.model;

import android.media.Image;

public class Aparelho {

    private String nome;
    private String uso;
    private String foto;
    private Exercicio exercicio;

    public Aparelho(String nome, String uso, String foto, Exercicio exercicio) {
        this.nome = nome;
        this.uso = uso;
        this.foto = foto;
        this.exercicio = exercicio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    @Override
    public String toString() {
        return "Aparelho{" +
                ", nome='" + nome + '\'' +
                ", uso='" + uso + '\'' +
                ", foto='" + foto + '\'' +
                ", exercicio=" + exercicio +
                '}';
    }
}
