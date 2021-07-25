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
import br.edu.ifsp.arq.dmos5_2021.meutreino.constants.Constants;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.UsuarioController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.esportista.ListaExerciciosActivity;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.educador.VisaoGeralEducadorActivity;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.esportista.VisaoGeralEsportistaActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mUserEditText;
    private EditText mPasswordEditText;
    private Spinner mSpinner;
    private Button mLoginButton;
    private ArrayAdapter<CharSequence> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserEditText = findViewById(R.id.edit_user);
        mPasswordEditText = findViewById(R.id.edit_password);
        mLoginButton = findViewById(R.id.button_login);
        mLoginButton.setOnClickListener(l -> login());
        mSpinner = findViewById(R.id.spinner_tipo_usuario);
        mAdapter = ArrayAdapter.createFromResource(this,
                R.array.spiner_tipo_usuario, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
    }

    private void login(){
        Bundle bundle;
        String userName = mUserEditText.getText().toString();
        int password;
        String tipo = mSpinner.getSelectedItem().toString();
        try{
            password = Integer.valueOf(mPasswordEditText.getText().toString());
        } catch (NumberFormatException ex){
            password = 0;
        }

        if(UsuarioController.checkLogin(this, userName, password, tipo)){
            Intent intent = null;
            if(tipo.equals(Constants.EDUCADOR)){
                intent = new Intent(this, VisaoGeralEducadorActivity.class);
            }else if(tipo.equals(Constants.ESPORTISTA)){
                bundle = new Bundle();
                bundle.putString(Constants.KEY_USERNAME, userName);
                intent = new Intent(this, VisaoGeralEsportistaActivity.class);
                intent.putExtras(bundle);
            }
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, getString(R.string.msg_login_invalido), Toast.LENGTH_SHORT).show();
        }

    }
}