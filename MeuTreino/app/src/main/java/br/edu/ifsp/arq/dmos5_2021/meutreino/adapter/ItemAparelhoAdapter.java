package br.edu.ifsp.arq.dmos5_2021.meutreino.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.arq.dmos5_2021.meutreino.R;
import br.edu.ifsp.arq.dmos5_2021.meutreino.model.Aparelho;
import br.edu.ifsp.arq.dmos5_2021.meutreino.view.RecyclerItemClickListener;

public class ItemAparelhoAdapter extends RecyclerView.Adapter<ItemAparelhoAdapter.ViewHolder> {

    private List<Aparelho> dataSource;
    private static RecyclerItemClickListener mClickListener;

    public ItemAparelhoAdapter(List<Aparelho> dataSource, RecyclerItemClickListener listener) {
        this.dataSource = dataSource;
        mClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aparelho, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAparelhoAdapter.ViewHolder holder, int position) {
        holder.mInfoTextView.setText(dataSource.get(position).getNome());
        //holder.mFotoImageView.setImageDrawable(Drawable.createFromPath(dataSource.get(position).getFoto()));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(List<Aparelho> dataSource) {
        this.dataSource = dataSource;
        this.notifyDataSetChanged();
    }

    public List<Aparelho> getDataSource() {
        return dataSource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mInfoTextView;
       // public ImageView mFotoImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mInfoTextView = itemView.findViewById(R.id.txt_info_aparelho);
           // mFotoImageView = itemView.findViewById(R.id.img_aparelho_lista);
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
