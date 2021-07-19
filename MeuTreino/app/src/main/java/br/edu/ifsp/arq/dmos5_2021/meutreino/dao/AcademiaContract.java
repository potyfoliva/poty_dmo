package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.provider.BaseColumns;

public class AcademiaContract {

    private AcademiaContract() {
    }

    public static class  AcademiaEntry implements BaseColumns {
        public static final String TABLE_NAME = "academia";

        //public static final String COLUMN_USUARIO = "usuario";
        public static final String COLUMN_APARELHO = "aparelho";
    }

    public static String createTable() {
        return "CREATE TABLE " +  AcademiaContract.AcademiaEntry.TABLE_NAME + " ("
                //+ AcademiaContract.AcademiaEntry.COLUMN_USUARIO + " TEXT NOT NULL PRIMARY KEY, "
                + AcademiaContract.AcademiaEntry.COLUMN_APARELHO + " TEXT NOT NULL, "
                //+ "FOREIGN KEY (" + AcademiaContract.AcademiaEntry.COLUMN_USUARIO + ") REFERENCES " + UsuarioContract.UsuarioEntry.TABLE_NAME + "(" + UsuarioContract.UsuarioEntry.COLUMN_EMAIL + ") "
                + "FOREIGN KEY (" + AcademiaContract.AcademiaEntry.COLUMN_APARELHO + ") REFERENCES " + AparelhoContract.AparelhoEntry.TABLE_NAME + "(" + AparelhoContract.AparelhoEntry.COLUMN_NOME + ") )";
    }
}
