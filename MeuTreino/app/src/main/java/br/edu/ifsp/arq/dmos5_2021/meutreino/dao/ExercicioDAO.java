package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;

public class ExercicioDAO {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;


    public ExercicioDAO(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    /*public boolean insert(Exercicio exercicio){
        boolean deuCerto = true;
        ContentValues values = new ContentValues();
        values.put(ExercicioContract.ExercicioEntry.COLUMN_NOME, exercicio.getNome());

        try{
            mDatabase = mHelper.getWritableDatabase();
            long lines = mDatabase.insert(ExercicioContract.ExercicioEntry.TABLE_NAME, null, values);
            if(lines == -1)
                deuCerto = false;
        }catch (Exception e){
            deuCerto = false;
        }finally {
            mDatabase.close();
        }
        return deuCerto;
    }*/

    public List<Exercicio> recuperate(){
        List<Exercicio> exercicioList = new ArrayList<>();
        String columns[] = {
                ExercicioContract.ExercicioEntry.COLUMN_NOME
        };

        try{
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(
                    ExercicioContract.ExercicioEntry.TABLE_NAME,
                    columns,
                    null,null,null,null,
                    ExercicioContract.ExercicioEntry.COLUMN_NOME
            );

            while (cursor.moveToNext()){
                exercicioList.add(new Exercicio(cursor.getString(0)));
            }
        }catch (Exception e){
            exercicioList = null;
        }finally {
            mDatabase.close();
        }

        return exercicioList;
    }

    public Exercicio recuperate(String nome){
        Exercicio exercicio = null;
        String columns[] = {ExercicioContract.ExercicioEntry.COLUMN_NOME};
        String selection = ExercicioContract.ExercicioEntry.COLUMN_NOME + " = ?";
        String args[] = {nome};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor curso = mDatabase.query(ExercicioContract.ExercicioEntry.TABLE_NAME, columns, selection, args, null, null, null);
            if(curso.moveToNext()){
                exercicio = new Exercicio(curso.getString(0));
            }
        }catch (Exception e){
            exercicio = null;
        }finally {
            mDatabase.close();
        }
        return exercicio;
    }
}
