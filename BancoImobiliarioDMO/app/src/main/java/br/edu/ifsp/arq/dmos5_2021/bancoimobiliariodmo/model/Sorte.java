package br.edu.ifsp.arq.dmos5_2021.bancoimobiliariodmo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Sorte {
    private Map<Integer, String> sortes = new HashMap();
    private Random mRandom;

    public Sorte() {
        sortes.put(1, "Ande duas casas");
        sortes.put(2, "Fique uma rodada sem jogar");
        sortes.put(3, "Volte três casas");
        sortes.put(4, "Jogue novamente");
        sortes.put(5, "Pule a vez do próximo");
        sortes.put(6, "Ande três casas");
        sortes.put(7, "Volte quatro casas");
        sortes.put(8, "Avance quatro casas");
        sortes.put(9, "Fique duas rodadas sem jogar");
        sortes.put(10, "Volte uma casa");
        sortes.put(11, "Ande uma casa");
        this.mRandom = new Random();
    }

    private int sortear() {
        return mRandom.nextInt(sortes.size()) + 1;
    }

    public String getSorte() {
        int n = sortear();
        return sortes.get(n);
    }


}
