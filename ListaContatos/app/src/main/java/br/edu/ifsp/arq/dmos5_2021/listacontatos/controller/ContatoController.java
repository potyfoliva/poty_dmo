package br.edu.ifsp.arq.dmos5_2021.listacontatos.controller;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.listacontatos.dao.ContatoDAO;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.model.Contato;

public class ContatoController {

    public static List<Contato> allContatos(Context context){
        return ContatoDAO.getInstance(context).getContatos();
    }

    public static List<Contato> allFavoritos(Context context){
        return ContatoDAO.getInstance(context).getFavoritos();
    }

    public static void addContato(Context context, String nome, String apelido, String telefone, String email){
        Contato contato = new Contato(nome, apelido, telefone, email);
        ContatoDAO.getInstance(context).addContato(contato);
    }

    public static void addFavorito(Context context, Contato contato){
        ContatoDAO.getInstance(context).addFavorito(contato);
    }

    /*public static int tamanhoFav(Context context){
        ContatoDAO.getInstance(context).getTamanhoFav();
    }*/



    /*public static void updateContato(String oldTitle, String title, String url){
        Site alterar = SiteDAO.getInstance().find(oldTitle);
        if(alterar != null){
            alterar.setTitle(title);
            alterar.setUrl(url);
        }
    }*/
}
