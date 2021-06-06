package br.edu.ifsp.arq.dmos5_2021.listacontatos.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.listacontatos.R;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.adapter.ItemContatoAdapter;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.adapter.ItemFavoritoAdapter;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.controller.ContatoController;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.dao.ContatoDAO;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.model.Contato;

public class MainActivity extends AppCompatActivity {

    private List<Contato> mContatos;
    private List<Contato> mFavoritos;
    private RecyclerView mContatosRecyclerView;
    private RecyclerView mFavoritosRecyclerView;
    private FloatingActionButton mActionButton;
    private ItemContatoAdapter mItemContatoAdapter;
    private ItemFavoritoAdapter mItemFavAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContatos = ContatoController.allContatos(this);
        mFavoritos = ContatoController.allFavoritos(this);
        Log.i("contatos: ", mContatos.toString());
        Log.i("favoritos: ", mFavoritos.toString());

        mItemContatoAdapter = new ItemContatoAdapter(this, mContatos);
        mItemFavAdapter = new ItemFavoritoAdapter(this, mFavoritos);
        //mItemContatoAdapter.setClickListener(position -> updateSite(position));

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        mContatosRecyclerView = findViewById(R.id.recycler_contatos);
        mContatosRecyclerView.setLayoutManager(layoutManager1);
        mContatosRecyclerView.setAdapter(mItemContatoAdapter);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        mFavoritosRecyclerView = findViewById(R.id.recycler_favs);
        mFavoritosRecyclerView.setLayoutManager(layoutManager2);
        mFavoritosRecyclerView.setAdapter(mItemFavAdapter);

        mActionButton = findViewById(R.id.fab_add_contato);
        mActionButton.setOnClickListener(v -> newContato());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constantes.REQUEST_CODE_NEW_SITE:
                    ContatoController.addContato(
                           this,
                            data.getStringExtra(Constantes.KEY_NOME),
                            data.getStringExtra(Constantes.KEY_APELIDO),
                            data.getStringExtra(Constantes.KEY_TELEFONE),
                            data.getStringExtra(Constantes.KEY_EMAIL)
                    );
                    break;
                /*case Constantes.REQUEST_CODE_UPDATE_SITE:
                    String o = data.getStringExtra(Constantes.KEY_OLD_TITLE);
                    String t = data.getStringExtra(Constantes.KEY_TITLE);
                    String u = data.getStringExtra(Constantes.KEY_URL);
                    SiteController.updateSite(o, t, u);
                    break;*/
            }
            updateAdapter();
        }
    }

    private void newContato() {
        Intent intent = new Intent(this, NovoContatoActivity.class);
        startActivityForResult(intent, Constantes.REQUEST_CODE_NEW_SITE);
    }

    /*private void updateSite(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_TITLE, mContatos.get(position).getTitle());
        bundle.putString(Constantes.KEY_URL, mContatos.get(position).getUrl());

        Intent intent = new Intent(this, SiteActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Constantes.REQUEST_CODE_UPDATE_SITE);
    }*/

    private void updateAdapter() {
        mItemContatoAdapter.notifyDataSetChanged();
    }
}