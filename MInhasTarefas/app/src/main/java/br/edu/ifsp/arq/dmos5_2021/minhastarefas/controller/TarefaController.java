package br.edu.ifsp.arq.dmos5_2021.minhastarefas.controller;

import android.content.Context;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.minhastarefas.dao.TarefaDAO;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.model.Tarefa;

public class TarefaController {

    public static List<Tarefa> allTarefas(Context context){
        return TarefaDAO.getInstance(context).getTarefas();
    }

    public static List<Tarefa> allTarefasConcluidas(Context context){
        return TarefaDAO.getInstance(context).getTarefasConcluidas();
    }

    public static void addTarefa(Context context, String descricao, String prioridade, String data, String telefone){
        Tarefa novaTarefa = new Tarefa(descricao, prioridade, data, telefone);
        TarefaDAO.getInstance(context).addTarefa(novaTarefa);
    }

    public static void removeTarefa(Context context, Tarefa tarefa){
        TarefaDAO.getInstance(context).removerTarefa(tarefa);
    }

    public static void concluiTarefa(Context context, Tarefa tarefa){
        TarefaDAO.getInstance(context).concluirTarefa(tarefa);
    }

    public static void updateTarefa(Context context, String oldDescricao, String descricao, String prioridade, String data, String telefone){
        TarefaDAO.getInstance(context).updateTarefa(oldDescricao, descricao, prioridade, data, telefone);
    }
}
