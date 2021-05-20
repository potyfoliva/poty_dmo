package br.edu.ifsp.arq.dmos5_2021.loginhardcode.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2021.loginhardcode.R;
import br.edu.ifsp.arq.dmos5_2021.loginhardcode.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.loginhardcode.controller.LoginController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mUserEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserEditText = findViewById(R.id.edit_user);
        mPasswordEditText = findViewById(R.id.edit_password);
        mLoginButton = findViewById(R.id.button_login);

        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mLoginButton){
            login();
        }
    }
    
    private void login(){
        String userName = mUserEditText.getText().toString();
        int password;
        try{
            password = Integer.valueOf(mPasswordEditText.getText().toString());
        } catch (NumberFormatException ex){
            password = 0;
        }

        Bundle embrulho = new Bundle();
        embrulho.putString(Constantes.KEY_USERNAME, userName);
        embrulho.putInt(Constantes.KEY_PASSWORD, password);

        Intent intencao = new Intent(this, LoginActivity.class);
        intencao.putExtras(embrulho);
        startActivity(intencao);

    }

}