package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.R;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.constantes.Constantes;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.controller.MediaController;

public class MediaPonderadaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mPeso1EditText;
    private EditText mPeso2EditText;
    private EditText mPeso3EditText;
    private EditText mPeso4EditText;
    private EditText mPeso5EditText;
    private Button mCalcularMediaPonderadaButton;
    private Button mLimparCamposButton;
    private TextView mTextView;
    private Double v1, v2, v3, v4, v5;
    private Integer p1, p2, p3, p4, p5;
    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_ponderada);

        mPeso1EditText = findViewById(R.id.edit_peso1);
        mPeso2EditText = findViewById(R.id.edit_peso2);
        mPeso3EditText = findViewById(R.id.edit_peso3);
        mPeso4EditText = findViewById(R.id.edit_peso4);
        mPeso5EditText = findViewById(R.id.edit_peso5);
        mCalcularMediaPonderadaButton = findViewById(R.id.button_calcular_media_ponderada);
        mLimparCamposButton = findViewById(R.id.button_limpar_campos_p);
        mTextView = findViewById(R.id.text_media_ponderada);

        mCalcularMediaPonderadaButton.setOnClickListener(this);
        mLimparCamposButton.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        v1 = bundle.getDouble(Constantes.VALOR1, 0D);
        v2 = bundle.getDouble(Constantes.VALOR2, 0D);
        v3 = bundle.getDouble(Constantes.VALOR3, 0D);
        v4 = bundle.getDouble(Constantes.VALOR4, 0D);
        v5 = bundle.getDouble(Constantes.VALOR5, 0D);
    }

    @Override
    public void onClick(View v) {
        if (v == mCalcularMediaPonderadaButton) {
            calcularMediaPonderada();
        }
        if (v == mLimparCamposButton) {
            limparCampos();
        }
    }

    private void calcularMediaPonderada() {
        p1 = 0;
        p2 = 0;
        p3 = 0;
        p4 = 0;
        p5 = 0;
        boolean aux = true;
        try {
            p1 = Integer.valueOf(mPeso1EditText.getText().toString()).intValue();
            p2 = Integer.valueOf(mPeso2EditText.getText().toString()).intValue();
            p3 = Integer.valueOf(mPeso3EditText.getText().toString()).intValue();
            p4 = Integer.valueOf(mPeso4EditText.getText().toString()).intValue();
            p5 = Integer.valueOf(mPeso5EditText.getText().toString()).intValue();
        } catch (NumberFormatException ex) {
            aux = false;
        }
        if (aux) {
            Double media = MediaController.mediaPonderada(v1, v2, v3, v4, v5, p1, p2, p3, p4, p5);
            if (media != 0) {
                msg = "Valor -> peso:\n" +
                        v1.toString() + " -> " + p1.toString() + "\n" +
                        v2.toString() + " -> " + p2.toString() + "\n" +
                        v3.toString() + " -> " + p3.toString() + "\n" +
                        v4.toString() + " -> " + p4.toString() + "\n" +
                        v5.toString() + " -> " + p5.toString() + "\n" +
                        "Média Ponderada: " +
                        String.format("%.2f", MediaController.mediaPonderada(v1, v2, v3, v4, v5, p1, p2, p3, p4, p5));

                mTextView.setText(msg);
            } else {
                mTextView.setText(R.string.msg_erro_divisao);
            }
        } else {
            Toast.makeText(this, getString(R.string.msg_erro), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void limparCampos() {
        mTextView.setText("");
        mPeso1EditText.setText("");
        mPeso2EditText.setText("");
        mPeso3EditText.setText("");
        mPeso4EditText.setText("");
        mPeso5EditText.setText("");
    }
}