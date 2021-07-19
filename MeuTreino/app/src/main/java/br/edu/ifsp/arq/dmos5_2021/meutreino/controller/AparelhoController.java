package br.edu.ifsp.arq.dmos5_2021.meutreino.controller;

import android.content.Context;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemAparelhoAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.AparelhoDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class AparelhoController {

    public static ItemAparelhoAdapter getAparelhosAdapter(Context context, RecyclerItemClickListener listener){
        List<Aparelho> aparelhos;
        AparelhoDAO dao = new AparelhoDAO(context);
        aparelhos = dao.recuperate();
        return new ItemAparelhoAdapter(aparelhos, listener);
    }

    public static Aparelho exibirAparelho(Context context, String nome){
        Aparelho aparelho;
        AparelhoDAO dao = new AparelhoDAO(context);
        aparelho = dao.recuperate(nome);
        return aparelho;
    }


    /*public static List<Aparelho> allAparelhos(Context context){
        return AparelhoDAO.getInstance(context).getListaAparelhos();
    }

    public static List<String> allExercicios(Context context){
        return AparelhoDAO.getInstance(context).getListaExercicios();
    }*/
}
