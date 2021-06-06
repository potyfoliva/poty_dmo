package br.edu.ifsp.arq.dmos5_2021.listacontatos.model;

import java.util.ArrayList;
import java.util.List;

public class Contato implements Comparable<Contato>{

    private String nome;
    private String apelido;
    private String telefone;
    private String email;
    /*private List<String> telefones = new ArrayList<*/
    private boolean favorite;

    public Contato(String nome, String apelido, String telefone, String email) {
        this.nome = nome;
        this.apelido = apelido;
        /*if(!telefone.equals("")) {
            telefones.add(telefone);
        }
        if(!email.equals("")) {
            emails.add(email);
        }*/
        this.telefone = telefone;
        this.email = email;
        this.favorite = false;
    }

    public Contato(String nome, String apelido, boolean favorite) {
        this.nome = nome;
        this.apelido = apelido;
        this.favorite = favorite;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", apelido='" + apelido + '\'' +
                ", telefone=" + telefone +
                ", email=" + email +
                '}';
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Contato c) {
        return this.getNome().toLowerCase().compareTo(c.getNome().toLowerCase());
    }
}
