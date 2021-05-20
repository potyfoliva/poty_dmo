package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.R;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.constantes.Constantes;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.controller.MediaController;

public class MediaAritmeticaActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_aritmetica);

        mTextView = findViewById(R.id.text_media_aritmetica);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        Double v1 = bundle.getDouble(Constantes.VALOR1, 0D);
        Double v2 = bundle.getDouble(Constantes.VALOR2, 0D);
        Double v3 = bundle.getDouble(Constantes.VALOR3, 0D);
        Double v4 = bundle.getDouble(Constantes.VALOR4, 0D);
        Double v5 = bundle.getDouble(Constantes.VALOR5, 0D);

        String msg = "Valores informados:\n" +
                v1.toString() + ", " +
                v2.toString() + ", " +
                v3.toString() + ", " +
                v4.toString() + " e " +
                v5.toString() + "\n\n" +
                "Média Aritmética: " +
                String.format("%.2f", MediaController.mediaArimetica(v1, v2, v3, v4, v5));

        mTextView.setText(msg);
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
}