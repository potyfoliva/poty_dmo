package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.provider.BaseColumns;

public class ExercicioContract {

    private ExercicioContract() {
    }

    public static class ExercicioEntry implements BaseColumns {
        public static final String TABLE_NAME = "exercicio";
        public static final String COLUMN_NOME = "nome";
    }

    public static String createTable() {
        return "CREATE TABLE " + ExercicioEntry.TABLE_NAME + " ("
                + ExercicioEntry.COLUMN_NOME + " TEXT NOT NULL)";
    }
}
