package br.edu.ifsp.arq.dmos5_2021.listacontatos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2021.listacontatos.R;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.constantes.Constantes;

public class NovoContatoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditNome;
    private EditText mEditApelido;
    private EditText mEditTelefone;
    private EditText mEditEmail;
    private Button mButtonSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);

        mEditNome = findViewById(R.id.edit_text_nome);
        mEditApelido = findViewById(R.id.edit_text_apelido);
        mEditTelefone = findViewById(R.id.edit_text_telefone);
        mEditEmail = findViewById(R.id.edit_text_email);
        mButtonSalvar = findViewById(R.id.button_salvar);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String nome = bundle.getString(Constantes.KEY_NOME);
            String apelido = bundle.getString(Constantes.KEY_APELIDO);
            String telefone = bundle.getString(Constantes.KEY_TELEFONE);
            String email = bundle.getString(Constantes.KEY_EMAIL);
        }

        mButtonSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mButtonSalvar){
            addContato();
        }
    }

    private void addContato(){
        String nome = mEditNome.getText().toString();
        String apelido = mEditApelido.getText().toString();
        String telefone = mEditTelefone.getText().toString();
        String email = mEditEmail.getText().toString();

        if( nome.isEmpty() ) {
            Toast.makeText(this, "Insira o nome", Toast.LENGTH_SHORT).show();
        }
        else  if( apelido.isEmpty() ) {
            Toast.makeText(this, "Insira o apelido", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                Intent intent = new Intent();
                intent.putExtra(Constantes.KEY_NOME, nome);
                intent.putExtra(Constantes.KEY_APELIDO, apelido);
                intent.putExtra(Constantes.KEY_TELEFONE, telefone);
                intent.putExtra(Constantes.KEY_EMAIL, email);

                setResult(Activity.RESULT_OK, intent);

                Toast.makeText(this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show();

                finish();
            } catch (Exception ex) {
                Toast.makeText(this, "Não foi possível adicionar o contato", Toast.LENGTH_SHORT).show();
            }
        }

    }
}