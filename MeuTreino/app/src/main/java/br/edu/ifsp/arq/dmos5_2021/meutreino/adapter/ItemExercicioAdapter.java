package br.edu.ifsp.arq.dmos5_2021.meutreino.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class ItemExercicioAdapter extends RecyclerView.Adapter<ItemExercicioAdapter.ViewHolder>{

    private List<Exercicio> dataSource;
    private static RecyclerItemClickListener mClickListener;

    public ItemExercicioAdapter(List<Exercicio> dataSource, RecyclerItemClickListener listener) {
        this.dataSource = dataSource;
        mClickListener = listener;
    }

    @NonNull
    @Override
    public ItemExercicioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercicio, parent, false);
        ItemExercicioAdapter.ViewHolder holder = new ItemExercicioAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemExercicioAdapter.ViewHolder holder, int position) {
        holder.mNomeExercicioTextView.setText(dataSource.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(List<Exercicio> dataSource) {
        this.dataSource = dataSource;
        this.notifyDataSetChanged();
    }

    public List<Exercicio> getDataSource() {
        return dataSource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNomeExercicioTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNomeExercicioTextView = itemView.findViewById(R.id.txt_nome_exercicio);
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
