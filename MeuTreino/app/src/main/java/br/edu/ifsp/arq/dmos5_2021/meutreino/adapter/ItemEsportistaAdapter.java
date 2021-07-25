package br.edu.ifsp.arq.dmos5_2021.meutreino.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.constants.Constants;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Usuario;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class ItemEsportistaAdapter extends RecyclerView.Adapter<ItemEsportistaAdapter.ViewHolder>{

    private List<Usuario> dataSource;
    private static RecyclerItemClickListener mClickListener;

    public ItemEsportistaAdapter(List<Usuario> dataSource, RecyclerItemClickListener listener) {
        this.dataSource = dataSource;
        mClickListener = listener;
    }

    @NonNull
    @Override
    public ItemEsportistaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_esportista, parent, false);
        ItemEsportistaAdapter.ViewHolder holder = new ItemEsportistaAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemEsportistaAdapter.ViewHolder holder, int position) {
        holder.mNomeUsuarioTextView.setText(dataSource.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(List<Usuario> dataSource) {
        this.dataSource = dataSource;
        this.notifyDataSetChanged();
    }

    public List<Usuario> getDataSource() {
        return dataSource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNomeUsuarioTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNomeUsuarioTextView = itemView.findViewById(R.id.txt_nome_usuario);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null){
                mClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
