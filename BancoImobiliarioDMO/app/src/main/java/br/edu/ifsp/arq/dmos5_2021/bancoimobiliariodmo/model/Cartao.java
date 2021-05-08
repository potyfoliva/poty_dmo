package br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.model;

import android.util.Log;

public class Cartao {

    private final static double VALOR_INICIAL = 15000;
    private int mIdCartao;
    private double mSaldo;

    public Cartao(int idCartao) {
        mIdCartao = idCartao;
        mSaldo = VALOR_INICIAL;
    }

    public int getmIdCartao() {
        return mIdCartao;
    }

    public double getmSaldo() {
        return mSaldo;
    }

    public void resetmSaldo() {
        mSaldo = VALOR_INICIAL;
    }

    public void receber(double valor) {
        mSaldo += valor;
    }

    public boolean pagar(double valor) {
        if (valor <= mSaldo) {
            mSaldo = mSaldo - valor;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "mIdCartao=" + mIdCartao +
                ", mSaldo=" + mSaldo +
                '}';
    }

}
