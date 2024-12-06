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

public class Fragment_Truyenhinh extends Fragment {
    private RecyclerView recyclerView;
    private AddapterRecyclerViewFor_OtherFr addapterRecyclerView;
    private List<Phim> mlist_tvshow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_truyenhinh_,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ánh  xạ
        recyclerView = view.findViewById(R.id.rcv_fr_tvShow);

        // thiết lập layout
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //goi ham dữ liệu
        dataListTVShow();

        // khởi tạo addapter
        addapterRecyclerView = new AddapterRecyclerViewFor_OtherFr(getContext(), new OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Activity_Thongtinphim.class);
                intent.putExtra("img",mlist_tvshow.get(position).getResource_img());
                intent.putExtra("tenPhim",mlist_tvshow.get(position).getTenPhim());
                intent.putExtra("moTa",mlist_tvshow.get(position).getMoTa());
                intent.putExtra("dienVien",mlist_tvshow.get(position).getDienVien());
                intent.putExtra("ngayPhatHanh",mlist_tvshow.get(position).getNgayPhatHanh());
                intent.putExtra("thoiLuong",mlist_tvshow.get(position).getThoiLuong());
                intent.putExtra("resource_video",mlist_tvshow.get(position).getResource_video());
                startActivity(intent);
            }
        });
        addapterRecyclerView.setDaTa(mlist_tvshow);
        recyclerView.setAdapter(addapterRecyclerView);
    }
    private void dataListTVShow(){
        int img[] = {
                R.drawable.tvshow_gieogigatnay,
                R.drawable.tvshow_runningman,
                R.drawable.tvshow_familyouting,
        };
        String tenPhim[] = {
                "Gieo Gì Gặt Nấy",
                "Running Man",
                "Family Outing",
        };
        String moTa[] = {
                getString(R.string.mota_gieogigatnay),
                getString(R.string.mota_runningman),
                getString(R.string.mota_familyouting),
        };
        String dienVien[] ={
                getString(R.string.dv_gieogigatnay),
                getString(R.string.dv_runningman),
                getString(R.string.dv_familyouting),
        };
        String ngayPhatHanh[] = {
                getString(R.string.rel_gieogigatnay),
                getString(R.string.rel_runningman),
                getString(R.string.rel_familyouting),
        };
        int thoiLuong[] = {
                getResources().getInteger(R.integer.time_gieogigatnay),
                getResources().getInteger(R.integer.time_runningman),
                getResources().getInteger(R.integer.time_familyouting),
        };
        int resource_video [] = {
                R.raw.tvshow_gieogigatnay,
                R.raw.tvshow_runningman,
                R.raw.tvshow_familyouting,
        };
        mlist_tvshow = new ArrayList<>();
        for(int i=0;i<img.length;i++){
            mlist_tvshow.add(new Phim(img[i],tenPhim[i],moTa[i],dienVien[i],ngayPhatHanh[i],thoiLuong[i],resource_video[i]));
        }

    }
}