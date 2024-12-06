package com.example.appxemphim_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddapterRecyclerViewFor_Search extends RecyclerView.Adapter<AddapterRecyclerViewFor_Search.myViewHolder> implements Filterable {
    private Context context;
    private List<Phim> mListMovie;
    private List<Phim> mListMovie_filter;
    private OnItemClickListener_mv onItemClicklistener;

    public AddapterRecyclerViewFor_Search(Context context, List<Phim> mListMovie, OnItemClickListener_mv onItemClicklistener) {
        this.context = context;
        this.onItemClicklistener = onItemClicklistener;
        this.mListMovie = mListMovie;
        this.mListMovie_filter = mListMovie;
    }

    @NonNull
    @Override
    public AddapterRecyclerViewFor_Search.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_for_otherfr,parent,false);
        return new AddapterRecyclerViewFor_Search.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddapterRecyclerViewFor_Search.myViewHolder holder, int position) {
        Phim mv = mListMovie.get(position);
        if (mv == null){
            return;
        }
        holder.img_phim.setImageResource(mv.getResource_img());
        holder.txt_tenPhim.setText(mv.getTenPhim());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicklistener.onItemClick(mv);
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
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()) {
                    mListMovie = mListMovie_filter;
                } else {
                    List<Phim> filteredList = new ArrayList<>();
                    for (Phim movie : mListMovie_filter) {
                        if (movie.getTenPhim().toLowerCase().contains(search.toLowerCase())) {
                            filteredList.add(movie);
                        }
                    }
                    mListMovie = filteredList;
                }
                FilterResults results = new FilterResults();
                results.values = mListMovie;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListMovie = (List<Phim>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
