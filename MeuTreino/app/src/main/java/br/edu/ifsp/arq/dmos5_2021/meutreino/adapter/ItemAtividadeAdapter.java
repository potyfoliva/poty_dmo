package br.edu.ifsp.arq.dmos5_2021.meutreino.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Atividade;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Exercicio;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class ItemAtividadeAdapter extends RecyclerView.Adapter<ItemAtividadeAdapter.ViewHolder> {

    private List<Exercicio> dataSource;
    private static RecyclerView mClickListener;
    private ArrayAdapter<CharSequence> mAdapter;
    private Context context;

    public ItemAtividadeAdapter(Context context, List<Exercicio> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public ItemAtividadeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atividade_cadastro, parent, false);
        ItemAtividadeAdapter.ViewHolder holder = new ItemAtividadeAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAtividadeAdapter.ViewHolder holder, int position) {
        holder.mNomeExercicioTxtView.setText(dataSource.get(position).getNome());

        mAdapter = ArrayAdapter.createFromResource(context, R.array.spiner_tempo_repeticao, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.mSpinner.setAdapter(mAdapter);
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
        public TextView mNomeExercicioTxtView;
        public Spinner mSpinner;
        public EditText mTempoRepeticaoEditTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNomeExercicioTxtView = itemView.findViewById(R.id.txt_nome_exercicio_atividade);
            mSpinner = itemView.findViewById(R.id.spinner_tempo_repeticao);
            mTempoRepeticaoEditTxt = itemView.findViewById(R.id.edit_tempo_repeticao);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null){
                //mClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
