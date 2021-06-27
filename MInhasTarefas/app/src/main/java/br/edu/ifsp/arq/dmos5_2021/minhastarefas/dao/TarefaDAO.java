package br.edu.ifsp.arq.dmos5_2021.minhastarefas.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.edu.ifsp.arq.dmos5_2021.minhastarefas.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.model.Tarefa;

public class TarefaDAO {

    private final String TAG = this.getClass().getSimpleName();
    private static TarefaDAO instance = null;
    private List<Tarefa> listaTarefas;
    private Context context;

    public TarefaDAO() {
        //tarefaList = new ArrayList<>(2);
        //tarefaList.add(new Tarefa("tarefa teste 1", "Média", ""));
        //tarefaList.add(new Tarefa("tarefa teste 2", "Baixa", "22/06/2021"));
    }

    private TarefaDAO(Context context){
        this.context = context;
        listaTarefas = new ArrayList<>();
        selectAll();
        ordenarTarefas();
    }

    public static synchronized TarefaDAO getInstance(Context context){
        if(instance == null){
            instance = new TarefaDAO(context);
        }
        return instance;
    }

    public List<Tarefa> getTarefas(){
        List<Tarefa> listaTarefasNaoConcluidas = new ArrayList<>();
        for (Tarefa t : listaTarefas) {
            if(!t.isConcluida()){
                listaTarefasNaoConcluidas.add(t);
            }
        }
        return listaTarefasNaoConcluidas;
    }

    public List<Tarefa> getTarefasConcluidas(){
        List<Tarefa> listaTarefasConcluidas = new ArrayList<>();
        for (Tarefa t : listaTarefas) {
            if(t.isConcluida()){
                listaTarefasConcluidas.add(t);
            }
        }
        return listaTarefasConcluidas;
    }

    private void selectAll() {
        SharedPreferences sharedPreferences;
        JSONObject jsonObject;
        JSONArray jsonArray;
        String jsonString;
        Tarefa tarefa;

        sharedPreferences = this.context.getSharedPreferences(Constantes.DATA_FILE_NAME, Context.MODE_PRIVATE);
        jsonString = sharedPreferences.getString(Constantes.TABLE_NAME, "");

        if(!jsonString.isEmpty()){
            try{
                jsonArray = new JSONArray(jsonString);
                for(int i=0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    tarefa = new Tarefa(
                            jsonObject.getString(Constantes.ATTR_DESCRICAO),
                            jsonObject.getString(Constantes.ATTR_PRIORIDADE),
                            jsonObject.getString(Constantes.ATTR_DATA),
                            jsonObject.getString(Constantes.ATTR_TELEFONE));
                    tarefa.setConcluida(jsonObject.getBoolean(Constantes.ATTR_CONCLUIDA));
                    listaTarefas.add(tarefa);
                    Log.i("tag", tarefa.toString());
                }
                ordenarTarefas();
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

        for(Tarefa t : listaTarefas){
            jsonObject = new JSONObject();
                try {
                    jsonObject.put(Constantes.ATTR_DESCRICAO, t.getDescricao());
                    jsonObject.put(Constantes.ATTR_PRIORIDADE, t.getPrioridade());
                    jsonObject.put(Constantes.ATTR_DATA, t.getData());
                    jsonObject.put(Constantes.ATTR_TELEFONE, t.getTelefone());
                    jsonObject.put(Constantes.KEY_CONCLUIDA, t.isConcluida());
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                }
        }
        editor.putString(Constantes.TABLE_NAME, jsonArray.toString());
        editor.commit();
    }

    public void addTarefa(Tarefa tarefa){
        if(tarefa != null){
            listaTarefas.add(tarefa);
            ordenarTarefas();
            commitAll();
            if(!listaTarefas.isEmpty()){
                listaTarefas.clear();
            }
            selectAll();
        }
    }

    public void updateTarefa(String oldDescricao, String descricao, String prioridade, String data, String telefone){
        Tarefa alterar = find(oldDescricao);
        if(alterar != null){
            alterar.setDescricao(descricao);
            alterar.setPrioridade(prioridade);
            alterar.setData(data);
            alterar.setTelefone(telefone);
            ordenarTarefas();
            commitAll();
            if(!listaTarefas.isEmpty()){
                listaTarefas.clear();
            }
            selectAll();
        }
    }

    public Tarefa find(int i){
        return listaTarefas.get(i);
    }

    public Tarefa find(String descricao){
        for(Tarefa t : listaTarefas){
            if(t.getDescricao().equals(descricao)){
                return t;
            }
        }
        return null;
    }

    public void removerTarefa(Tarefa tarefa){
        if(tarefa != null) {
            if (listaTarefas.contains(tarefa)) {
                listaTarefas.remove(tarefa);
            }
            ordenarTarefas();
            commitAll();
            if(!listaTarefas.isEmpty()){
                listaTarefas.clear();
            }
            selectAll();
        }

    }

    public void concluirTarefa(Tarefa tarefa){
        if(tarefa != null && listaTarefas.contains(tarefa) && !tarefa.isConcluida()){
            tarefa.setConcluida(true);
            ordenarTarefas();
            commitAll();
            if(!listaTarefas.isEmpty()){
                listaTarefas.clear();
            }
            selectAll();
        }
    }

    private void ordenarTarefas(){

        List<Tarefa> listaAux = listaTarefas;
        List<Tarefa> listaPrioridadeAlta = new ArrayList<>();
        List<Tarefa> listaPrioridadeMedia = new ArrayList<>();
        List<Tarefa> listaPrioridadeBaixa = new ArrayList<>();
        List<Tarefa> listaPrioridadeVazia = new ArrayList<>();
        List<Tarefa> listaOrdenada = new ArrayList<>();

        for (Tarefa t : listaAux) {
            if(t.getPrioridade().equals(Constantes.PRIORIDADE_ALTA)){
               listaPrioridadeAlta.add(t);
               Collections.sort(listaPrioridadeAlta);
            }
            else if(t.getPrioridade().equals(Constantes.PRIORIDADE_MEDIA)){
                listaPrioridadeMedia.add(t);
                Collections.sort(listaPrioridadeMedia);
            }
            else if(t.getPrioridade().equals(Constantes.PRIORIDADE_BAIXA)){
                listaPrioridadeBaixa.add(t);
                Collections.sort(listaPrioridadeBaixa);
            }
            else if(t.getPrioridade().equals(Constantes.PRIORIDADE_VAZIA)){
                listaPrioridadeVazia.add(t);
                Collections.sort(listaPrioridadeVazia);
            }
        }

        for (Tarefa t : listaPrioridadeAlta) {
            if(!listaOrdenada.contains(t)){
                listaOrdenada.add(t);
            }
        }

        for (Tarefa t : listaPrioridadeMedia) {
            if(!listaOrdenada.contains(t)){
                listaOrdenada.add(t);
            }
        }

        for (Tarefa t : listaPrioridadeBaixa) {
            if(!listaOrdenada.contains(t)){
                listaOrdenada.add(t);
            }
        }

        for (Tarefa t : listaPrioridadeVazia) {
            if(!listaOrdenada.contains(t)){
                listaOrdenada.add(t);
            }
        }

        listaTarefas.clear();
        listaTarefas.addAll(listaOrdenada);

        //Log.i("lista ordenada pela prioridade", listaAux.toString());

    }
}
