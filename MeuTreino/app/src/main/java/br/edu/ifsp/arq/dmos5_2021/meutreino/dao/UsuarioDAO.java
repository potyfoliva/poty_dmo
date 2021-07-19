package br.edu.ifsp.arq.dmos5_2021.meutreino.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;

public class UsuarioDAO {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;


    public UsuarioDAO(Context context) {
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

    public List<Usuario> recuperate(){
        List<Usuario> usuarioList = new ArrayList<>();
        String columns[] = {
                UsuarioContract.UsuarioEntry.COLUMN_NOME,
                UsuarioContract.UsuarioEntry.COLUMN_SENHA,
                UsuarioContract.UsuarioEntry.COLUMN_EMAIL,
                UsuarioContract.UsuarioEntry.COLUMN_TIPO
        };

        try{
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(
                    UsuarioContract.UsuarioEntry.TABLE_NAME,
                    columns,
                    null,null,null,null,
                    UsuarioContract.UsuarioEntry.COLUMN_EMAIL
            );

            while (cursor.moveToNext()){
                usuarioList.add(new Usuario(cursor.getString(0), cursor.getLong(1), cursor.getString(2), cursor.getString(3)));
            }
        }catch (Exception e){
            usuarioList = null;
        }finally {
            mDatabase.close();
        }

        return usuarioList;
    }

    public Usuario recuperate(String email){
        Usuario usuario = null;
        String columns[] = {UsuarioContract.UsuarioEntry.COLUMN_NOME,
                            UsuarioContract.UsuarioEntry.COLUMN_SENHA,
                            UsuarioContract.UsuarioEntry.COLUMN_EMAIL,
                            UsuarioContract.UsuarioEntry.COLUMN_TIPO};
        String selection = UsuarioContract.UsuarioEntry.COLUMN_EMAIL + " = ?";
        String args[] = {email};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(ExercicioContract.ExercicioEntry.TABLE_NAME, columns, selection, args, null, null, null);
            if(cursor.moveToNext()){
                usuario = new Usuario(cursor.getString(0), cursor.getLong(1), cursor.getString(2), cursor.getString(3));
            }
        }catch (Exception e){
            usuario = null;
        }finally {
            mDatabase.close();
        }
        return usuario;
    }
}
