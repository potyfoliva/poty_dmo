package br.edu.ifsp.arq.dmos5_2021.conversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEntradaText;
    private Button mCelsiusButton;
    private Button mFahrenheitButton;
    private TextView mSaidaView;
    private TextView mErroView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntradaText = findViewById(R.id.text_entrada);
        mCelsiusButton = findViewById(R.id.button_para_celsius);
        mFahrenheitButton = findViewById(R.id.button_para_fahrenheit);
        mSaidaView = findViewById(R.id.text_saida);
        mErroView = findViewById(R.id.text_erro);

        mCelsiusButton.setOnClickListener(this);
        mFahrenheitButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        float entrada;
        mErroView.setText("");
        mSaidaView.setText("");

        try {
            entrada = Float.valueOf(mEntradaText.getText().toString());
            if (v == mFahrenheitButton) {
                converterParaFahrenheit(entrada);
            }
            if (v == mCelsiusButton) {
                converterParaCelsius(entrada);
            }
        } catch (NumberFormatException ex) {
            mErroView.setText("Informe novamente a temperatura");
        }
    }

    private void converterParaFahrenheit(float entrada) {
        double resultado = (1.8 * entrada) + 32;
        mSaidaView.setText(String.format(Locale.ROOT, "%.2f %s", resultado, "ºF"));
    }

    private void converterParaCelsius(float entrada) {
        double resultado = (entrada - 32) / 1.8;
        mSaidaView.setText(String.format(Locale.ROOT, "%.2f %s", resultado, "ºC"));
    }
}