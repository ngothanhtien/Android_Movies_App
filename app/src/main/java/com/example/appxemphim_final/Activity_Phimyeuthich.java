package com.example.appxemphim_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Activity_Phimyeuthich extends AppCompatActivity {
    private ImageButton btn_back;
    private RecyclerView  recyclerView;
    private AddapterRecyclerViewFor_OtherFr addapterRecyclerView;
    private List<Phim> mlist_Favorite;
    private String username_current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phimyeuthich);

        btn_back = findViewById(R.id.btn_back_favorite);
        recyclerView = findViewById(R.id.rcv_favorite);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // lay tai khoan hien tai
        SharedPreferences sharedPreferences = getSharedPreferences("AppData", MODE_PRIVATE);
        username_current = sharedPreferences.getString("username",null);

        TaiPhimYeuThich();

        addapterRecyclerView = new AddapterRecyclerViewFor_OtherFr(this, new OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Activity_Phimyeuthich.this, Activity_Thongtinphim.class);
                intent.putExtra("img",mlist_Favorite.get(position).getResource_img());
                intent.putExtra("tenPhim",mlist_Favorite.get(position).getTenPhim());
                intent.putExtra("moTa",mlist_Favorite.get(position).getMoTa());
                intent.putExtra("dienVien",mlist_Favorite.get(position).getDienVien());
                intent.putExtra("ngayPhatHanh",mlist_Favorite.get(position).getNgayPhatHanh());
                intent.putExtra("thoiLuong",mlist_Favorite.get(position).getThoiLuong());
                intent.putExtra("resource_video",mlist_Favorite.get(position).getResource_video());
                startActivity(intent);
            }
        });
        addapterRecyclerView.setDaTa(mlist_Favorite);
        recyclerView.setAdapter(addapterRecyclerView);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void TaiPhimYeuThich() {
        SharedPreferences sharedPreferences = getSharedPreferences("FavoriteMovies", MODE_PRIVATE); // sửa tên cho đồng nhất
        String key = "favorites__" + username_current;
        String movieList = sharedPreferences.getString(key, "[]");
        Gson gson = new Gson();
        mlist_Favorite = gson.fromJson(movieList, new TypeToken<List<Phim>>(){}.getType());
        if (mlist_Favorite == null) {
            mlist_Favorite = new ArrayList<>();
        }
    }
}