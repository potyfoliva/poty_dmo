package br.edu.ifsp.arq.dmos5_2021.meutreino.model;


public class Usuario {

    private String nome;
    private long senha;
    private String email;
    private String tipo;

    public Usuario(String nome, long senha, String email, String tipo) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getSenha() {
        return senha;
    }

    public void setSenha(long senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean validate(String userName, int password, String tipo){
        if(userName != null){
            return this.email.equals(userName) && this.senha == password && this.tipo.equals(tipo);
        }
        return false;
    }

    @Override
    public String toString() {
        return nome;
    }
}


