package br.edu.ifsp.arq.dmos5_2021.listacontatos.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.listacontatos.R;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.model.Contato;

public class ContatoDAO {

    private final String TAG = this.getClass().getSimpleName();
    private static ContatoDAO instance = null;
    private List<Contato> contatos;
    private List<Contato> favoritos;
    Context context;

    public ContatoDAO() {
        /*contatos = new ArrayList<>(8);
        contatos.add(new Contato("Poty", "poty", "(16)99999-5632", "poty@email.com"));
        contatos.add(new Contato("Ana", "ana", "(16)99555-6521", "ana@email.com"));
        contatos.add(new Contato("Luisa", "luisa", "(16)99999-5632", "luisa@email.com"));
        contatos.add(new Contato("Maria", "maria", "(16)99555-6521", "maria@email.com"));
        contatos.add(new Contato("Cintia", "cintia", "(16)99999-5632", "cintia@email.com"));
        contatos.add(new Contato("Aline", "aline", "(16)99555-6521", "aline@email.com"));
        contatos.add(new Contato("Carla", "carla", "(16)99999-5632", "carla@email.com"));
        contatos.add(new Contato("Vilma", "vilma", "(16)99555-6521", "vilma@email.com"));*/

    }

    public static synchronized ContatoDAO getInstance(Context context){
        if(instance == null){
            instance = new ContatoDAO(context);
        }
        return instance;
    }

    private ContatoDAO(Context context){
        this.context = context;
        contatos = new ArrayList<>();
        favoritos = new ArrayList<>();
        selectAll();
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void addContato(Contato contato){
        if(contato != null){
            contatos.add(contato);
            ordenarContatos();
            commitAll();
        }
    }

    private void selectAll() {
        SharedPreferences sharedPreferences;
        JSONObject jsonObject;
        JSONArray jsonArray;
        String jsonString;
        Contato contato;

        sharedPreferences = this.context.getSharedPreferences(Constantes.DATA_FILE_NAME, Context.MODE_PRIVATE);
        jsonString = sharedPreferences.getString(Constantes.TABLE_NAME, "");
        Log.i(TAG, jsonString);
        if(!jsonString.isEmpty()){
            try{
                jsonArray = new JSONArray(jsonString);
                for(int i=0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    contato = new Contato(
                            jsonObject.getString(Constantes.ATTR_NOME),
                            jsonObject.getString(Constantes.ATTR_APELIDO),
                            jsonObject.getString(Constantes.ATTR_TELEFONE),
                            jsonObject.getString(Constantes.ATTR_EMAIL));
                    contato.setFavorite(jsonObject.getBoolean(Constantes.ATTR_FAVORITE));
                    contatos.add(contato);
                    ordenarContatos();
                    if(contato.isFavorite()) {
                        favoritos.add(contato);
                    }
                }
            }catch (JSONException e){
                Log.e(TAG, "Erro ao recuperar dados do JSON");
            }
        }else {
            Log.i(TAG, "Sem dados armazenados");
        }
    }

    private void commitAll(){
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        JSONObject jsonObject;
        JSONArray jsonArray;

        sharedPreferences = this.context.getSharedPreferences(Constantes.DATA_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        jsonArray = new JSONArray();

        for(Contato c : contatos){
            jsonObject = new JSONObject();
            try {
                jsonObject.put("nome", c.getNome());
                jsonObject.put("apelido", c.getApelido());
                jsonObject.put("telefone", c.getTelefone());
                jsonObject.put("email", c.getEmail());
                jsonObject.put("favorite", c.isFavorite());
                jsonArray.put(jsonObject);
            }catch (JSONException e){
                Log.e(TAG, e.getMessage());
            }
        }
        editor.putString(Constantes.TABLE_NAME, jsonArray.toString());
        editor.commit();
    }

    public Contato find(int i) {
        return contatos.get(i);
    }

    public Contato find(String nome) {
        for (Contato c : contatos) {
            if (c.getNome().equals(nome)) {
                return c;
            }
        }
        return null;
    }

    private void ordenarContatos() {
        Collections.sort(contatos);
    }

    public void addFavorito(Contato contato) {
       if (favoritos.contains(contato)) {
           if (!contato.isFavorite()) {
                favoritos.remove(contato);
           }
       }else{
           if (contato.isFavorite()) {
               favoritos.add(contato);
           }
       }
       commitAll();
    }

    public int getTamanhoFav() {
        return favoritos.size();
    }

    public List<Contato> getFavoritos() {
        return favoritos;
    }

}
