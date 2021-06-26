package br.edu.ifsp.arq.dmos5_2021.minhastarefas.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.minhastarefas.R;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.controller.TarefaController;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.model.Tarefa;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.view.ListaTarefasActivity;
import br.edu.ifsp.arq.dmos5_2021.minhastarefas.view.RecyclerItemClickListener;

public class ItemTarefaAdapter extends RecyclerView.Adapter<ItemTarefaAdapter.ViewHolder> {

    private Context mContext;
    private List<Tarefa> mTarefas;
    private static RecyclerItemClickListener mClickListener;

    public ItemTarefaAdapter(Context context, List<Tarefa> data) {
        this.mContext = context;
        this.mTarefas = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tarefa, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTarefaAdapter.ViewHolder holder, int position) {
        Tarefa tarefa = mTarefas.get(position);
        holder.descricaoTextView.setText(tarefa.getDescricao());
        holder.dataTextView.setText(tarefa.getData());

        if (tarefa.getPrioridade().equals(Constantes.PRIORIDADE_BAIXA)) {
            holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.green, mContext.getTheme()));
        }
        else if (tarefa.getPrioridade().equals(Constantes.PRIORIDADE_MEDIA)) {
            holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.yellow, mContext.getTheme()));
        }
        else if (tarefa.getPrioridade().equals(Constantes.PRIORIDADE_ALTA)) {
            holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.red, mContext.getTheme()));
        }
        else if (tarefa.getPrioridade().equals(Constantes.PRIORIDADE_VAZIA)) {
            holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.grey, mContext.getTheme()));
        }

       holder.removeTarefaImageView.setOnClickListener(v -> {
           if(v == holder.removeTarefaImageView){
               confirmaRemoveTarefa(position);
           }
       });

        if(tarefa.isConcluida()){
            holder.concluirTarefaImageView.setEnabled(false);
            holder.concluirTarefaImageView.setVisibility(View.INVISIBLE);
        }else{
            holder.concluirTarefaImageView.setOnClickListener(v -> {
                if(v == holder.concluirTarefaImageView){
                    confirmaConcluiTarefa(position);
                }
            });

        }
    }

    private void confirmaConcluiTarefa(int position) {
        new AlertDialog.Builder(mContext)
                .setTitle("Concluindo...")
                .setMessage("Tem certeza que deseja concluir a tarefa?")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        concluiTarefa(position);
                    }
                }).setNegativeButton("não", null).show();
    }

    private void concluiTarefa(int position){
        Tarefa tarefa = mTarefas.get(position);
        TarefaController.concluiTarefa(mContext, tarefa);
        //notifyItemChanged(position);
        mTarefas.remove(tarefa);

        notifyDataSetChanged();
        //notifyItemRemoved(position);

        /*Intent intent = new Intent();
        intent.putExtra("tarefa", tarefa);
        setResult(ListaTarefasActivity.RESULT_OK, intent);*/

    }


    private void confirmaRemoveTarefa(int position) {
        new AlertDialog.Builder(mContext)
                .setTitle("Removendo...")
                .setMessage("Tem certeza que deseja remover a tarefa?")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeTarefa(position);
                    }
                }).setNegativeButton("não", null).show();
    }

    private void removeTarefa(int position) {
        Tarefa tarefa = mTarefas.get(position);
        TarefaController.removeTarefa(mContext, tarefa);
        mTarefas.remove(tarefa);
        //notifyItemChanged(position);
        //notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTarefas.size();
    }

    public void setClickListener(RecyclerItemClickListener clickListener){
        this.mClickListener = clickListener;
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView prioridadeImageView;
        public TextView descricaoTextView;
        public TextView dataTextView;
        public ImageView removeTarefaImageView;
        public ImageView concluirTarefaImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            prioridadeImageView = itemView.findViewById(R.id.img_icone_prioridade);
            descricaoTextView = itemView.findViewById(R.id.text_descricao);
            dataTextView = itemView.findViewById(R.id.text_data);
            removeTarefaImageView = itemView.findViewById(R.id.img_icone_remover);
            concluirTarefaImageView = itemView.findViewById(R.id.img_icone_concluir);
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
