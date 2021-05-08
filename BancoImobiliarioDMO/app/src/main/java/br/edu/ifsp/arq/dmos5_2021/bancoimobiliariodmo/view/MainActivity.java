package br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.R;
import br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.model.Cartao;
import br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.model.Sorte;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int MIN_JOGADORES = 2;
    private final int MAX_JOGADORES = 6;

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

    Cartao cartao;

    Sorte sorte;

    List<Cartao> cartoes = new ArrayList<>();

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

        //mValorText.setEnabled(false);
        mValorText.setVisibility(View.INVISIBLE);
       // mCentenaButton.setEnabled(false);
        mCentenaButton.setVisibility(View.INVISIBLE);
        //mMilharButton.setEnabled(false);
        mMilharButton.setVisibility(View.INVISIBLE);

        mIdCartaoText.setVisibility(View.INVISIBLE);
        mPagarButton.setVisibility(View.INVISIBLE);
        mReceberButton.setVisibility(View.INVISIBLE);
        mOpPagarReceberButton.setVisibility(View.INVISIBLE);
        mOpTransferenciaButton.setVisibility(View.INVISIBLE);
        mIdCartaoPagText.setVisibility(View.INVISIBLE);
        mIdCartaoRecText.setVisibility(View.INVISIBLE);
        mTransferirButton.setVisibility(View.INVISIBLE);
        mSorteButton.setVisibility(View.INVISIBLE);
        mSorteView.setVisibility(View.INVISIBLE);
        mResetButton.setVisibility(View.INVISIBLE);

        mNumJogadoresEditText.setText("");
        mInfoView.setText("");

    }

    @Override
    public void onClick(View v) {
        double valor = getValor();
        switch (v.getId()) {
            case R.id.button_iniciar:
                mInfoView.setText("");
                mSorteView.setText("");
                iniciar();
                break;
            case R.id.button_centena:
                mInfoView.setText("");
                mSorteView.setText("");
                valor = centena(valor);
                mValorText.setText(String.format("%.0f", valor));
                break;
            case R.id.button_milhar:
                mInfoView.setText("");
                mSorteView.setText("");
                mValorText.setText(String.format("%.0f", valor * 1000));
                break;
            case R.id.button_op_pagar_receber:
                mIdCartaoPagText.setVisibility(View.INVISIBLE);
                mIdCartaoRecText.setVisibility(View.INVISIBLE);
                mTransferirButton.setVisibility(View.INVISIBLE);
                mIdCartaoText.setVisibility(View.VISIBLE);
                mPagarButton.setVisibility(View.VISIBLE);
                mReceberButton.setVisibility(View.VISIBLE);
                /*mOpPagarReceberButton.setVisibility(View.INVISIBLE);
                mOpTransferenciaButton.setVisibility(View.VISIBLE);*/
                mInfoView.setText("");
                mSorteView.setText("");
                break;
            case R.id.button_op_transferencia:
                mIdCartaoPagText.setVisibility(View.VISIBLE);
                mIdCartaoRecText.setVisibility(View.VISIBLE);
                mTransferirButton.setVisibility(View.VISIBLE);
                mIdCartaoText.setVisibility(View.INVISIBLE);
                mPagarButton.setVisibility(View.INVISIBLE);
                mReceberButton.setVisibility(View.INVISIBLE);
               /* mOpPagarReceberButton.setVisibility(View.VISIBLE);
                mOpTransferenciaButton.setVisibility(View.INVISIBLE);*/
                mInfoView.setText("");
                mSorteView.setText("");
                break;
            case R.id.button_pagar:
                mInfoView.setText("");
                mSorteView.setText("");
                valor = getValor();
                pagar(valor);
                break;
            case R.id.button_receber:
                mInfoView.setText("");
                mSorteView.setText("");
                valor = getValor();
                receber(valor);
                break;
            case R.id.button_transferir:
                mInfoView.setText("");
                mSorteView.setText("");
                valor = getValor();
                transferir(valor);
                break;
            case R.id.button_sorte:
                mInfoView.setText("");
                mSorteView.setText("");
                sorte();
                break;
            case R.id.button_reset:
                mInfoView.setText("");
                mSorteView.setText("");
                confirmaReset();;
                break;
        }
    }

    private void iniciar() {
        if (!cartoes.isEmpty()) {
            cartoes.clear();
        }
        int numJogadores;
        try {
            numJogadores = Integer.parseInt(mNumJogadoresEditText.getText().toString());
        } catch (NumberFormatException ex) {
            numJogadores = 0;
        }

        if (numJogadores != 0 && (numJogadores >= MIN_JOGADORES && numJogadores <= MAX_JOGADORES)) {
            sorte = new Sorte();

            for (int i = 1; i <= numJogadores; i++) {
                cartao = new Cartao(i);
                cartoes.add(cartao);
            }

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

        } else {
            mInfoView.setText("Digite entre 2 e 6");
            mNumJogadoresEditText.setText("");
        }
        //Log.d("cartoes", cartoes.toString());

    }

    private void pagar(double valor) {
        if(valor != 0) {
            int idCartao;
            try {
                idCartao = Integer.valueOf(mIdCartaoText.getText().toString());
                mValorText.setText("");
            } catch (NumberFormatException ex) {
                idCartao = 0;
            }
            if (idCartao != 0 && idCartao <= cartoes.size()) {
                for (Cartao c : cartoes) {
                    if (c.getmIdCartao() == idCartao) {
                        if (c.pagar(valor)) {
                            mIdCartaoText.setText("");
                            mInfoView.setText(String.format("Sucesso, ID cartão: %d, saldo: R$  %.2f", c.getmIdCartao(), c.getmSaldo()));
                            //Log.d("pg", c.toString());
                        } else {
                            mInfoView.setText("Saldo insuficiente");
                            mIdCartaoText.setText("");
                        }
                    }
                }
            } else {
                mInfoView.setText("Nº cartão inválido");
                mIdCartaoText.setText("");
            }
        }else{
            mInfoView.setText("Informe o valor!");
            mIdCartaoText.setText("");
        }
    }

    private void receber(double valor) {
        if(valor != 0) {
            int idCartao;
            try {
                idCartao = Integer.valueOf(mIdCartaoText.getText().toString());
                mValorText.setText("");
            } catch (NumberFormatException ex) {
                idCartao = 0;
            }
            if (idCartao != 0 && idCartao <= cartoes.size()) {
                for (Cartao c : cartoes) {
                    if (c.getmIdCartao() == idCartao) {
                        c.receber(valor);
                        mIdCartaoText.setText("");
                        mInfoView.setText(String.format("Sucesso, ID cartão: %d, saldo: R$  %.2f", c.getmIdCartao(), c.getmSaldo()));
                        //Log.d("rcb", c.toString());
                    }
                }
            } else {
                mInfoView.setText("Nº cartão inválido");
                mIdCartaoText.setText("");
            }
        }else{
            mInfoView.setText("Informe o valor!");
            mIdCartaoText.setText("");
        }
    }

    private void transferir(double valor) {
        if(valor != 0) {
            int idCartaoPag, idCartaoRec;
            try {
                idCartaoPag = Integer.valueOf(mIdCartaoPagText.getText().toString());
                idCartaoRec = Integer.valueOf(mIdCartaoRecText.getText().toString());
                mValorText.setText("");
            } catch (NumberFormatException ex) {
                idCartaoPag = 0;
                idCartaoRec = 0;
            }

            if (verificarCartoesTransf(idCartaoPag, idCartaoRec)) {
                for (Cartao c : cartoes) {
                    if (c.getmIdCartao() == idCartaoPag) {
                        if (c.pagar(valor)) {
                            for (Cartao c2 : cartoes) {
                                if (c2.getmIdCartao() == idCartaoRec) {
                                    c2.receber(valor);
                                  //  Log.d("transf recebedor", c2.toString());
                                }
                            }
                            limparTransferencias();
                            mInfoView.setText("Transferência OK");
                            //Log.d("transf pagador", c.toString());

                        } else {
                            limparTransferencias();
                            mInfoView.setText("Saldo insuficiente");
                        }
                    }
                }
            }
        }else{
            limparTransferencias();
            mInfoView.setText("Informe o valor!");
        }

    }

    private void limparTransferencias(){
        mIdCartaoPagText.setText("");
        mIdCartaoRecText.setText("");
        mValorText.setText("");
    }

    private void sorte() {
        mSorteView.setText(sorte.getSorte());
    }

    private void confirmaReset(){
        new AlertDialog.Builder(this)
                .setTitle("Resetando...")
                .setMessage("Tem certeza que deseja resetar o jogo?")
                .setPositiveButton("sim",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reset();
                    } }).setNegativeButton("não", null).show();
    }

    private void reset() {
        for (Cartao c : cartoes) {
            c.resetmSaldo();
        }
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

    private boolean verificarCartoesTransf(int idPag, int idRec){
        int idCartaoPag = idPag;
        int idCartaoRec = idRec;
        int aux = 0;
        if(idCartaoPag != idCartaoRec) {
            if (idCartaoPag == 0 || !(idCartaoPag <= cartoes.size())) {
                aux++;
                mInfoView.setText("Nº cartão pagador inválido");
            } else if (idCartaoRec == 0 || !(idCartaoRec <= cartoes.size())) {
                aux++;
                mInfoView.setText("Nº cartão recebedor inválido");
            }
        }else{
            aux++;
            mInfoView.setText("IDs dos cartões iguais");
        }

        if(aux == 0){
            return true;
        }else{
            return false;
        }

    }

    private double centena(double valor){
        if(valor < 10){
            return valor * 100;
        }else{
            return valor;
        }
    }



}