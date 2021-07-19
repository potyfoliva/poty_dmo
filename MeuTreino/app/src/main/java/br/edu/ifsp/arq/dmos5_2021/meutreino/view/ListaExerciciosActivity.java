package br.edu.ifsp.arq.dmos5_2021.meutreino.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemExercicioAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.constants.Constants;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.AparelhoController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.ExercicioController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;

public class ListaExerciciosActivity extends AppCompatActivity {

    private RecyclerView mExerciciosRecyclerView;
    private ItemExercicioAdapter mItemExercicioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exercicios);

        mExerciciosRecyclerView = findViewById(R.id.recycler_exercicios);

        mItemExercicioAdapter = ExercicioController.getExerciciosAdapter(this, position -> exibirAparelho(position));

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mExerciciosRecyclerView.setLayoutManager(manager);
        mExerciciosRecyclerView.setAdapter(mItemExercicioAdapter);

        mExerciciosRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void exibirAparelho(int position){
        String nome = mItemExercicioAdapter.getDataSource().get(position).getNome();
        Aparelho aparelho = ExercicioController.exibirAparelho(this, nome);

        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_NOME, aparelho.getNome());
        bundle.putString(Constants.KEY_USO, aparelho.getUso());
        bundle.putString(Constants.KEY_FOTO, aparelho.getFoto());
        bundle.putString(Constants.KEY_EXERCICIO, aparelho.getExercicio().getNome());

        Intent intent = new Intent(this, AparelhoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}