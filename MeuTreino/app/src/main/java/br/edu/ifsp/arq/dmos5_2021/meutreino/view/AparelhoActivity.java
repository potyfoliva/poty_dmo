package br.edu.ifsp.arq.dmos5_2021.meutreino.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.constants.Constants;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.AcademiaController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.controller.UsuarioController;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;

public class AparelhoActivity extends AppCompatActivity {

    private TextView mNomeAparelhoTextView;
    private ImageView mFotoImageView;
    private TextView mInfoTextView;
    private Button mAdicionarButton;
    private Button mRemoverButton;

    private Aparelho aparelho;
    private Usuario usuario;
    private Exercicio exercicio;
    private String nome;
    private String foto;
    private String uso;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparelho);

        mNomeAparelhoTextView = findViewById(R.id.txt_nome_aparelho);
        mFotoImageView = findViewById(R.id.img_aparelho);
        mInfoTextView = findViewById(R.id.txt_info_aparelho);
        mAdicionarButton = findViewById(R.id.button_add);
        mAdicionarButton.setOnClickListener(v -> adicionarAparelho());
        mRemoverButton = findViewById(R.id.button_remove);
        mRemoverButton.setOnClickListener(v -> removerAparelho());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            nome = bundle.getString(Constants.KEY_NOME);
            foto = bundle.getString(Constants.KEY_FOTO);
            uso = bundle.getString(Constants.KEY_USO);
            exercicio = new Exercicio(bundle.getString(Constants.KEY_EXERCICIO));
            aparelho = new Aparelho(nome, uso, foto, exercicio);
            username = bundle.getString(Constants.KEY_USERNAME);

            int imageResource = getResources().getIdentifier(foto , null, getPackageName());
            Drawable res = ContextCompat.getDrawable(getApplicationContext(), imageResource);

            mNomeAparelhoTextView.setText(nome);
            mFotoImageView.setImageDrawable(res);
            mInfoTextView.setText(uso);
        }
    }

    private void adicionarAparelho() {
        usuario = UsuarioController.getUsuario(this, username);
        if (AcademiaController.adicionarAparelho(this, aparelho, usuario)){
            Toast.makeText(this, getString(R.string.msg_aparelho_inserido), Toast.LENGTH_SHORT).show();
        }
    }

    private void removerAparelho(){
        Usuario usuario = UsuarioController.getUsuario(this, username);
        if (AcademiaController.removerAparelho(this, aparelho, usuario)){
            Toast.makeText(this, getString(R.string.msg_aparelho_removido), Toast.LENGTH_SHORT).show();
        }

    }
}