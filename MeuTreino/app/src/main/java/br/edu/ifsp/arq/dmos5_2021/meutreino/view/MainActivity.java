package br.edu.ifsp.arq.dmos5_2021.meutreino.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;

public class MainActivity extends AppCompatActivity {

    private Button mExerciciosBtn;
    private Button mAcademiaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExerciciosBtn = findViewById(R.id.button_exercicios);
        mExerciciosBtn.setOnClickListener(v -> exibirExercicios());

        mAcademiaBtn = findViewById(R.id.button_academia);
        mAcademiaBtn.setOnClickListener(v -> exibirAcademia());
    }

    private void exibirExercicios(){
        Intent intent = new Intent(this, ListaExerciciosActivity.class);
        startActivity(intent);
    }

    private void exibirAcademia(){
        Intent intent = new Intent(this, AcademiaActivity.class);
        startActivity(intent);
    }
}