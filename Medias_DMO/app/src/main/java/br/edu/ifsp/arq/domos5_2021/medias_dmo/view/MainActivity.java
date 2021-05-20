package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.R;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.constantes.Constantes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mValor1EditText;
    private EditText mValor2EditText;
    private EditText mValor3EditText;
    private EditText mValor4EditText;
    private EditText mValor5EditText;
    private Button mMediaAritmeticaButton;
    private Button mMediaHarmonicaButton;
    private Button mMediaPonderadaButton;
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
        mMediaAritmeticaButton = findViewById(R.id.button_media_aritmetica);
        mMediaHarmonicaButton = findViewById(R.id.button_media_harmonica);
        mMediaPonderadaButton = findViewById(R.id.button_media_ponderada);
        mLimparCamposButton = findViewById(R.id.button_limpar_campos);

        mMediaAritmeticaButton.setOnClickListener(this);
        mMediaHarmonicaButton.setOnClickListener(this);
        mMediaPonderadaButton.setOnClickListener(this);
        mLimparCamposButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mMediaAritmeticaButton) {
            mediaAritmetica();
        }
        if (v == mMediaHarmonicaButton) {
            mediaHarmonica();
        }
        if (v == mMediaPonderadaButton) {
            mediaPonderada();
        }
        if (v == mLimparCamposButton) {
            limparCampos();
        }
    }

    private void mediaAritmetica() {
        boolean aux = getValues();
        if (aux) {
            Bundle embrulho = getEmbrulho();
            Intent intencao = new Intent(this, MediaAritmeticaActivity.class);
            intencao.putExtras(embrulho);
            startActivity(intencao);
            //Toast.makeText(this, "Média aritmética: " + String.valueOf(MediaController.mediaArimetica(v1, v2, v3, v4, v5)), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.msg_erro), Toast.LENGTH_SHORT).show();
        }
    }

    private void mediaHarmonica() {
        boolean aux = getValues();
        if (aux) {
            Bundle embrulho = getEmbrulho();
            Intent intencao = new Intent(this, MediaHarmonicaActivity.class);
            intencao.putExtras(embrulho);
            startActivity(intencao);
           /* List<Double> valores = new ArrayList<>();
            valores.add(v1);
            valores.add(v2);
            valores.add(v3);
            valores.add(v4);
            valores.add(v5);

            Bundle embrulho = new Bundle();
            embrulho.putDoubleArray("valores", valores);*/
            //Toast.makeText(this, "Média harmônica: " + String.valueOf(MediaController.mediaHarmonica(valores)), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.msg_erro), Toast.LENGTH_SHORT).show();
        }
    }

    private void mediaPonderada() {
        boolean aux = getValues();
        if (aux) {
            Bundle embrulho = getEmbrulho();
            Intent intencao = new Intent(this, MediaPonderadaActivity.class);
            intencao.putExtras(embrulho);
            startActivity(intencao);
        } else {
            Toast.makeText(this, getString(R.string.msg_erro), Toast.LENGTH_SHORT).show();
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

    private Bundle getEmbrulho() {
        Bundle embrulho = new Bundle();
        embrulho.putDouble(Constantes.VALOR1, v1);
        embrulho.putDouble(Constantes.VALOR2, v2);
        embrulho.putDouble(Constantes.VALOR3, v3);
        embrulho.putDouble(Constantes.VALOR4, v4);
        embrulho.putDouble(Constantes.VALOR5, v5);
        return embrulho;
    }
}