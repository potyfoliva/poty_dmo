package br.edu.ifsp.arq.dmos5_2021.loginhardcode.controller;

import br.edu.ifsp.arq.dmos5_2021.loginhardcode.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.loginhardcode.data.UsuarioDAO;
import br.edu.ifsp.arq.dmos5_2021.loginhardcode.model.Usuario;

public class LoginController {

    public static boolean checkLogin(String userName, int password){
        UsuarioDAO usuarioDAO;
        Usuario usuario;
        usuarioDAO = new UsuarioDAO();
        usuario = usuarioDAO.recuperate(userName);
        if(usuario != null){
            return usuario.validate(userName, password);
        }
        return false;
    }
}
