package br.edu.ifsp.arq.dmos5_2021.github_dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.github_dmos5.R;
import br.edu.ifsp.arq.dmos5_2021.github_dmos5.adapter.ItemRepositorioAdapter;
import br.edu.ifsp.arq.dmos5_2021.github_dmos5.api.RetrofitService;
import br.edu.ifsp.arq.dmos5_2021.github_dmos5.model.RepositorioNome;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_PERMISSION = 64;
    private static final String BASE_URL = "https://api.github.com/";

    private Retrofit mRetrofit;

    private EditText mNomeUserEditText;
    private Button mBuscarButton;

    private String mUser;

    private RecyclerView mRepositoriosRecyclerView;
    private ItemRepositorioAdapter mItemRepositorioAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNomeUserEditText = findViewById(R.id.edittext_repositorio);

        mRepositoriosRecyclerView = findViewById(R.id.recycler_repositorios);
        mItemRepositorioAdapter = new ItemRepositorioAdapter(null);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRepositoriosRecyclerView.setLayoutManager(layoutManager);
        mRepositoriosRecyclerView.setAdapter(mItemRepositorioAdapter);

        mRepositoriosRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mBuscarButton = findViewById(R.id.button_buscar);
        mBuscarButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_buscar:
                if(temPermissao()){
                    buscarRepositorio();
                }else{
                    solicitaPermissao();
                }

        }
    }

    private void buscarRepositorio(){
        mUser = mNomeUserEditText.getText().toString();

        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitService mRetrofitService = mRetrofit.create(RetrofitService.class);

        Call<List<RepositorioNome>> call = mRetrofitService.recuperarRepositorios(mUser);

        call.enqueue(new Callback<List<RepositorioNome>>() {
            @Override
            public void onResponse(Call<List<RepositorioNome>> call, Response<List<RepositorioNome>> response) {
                if(response.isSuccessful()){
                    List<RepositorioNome> listaRepositorios = response.body();
                    Log.i("lista", listaRepositorios.toString());
                    mItemRepositorioAdapter.atualizar(listaRepositorios, mRepositoriosRecyclerView);
                }
            }

            @Override
            public void onFailure(Call<List<RepositorioNome>> call, Throwable t) {
                Toast.makeText(MainActivity.this, getString(R.string.erro_api), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean temPermissao(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    private void solicitaPermissao() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
            final Activity activity = this;
            new AlertDialog.Builder(this)
                    .setMessage(R.string.explicacao_permissao)
                    .setPositiveButton(R.string.botao_fornecer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, REQUEST_PERMISSION);
                        }
                    })
                    .setNegativeButton(R.string.botao_nao_fornecer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.INTERNET
                    },
                    REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            for (int i = 0; i < permissions.length; i++) {

                if (permissions[i].equalsIgnoreCase(Manifest.permission.INTERNET) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    buscarRepositorio();
                }

            }
        }
    }

}