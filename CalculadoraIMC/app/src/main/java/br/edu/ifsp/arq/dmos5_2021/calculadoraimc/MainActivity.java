package br.edu.ifsp.arq.dmos5_2021.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mPesoText;
    private EditText mAlturaText;
    private TextView mImcView;
    private TextView mClassificacaoView;
    private Button mCalcularButton;
    private Button mLimparButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //esconder a barra superior do app
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mPesoText = findViewById(R.id.text_peso);
        mAlturaText = findViewById(R.id.text_altura);
        mImcView = findViewById(R.id.view_saida_imc);
        mClassificacaoView = findViewById(R.id.view_saida_classificacao);
        mCalcularButton = findViewById(R.id.button_calcular);
        mLimparButton = findViewById(R.id.button_limpar);

        mCalcularButton.setOnClickListener(this);
        mLimparButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCalcularButton) {
            calcularIMC();
        }
        if(v == mLimparButton){
            mPesoText.setText("");
            mAlturaText.setText("");
            mImcView.setText("");
            mClassificacaoView.setText("");
        }
    }

    private void calcularIMC() {
        float peso, altura;
        float imc = 0;
        try {
            peso = Float.valueOf(mPesoText.getText().toString());
            altura = Float.valueOf(mAlturaText.getText().toString());
        } catch (Exception ex) {
            peso = 0;
            altura = 0;
        }

        if (peso != 0 && altura != 0) {
            imc = peso / (altura * altura);

            if(imc > 1) {
                mImcView.setText(String.format("%s %.2f", "IMC: ", imc));

                if (imc > 1 && imc < 18.5) {
                    mClassificacaoView.setText("Classificação: Magreza");
                } else if (imc > 18.4 && imc < 25) {
                    mClassificacaoView.setText("Classificação: Normal");
                } else if (imc > 24.9 && imc < 30) {
                    mClassificacaoView.setText("Classificação: Sobrepeso");
                } else if (imc > 29.9 && imc < 40) {
                    mClassificacaoView.setText("Classificação: Obesidade");
                } else if (imc > 39.9) {
                    mClassificacaoView.setText("Classificação: Obesidade grave");
                }

            }else {
                mImcView.setText("Dados informados estão incorretos.");
            }

        } else {
            mImcView.setText("Dados informados estão incorretos.");
        }
    }
}