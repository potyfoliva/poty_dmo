
package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.provider.BaseColumns;

public class AtividadeContract {

    private AtividadeContract() {
    }

    public static class  AtividadeEntry implements BaseColumns {
        public static final String TABLE_NAME = "atividade";

        public static final String COLUMN_APARELHO = "aparelho";
        public static final String COLUMN_REPETICAO = "repeticao";
        public static final String COLUMN_TEMPO = "tempo";
        public static final String COLUMN_COMPLETADO = "completado";

    }

    public static String createTable() {
        return "CREATE TABLE " +  AtividadeEntry.TABLE_NAME + " ("
                + AtividadeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AtividadeEntry.COLUMN_APARELHO + " TEXT NOT NULL, "
                + AtividadeEntry.COLUMN_REPETICAO + " INTEGER, "
                + AtividadeEntry.COLUMN_TEMPO + " INTEGER, "
                + AtividadeEntry.COLUMN_COMPLETADO + " INTEGER CHECK (" + AtividadeEntry.COLUMN_COMPLETADO + " IN (0,1)) DEFAULT 0, "
                + "FOREIGN KEY (" + AtividadeContract.AtividadeEntry.COLUMN_APARELHO + ") REFERENCES " + AparelhoContract.AparelhoEntry.TABLE_NAME + "(" + AparelhoContract.AparelhoEntry.COLUMN_NOME + ") )";
    }
}
