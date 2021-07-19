package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.provider.BaseColumns;

public class UsuarioContract {

    private UsuarioContract() {
    }

    public static class UsuarioEntry implements BaseColumns {
        public static final String TABLE_NAME = "usuario";

        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_SENHA = "senha";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_TIPO = "tipo";
    }

    public static String createTable() {
        return "CREATE TABLE " +  UsuarioContract.UsuarioEntry.TABLE_NAME + " ("
                + UsuarioContract.UsuarioEntry.COLUMN_NOME + " TEXT NOT NULL, "
                + UsuarioContract.UsuarioEntry.COLUMN_SENHA + " INTEGER NOT NULL, "
                + UsuarioContract.UsuarioEntry.COLUMN_EMAIL + " TEXT NOT NULL PRIMARY KEY, "
                + UsuarioContract.UsuarioEntry.COLUMN_TIPO + " TEXT NOT NULL)";
    }
}
