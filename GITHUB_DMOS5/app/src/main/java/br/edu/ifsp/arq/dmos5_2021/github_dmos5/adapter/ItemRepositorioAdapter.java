package br.edu.ifsp.arq.dmos5_2021.github_dmos5.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.github_dmos5.R;
import br.edu.ifsp.arq.dmos5_2021.github_dmos5.model.RepositorioNome;

public class ItemRepositorioAdapter extends RecyclerView.Adapter<ItemRepositorioAdapter.ViewHolder>{

    private List<RepositorioNome> mRepositorios;

    public ItemRepositorioAdapter(@NonNull List<RepositorioNome> data) {
        this.mRepositorios = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_repositorio, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRepositorioAdapter.ViewHolder holder, int position) {
        holder.nomeRepositorioTextView.setText(mRepositorios.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mRepositorios != null){
            return mRepositorios.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nomeRepositorioTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeRepositorioTextView = itemView.findViewById(R.id.text_nome_repositorio);
        }
    }

    public void atualizar(List<RepositorioNome> repositorios, RecyclerView recyclerView) {
        this.mRepositorios = repositorios;
        notifyDataSetChanged();
    }
}
