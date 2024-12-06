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

public class Fragment_Phimle extends Fragment {
    private RecyclerView recyclerView;
    private AddapterRecyclerViewFor_OtherFr addapterRecyclerView;
    private List<Phim> mlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phimle_,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ánh xạ các thành phần
        recyclerView = view.findViewById(R.id.rcv_fr_movies);

        // set layout cho recyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // gọi hàm lấy dữ liệu list phim
        dataListMovies();
        // khởi tạo adapter cho recyclerView
        addapterRecyclerView = new AddapterRecyclerViewFor_OtherFr(getContext(), new OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Activity_Thongtinphim.class);
                intent.putExtra("img",mlist.get(position).getResource_img());
                intent.putExtra("tenPhim",mlist.get(position).getTenPhim());
                intent.putExtra("moTa",mlist.get(position).getMoTa());
                intent.putExtra("dienVien",mlist.get(position).getDienVien());
                intent.putExtra("ngayPhatHanh",mlist.get(position).getNgayPhatHanh());
                intent.putExtra("thoiLuong",mlist.get(position).getThoiLuong());
                intent.putExtra("resource_video",mlist.get(position).getResource_video());
                startActivity(intent);
            }
        });
        addapterRecyclerView.setDaTa(mlist);
        recyclerView.setAdapter(addapterRecyclerView);

    }
    private void dataListMovies(){
        int img[] = {
                R.drawable.movies_fast6,
                R.drawable.movies_fast7,
                R.drawable.movies_fast8,
                R.drawable.movies_fast9,
                R.drawable.movies_fastx,
                R.drawable.movies_aquaman,
                R.drawable.movies_shawshank,
                R.drawable.movies_kegiamho
        };
        String tenPhim[] = {
                "Fast & Furious 6",
                "Fast & Furious 7",
                "Fast & Furious 8",
                "Fast & Furious 9",
                "Fast & Furious X",
                "Aquaman",
                "ShawShank",
                "Kẻ Giám Hộ"
        };
        String moTa[] = {
                getString(R.string.mota_fast6),
                getString(R.string.mota_fast7),
                getString(R.string.mota_fast8),
                getString(R.string.mota_fast9),
                getString(R.string.mota_fastx),
                getString(R.string.mota_aquaman),
                getString(R.string.mota_shawshank),
                getString(R.string.mota_kegiamho)
        };
        String dienVien[] = {
                getString(R.string.dv_fast6),
                getString(R.string.dv_fast7),
                getString(R.string.dv_fast8),
                getString(R.string.dv_fast9),
                getString(R.string.dv_fastx),
                getString(R.string.dv_aquaman),
                getString(R.string.dv_shawshank),
                getString(R.string.dv_kegiamho)
        };
        String ngayPhatHanh[] = {
                getString(R.string.rel_fast6),
                getString(R.string.rel_fast7),
                getString(R.string.rel_fast8),
                getString(R.string.rel_fast9),
                getString(R.string.rel_fastx),
                getString(R.string.rel_aquaman),
                getString(R.string.rel_shawshank),
                getString(R.string.rel_kegiamho)
        };
        int thoiLuong[]={
                getResources().getInteger(R.integer.time_fast6),
                getResources().getInteger(R.integer.time_fast7),
                getResources().getInteger(R.integer.time_fast8),
                getResources().getInteger(R.integer.time_fast9),
                getResources().getInteger(R.integer.time_fastx),
                getResources().getInteger(R.integer.time_aquaman),
                getResources().getInteger(R.integer.time_shawshank),
                getResources().getInteger(R.integer.time_kegiamho)
        };
        int resource_video[] ={
                R.raw.movies_fast6,
                R.raw.movies_fast7,
                R.raw.movies_fast8,
                R.raw.movies_fast9,
                R.raw.movies_fastx,
                R.raw.movies_aquaman,
                R.raw.movies_shawhank,
                R.raw.movies_kegiamho
        };
        mlist = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            mlist.add(new Phim(img[i], tenPhim[i],moTa[i],dienVien[i],ngayPhatHanh[i],thoiLuong[i],resource_video[i]));
        }
    }

}