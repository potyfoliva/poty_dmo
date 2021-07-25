package br.edu.ifsp.arq.dmos5_2021.meutreino.view.geral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.UsuarioController;

public class CadastroActivity extends AppCompatActivity {

    private EditText mNomeEditText;
    private EditText mEmailEditText;
    private EditText mSenhaEditText;
    private EditText mSenhaConfirmaEditText;
    private Spinner mSpinner;
    private Button mCadastrarButton;
    private ArrayAdapter<CharSequence> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        mNomeEditText = findViewById(R.id.edit_nome);
        mEmailEditText = findViewById(R.id.edit_email);
        mSenhaEditText = findViewById(R.id.edit_senha);
        mSenhaConfirmaEditText = findViewById(R.id.edit_senha_confirmacao);

        mSpinner = findViewById(R.id.spinner_tipo_usuario);
        mAdapter = ArrayAdapter.createFromResource(this,
                R.array.spiner_tipo_usuario, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        mCadastrarButton = findViewById(R.id.button_salvar);
        mCadastrarButton.setOnClickListener(u -> cadastrar());
    }

    private void cadastrar(){
        String nome = mNomeEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        int senha;
        int senhaConfirmacao;
        try{
            senha = Integer.valueOf(mSenhaEditText.getText().toString());
            senhaConfirmacao = Integer.valueOf(mSenhaConfirmaEditText.getText().toString());
        } catch (NumberFormatException ex){
            senha = 0;
            senhaConfirmacao = 0;
        }
        String tipo = mSpinner.getSelectedItem().toString();

        if(senha != 0 && senha == senhaConfirmacao){
            if(UsuarioController.cadastrar(this, nome, senha, email, tipo)){
                Intent intencao = new Intent(this, LoginActivity.class);
                startActivity(intencao);
                finish();
            }else{
                Toast.makeText(this, getString(R.string.cadastrar_erro), Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, getString(R.string.msg_senhas_diferentes), Toast.LENGTH_SHORT).show();
        }




    }
}