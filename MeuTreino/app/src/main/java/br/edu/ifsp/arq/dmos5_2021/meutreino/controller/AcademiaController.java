package br.edu.ifsp.arq.dmos5_2021.meutreino.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemAparelhoAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.adapter.ItemAtividadeAdapter;
import br.edu.ifsp.arq.dmos5_2021.meutreino.dao.AcademiaDAO;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Academia;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Atividade;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class AcademiaController {

    public static boolean adicionarAparelho(Context context, Aparelho aparelho, Usuario usuario){
        AcademiaDAO dao = new AcademiaDAO(context);
        return dao.insert(new Academia(usuario, aparelho));
    }

    public static boolean removerAparelho(Context context, Aparelho aparelho, Usuario usuario){
        AcademiaDAO dao = new AcademiaDAO(context);
        return dao.delete(new Academia(usuario, aparelho));
    }

    public static void updateAdapterDataSource(Context context, String userName, ItemAparelhoAdapter adapter){
        List<Academia> aparelhosAcademia;
        AcademiaDAO dao = new AcademiaDAO(context);
        aparelhosAcademia = dao.recuperate(userName);

        List<Aparelho> aparelhos = new ArrayList<>();
        for (Academia a : aparelhosAcademia) {
            aparelhos.add(a.getAparelho());
        }

        adapter.setDataSource(aparelhos);
    }

    public static ItemAparelhoAdapter getAparelhosAcademiaAdapter(Context context, String userName, RecyclerItemClickListener listener){
        List<Academia> aparelhosAcademia;
        AcademiaDAO dao = new AcademiaDAO(context);
        aparelhosAcademia = dao.recuperate(userName);

        List<Aparelho> aparelhos = new ArrayList<>();
        for (Academia a : aparelhosAcademia) {
            aparelhos.add(a.getAparelho());
        }
        return new ItemAparelhoAdapter(aparelhos, listener);
    }

    /*public static ItemAtividadeAdapter getAtividadesAdapter(Context context, String userName, RecyclerItemClickListener listener){
        List<Academia> aparelhosAcademia;
        AcademiaDAO dao = new AcademiaDAO(context);
        aparelhosAcademia = dao.recuperate(userName);

        List<Exercicio> atividades = new ArrayList<>();
        for (Academia a : aparelhosAcademia) {
            atividades.add(a.getAparelho().getExercicio());
        }
        return new ItemAtividadeAdapter(context, atividades, listener);
    }*/

    /*public static List<Academia> getAcademiaPorEsportista(Context context, Usuario usuario){
        List<Academia> academiaList;
        AcademiaDAO dao = new AcademiaDAO(context);
        academiaList = dao.recuperate(usuario);
        return academiaList;
    }*/


}
