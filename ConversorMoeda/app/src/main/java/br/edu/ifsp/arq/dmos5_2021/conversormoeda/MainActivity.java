package br.edu.ifsp.arq.dmos5_2021.conversormoeda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final float VALOR_DOLAR = 5.4f;

    private EditText mEntradaText;
    private Button mParaDolarButton;
    private Button mParaRealButton;
    private TextView mSaidaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntradaText = findViewById(R.id.text_entrada);
        mSaidaView = findViewById(R.id.text_saida);
        mParaDolarButton = findViewById(R.id.button_para_dolar);
        mParaRealButton = findViewById(R.id.button_para_real);

        mParaDolarButton.setOnClickListener(this);
        mParaRealButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        float entrada, resultado;
        try{
            entrada = Float.valueOf(mEntradaText.getText().toString());
        }catch (NumberFormatException ex){
            entrada = 0;
        }

        switch (v.getId()){
            case R.id.button_para_dolar:
                resultado = entrada / VALOR_DOLAR;
                mSaidaView.setText("U$ " + resultado);
            case R.id.button_para_real:
                resultado = entrada * VALOR_DOLAR;
                mSaidaView.setText("R$ " + resultado);
        }
        //ou if(v == mParaDolarButton){...}

    }
}