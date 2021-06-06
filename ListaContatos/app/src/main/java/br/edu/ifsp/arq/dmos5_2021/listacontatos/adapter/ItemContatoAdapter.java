package br.edu.ifsp.arq.dmos5_2021.listacontatos.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.listacontatos.R;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.constantes.Constantes;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.controller.ContatoController;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.dao.ContatoDAO;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.model.Contato;
import br.edu.ifsp.arq.dmos5_2021.listacontatos.view.RecyclerItemClickListener;

public class ItemContatoAdapter extends RecyclerView.Adapter<ItemContatoAdapter.ViewHolder>  {

    private Context mContext;
    private List<Contato> mContatos;
    private static RecyclerItemClickListener mClickListener;
    private int mTamanhoFav;

    public ItemContatoAdapter(Context context, List<Contato> data) {
        this.mContext = context;
        this.mContatos = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_contato, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemContatoAdapter.ViewHolder holder, int position) {
        Contato contato = mContatos.get(position);
        holder.nomeTextView.setText(contato.getNome());
        holder.apelidoTextView.setText(contato.getApelido());
        holder.telefoneTextView.setText(contato.getTelefone());
        holder.emailTextView.setText(contato.getEmail());
        if (contato.isFavorite()) {
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.red, mContext.getTheme()));
        } else {
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.gray, mContext.getTheme()));
        }
        holder.favoritoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == holder.favoritoImageView) {
                    heartClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContatos.size();
    }

    public void setClickListener(RecyclerItemClickListener clickListener){
        this.mClickListener = clickListener;
    }

    private void heartClick(int position) {
        mTamanhoFav = ContatoController.allFavoritos(mContext).size();
        Log.i("tam fav:", String.valueOf(mTamanhoFav));
        Contato c = mContatos.get(position);
        if(!c.isFavorite()) {
            if (mTamanhoFav < Constantes.MAX_FAVORITOS) {
                c.setFavorite(!c.isFavorite());
                ContatoController.addFavorito(mContext, c);
                notifyDataSetChanged();
            } else {
                Toast.makeText(mContext, R.string.msg_fav, Toast.LENGTH_SHORT).show();
            }
        }else{
            c.setFavorite(!c.isFavorite());
            ContatoController.addFavorito(mContext, c);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nomeTextView;
        public TextView apelidoTextView;
        public TextView telefoneTextView;
        public TextView emailTextView;
        public ImageView favoritoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.text_nome);
            apelidoTextView = itemView.findViewById(R.id.text_apelido);
            telefoneTextView = itemView.findViewById(R.id.text_telefone);
            emailTextView = itemView.findViewById(R.id.text_email);
            favoritoImageView = itemView.findViewById(R.id.img_icon_favorite);
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
