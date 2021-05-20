package br.edu.ifsp.arq.dmos5_2021.loginhardcode.data;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.loginhardcode.model.Usuario;

public class UsuarioDAO {

    private List<Usuario> usuarioList;

    public UsuarioDAO() {
        this.usuarioList = new ArrayList<>(10);
        usuarioList.add(new Usuario("poty@email.com", 123));
        usuarioList.add(new Usuario("poty2@email.com", 123));
        usuarioList.add(new Usuario("poty3@email.com", 123));
        usuarioList.add(new Usuario("poty4@email.com", 123));
        usuarioList.add(new Usuario("poty5@email.com", 123));
    }

    public Usuario recuperate(String userName){
        for(Usuario u : usuarioList){
            if(u.getUserName().equalsIgnoreCase(userName)){
                return u;
            }
        }
        return null;
    }
}
