package br.edu.ifsp.arq.domos5_2021.medias_dmo.controller;

import java.util.List;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaAritmetica;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaHarmonica;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaPonderada;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaStrategy;

public class MediaController {

    public static Double mediaPonderada(Double v1, Double v2, Double v3, Double v4, Double v5, int p1, int p2, int p3, int p4, int p5) {
        MediaPonderada ponderada = new MediaPonderada(v1, v2, v3, v4, v5, p1, p2, p3, p4, p5);
        return media(ponderada);
    }

    public static Double mediaArimetica(Double... doubles) {
        MediaAritmetica aritmetica = new MediaAritmetica(doubles);
        return media(aritmetica);
    }

    public static Double mediaHarmonica(List<Double> valores) {
        MediaHarmonica harmonica = new MediaHarmonica(valores);
        return media(harmonica);
    }

    private static Double media(MediaStrategy mediaStrategy) {
        return mediaStrategy.calcularMedia();
    }

}
