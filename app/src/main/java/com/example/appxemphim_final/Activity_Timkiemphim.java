package com.example.appxemphim_final;

import static java.util.Locale.filter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Timkiemphim extends AppCompatActivity {
    private AddapterRecyclerViewFor_Search addapterSearch;
    private ImageButton btn_back;
    private SearchView searchView;
    private List<Phim> mlist_movie_search;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timkiem);
        // ánh xạ
        recyclerView = findViewById(R.id.rcv_search);
        btn_back = findViewById(R.id.btn_back_search);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        addapterSearch = new AddapterRecyclerViewFor_Search(this,getDaTaMovie(),new OnItemClickListener_mv() {
            @Override
            public void onItemClick(Phim mv) {
                Intent intent = new Intent(Activity_Timkiemphim.this, Activity_Thongtinphim.class);
                intent.putExtra("img", mv.getResource_img());
                intent.putExtra("tenPhim", mv.getTenPhim());
                intent.putExtra("moTa", mv.getMoTa());
                intent.putExtra("dienVien",mv.getDienVien());
                intent.putExtra("ngayPhatHanh", mv.getNgayPhatHanh());
                intent.putExtra("thoiLuong", mv.getThoiLuong());
                intent.putExtra("resource_video", mv.getResource_video());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(addapterSearch);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addapterSearch.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                addapterSearch.getFilter().filter(newText);
                return false;
            }
        });
    }

    public List<Phim> getDaTaMovie() {
        int img[] = {
                R.drawable.movies_fast6,
                R.drawable.movies_fast7,
                R.drawable.movies_fast8,
                R.drawable.movies_fast9,
                R.drawable.movies_fastx,
                R.drawable.movies_aquaman,
                R.drawable.movies_shawshank,
                R.drawable.movies_kegiamho,
                R.drawable.movies_sr_chosancongly,
                R.drawable.movies_sr_squidgame,
                R.drawable.movies_sr_moneyheist,
                R.drawable.movies_sr_vincenzo,
                R.drawable.movies_sr_queenoftears,
                R.drawable.tvshow_gieogigatnay,
                R.drawable.tvshow_runningman,
                R.drawable.tvshow_familyouting,
        };
        String tenPhim[] = {
                "Fast & Furious 6",
                "Fast & Furious 7",
                "Fast & Furious 8",
                "Fast & Furious 9",
                "Fast & Furious X",
                "Aquaman",
                "Shawshank",
                "Kẻ Giám Hộ",
                "Chó Săn Công Lý",
                "SquidGame",
                "Money Heist",
                "Vincenzo",
                "Queen of Tears",
                "Gieo Gì Gặt Nấy",
                "Running Man",
                "Family Outing",
        };
        String moTa[] = {
                getString(R.string.mota_fast6),
                getString(R.string.mota_fast7),
                getString(R.string.mota_fast8),
                getString(R.string.mota_fast9),
                getString(R.string.mota_fastx),
                getString(R.string.mota_aquaman),
                getString(R.string.mota_shawshank),
                getString(R.string.mota_kegiamho),
                getString(R.string.mota_chosancongly),
                getString(R.string.mota_squidgame),
                getString(R.string.mota_moneyheist),
                getString(R.string.mota_vincenzo),
                getString(R.string.mota_queenoftears),
                getString(R.string.mota_gieogigatnay),
                getString(R.string.mota_runningman),
                getString(R.string.mota_familyouting)
        };
        String dienVien[] = {
                getString(R.string.dv_fast6),
                getString(R.string.dv_fast7),
                getString(R.string.dv_fast8),
                getString(R.string.dv_fast9),
                getString(R.string.dv_fastx),
                getString(R.string.dv_aquaman),
                getString(R.string.dv_shawshank),
                getString(R.string.dv_kegiamho),
                getString(R.string.dv_chosancongly),
                getString(R.string.dv_squidgame),
                getString(R.string.dv_moneyheist),
                getString(R.string.dv_vincenzo),
                getString(R.string.dv_queenoftears),
                getString(R.string.dv_gieogigatnay),
                getString(R.string.dv_runningman),
                getString(R.string.dv_familyouting)
        };
        String ngayPhatHanh[] = {
                getString(R.string.rel_fast6),
                getString(R.string.rel_fast7),
                getString(R.string.rel_fast8),
                getString(R.string.rel_fast9),
                getString(R.string.rel_fastx),
                getString(R.string.rel_aquaman),
                getString(R.string.rel_shawshank),
                getString(R.string.rel_kegiamho),
                getString(R.string.rel_chosancongly),
                getString(R.string.rel_squidgame),
                getString(R.string.rel_moneyheist),
                getString(R.string.rel_vincenzo),
                getString(R.string.rel_queenoftears),
                getString(R.string.rel_gieogigatnay),
                getString(R.string.rel_runningman),
                getString(R.string.rel_familyouting)
        };
        int thoiLuong[]={
                getResources().getInteger(R.integer.time_fast6),
                getResources().getInteger(R.integer.time_fast7),
                getResources().getInteger(R.integer.time_fast8),
                getResources().getInteger(R.integer.time_fast9),
                getResources().getInteger(R.integer.time_fastx),
                getResources().getInteger(R.integer.time_aquaman),
                getResources().getInteger(R.integer.time_shawshank),
                getResources().getInteger(R.integer.time_kegiamho),
                getResources().getInteger(R.integer.time_chosancongly),
                getResources().getInteger(R.integer.time_squidgame),
                getResources().getInteger(R.integer.time_moneyheist),
                getResources().getInteger(R.integer.time_vincenzo),
                getResources().getInteger(R.integer.time_queenoftears),
                getResources().getInteger(R.integer.time_gieogigatnay),
                getResources().getInteger(R.integer.time_runningman),
                getResources().getInteger(R.integer.time_familyouting)
        };
        int resource_video[] ={
                R.raw.movies_fast6,
                R.raw.movies_fast7,
                R.raw.movies_fast8,
                R.raw.movies_fast9,
                R.raw.movies_fastx,
                R.raw.movies_aquaman,
                R.raw.movies_shawhank,
                R.raw.movies_kegiamho,
                R.raw.movies_series_chosancongly,
                R.raw.movies_series_squidgame,
                R.raw.movies_series_moneyheist,
                R.raw.movies_series_vincenzo,
                R.raw.movies_series_queenoftears,
                R.raw.tvshow_gieogigatnay,
                R.raw.tvshow_runningman,
                R.raw.tvshow_familyouting
        };
        mlist_movie_search = new ArrayList<>();
        for(int i=0;i<img.length;i++){
            mlist_movie_search.add(new Phim(img[i],tenPhim[i],moTa[i],dienVien[i],ngayPhatHanh[i],thoiLuong[i],resource_video[i]));
        }
        return mlist_movie_search;
    }
}