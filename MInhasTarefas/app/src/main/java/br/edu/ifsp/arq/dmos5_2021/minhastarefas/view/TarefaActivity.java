package br.edu.ifsp.arq.dmos5_2021.minhastarefas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import br.edu.ifsp.arq.dmos5_2021.minhastarefas.R;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.controller.TarefaController;

public class TarefaActivity extends AppCompatActivity {

    private EditText mDescricaoEdit;
    private EditText mDataEdit;
    private Spinner mSpinner;
    private ArrayAdapter<CharSequence> mAdapter;
    private boolean checked;
    private EditText mTelefoneEdit;
    private Button mSalvarBtn;
    private String descricao;
    private String data;
    private String prioridade;
    private String telefone;
    private String temaSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            temaSelecionado = bundle.getString(Constantes.THEME_KEY);
        }

        getTema();

        setContentView(R.layout.activity_tarefa);

        mDescricaoEdit = findViewById(R.id.edit_descricao);
        mDataEdit = findViewById(R.id.edit_data);
        mTelefoneEdit = findViewById(R.id.edit_fone);
        mTelefoneEdit.setEnabled(false);
        mSpinner = (Spinner) findViewById(R.id.spiner_prioridades);
        mAdapter = ArrayAdapter.createFromResource(this,
                R.array.spiner_prioridades, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        int spinnerPos;
        if(bundle != null){
            descricao = bundle.getString(Constantes.KEY_DESCRICAO);
            data = bundle.getString(Constantes.KEY_DATA);
            prioridade = bundle.getString(Constantes.KEY_PRIORIDADE);
            telefone = bundle.getString(Constantes.KEY_TELEFONE);

            mDescricaoEdit.setText(descricao);
            mDataEdit.setText(data);
            mTelefoneEdit.setText(telefone);
            spinnerPos = mAdapter.getPosition(prioridade);
            mSpinner.setSelection(spinnerPos);

        }else{
           descricao = "";
           data = "";
           spinnerPos = mAdapter.getPosition(Constantes.PRIORIDADE_VAZIA);
           mSpinner.setSelection(spinnerPos);
        }

        mSalvarBtn = findViewById(R.id.button_salvar);
        mSalvarBtn.setOnClickListener(v -> salvarTarefas());

    }

    public void onCheckboxClicked(View view) {
         checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_ligacao:
                if (checked){
                    mTelefoneEdit.setEnabled(true);
                    mTelefoneEdit.getText().toString();
                }else{
                    mTelefoneEdit.setEnabled(false);
                    mTelefoneEdit.setText("");
                }
            break;
        }
    }

    private void salvarTarefas(){
        Intent intent = new Intent();
        if(checked){
            intent.putExtra("phone", mTelefoneEdit.getText().toString());
        }
        intent.putExtra(Constantes.KEY_DESCRICAO, mDescricaoEdit.getText().toString());
        intent.putExtra(Constantes.KEY_PRIORIDADE, mSpinner.getSelectedItem().toString());
        intent.putExtra(Constantes.KEY_DESCRICAO_ANTERIOR, descricao);
        intent.putExtra(Constantes.KEY_TELEFONE, mTelefoneEdit.getText().toString());
        if (mDataEdit.getText().toString().isEmpty()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate now = LocalDate.now();
            String hoje = dtf.format(now);
            intent.putExtra(Constantes.KEY_DATA, hoje);
        } else {
            intent.putExtra(Constantes.KEY_DATA, mDataEdit.getText().toString());
        }

        setResult(ListaTarefasActivity.RESULT_OK, intent);
        Toast.makeText(this, getString(R.string.success_msg), Toast.LENGTH_SHORT).show();
        finish();

    }

    private void getTema(){
        if(temaSelecionado.equals(Constantes.THEME_PADRAO)){
            getTheme().applyStyle(R.style.Theme_MInhasTarefas, true);
        }else if(temaSelecionado.equals(Constantes.THEME_CANDY)){
            getTheme().applyStyle(R.style.Theme_Candy, true);
        }else if(temaSelecionado.equals(Constantes.THEME_ESCURO)){
            getTheme().applyStyle(R.style.Theme_Escuro, true);
        }
    }

}