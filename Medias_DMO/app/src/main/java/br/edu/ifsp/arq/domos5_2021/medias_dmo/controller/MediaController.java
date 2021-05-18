package br.edu.ifsp.arq.domos5_2021.medias_dmo.controller;

import java.util.List;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaAritmetica;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaHarmonica;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaPonderada;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaStrategy;

public class MediaController {

    public static Double mediaPonderada(/*Indicar argumentos, se necessário*/){
        MediaPonderada ponderada = null;
        //TODO implementar o restante do método
        return media(ponderada);
    }

    public static Double mediaArimetica(Double v1, Double v2, Double v3, Double v4, Double v5){
        MediaAritmetica aritmetica = new MediaAritmetica(v1, v2, v3, v4, v5);
        return media(aritmetica);
    }

    public static Double mediaHarmonica(/*Indicar argumentos, se necessário*/){
        MediaHarmonica harmonica = null;
        //TODO implementar o restante do método
        return media(harmonica);
    }

    private static Double media(MediaStrategy mediaStrategy){
        return mediaStrategy.calcularMedia();
    }

}
