package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.provider.BaseColumns;

import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;

public class AparelhoContract {

    private AparelhoContract() {
    }

    public static class  AparelhoEntry implements BaseColumns {
        public static final String TABLE_NAME = "aparelho";

        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_USO = "uso";
        public static final String COLUMN_FOTO = "foto";
        public static final String COLUMN_EXERCICIO = "exercicio";
    }

    public static String createTable() {
        return "CREATE TABLE " +  AparelhoEntry.TABLE_NAME + " ("
                + AparelhoEntry.COLUMN_NOME + " TEXT NOT NULL, "
                + AparelhoEntry.COLUMN_USO + " TEXT NOT NULL, "
                + AparelhoEntry.COLUMN_FOTO + " TEXT NOT NULL, "
                + AparelhoEntry.COLUMN_EXERCICIO + " TEXT NOT NULL, "
                + " FOREIGN KEY (" + AparelhoEntry.COLUMN_EXERCICIO + ") REFERENCES " + ExercicioContract.ExercicioEntry.TABLE_NAME + "(" + ExercicioContract.ExercicioEntry.COLUMN_NOME + ") )";
    }
}
