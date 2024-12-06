package com.example.appxemphim_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Phimbo extends Fragment {
    private RecyclerView recyclerView;
    private AddapterRecyclerViewFor_OtherFr addapterRecyclerView;
    private List<Phim> mlist_movies_series;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phimbo_,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ánh xạ
        recyclerView = view.findViewById(R.id.rcv_fr_movies_series);
        // gọi hàm dữ liệu
        dataListMoviesSeries();
        // thiết lập layout
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        // khởi tạo addapter
        addapterRecyclerView = new AddapterRecyclerViewFor_OtherFr(getContext(), new OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Activity_Thongtinphim.class);
                intent.putExtra("img",mlist_movies_series.get(position).getResource_img());
                intent.putExtra("tenPhim",mlist_movies_series.get(position).getTenPhim());
                intent.putExtra("moTa",mlist_movies_series.get(position).getMoTa());
                intent.putExtra("dienVien",mlist_movies_series.get(position).getDienVien());
                intent.putExtra("ngayPhatHanh",mlist_movies_series.get(position).getNgayPhatHanh());
                intent.putExtra("thoiLuong",mlist_movies_series.get(position).getThoiLuong());
                intent.putExtra("resource_video",mlist_movies_series.get(position).getResource_video());
                startActivity(intent);
            }
        });
        addapterRecyclerView.setDaTa(mlist_movies_series);
        recyclerView.setAdapter(addapterRecyclerView);

    }
    private void dataListMoviesSeries(){
        int img[] = {
                R.drawable.movies_sr_chosancongly,
                R.drawable.movies_sr_squidgame,
                R.drawable.movies_sr_moneyheist,
                R.drawable.movies_sr_vincenzo,
                R.drawable.movies_sr_queenoftears
        };
        String tenPhim[] = {
                "Chó Săn Công Lý",
                "SquidGame",
                "Money Heist",
                "Vincenzo",
                "Queen of Tears",
        };
        String moTa[] ={
                getString(R.string.mota_chosancongly),
                getString(R.string.mota_squidgame),
                getString(R.string.mota_moneyheist),
                getString(R.string.mota_vincenzo),
                getString(R.string.mota_queenoftears)
        };
        String dienVien[] ={
                getString(R.string.dv_chosancongly),
                getString(R.string.dv_squidgame),
                getString(R.string.dv_moneyheist),
                getString(R.string.dv_vincenzo),
                getString(R.string.dv_queenoftears)
        };
        String ngayPhatHanh[] ={
                getString(R.string.rel_chosancongly),
                getString(R.string.rel_squidgame),
                getString(R.string.rel_moneyheist),
                getString(R.string.rel_vincenzo),
                getString(R.string.rel_queenoftears)
        };
        int thoiLuong[] ={
                getResources().getInteger(R.integer.time_chosancongly),
                getResources().getInteger(R.integer.time_squidgame),
                getResources().getInteger(R.integer.time_moneyheist),
                getResources().getInteger(R.integer.time_vincenzo),
                getResources().getInteger(R.integer.time_queenoftears)
        };
        int resource_video [] ={
                R.raw.movies_series_chosancongly,
                R.raw.movies_series_squidgame,
                R.raw.movies_series_moneyheist,
                R.raw.movies_series_vincenzo,
                R.raw.movies_series_queenoftears
        };
        mlist_movies_series = new ArrayList<>();
        for(int i=0;i<img.length;i++){
            mlist_movies_series.add(new Phim(img[i],tenPhim[i],moTa[i],dienVien[i],ngayPhatHanh[i],thoiLuong[i],resource_video[i]));
        }
    }
}