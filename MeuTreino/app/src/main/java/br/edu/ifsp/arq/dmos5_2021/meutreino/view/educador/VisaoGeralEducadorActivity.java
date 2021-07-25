package br.edu.ifsp.arq.dmos5_2021.meutreino.view.educador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;

public class VisaoGeralEducadorActivity extends AppCompatActivity {

    private Button mUsuariosButton;
    private Button mTreinosButton;
    private Button mCadastroTreinosButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visao_geral_educador);

        mUsuariosButton = findViewById(R.id.button_usuarios);
        mTreinosButton = findViewById(R.id.button_treinos);
        mCadastroTreinosButton = findViewById(R.id.button_cadastro_treino);

        mUsuariosButton.setOnClickListener(u -> listarUsuarios());
        mTreinosButton.setOnClickListener(u -> listarTreinos());
        mCadastroTreinosButton.setOnClickListener(u -> cadastrarTreino());
    }

    private void listarUsuarios(){
        Intent intent = new Intent(this, ListaEsportistasActivity.class);
        startActivity(intent);
    }

    private void listarTreinos(){

    }

    private void cadastrarTreino(){

    }
}