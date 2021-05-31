package br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.R;
import br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.controller.ControladorJogo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mIniciarButton;
    private EditText mNumJogadoresEditText;
    private TextView mInfoView;

    private EditText mValorText;
    private Button mMilharButton;
    private Button mCentenaButton;

    private Button mOpPagarReceberButton;
    private EditText mIdCartaoText;
    private Button mPagarButton;
    private Button mReceberButton;

    private Button mOpTransferenciaButton;
    private EditText mIdCartaoPagText;
    private EditText mIdCartaoRecText;
    private Button mTransferirButton;

    private Button mSorteButton;
    private TextView mSorteView;

    private Button mResetButton;

    ControladorJogo controladorJogo;

    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIniciarButton = findViewById(R.id.button_iniciar);
        mNumJogadoresEditText = findViewById(R.id.text_numero_jogadores);
        mInfoView = findViewById(R.id.view_info);
        mValorText = findViewById(R.id.text_valor);
        mMilharButton = findViewById(R.id.button_milhar);
        mCentenaButton = findViewById(R.id.button_centena);
        mOpPagarReceberButton = findViewById(R.id.button_op_pagar_receber);
        mIdCartaoText = findViewById(R.id.text_id_cartao);
        mPagarButton = findViewById(R.id.button_pagar);
        mReceberButton = findViewById(R.id.button_receber);
        mOpTransferenciaButton = findViewById(R.id.button_op_transferencia);
        mIdCartaoPagText = findViewById(R.id.text_id_cartao_pagador);
        mIdCartaoRecText = findViewById(R.id.text_id_cartao_receptor);
        mTransferirButton = findViewById(R.id.button_transferir);
        mSorteButton = findViewById(R.id.button_sorte);
        mSorteView = findViewById(R.id.view_sorte);
        mResetButton = findViewById(R.id.button_reset);

        mIniciarButton.setOnClickListener(this);
        mMilharButton.setOnClickListener(this);
        mCentenaButton.setOnClickListener(this);
        mOpPagarReceberButton.setOnClickListener(this);
        mPagarButton.setOnClickListener(this);
        mReceberButton.setOnClickListener(this);
        mOpTransferenciaButton.setOnClickListener(this);
        mTransferirButton.setOnClickListener(this);
        mSorteButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);

        mValorText.setVisibility(View.INVISIBLE);
        mCentenaButton.setVisibility(View.INVISIBLE);
        mMilharButton.setVisibility(View.INVISIBLE);
        mOpPagarReceberButton.setVisibility(View.INVISIBLE);
        mOpTransferenciaButton.setVisibility(View.INVISIBLE);
        mIdCartaoText.setVisibility(View.INVISIBLE);
        mPagarButton.setVisibility(View.INVISIBLE);
        mReceberButton.setVisibility(View.INVISIBLE);
        mIdCartaoPagText.setVisibility(View.INVISIBLE);
        mIdCartaoRecText.setVisibility(View.INVISIBLE);
        mTransferirButton.setVisibility(View.INVISIBLE);
        mSorteButton.setVisibility(View.INVISIBLE);
        mSorteView.setVisibility(View.INVISIBLE);
        mResetButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        double valor = getValor();
        switch (v.getId()) {
            case R.id.button_iniciar:
                limparViews();
                iniciar();
                break;
            case R.id.button_centena:
                limparViews();
                valor = centena(valor);
                mValorText.setText(String.format("%.0f", valor));
                break;
            case R.id.button_milhar:
                limparViews();
                mValorText.setText(String.format("%.0f", valor * 1000));
                break;
            case R.id.button_op_pagar_receber:
                opPagarReceberBotoes();
                limparViews();
                break;
            case R.id.button_op_transferencia:
                opTransferenciaBotoes();
                limparViews();
                break;
            case R.id.button_pagar:
                limparViews();
                valor = getValor();
                pagar(valor);
                break;
            case R.id.button_receber:
                limparViews();
                valor = getValor();
                receber(valor);
                break;
            case R.id.button_transferir:
                limparViews();
                valor = getValor();
                transferir(valor);
                break;
            case R.id.button_sorte:
                limparViews();
                sorte();
                break;
            case R.id.button_reset:
                limparViews();
                confirmaReset();
                break;
        }
    }

    private void iniciar() {
        controladorJogo = new ControladorJogo();
        int numJogadores;

        try {
            numJogadores = Integer.parseInt(mNumJogadoresEditText.getText().toString());
        } catch (NumberFormatException ex) {
            numJogadores = 0;
        }

        if (controladorJogo.iniciar(numJogadores)) {
            iniciarBotoes();
        } else {
            mInfoView.setText("Digite entre 2 e 6");
            mNumJogadoresEditText.setText("");
        }

    }

    private void pagar(double valor) {
        if (valor != 0) {
            int idCartao;
            try {
                idCartao = Integer.valueOf(mIdCartaoText.getText().toString());
                mValorText.setText("");
            } catch (NumberFormatException ex) {
                idCartao = 0;
            }
            msg = controladorJogo.pagar(idCartao, valor);
            mInfoView.setText(msg);
            mIdCartaoText.setText("");
        } else {
            mInfoView.setText("Informe o valor!");
            mIdCartaoText.setText("");
        }
    }

    private void receber(double valor) {
        if (valor != 0) {
            int idCartao;
            try {
                idCartao = Integer.valueOf(mIdCartaoText.getText().toString());
                mValorText.setText("");
            } catch (NumberFormatException ex) {
                idCartao = 0;
            }
            msg = controladorJogo.receber(idCartao, valor);
            mInfoView.setText(msg);
            mIdCartaoText.setText("");
        } else {
            mInfoView.setText("Informe o valor!");
            mIdCartaoText.setText("");
        }
    }

    private void transferir(double valor) {
        if (valor != 0) {
            int idCartaoPag, idCartaoRec;
            try {
                idCartaoPag = Integer.valueOf(mIdCartaoPagText.getText().toString());
                idCartaoRec = Integer.valueOf(mIdCartaoRecText.getText().toString());
                mValorText.setText("");
            } catch (NumberFormatException ex) {
                idCartaoPag = 0;
                idCartaoRec = 0;
            }
            msg = controladorJogo.verificarCartoesTransf(idCartaoPag, idCartaoRec);
            if (!msg.equals("")) {
                limparTransferenciasBotoes();
                mInfoView.setText(msg);
            } else {
                msg = controladorJogo.transferir(idCartaoPag, idCartaoRec, valor);
                limparTransferenciasBotoes();
                mInfoView.setText(msg);
            }
        } else {
            limparTransferenciasBotoes();
            mInfoView.setText("Informe o valor!");
        }
    }

    private void iniciarBotoes() {
        mIniciarButton.setVisibility(View.INVISIBLE);
        mNumJogadoresEditText.setVisibility(View.INVISIBLE);
        mValorText.setVisibility(View.VISIBLE);
        mCentenaButton.setVisibility(View.VISIBLE);
        mMilharButton.setVisibility(View.VISIBLE);
        mOpPagarReceberButton.setVisibility(View.VISIBLE);
        mOpTransferenciaButton.setVisibility(View.VISIBLE);
        mSorteButton.setVisibility(View.VISIBLE);
        mSorteView.setVisibility(View.VISIBLE);
        mResetButton.setVisibility(View.VISIBLE);
    }

    private void limparTransferenciasBotoes() {
        mIdCartaoPagText.setText("");
        mIdCartaoRecText.setText("");
        mValorText.setText("");
    }

    private void opPagarReceberBotoes() {
        mIdCartaoPagText.setVisibility(View.INVISIBLE);
        mIdCartaoRecText.setVisibility(View.INVISIBLE);
        mTransferirButton.setVisibility(View.INVISIBLE);
        mIdCartaoText.setVisibility(View.VISIBLE);
        mPagarButton.setVisibility(View.VISIBLE);
        mReceberButton.setVisibility(View.VISIBLE);
    }

    private void opTransferenciaBotoes() {
        mIdCartaoPagText.setVisibility(View.VISIBLE);
        mIdCartaoRecText.setVisibility(View.VISIBLE);
        mTransferirButton.setVisibility(View.VISIBLE);
        mIdCartaoText.setVisibility(View.INVISIBLE);
        mPagarButton.setVisibility(View.INVISIBLE);
        mReceberButton.setVisibility(View.INVISIBLE);
    }

    private void limparViews() {
        mInfoView.setText("");
        mSorteView.setText("");
    }

    private void sorte() {
        mSorteView.setText(controladorJogo.getSorte());
    }

    private void confirmaReset() {
        new AlertDialog.Builder(this)
                .setTitle("Resetando...")
                .setMessage("Tem certeza que deseja resetar o jogo?")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reset();
                    }
                }).setNegativeButton("não", null).show();
    }

    private void reset() {
        controladorJogo.reset();
        mSorteView.setText("Cartões reiniciados");
    }

    private double getValor() {
        double valor;
        try {
            valor = Double.valueOf(mValorText.getText().toString());
        } catch (NumberFormatException ex) {
            valor = 0;
        }
        return valor;
    }

    private double centena(double valor) {
        if (valor < 10) {
            return valor * 100;
        } else {
            return valor;
        }
    }

}

/*
Excelente seu projeto! Parabéns!!
Gostei bastante das propostas de sorte.

Achei falta da funcionalidade de saldo do cartão.

Acho que pode remodelar o layout para ficar mais intuitivo ao usuário, por exemplo, deixando botões
inativos durante a realização de uma operação. ALém disso, na máquina de cartões física, os botões
M e K são os responsáveis por efetivar a operação, em seu app deve-se utilizar o botão "Transferir",
por exemplo. Outra sugestão é ser prudente com as cores, o exagero de cores incomoda muitos usuários.

Nota: 9,5
 */
