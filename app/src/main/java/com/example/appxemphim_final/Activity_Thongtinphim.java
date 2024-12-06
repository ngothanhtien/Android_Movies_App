package com.example.appxemphim_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Activity_Thongtinphim extends AppCompatActivity {
    private ImageButton btn_back,btn_play;
    private ImageView img_details;
    private TextView txt_tenPhim,txt_moTaPhim,txt_dienVien,txt_ngayPhatHanh,txt_thoiLuong;
    private ImageButton btn_add_favoriteMovie;
    private List<Phim> favoriteMovies;
    private Gson gson = new Gson();
    private String username_current;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinphim);
        EdgeToEdge.enable(this);

        // ánh xạ các thành phần trong sub
        btn_back = findViewById(R.id.btn_sub_back);
        btn_play = findViewById(R.id.btn_sub_play);
        img_details = findViewById(R.id.img_sub_details);
        txt_tenPhim = findViewById(R.id.txt_sub_tenPhim);
        txt_moTaPhim = findViewById(R.id.txt_sub_moTaPhim);
        txt_dienVien = findViewById(R.id.txt_sub_dienVien);
        txt_ngayPhatHanh = findViewById(R.id.txt_sub_NgayPhatHanh);
        txt_thoiLuong = findViewById(R.id.txt_sub_thoiLuong);
        btn_add_favoriteMovie = findViewById(R.id.btn_add_favoriteMovie);

        // nhận dữ liệu từ các fragment
        Bundle bundle = getIntent().getExtras();
        int img = bundle.getInt("img");
        String tenPhim = bundle.getString("tenPhim");
        String moTa = bundle.getString("moTa");
        String dienVien = bundle.getString("dienVien");
        String ngayPhatHanh = bundle.getString("ngayPhatHanh");
        int thoiLuong = bundle.getInt("thoiLuong");
        int resource_video  = bundle.getInt("resource_video");

        // đẩy dư liệu lên các thành phần
        img_details.setImageResource(img);
        txt_tenPhim.setText(tenPhim);
        txt_moTaPhim.setText(moTa);
        txt_dienVien.setText(dienVien);
        txt_ngayPhatHanh.setText(ngayPhatHanh);
        txt_thoiLuong.setText(thoiLuong+"p");

        SharedPreferences sharedPreferences = getSharedPreferences("AppData", MODE_PRIVATE);
        username_current = sharedPreferences.getString("username",null);

        TaiPhimYeuThich();

        btn_add_favoriteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kiemTraTonTai(tenPhim,resource_video)) {
                    Toast.makeText(Activity_Thongtinphim.this, "Phim đã có trong danh sách yêu thích", Toast.LENGTH_SHORT).show();
                } else {
                    // Add new movie to favorites
                    favoriteMovies.add(new Phim(img, tenPhim, moTa, dienVien, ngayPhatHanh, thoiLuong, resource_video));
                    LuuPhimYeuThich();
                    Toast.makeText(Activity_Thongtinphim.this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    btn_add_favoriteMovie.setImageResource(R.drawable.icon_done_while);
                }
            }
        });

        // sự kiện quay lại fragment
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Thongtinphim.this, Activity_Xemphim.class);
                intent.putExtra("resource_video",resource_video);
                intent.putExtra("tenPhim",tenPhim);
                startActivity(intent);
            }
        });
    }
    private void TaiPhimYeuThich() {
        SharedPreferences sharedPreferences = getSharedPreferences("FavoriteMovies", MODE_PRIVATE); // sửa tên cho đồng nhất
        String key = "favorites__" + username_current;
        String movieList = sharedPreferences.getString(key, "[]");
        favoriteMovies = gson.fromJson(movieList, new TypeToken<List<Phim>>(){}.getType());
        if (favoriteMovies == null) {
            favoriteMovies = new ArrayList<>();
        }
    }

    private void LuuPhimYeuThich() {
        SharedPreferences sharedPreferences = getSharedPreferences("FavoriteMovies", MODE_PRIVATE); // sửa tên cho đồng nhất
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String key = "favorites__" + username_current;
        String movieListJson = gson.toJson(favoriteMovies);
        editor.putString(key, movieListJson);
        editor.apply();
    }
    private boolean kiemTraTonTai(String tenPhim,int resource_video) {
        for (Phim movie : favoriteMovies) {
            if (movie.getTenPhim().equals(tenPhim) && movie.getResource_video() == resource_video)         {
                return true;
            }
        }
        return false;
    }
}
