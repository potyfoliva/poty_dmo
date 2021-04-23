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
    private TextView mSaidaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntradaText = findViewById(R.id.text_entrada);
        mCelsiusButton = findViewById(R.id.button_para_celsius);
        mSaidaView = findViewById(R.id.text_saida);

        mCelsiusButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        float entrada;
        double resultado;
        if (v == mCelsiusButton) {
            try {
                entrada = Float.valueOf(mEntradaText.getText().toString());
            } catch (NumberFormatException ex) {
                entrada = 0;
            }
            resultado = (entrada - 32) / 1.8;
            mSaidaView.setText(String.format(Locale.ROOT, "%.2f %s", resultado, "ºC"));
        }
    }
}