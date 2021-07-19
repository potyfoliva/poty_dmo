package br.edu.ifsp.arq.dmos5_2021.meutreino.controller;

import android.content.Context;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemExercicioAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.AparelhoDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.ExercicioDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class ExercicioController {

    public static ItemExercicioAdapter getExerciciosAdapter(Context context, RecyclerItemClickListener listener){
        List<Exercicio> exercicios;
        ExercicioDAO dao = new ExercicioDAO(context);
        exercicios = dao.recuperate();
        return new ItemExercicioAdapter(exercicios, listener);
    }

    public static Aparelho exibirAparelho(Context context, String nome){
        Aparelho aparelho;
        AparelhoDAO dao = new AparelhoDAO(context);
        aparelho = dao.recuperateExercicio(nome);
        return aparelho;
    }



}
