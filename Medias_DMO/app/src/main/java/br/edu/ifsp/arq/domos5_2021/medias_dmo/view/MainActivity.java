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
    private Button mMediaAritmeticaButton;
    private Button mMediaHarmonicaButton;
    private Button mLimparCamposButton;
    private Double v1, v2, v3, v4, v5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mValor1EditText = findViewById(R.id.edit_v1);
        mValor2EditText = findViewById(R.id.edit_v2);
        mValor3EditText = findViewById(R.id.edit_v3);
        mValor4EditText = findViewById(R.id.edit_v4);
        mValor5EditText = findViewById(R.id.edit_v5);
        mMediaAritmeticaButton = findViewById(R.id.button_calcular_media_aritmetica);
        mMediaHarmonicaButton = findViewById(R.id.button_calcular_media_harmonica);
        mLimparCamposButton = findViewById(R.id.button_limpar_campos);

        mMediaAritmeticaButton.setOnClickListener(this);
        mMediaHarmonicaButton.setOnClickListener(this);
        mLimparCamposButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mMediaAritmeticaButton) {
            calcularMediaAritmetica();
        }
        if (v == mMediaHarmonicaButton) {
            calcularMediaHarmonica();
        }
        if (v == mLimparCamposButton) {
            limparCampos();
        }
    }

    private void calcularMediaAritmetica() {
        boolean aux = getValues();
        if (aux) {
            Toast.makeText(this, "Média aritmética: " + String.valueOf(MediaController.mediaArimetica(v1, v2, v3, v4, v5)), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.msg_erro), Toast.LENGTH_LONG).show();
        }
    }

    private void calcularMediaHarmonica() {
        boolean aux = getValues();
        if (aux) {
            List<Double> valores = new ArrayList<>();
            valores.add(v1);
            valores.add(v2);
            valores.add(v3);
            valores.add(v4);
            valores.add(v5);
            Toast.makeText(this, "Média harmônica: " + String.valueOf(MediaController.mediaHarmonica(valores)), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.msg_erro), Toast.LENGTH_LONG).show();
        }
    }

    private boolean getValues() {
        v1 = 0D;
        v2 = 0D;
        v3 = 0D;
        v4 = 0D;
        v5 = 0D;
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
        return aux;
    }

    private void limparCampos() {
        mValor1EditText.setText("");
        mValor2EditText.setText("");
        mValor3EditText.setText("");
        mValor4EditText.setText("");
        mValor5EditText.setText("");
    }


}