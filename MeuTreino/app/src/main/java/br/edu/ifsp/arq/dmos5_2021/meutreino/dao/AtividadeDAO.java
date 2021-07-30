package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Atividade;

public class AtividadeDAO {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;
    private AparelhoDAO mAparelhoDAO;

    public AtividadeDAO(@Nullable Context context) {
        mHelper = new SQLiteHelper(context);
        mAparelhoDAO = new AparelhoDAO(context);
    }

    public boolean insert(Atividade atividade){
        long linhas;
        ContentValues values = new ContentValues();
        values.put(AtividadeContract.AtividadeEntry.COLUMN_APARELHO, atividade.getAparelho().getNome());
        values.put(AtividadeContract.AtividadeEntry.COLUMN_TEMPO_REPETICAO, atividade.getTempo_repeticao());
        values.put(AtividadeContract.AtividadeEntry.COLUMN_COMPLETADO, atividade.getCompletado());
        if(recuperate(atividade) == null) {
            try {
                mDatabase = mHelper.getWritableDatabase();
                linhas = mDatabase.insert(
                        AtividadeContract.AtividadeEntry.TABLE_NAME,
                        null,
                        values
                );
                //mDatabase.close();
            } catch (Exception e) {
                linhas = -1;
            } finally {
                mDatabase.close();
            }
        }else{
            linhas = -1;
        }
        return ! (linhas == -1);
    }

    public Atividade recuperate(Atividade atividade){
        Atividade aux = null;
        String columns[] = new String[]{
                AtividadeContract.AtividadeEntry._ID,
                AtividadeContract.AtividadeEntry.COLUMN_APARELHO,
                AtividadeContract.AtividadeEntry.COLUMN_TEMPO_REPETICAO,
                AtividadeContract.AtividadeEntry.COLUMN_COMPLETADO,
        };
        String selection = AcademiaContract.AcademiaEntry._ID + " = ?";
        String args[] = {String.valueOf(atividade.getId())};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(AtividadeContract.AtividadeEntry.TABLE_NAME, columns, selection, args, null, null, null);
            if(cursor.moveToNext()){
                aux = new Atividade(cursor.getInt(0), mAparelhoDAO.recuperate(cursor.getString(1)), cursor.getString(2), cursor.getInt(3));
            }
        }catch (Exception e){
            aux = null;
        }finally {
            mDatabase.close();
        }
        return aux;
    }

}
