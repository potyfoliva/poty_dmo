package br.edu.ifsp.arq.dmos5_2021.minhastarefas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ifsp.arq.dmos5_2021.minhastarefas.R;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.constantes.Constantes;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button mTarefasBtn;
    private Button mTarefasConcluidasBtn;
    private Button mTemaCandyBtn;
    private Button mTemaEscuroBtn;
    private Button mTemaPadraoBtn;
    private SharedPreferences sharedPreferences;
    private String temaSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getTema();

        setContentView(R.layout.activity_main);

        mTarefasBtn = findViewById(R.id.button_tarefas);
        mTarefasBtn.setOnClickListener(v -> exibirTarefas());

        mTarefasConcluidasBtn = findViewById(R.id.button_tarefas_concluidas);
        mTarefasConcluidasBtn.setOnClickListener(v -> exibirTarefasConcluidas());

        mTemaPadraoBtn = findViewById(R.id.button_tema_padrao);
        mTemaPadraoBtn.setOnClickListener(this);

        mTemaCandyBtn = findViewById(R.id.button_tema_candy);
        mTemaCandyBtn.setOnClickListener(this);

        mTemaEscuroBtn = findViewById(R.id.button_tema_escuro);
        mTemaEscuroBtn.setOnClickListener(this);

    }

    private void exibirTarefas() {
        Intent intent = new Intent(this, ListaTarefasActivity.class);
        intent.putExtra(Constantes.TIPO_LISTA, Constantes.LISTA1);
        intent.putExtra(Constantes.THEME_KEY, temaSelecionado);
        startActivity(intent);
    }

    private void exibirTarefasConcluidas() {
        Intent intent = new Intent(this, ListaTarefasActivity.class);
        intent.putExtra(Constantes.TIPO_LISTA, Constantes.LISTA2);
        intent.putExtra(Constantes.THEME_KEY, temaSelecionado);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == mTemaPadraoBtn){
            sharedPreferences.edit().putString(Constantes.THEME_KEY, Constantes.THEME_PADRAO).apply();
        }
        if(v == mTemaCandyBtn){
            sharedPreferences.edit().putString(Constantes.THEME_KEY, Constantes.THEME_CANDY).apply();
        }
        if(v == mTemaEscuroBtn){
            sharedPreferences.edit().putString(Constantes.THEME_KEY, Constantes.THEME_ESCURO).apply();
        }

        recreate();

    }

    private void getTema(){
        sharedPreferences = getSharedPreferences(Constantes.THEME_SELECIONADO, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(Constantes.THEME_KEY, "").equals(Constantes.THEME_PADRAO)){
            temaSelecionado = Constantes.THEME_PADRAO;
            getTheme().applyStyle(R.style.Theme_MInhasTarefas, true);
        }else if(sharedPreferences.getString(Constantes.THEME_KEY, "").equals(Constantes.THEME_CANDY)){
            temaSelecionado = Constantes.THEME_CANDY;
            getTheme().applyStyle(R.style.Theme_Candy, true);
        }else if(sharedPreferences.getString(Constantes.THEME_KEY, "").equals(Constantes.THEME_ESCURO)){
            temaSelecionado = Constantes.THEME_ESCURO;
            getTheme().applyStyle(R.style.Theme_Escuro, true);
        }
    }

}
/*
Parabéns pelo projeto!!!
Nota 10.
 */