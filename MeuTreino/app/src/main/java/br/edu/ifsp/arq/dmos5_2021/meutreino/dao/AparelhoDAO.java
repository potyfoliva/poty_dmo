package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;

public class AparelhoDAO {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;
    private ExercicioDAO exercicioDAO;

    public AparelhoDAO(@Nullable Context context) {
        mHelper = new SQLiteHelper(context);
        exercicioDAO = new ExercicioDAO(context);
    }

    public Aparelho recuperate(String nome){
        Aparelho aparelho = null;
        String columns[] = new String[]{
                AparelhoContract.AparelhoEntry.COLUMN_NOME,
                AparelhoContract.AparelhoEntry.COLUMN_USO,
                AparelhoContract.AparelhoEntry.COLUMN_FOTO,
                AparelhoContract.AparelhoEntry.COLUMN_EXERCICIO,
        };
        String selection = AparelhoContract.AparelhoEntry.COLUMN_NOME + " = ?";
        String args[] = {nome};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(AparelhoContract.AparelhoEntry.TABLE_NAME, columns, selection, args, null, null, null);
            if(cursor.moveToNext()){
                aparelho = new Aparelho(cursor.getString(0), cursor.getString(1), cursor.getString(2), exercicioDAO.recuperate(cursor.getString(3)));
            }
        }catch (Exception e){
            aparelho = null;
        }finally {
            mDatabase.close();
        }
        return aparelho;
    }

    public Aparelho recuperateExercicio(String nome){
        Aparelho aparelho = null;
        String columns[] = new String[]{
                AparelhoContract.AparelhoEntry.COLUMN_NOME,
                AparelhoContract.AparelhoEntry.COLUMN_USO,
                AparelhoContract.AparelhoEntry.COLUMN_FOTO,
                AparelhoContract.AparelhoEntry.COLUMN_EXERCICIO,
        };
        String selection = AparelhoContract.AparelhoEntry.COLUMN_EXERCICIO + " = ?";
        String args[] = {nome};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(AparelhoContract.AparelhoEntry.TABLE_NAME, columns, selection, args, null, null, null);
            if(cursor.moveToNext()){
                aparelho = new Aparelho(cursor.getString(0), cursor.getString(1), cursor.getString(2), exercicioDAO.recuperate(cursor.getString(3)));
            }
        }catch (Exception e){
            aparelho = null;
        }finally {
            mDatabase.close();
        }
        return aparelho;
    }

    public List<Aparelho> recuperate(){
        Aparelho mAparelho;
        List<Aparelho> mAparelhos = new ArrayList<>();

        Cursor mCursor = null;

        String mColunas[] = new String[]{
                AparelhoContract.AparelhoEntry.COLUMN_NOME,
                AparelhoContract.AparelhoEntry.COLUMN_USO,
                AparelhoContract.AparelhoEntry.COLUMN_FOTO,
                AparelhoContract.AparelhoEntry.COLUMN_EXERCICIO,
        };
        try {
            mDatabase = mHelper.getReadableDatabase();
            mCursor = mDatabase.query(
                    AparelhoContract.AparelhoEntry.TABLE_NAME,
                    mColunas,
                    null,
                    null,
                    null,
                    null,
                    AparelhoContract.AparelhoEntry.COLUMN_NOME
            );

            while (mCursor.moveToNext()) {
                mAparelho = new Aparelho(
                        mCursor.getString(0),
                        mCursor.getString(1),
                        mCursor.getString(2),
                        exercicioDAO.recuperate(mCursor.getString(3))
                );
                mAparelhos.add(mAparelho);
            }
        }catch (Exception e){
            mAparelhos = null;
        }finally {
            mCursor.close();
            mDatabase.close();
        }

        return mAparelhos;
    }
}
