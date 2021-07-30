
package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.provider.BaseColumns;

public class AtividadeContract {

    private AtividadeContract() {
    }

    public static class  AtividadeEntry implements BaseColumns {
        public static final String TABLE_NAME = "atividade";

        public static final String COLUMN_APARELHO = "aparelho";
        public static final String COLUMN_TEMPO_REPETICAO = "tempo_repeticao";
        public static final String COLUMN_COMPLETADO = "completado";

    }

    public static String createTable() {
        return "CREATE TABLE " +  AtividadeEntry.TABLE_NAME + " ("
                + AtividadeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AtividadeEntry.COLUMN_APARELHO + " TEXT NOT NULL, "
                + AtividadeEntry.COLUMN_TEMPO_REPETICAO + " TEXT, "
                + AtividadeEntry.COLUMN_COMPLETADO + " INTEGER, "
                + "FOREIGN KEY (" + AtividadeContract.AtividadeEntry.COLUMN_APARELHO + ") REFERENCES " + AparelhoContract.AparelhoEntry.TABLE_NAME + "(" + AparelhoContract.AparelhoEntry.COLUMN_NOME + ") )";
    }
}
