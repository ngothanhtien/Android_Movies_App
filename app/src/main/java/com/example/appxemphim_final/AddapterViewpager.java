package com.example.appxemphim_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class AddapterViewpager extends PagerAdapter {
    private Context context;
    private List<Phim> mlist_movie_rd;
    private OnItemClicklistener onItemClicklistener;
    public AddapterViewpager(Context context, OnItemClicklistener onItemClicklistener) {
        this.context = context;
        this.onItemClicklistener = onItemClicklistener;
    }
    public void setData(List<Phim> list) {
        this.mlist_movie_rd = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo_movies,container,false);
        ImageView img = view.findViewById(R.id.img_phoTo_ViewPager);
        Phim mv = mlist_movie_rd.get(position);
        if(mv !=null){
            Glide.with(context).load(mv.getResource_img()).into(img);
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClicklistener!=null){
                    onItemClicklistener.onItemClick(position);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(mlist_movie_rd !=null){
            return mlist_movie_rd.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
