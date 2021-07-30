package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.provider.BaseColumns;

public class TreinoContract {

    private TreinoContract() {
    }

    public static class  TreinoEntry implements BaseColumns {
        public static final String TABLE_NAME = "treino";

        public static final String COLUMN_ATIVIDADE = "atividade";
        public static final String COLUMN_USUARIO = "usuario";
    }

    public static String createTable() {
        return "CREATE TABLE " +  TreinoContract.TreinoEntry.TABLE_NAME + " ("
                + TreinoContract.TreinoEntry.COLUMN_ATIVIDADE + " INTEGER NOT NULL, "
                + TreinoContract.TreinoEntry.COLUMN_USUARIO + " TEXT NOT NULL PRIMARY KEY, "
                + "FOREIGN KEY (" + TreinoContract.TreinoEntry.COLUMN_ATIVIDADE + ") REFERENCES " + AtividadeContract.AtividadeEntry.TABLE_NAME + "(" + AtividadeContract.AtividadeEntry._ID + ") "
                + "FOREIGN KEY (" + TreinoContract.TreinoEntry.COLUMN_USUARIO + ") REFERENCES " + UsuarioContract.UsuarioEntry.TABLE_NAME + "(" + UsuarioContract.UsuarioEntry.COLUMN_NOME + ") )";
    }
}
