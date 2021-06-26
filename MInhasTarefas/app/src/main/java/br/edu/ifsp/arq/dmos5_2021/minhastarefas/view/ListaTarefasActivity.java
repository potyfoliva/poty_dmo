package br.edu.ifsp.arq.dmos5_2021.minhastarefas.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.minhastarefas.R;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.adapter.ItemTarefaAdapter;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.controller.TarefaController;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.model.Tarefa;

public class ListaTarefasActivity extends AppCompatActivity {

    private List<Tarefa> mTarefas = new ArrayList<>();
    private RecyclerView mTarefasRecyclerView;
    private FloatingActionButton mActionButton;
    private ItemTarefaAdapter mItemTarefaAdapter;
    private String tipoLista;
    private String temaSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            tipoLista = bundle.getString(Constantes.TIPO_LISTA);
            temaSelecionado = bundle.getString(Constantes.THEME_KEY);
        }

        getTema();

        setContentView(R.layout.activity_lista_tarefas);

        if(!mTarefas.isEmpty()){
            mTarefas.clear();
        }

        getLista();

        mItemTarefaAdapter = new ItemTarefaAdapter(this, mTarefas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mTarefasRecyclerView = findViewById(R.id.recycler_tarefas);
        mTarefasRecyclerView.setLayoutManager(layoutManager);
        mTarefasRecyclerView.setAdapter(mItemTarefaAdapter);

        mTarefasRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mActionButton = findViewById(R.id.fab_add_tarefa);
        mActionButton.setOnClickListener(v -> novaTarefa());

        if(tipoLista.equals(Constantes.LISTA1)) {
            mItemTarefaAdapter.setClickListener(position -> updateTarefa(position));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constantes.REQUEST_CODE_NEW_TAREFA:
                    TarefaController.addTarefa(
                            this,
                            data.getStringExtra(Constantes.KEY_DESCRICAO),
                            data.getStringExtra(Constantes.KEY_PRIORIDADE),
                            data.getStringExtra(Constantes.KEY_DATA)
                    );
                    break;
                case Constantes.REQUEST_CODE_UPDATE_TAREFA:
                    String descricaoAnterior = data.getStringExtra(Constantes.KEY_DESCRICAO_ANTERIOR);
                    String descricao = data.getStringExtra(Constantes.KEY_DESCRICAO);
                    String prioridade = data.getStringExtra(Constantes.KEY_PRIORIDADE);
                    String dataLimite = data.getStringExtra(Constantes.KEY_DATA);
                    TarefaController.updateTarefa(this, descricaoAnterior, descricao, prioridade, dataLimite);
                    break;
            }
            //updateAdapter();
            recreate();
        }
    }

    @SuppressWarnings("deprecation")
    private void novaTarefa(){
        Intent intent = new Intent(this, TarefaActivity.class);
        intent.putExtra(Constantes.THEME_KEY, temaSelecionado);
        startActivityForResult(intent, Constantes.REQUEST_CODE_NEW_TAREFA);
    }

    @SuppressWarnings("deprecation")
    private void updateTarefa(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_DESCRICAO, mTarefas.get(position).getDescricao());
        bundle.putString(Constantes.KEY_PRIORIDADE, mTarefas.get(position).getPrioridade());
        bundle.putString(Constantes.KEY_DATA, mTarefas.get(position).getData());

        Intent intent = new Intent(this, TarefaActivity.class);
        intent.putExtra(Constantes.THEME_KEY, temaSelecionado);
        intent.putExtras(bundle);
        startActivityForResult(intent, Constantes.REQUEST_CODE_UPDATE_TAREFA);
    }

    /*private void removeTarefa(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_DESCRICAO, mTarefas.get(position).getDescricao());
        bundle.putString(Constantes.KEY_PRIORIDADE, mTarefas.get(position).getPrioridade());
        bundle.putString(Constantes.KEY_DATA, mTarefas.get(position).getData());

        Intent intent = new Intent(this, TarefaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Constantes.REQUEST_CODE_UPDATE_TAREFA);
    }*/

    private void getLista(){
        if(tipoLista.equals(Constantes.LISTA1)){
            mTarefas = TarefaController.allTarefas(this);
        }else if(tipoLista.equals(Constantes.LISTA2)){
            mTarefas = TarefaController.allTarefasConcluidas(this);
        }
    }

    private void getTema(){
        if(temaSelecionado.equals(Constantes.THEME_PADRAO)){
            getTheme().applyStyle(R.style.Theme_MInhasTarefas, true);
        }else if(temaSelecionado.equals(Constantes.THEME_CANDY)){
            getTheme().applyStyle(R.style.Theme_Candy, true);
        }else if(temaSelecionado.equals(Constantes.THEME_ESCURO)){
            getTheme().applyStyle(R.style.Theme_Escuro, true);
        }
    }

    public void updateAdapter() {
        mItemTarefaAdapter.notifyDataSetChanged();
    }
}