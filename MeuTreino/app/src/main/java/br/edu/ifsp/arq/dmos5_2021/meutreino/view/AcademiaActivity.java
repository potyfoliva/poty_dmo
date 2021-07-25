package br.edu.ifsp.arq.dmos5_2021.meutreino.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemAparelhoAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.constants.Constants;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.AcademiaController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.AparelhoController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;

public class AcademiaActivity extends AppCompatActivity {

    private RecyclerView mAparelhosRecyclerView;
    private ItemAparelhoAdapter mItemAparelhoAdapter;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academia);

        mAparelhosRecyclerView = findViewById(R.id.recycler_aparelhos);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            username = bundle.getString(Constants.KEY_USERNAME);
        }

        mItemAparelhoAdapter = AcademiaController.getAparelhosAcademiaAdapter(this, username, position -> exibirAparelho(position));

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mAparelhosRecyclerView.setLayoutManager(manager);
        mAparelhosRecyclerView.setAdapter(mItemAparelhoAdapter);

        mAparelhosRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        AcademiaController.updateAdapterDataSource(this, username, mItemAparelhoAdapter);
    }

    private void exibirAparelho(int position){
       /* String nome = mItemAparelhoAdapter.getDataSource().get(position).getNome();
        Aparelho aparelho = AparelhoController.exibirAparelho(this, nome);

        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_NOME, aparelho.getNome());
        bundle.putString(Constants.KEY_USO, aparelho.getUso());
        bundle.putString(Constants.KEY_FOTO, aparelho.getFoto());
        bundle.putString(Constants.KEY_EXERCICIO, aparelho.getExercicio().getNome());

        Intent intent = new Intent(this, AparelhoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);*/
    }
}