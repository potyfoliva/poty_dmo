package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Academia;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "meu_treino.db";
    public static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ExercicioContract.createTable());
        inserirExercicios(db);

        db.execSQL(AparelhoContract.createTable());
        inserirAparelhos(db);

        db.execSQL(AcademiaContract.createTable());
    }

    public void inserirExercicios(SQLiteDatabase db){
        String sql_exercicio_1 = "INSERT INTO " + ExercicioContract.ExercicioEntry.TABLE_NAME
                + " VALUES "
                + "('Abdominal')";
        db.execSQL(sql_exercicio_1);

        String sql_exercicio_2 = "INSERT INTO " + ExercicioContract.ExercicioEntry.TABLE_NAME
                + " VALUES "
                + "('Caminhada')";
        db.execSQL(sql_exercicio_2);

        String sql_exercicio_3 = "INSERT INTO " + ExercicioContract.ExercicioEntry.TABLE_NAME
                + " VALUES "
                + "('Peitoral')";
        db.execSQL(sql_exercicio_3);

        String sql_exercicio_4 = "INSERT INTO " + ExercicioContract.ExercicioEntry.TABLE_NAME
                + " VALUES "
                + "('Remador')";
        db.execSQL(sql_exercicio_4);

        String sql_exercicio_5 = "INSERT INTO " + ExercicioContract.ExercicioEntry.TABLE_NAME
                + " VALUES "
                + "('Escada')";
        db.execSQL(sql_exercicio_5);
    }

    public void inserirAparelhos(SQLiteDatabase db){
        String sql_aparelho_1 = "INSERT INTO " + AparelhoContract.AparelhoEntry.TABLE_NAME
                + " VALUES "
                + " ("
                + "'Abdominal'" + ", "
                + "'Este aparelho tem como objetivo o fortalecimento da musculatura abdominal. Exercício essencial para definir os músculos abdominais e eliminar gorduras indesejadas e também auxilia no equilíbrio postural do corpo.'" + ", "
                + "'@drawable/abdominal'" + ", "
                + "'Abdominal'"
                + ")";
        db.execSQL(sql_aparelho_1);

        String sql_aparelho_2 = "INSERT INTO " + AparelhoContract.AparelhoEntry.TABLE_NAME
                + " VALUES "
                + " ("
                + "'Elíptico'" + ", "
                + "'Estimula a coordenação motora, aumenta a resistência muscular dos membros inferiores e desenvolve o trabalho aeróbico. É de extrema importância que os pés do usuário fiquem apoiado nos suportes, para poupar os joelhos e a panturrilha, e faça movimentos para frente e para trás.'" + ", "
                + "'@drawable/eliptico'" + ", "
                + "'Caminhada'"
                + ")";
        db.execSQL(sql_aparelho_2);

        String sql_aparelho_3 = "INSERT INTO " + AparelhoContract.AparelhoEntry.TABLE_NAME
                + " VALUES "
                + " ("
                + "'Peitoral'" + ", "
                + "'Este aparelho fortalece os membros superiores: ombros, braços e peitoral. Para um treino com ótimo resultado é necessário que faça movimentos com amplitude.'" + ", "
                + "'@drawable/peitoral'" + ", "
                + "'Peitoral'"
                + ")";
        db.execSQL(sql_aparelho_3);

        String sql_aparelho_4 = "INSERT INTO " + AparelhoContract.AparelhoEntry.TABLE_NAME
                + " VALUES "
                + " ("
                + "'Remadores'" + ", "
                + "'Este aparelho trabalha a musculatura das costas e posterior de ombro, seu propósito é fortalecer todos os músculos posterior ao ombro. Mas tenha cuidado ao realizar este exercício e não cometer erros posturais que irá prejudicar a sua coluna futuramente.'" + ", "
                + "'@drawable/remadores'" + ", "
                + "'Remador'"
                + ")";
        db.execSQL(sql_aparelho_4);

        String sql_aparelho_5 = "INSERT INTO " + AparelhoContract.AparelhoEntry.TABLE_NAME
                + " VALUES "
                + " ("
                + "'Escada'" + ", "
                + "'Melhora a agilidade e fortalece a musculatura dos membros inferiores. Um dos melhores equipamentos que vai cuidar da sua situação cardiorrespiratória e ainda irá fortalecer sua panturrilha, coxa (superior e inferior) e firmar o glúteo. Ele ainda contribui para a perda de peso e a diminuição calórica.'" + ", "
                + "'@drawable/simulador_escada'" + ", "
                + "'Escada'"
                + ")";
        db.execSQL(sql_aparelho_5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql;
        switch (oldVersion){
            case 1:
                db.execSQL(LivroContract.alterTableToVersao2());
            case 2:
                //Criar table amigo
                db.execSQL(AmigoContract.createTable());

                //Renomeia tabela livros
                sql = "ALTER TABLE " + LivroContract.LivroEntry.TABLE_NAME
                        + " RENAME TO " + LivroContract.LivroEntry.TABLE_NAME_OLD;
                db.execSQL(sql);

                //Cria nova tabela livros com a chave primaria
                db.execSQL(LivroContract.createTable());

                //Insere todos os livros já cadastrados na nova tabela livros
                sql = "INSERT INTO " + LivroContract.LivroEntry.TABLE_NAME + " ("
                        + LivroContract.LivroEntry.COLUMN_TITLE + ", "
                        + LivroContract.LivroEntry.COLUMN_AUTHOR + ", "
                        + LivroContract.LivroEntry.COLUMN_BORROWED + ") "
                        + " SELECT "
                        + LivroContract.LivroEntry.COLUMN_TITLE + ", "
                        + LivroContract.LivroEntry.COLUMN_AUTHOR + ", "
                        + LivroContract.LivroEntry.COLUMN_BORROWED
                        + " FROM " + LivroContract.LivroEntry.TABLE_NAME_OLD;
                db.execSQL(sql);

                //Apaga a tabela livros antiga
                sql = "DROP TABLE " + LivroContract.LivroEntry.TABLE_NAME_OLD;
                db.execSQL(sql);
        }
    }*/
}
