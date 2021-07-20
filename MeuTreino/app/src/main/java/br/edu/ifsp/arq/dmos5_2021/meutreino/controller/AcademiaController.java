package br.edu.ifsp.arq.dmos5_2021.meutreino.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemAparelhoAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.AcademiaDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Academia;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class AcademiaController {

    public static boolean adicionarAparelho(Context context, Aparelho aparelho){
        AcademiaDAO dao = new AcademiaDAO(context);
        return dao.insert(new Academia(aparelho));
    }

    public static boolean removerAparelho(Context context, Aparelho aparelho){
        AcademiaDAO dao = new AcademiaDAO(context);
        return dao.delete(new Academia(aparelho));
    }

    public static void updateAdapterDataSource(Context context, ItemAparelhoAdapter adapter){
        List<Academia> aparelhosAcademia;
        AcademiaDAO dao = new AcademiaDAO(context);
        aparelhosAcademia = dao.recuperate();

        List<Aparelho> aparelhos = new ArrayList<>();
        for (Academia a : aparelhosAcademia) {
            aparelhos.add(a.getAparelho());
        }

        adapter.setDataSource(aparelhos);
    }

    public static ItemAparelhoAdapter getAparelhosAcademiaAdapter(Context context, RecyclerItemClickListener listener){
        List<Academia> aparelhosAcademia;
        AcademiaDAO dao = new AcademiaDAO(context);
        aparelhosAcademia = dao.recuperate();

        List<Aparelho> aparelhos = new ArrayList<>();
        for (Academia a : aparelhosAcademia) {
            aparelhos.add(a.getAparelho());
        }
        return new ItemAparelhoAdapter(aparelhos, listener);
    }


}
