package br.edu.ifsp.arq.dmos5_2021.meutreino.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemEsportistaAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.constants.Constants;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.AparelhoDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.UsuarioDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class UsuarioController {

    public static boolean cadastrar(Context context, String nome, int senha, String email, String tipo){
        Usuario usuario = new Usuario(nome, senha, email, tipo);
        UsuarioDAO dao = new UsuarioDAO(context);
        return dao.insert(usuario);
    }

    public static boolean checkLogin(Context context, String userName, int password, String tipo){
        UsuarioDAO usuarioDAO;
        usuarioDAO = new UsuarioDAO(context);
        //usuario = usuarioDAO.recuperate(userName);
        /*if(usuario != null){
            return usuario.validate(userName, password, tipo);
        }*/
        if(usuarioDAO.validate(userName, password, tipo)){
            return true;
        }
        return false;
    }

    public static ItemEsportistaAdapter getEsportistasAdapter(Context context, RecyclerItemClickListener listener){
        UsuarioDAO dao = new UsuarioDAO(context);
        List<Usuario> usuarios = dao.recuperate();
        List<Usuario> aux = new ArrayList<>();
        for (Usuario u: usuarios) {
            if(u.getTipo().equals(Constants.ESPORTISTA)){
                aux.add(u);
            }
        }
        return new ItemEsportistaAdapter(aux, listener);
    }

    public static Usuario getUsuario(Context context, String userName){
        Usuario usuario;
        UsuarioDAO dao = new UsuarioDAO(context);
        usuario = dao.recuperate(userName);
        return usuario;
    }


}
