package com.example.appxemphim_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Khachvanglai extends AppCompatActivity {
    private Button btn_dangnhap;
    private RecyclerView rcv_another_user;
    private AddapterRecyclerViewFor_OtherFr addapterRecyclerView2;
    private List<Phim> mList_movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khachvanglai);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        rcv_another_user = findViewById(R.id.rcv_another_user);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_another_user.setLayoutManager(gridLayoutManager);

        dataMovieForAnotherUser();
        addapterRecyclerView2 = new AddapterRecyclerViewFor_OtherFr(this, new OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Activity_Khachvanglai.this, Activity_Thongtinphim.class);
                intent.putExtra("img",mList_movie.get(position).getResource_img());
                intent.putExtra("tenPhim",mList_movie.get(position).getTenPhim());
                intent.putExtra("moTa",mList_movie.get(position).getMoTa());
                intent.putExtra("dienVien",mList_movie.get(position).getDienVien());
                intent.putExtra("ngayPhatHanh",mList_movie.get(position).getNgayPhatHanh());
                intent.putExtra("thoiLuong",mList_movie.get(position).getThoiLuong());
                intent.putExtra("resource_video",mList_movie.get(position).getResource_video());
                startActivity(intent);
            }
        });
        rcv_another_user.setAdapter(addapterRecyclerView2);
        addapterRecyclerView2.setDaTa(mList_movie);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Khachvanglai.this, Activity_Dangnhap.class);
                startActivity(intent);
            }
        });
    }

    public void dataMovieForAnotherUser(){
        int img[] = {
                R.drawable.movies_aquaman,
                R.drawable.movies_fast7,
                R.drawable.movies_shawshank,
                R.drawable.movies_sr_moneyheist,
                R.drawable.movies_fast6,
                R.drawable.movies_sr_squidgame,
                R.drawable.movies_kegiamho};
        String tenPhim[] = {
                "Aquaman",
                "Fast & Furious 7",
                "Shawshank",
                "Money Heist",
                "Fast & Furious 6",
                "SquidGame",
                "Kẻ Giám Hộ"};
        String moTa[] = {
                getString(R.string.mota_aquaman),
                getString(R.string.mota_fast7),
                getString(R.string.mota_shawshank),
                getString(R.string.mota_moneyheist),
                getString(R.string.mota_fast6),
                getString(R.string.mota_squidgame),
                getString(R.string.mota_kegiamho)
        };
        String dienVien[] = {
                getString(R.string.dv_aquaman),
                getString(R.string.dv_fast7),
                getString(R.string.dv_shawshank),
                getString(R.string.dv_moneyheist),
                getString(R.string.dv_fast6),
                getString(R.string.dv_squidgame),
                getString(R.string.dv_kegiamho)
        };
        String ngayPhatHanh[] = {
                getString(R.string.rel_aquaman),
                getString(R.string.rel_fast7),
                getString(R.string.rel_shawshank),
                getString(R.string.rel_moneyheist),
                getString(R.string.rel_fast6),
                getString(R.string.rel_squidgame),
                getString(R.string.rel_kegiamho)
        };
        int thoiLuong[]={
                getResources().getInteger(R.integer.time_aquaman),
                getResources().getInteger(R.integer.time_fast7),
                getResources().getInteger(R.integer.time_shawshank),
                getResources().getInteger(R.integer.time_moneyheist),
                getResources().getInteger(R.integer.time_fast6),
                getResources().getInteger(R.integer.time_squidgame),
                getResources().getInteger(R.integer.time_kegiamho)
        };
        int resource_video [] = {
                R.raw.movies_aquaman,
                R.raw.movies_fast7,
                R.raw.movies_shawhank,
                R.raw.movies_series_moneyheist,
                R.raw.movies_fast6,
                R.raw.movies_series_squidgame,
                R.raw.movies_kegiamho
        };
        mList_movie = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            mList_movie.add(new Phim(img[i], tenPhim[i],moTa[i],dienVien[i],ngayPhatHanh[i],thoiLuong[i],resource_video[i]));
        }
    }
}