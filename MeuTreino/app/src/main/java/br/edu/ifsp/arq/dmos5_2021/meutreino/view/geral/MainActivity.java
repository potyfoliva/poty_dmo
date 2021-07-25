package br.edu.ifsp.arq.dmos5_2021.meutreino.view.geral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;

public class MainActivity extends AppCompatActivity {

    //private Button mExerciciosBtn;
    //private Button mAcademiaBtn;

    private Button mLoginBtn;
    private Button mCadastroBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginBtn = findViewById(R.id.button_login);
        mLoginBtn.setOnClickListener(v -> login());

        mCadastroBtn = findViewById(R.id.button_cadastro);
        mCadastroBtn.setOnClickListener(v -> cadastrar());
    }

    private void login(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void cadastrar(){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
    /*private void exibirExercicios(){
        Intent intent = new Intent(this, ListaExerciciosActivity.class);
        startActivity(intent);
    }

    private void exibirAcademia(){
        Intent intent = new Intent(this, AcademiaActivity.class);
        startActivity(intent);
    }*/
}