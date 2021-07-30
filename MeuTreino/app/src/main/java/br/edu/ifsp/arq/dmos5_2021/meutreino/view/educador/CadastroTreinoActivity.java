package br.edu.ifsp.arq.dmos5_2021.meutreino.view.educador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemAparelhoAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemAtividadeAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemExercicioAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.ExercicioController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.UsuarioController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.AcademiaDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.ExercicioDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.UsuarioDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Academia;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Atividade;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;

public class CadastroTreinoActivity extends AppCompatActivity {

    private Spinner mEsportistasSpinner;
    private RecyclerView mAtividadesRecycler;
    private Button mSalvarTreinoButton;
    private ArrayAdapter mAdapter;
    private ItemAtividadeAdapter mItemAtividadeAdapter;

    private List<Usuario> mEsportistas;
    private Usuario usuario;
    private List<Exercicio> exercicios;

    private Atividade atividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_treino);

        mEsportistasSpinner = findViewById(R.id.spinner_esportista);

        mEsportistasSpinner.setAdapter(UsuarioController.getEsportistaSpinner(this));
        mEsportistasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                usuario = (Usuario) parent.getAdapter().getItem(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                usuario = null;
            }
        } );

        mAtividadesRecycler = findViewById(R.id.recycler_atividades);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mAtividadesRecycler.setLayoutManager(manager);
        mAtividadesRecycler.setAdapter(mItemAtividadeAdapter);

        mAtividadesRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        mSalvarTreinoButton = findViewById(R.id.button_cadastro_treino);


    }

    private void getExerciciosPorEsportista(Usuario usuario){

    }
}