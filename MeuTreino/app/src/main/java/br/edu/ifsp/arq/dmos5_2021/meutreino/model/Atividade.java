package br.edu.ifsp.arq.dmos5_2021.meutreino.model;

public class Atividade {

    private int id;
    private Aparelho aparelho;
    private int repeticao;
    private int tempo;
    private boolean completado;

    public Atividade(int id, Aparelho aparelho, int repeticao, int tempo, boolean completado) {
        this.id = id;
        this.aparelho = aparelho;
        this.repeticao = repeticao;
        this.tempo = tempo;
        this.completado = completado;
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

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", aparelho=" + aparelho +
                ", repeticao=" + repeticao +
                ", tempo=" + tempo +
                ", completado=" + completado +
                '}';
    }
}