package br.edu.ifsp.arq.dmos5_2021.meutreino.view.educador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemEsportistaAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.AcademiaController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.UsuarioController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Academia;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;

public class ListaEsportistasActivity extends AppCompatActivity {

    private RecyclerView mEsportistasRecyclerView;
    private ItemEsportistaAdapter mItemEsportistaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_esportistas);

        mEsportistasRecyclerView = findViewById(R.id.recycler_esportistas);

        mItemEsportistaAdapter = UsuarioController.getEsportistasAdapter(this, position -> exibirAcademiaEsportista(position));

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mEsportistasRecyclerView.setLayoutManager(manager);
        mEsportistasRecyclerView.setAdapter(mItemEsportistaAdapter);

        mEsportistasRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void exibirAcademiaEsportista(int position){
        String nome = mItemEsportistaAdapter.getDataSource().get(position).getNome();

        Usuario usuario;
        //List<Academia> academia = AcademiaController.getAcademia(this);
        //Aparelho aparelho = ExercicioController.exibirAparelho(this, nome);

        /*Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_NOME, aparelho.getNome());
        bundle.putString(Constants.KEY_USO, aparelho.getUso());
        bundle.putString(Constants.KEY_FOTO, aparelho.getFoto());
        bundle.putString(Constants.KEY_EXERCICIO, aparelho.getExercicio().getNome());

        Intent intent = new Intent(this, AparelhoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);*/
    }
}