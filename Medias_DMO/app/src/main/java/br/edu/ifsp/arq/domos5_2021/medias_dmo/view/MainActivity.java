package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.R;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.controller.MediaController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mValor1EditText;
    private EditText mValor2EditText;
    private EditText mValor3EditText;
    private EditText mValor4EditText;
    private EditText mValor5EditText;
    private Button mCalcularMediaButton;
    private Button mLimparCamposButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mValor1EditText = findViewById(R.id.edit_v1);
        mValor2EditText = findViewById(R.id.edit_v2);
        mValor3EditText = findViewById(R.id.edit_v3);
        mValor4EditText = findViewById(R.id.edit_v4);
        mValor5EditText = findViewById(R.id.edit_v5);
        mCalcularMediaButton = findViewById(R.id.button_calcular_media);
        mLimparCamposButton = findViewById(R.id.button_limpar_campos);

        mCalcularMediaButton.setOnClickListener(this);
        mLimparCamposButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCalcularMediaButton) {
            calcularMedia();
        }
        if(v == mLimparCamposButton){
            limparCampos();
        }
    }

    private void calcularMedia() {
        Double v1 = 0.0;
        Double v2 = 0.0;
        Double v3 = 0.0;
        Double v4 = 0.0;
        Double v5 = 0.0;
        boolean aux = true;
        try {
            v1 = Double.valueOf(mValor1EditText.getText().toString()).doubleValue();
            v2 = Double.valueOf(mValor2EditText.getText().toString()).doubleValue();
            v3 = Double.valueOf(mValor3EditText.getText().toString()).doubleValue();
            v4 = Double.valueOf(mValor4EditText.getText().toString()).doubleValue();
            v5 = Double.valueOf(mValor5EditText.getText().toString()).doubleValue();
        } catch (NumberFormatException ex) {
            aux = false;
        }

        if (aux) {
            Toast.makeText(this, "Média aritmética: " + String.valueOf(MediaController.mediaArimetica(v1, v2, v3, v4, v5)), Toast.LENGTH_SHORT).show();
            limparCampos();
        } else {
            Toast.makeText(this, getString(R.string.msg_erro), Toast.LENGTH_LONG).show();
        }
    }

    private void limparCampos() {
        mValor1EditText.setText("");
        mValor2EditText.setText("");
        mValor3EditText.setText("");
        mValor4EditText.setText("");
        mValor5EditText.setText("");
    }


}