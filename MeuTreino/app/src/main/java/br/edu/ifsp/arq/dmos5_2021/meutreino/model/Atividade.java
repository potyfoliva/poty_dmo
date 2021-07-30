package br.edu.ifsp.arq.dmos5_2021.meutreino.model;

public class Atividade {

    private int id;
    private Aparelho aparelho;
    private String tempo_repeticao;
    private int completado;

    public Atividade(int id, Aparelho aparelho, String tempo_repeticao, int completado) {
        this.id = id;
        this.aparelho = aparelho;
        this.tempo_repeticao = tempo_repeticao;
        this.completado = completado;
    }

    public Atividade(Aparelho aparelho) {
        this.aparelho = aparelho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aparelho getAparelho() {
        return aparelho;
    }

    public void setAparelho(Aparelho aparelho) {
        this.aparelho = aparelho;
    }


    public String getTempo_repeticao() {
        return tempo_repeticao;
    }

    public void setTempo_repeticao(String tempo_repeticao) {
        this.tempo_repeticao = tempo_repeticao;
    }

    public int getCompletado() {
        return completado;
    }

    public void setCompletado(int completado) {
        this.completado = completado;
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", aparelho=" + aparelho +
                ", tempo_repeticao='" + tempo_repeticao + '\'' +
                ", completado=" + completado +
                '}';
    }
}