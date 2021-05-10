package br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.controller;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.model.Cartao;
import br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.model.Sorte;

public class ControladorJogo {

    private final int MIN_JOGADORES = 2;
    private final int MAX_JOGADORES = 6;
    private Cartao cartao;
    private Sorte sorte;
    private List<Cartao> cartoes = new ArrayList<>();
    private String msg = "";


    public ControladorJogo() {
        sorte = new Sorte();
    }

    public boolean iniciar(int numJogadores) {
        if (!cartoes.isEmpty()) {
            cartoes.clear();
        }

        if (numJogadores != 0 && (numJogadores >= MIN_JOGADORES && numJogadores <= MAX_JOGADORES)) {
            sorte = new Sorte();
            for (int i = 1; i <= numJogadores; i++) {
                cartao = new Cartao(i);
                cartoes.add(cartao);
            }
        }
        Log.d("cartões", cartoes.toString());

        if (!cartoes.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String pagar(int idCartao, double valor) {
        if (idCartao != 0 && idCartao <= cartoes.size()) {
            for (Cartao c : cartoes) {
                if (c.getmIdCartao() == idCartao) {
                    if (c.pagar(valor)) {
                        msg = String.format("Sucesso, ID cartão: %d, saldo: R$  %.2f", c.getmIdCartao(), c.getmSaldo());
                        Log.d("pg", c.toString());
                    } else {
                        msg = "Saldo insuficiente";
                    }
                }
            }
        } else {
            msg = "Nº cartão inválido";
        }
        return msg;
    }

    public String receber(int idCartao, double valor) {
        if (idCartao != 0 && idCartao <= cartoes.size()) {
            for (Cartao c : cartoes) {
                if (c.getmIdCartao() == idCartao) {
                    c.receber(valor);
                    msg = String.format("Sucesso, ID cartão: %d, saldo: R$  %.2f", c.getmIdCartao(), c.getmSaldo());
                    Log.d("rcb", c.toString());
                }
            }
        } else {
            msg = "Nº cartão inválido";
        }
        return msg;
    }

    public String transferir(int idCartaoPag, int idCartaoRec, double valor) {

        for (Cartao c : cartoes) {
            if (c.getmIdCartao() == idCartaoPag) {
                if (c.pagar(valor)) {
                    for (Cartao c2 : cartoes) {
                        if (c2.getmIdCartao() == idCartaoRec) {
                            c2.receber(valor);
                            Log.d("transf recebedor", c2.toString());
                        }
                    }
                    msg = "Transferência OK";
                    Log.d("transf pagador", c.toString());
                } else {
                    msg = "Saldo insuficiente";
                }
            }
        }

        return msg;
    }

    public String verificarCartoesTransf(int idPag, int idRec) {
        msg = "";
        int idCartaoPag = idPag;
        int idCartaoRec = idRec;
        if (idCartaoPag != idCartaoRec) {
            if (idCartaoPag == 0 || !(idCartaoPag <= cartoes.size())) {
                msg = "Nº cartão pagador inválido";
            } else if (idCartaoRec == 0 || !(idCartaoRec <= cartoes.size())) {
                msg = "Nº cartão receptor inválido";
            }
        } else {
            msg = "IDs dos cartões iguais";
        }
        return msg;
    }

    public String getSorte() {
        return sorte.getSorte();
    }

    public void reset() {
        for (Cartao c : cartoes) {
            c.resetmSaldo();
        }
    }


}
