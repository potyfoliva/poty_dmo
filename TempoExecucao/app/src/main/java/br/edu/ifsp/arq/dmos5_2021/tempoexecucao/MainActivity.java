package br.edu.ifsp.arq.dmos5_2021.tempoexecucao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Poty";
    private long mTempoExecucao = 0;
    private long mTempoCriacao = 0;
    private long mTempoPausado = 0;
    private long mTempoParado = 0;
    private long mTempoInicial = 0;
    private boolean mCriado = false;
    private boolean mPausado = false;
    private long mInicioPausado = 0 ;
    private long mFimPausado = 0;
    private long mInicioParado = 0;
    private long mFimParado = 0;


    private TextView mViewCriado;
    private TextView mViewPausado;
    private TextView mViewParado;
    private Button mButtonTempoExecucao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTempoInicial = System.currentTimeMillis();
        mCriado = true;
        Log.i(TAG, "Executando o onCreate()");
        Log.i(TAG, "Estado: Created");

        mViewCriado = findViewById(R.id.view_criado);
        mViewPausado = findViewById(R.id.view_pausado);
        mViewPausado.setText(getString(R.string.tempo_pausado) + " 0s");
        mViewParado = findViewById(R.id.view_parado);
        mViewParado.setText(getString(R.string.tempo_pausado) + " 0s");
        mButtonTempoExecucao = findViewById(R.id.button_tempo_execucao);
        mButtonTempoExecucao.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Executando o onStart()");
        Log.i(TAG, "Estado: Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mCriado) {
            calcularTempoCriacao();
        }
        if(mPausado){
           calcularTempoPausado();
        }
        Log.i(TAG, "Executando o onResume()");
        Log.i(TAG, "Estado: Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Executando o onPause()");
        Log.i(TAG, "Estado: Paused");
        mInicioPausado = System.currentTimeMillis();
        mPausado = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Executando o onStop()");
        Log.i(TAG, "Estado: Stopped");
        calcularTempoPausado();
        mInicioParado = System.currentTimeMillis();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Executando o onRestart()");
        Log.i(TAG, "Estado: Started");
        calcularTempoParado();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Executando o onDestroy()");
        Log.i(TAG, "Estado: Destroyed");
    }

    @Override
    public void onClick(View v) {
        if(v == mButtonTempoExecucao){
           calcularTempoExecucao();
        }
    }

    private void calcularTempoCriacao(){
        mTempoCriacao = (System.currentTimeMillis() - mTempoInicial) / 1000;
        mCriado = false;
        mViewCriado.setText(getString(R.string.tempo_criacao) + " " + mTempoCriacao + "s");
    }

    private void calcularTempoPausado(){
        mFimPausado = System.currentTimeMillis();
        mTempoPausado += (mFimPausado - mInicioPausado) / 1000;
        mViewPausado.setText(getString(R.string.tempo_pausado) + " " +  mTempoPausado + "s");
        mPausado = false;
    }

    private void calcularTempoParado(){
        mFimParado = System.currentTimeMillis();
        mTempoParado += (mFimParado - mInicioParado) / 1000;
        mViewParado.setText(getString(R.string.tempo_parado) + " " +  mTempoParado + "s");
    }

    private void calcularTempoExecucao(){
        mTempoExecucao = ((System.currentTimeMillis() - mTempoInicial) / 1000) - (mTempoParado + mTempoPausado);
        Toast.makeText(this, getString(R.string.tempo_execucao) + " " +  mTempoExecucao + "s", Toast.LENGTH_LONG).show();
    }
}