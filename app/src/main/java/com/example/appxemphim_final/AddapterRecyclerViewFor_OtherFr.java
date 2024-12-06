package com.example.appxemphim_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddapterRecyclerViewFor_OtherFr extends RecyclerView.Adapter<AddapterRecyclerViewFor_OtherFr.myViewHolder> {
    private Context context;
    private List<Phim> mListMovie;
    private OnItemClicklistener onItemClicklistener;

    public AddapterRecyclerViewFor_OtherFr(Context context, OnItemClicklistener onItemClicklistener) {
        this.context = context;
        this.onItemClicklistener = onItemClicklistener;
    }

    public void setDaTa(List<Phim> mListMovie) {
        this.mListMovie = mListMovie;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_for_otherfr,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Phim mv = mListMovie.get(position);
        if (mv == null){
            return;
        }
        holder.img_phim.setImageResource(mv.getResource_img());
        holder.txt_tenPhim.setText(mv.getTenPhim());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicklistener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListMovie!=null){
            return mListMovie.size();
        }
        return 0;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_phim;
        private TextView txt_tenPhim;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img_phim = itemView.findViewById(R.id.img_phim_2);
            txt_tenPhim = itemView.findViewById(R.id.txt_tenphim_2);
        }
    }
}
