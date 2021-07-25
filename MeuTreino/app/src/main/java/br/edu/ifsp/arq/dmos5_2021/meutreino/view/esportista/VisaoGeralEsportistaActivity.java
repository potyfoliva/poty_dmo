package br.edu.ifsp.arq.dmos5_2021.meutreino.view.esportista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.constants.Constants;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.UsuarioController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.AcademiaActivity;

public class VisaoGeralEsportistaActivity extends AppCompatActivity {

    private TextView mNomeEsportistaTextView;
    private Button mExerciciosButton;
    private Button mMinhaAcademiaButton;
    private Button mMeuTreinoButton;

    private String userNameEsportista;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visao_geral_esportista);

        mNomeEsportistaTextView = findViewById(R.id.txt_nome_esportista);
        mExerciciosButton = findViewById(R.id.button_exercicios);
        mMinhaAcademiaButton = findViewById(R.id.button_minha_academia);
        mMeuTreinoButton = findViewById(R.id.button_meu_treino);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            userNameEsportista = bundle.getString(Constants.KEY_USERNAME);
        }
        usuario = UsuarioController.getUsuario(this, userNameEsportista);
        mNomeEsportistaTextView.setText(usuario.getNome().toUpperCase());

        mExerciciosButton.setOnClickListener(v -> listarExercicios());
        mMinhaAcademiaButton.setOnClickListener(v -> exibirAcademia());
        mMeuTreinoButton.setOnClickListener(v -> exibirTreino());
    }

    private void listarExercicios(){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_USERNAME, usuario.getEmail());
        Intent intent = new Intent(this, ListaExerciciosActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void exibirAcademia(){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_USERNAME, usuario.getEmail());
        Intent intent = new Intent(this, AcademiaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void exibirTreino(){

    }
}