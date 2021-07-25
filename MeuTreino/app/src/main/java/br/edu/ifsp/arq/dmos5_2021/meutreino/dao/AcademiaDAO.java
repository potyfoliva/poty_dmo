package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.constants.Constants;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Academia;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;

public class AcademiaDAO {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;
    private UsuarioDAO mUsuarioDAO;
    private AparelhoDAO mAparelhoDAO;

    public AcademiaDAO(@Nullable Context context) {
        mHelper = new SQLiteHelper(context);
        mUsuarioDAO = new UsuarioDAO(context);
        mAparelhoDAO = new AparelhoDAO(context);
    }

    public boolean insert(Academia academia){
        long linhas;
        ContentValues values = new ContentValues();
        values.put(AcademiaContract.AcademiaEntry.COLUMN_USUARIO, academia.getUsuario().getEmail());
        values.put(AcademiaContract.AcademiaEntry.COLUMN_APARELHO, academia.getAparelho().getNome());
        if(recuperate(academia.getAparelho()) == null) {
            try {
                mDatabase = mHelper.getWritableDatabase();
                linhas = mDatabase.insert(
                        AcademiaContract.AcademiaEntry.TABLE_NAME,
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

    public boolean delete(Academia academia) {
        long linhas;
        String chave1 = academia.getUsuario().getEmail();
        String chave2 = academia.getAparelho().getNome();
        String args[] = {chave1,chave2};
            try {
                mDatabase = mHelper.getWritableDatabase();
                linhas = mDatabase.delete(
                        AcademiaContract.AcademiaEntry.TABLE_NAME,
                        AcademiaContract.AcademiaEntry.COLUMN_USUARIO + "=? AND " + AcademiaContract.AcademiaEntry.COLUMN_APARELHO + "=?",
                         args);
                mDatabase.close();
            } catch (Exception e) {
                linhas = -1;
            } finally {
                mDatabase.close();
            }

        return ! (linhas == -1);

    }

    public Academia recuperate(Aparelho aparelho){
        Academia academia = null;
        String columns[] = new String[]{
                AcademiaContract.AcademiaEntry.COLUMN_USUARIO,
                AcademiaContract.AcademiaEntry.COLUMN_APARELHO
        };
        String selection = AcademiaContract.AcademiaEntry.COLUMN_APARELHO + " = ?";
        String args[] = {aparelho.getNome()};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(AcademiaContract.AcademiaEntry.TABLE_NAME, columns, selection, args, null, null, null);
            if(cursor.moveToNext()){
                academia = new Academia(mUsuarioDAO.recuperate(cursor.getString(0)), mAparelhoDAO.recuperate(cursor.getString(1)));
            }
        }catch (Exception e){
            academia = null;
        }finally {
            mDatabase.close();
        }
        return academia;
    }

    public List<Academia> recuperate(){
        Academia mAcademia;
        List<Academia> mAcademias = new ArrayList<>();

       // Cursor mCursor = null;

        String mColunas[] = new String[]{
                AcademiaContract.AcademiaEntry.COLUMN_USUARIO,
                AcademiaContract.AcademiaEntry.COLUMN_APARELHO
        };
        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor mCursor = mDatabase.query(
                    AcademiaContract.AcademiaEntry.TABLE_NAME,
                    mColunas,
                    null,
                    null,
                    null,
                    null,
                    AcademiaContract.AcademiaEntry.COLUMN_USUARIO,
                    AcademiaContract.AcademiaEntry.COLUMN_APARELHO
            );

            while (mCursor.moveToNext()) {
                mAcademia = new Academia(
                        mUsuarioDAO.recuperate(mCursor.getString(0)),
                        mAparelhoDAO.recuperate(mCursor.getString(1))
                );
                mAcademias.add(mAcademia);
            }
        }catch (Exception e){
            mAcademias = null;
        }finally {
//            mCursor.close();
            mDatabase.close();
        }

        return mAcademias;
    }

    public List<Academia> recuperate(String userName){
        List<Academia> mAcademias = new ArrayList<>();
        String mColunas[] = {
                AcademiaContract.AcademiaEntry.COLUMN_USUARIO,
                AcademiaContract.AcademiaEntry.COLUMN_APARELHO
        };
        String selection = AcademiaContract.AcademiaEntry.COLUMN_USUARIO + " = ?";
        String args[] = {userName};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor mCursor = mDatabase.query(
                    AcademiaContract.AcademiaEntry.TABLE_NAME,
                    mColunas,
                    selection,
                    args,
                    null,
                    null,
                    null
            );

            while(mCursor.moveToNext()) {
                mAcademias.add(new Academia(
                        mUsuarioDAO.recuperate(mCursor.getString(0)),
                        mAparelhoDAO.recuperate(mCursor.getString(1))));
            }
        }catch (Exception e){
            mAcademias = null;
        }finally {
            //mCursor.close();
            mDatabase.close();
        }

        return mAcademias;
    }

}
