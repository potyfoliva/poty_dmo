package br.edu.ifsp.arq.domos5_2021.medias_dmo.model;

import java.util.List;

public class MediaHarmonica implements MediaStrategy {

    private List<Double> values;

    public MediaHarmonica(List<Double> values) {
        this.values = values;
    }

    @Override
    public Double calcularMedia() {
        Double resultado = 0D;
        for (Double v : values) {
            resultado += 1 / v;
        }
        return values.size() / resultado;
    }
}
