package br.edu.ifsp.arq.dmos5_2021.meutreino.model;

public class Academia {

    //private Usuario usuario;
    private Aparelho aparelho;

    public Academia(/*Usuario usuario, */Aparelho aparelho) {
        //this.usuario = usuario;
        this.aparelho = aparelho;
    }

    /*public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }*/

    public Aparelho getAparelho() {
        return aparelho;
    }

    public void setAparelho(Aparelho aparelho) {
        this.aparelho = aparelho;
    }

    @Override
    public String toString() {
        return "Academia{" +
                //"usuario='" + usuario + '\'' +
                ", aparelho='" + aparelho + '\'' +
                '}';
    }
}
