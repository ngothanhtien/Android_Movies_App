package com.example.appxemphim_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class Fragment_Trangchu extends Fragment {
    private RecyclerView recyclerView_trending,recyclerView_newPhim;
    private AddapterRecyclerViewFor_homeFr addapterRecyclerView,addapterRecyclerView_newPhim;
    private List<Phim> mlist_trenDing,mlist_newPhim,mlist_photo_random;
    private ViewPager viewPager;
    private AddapterViewpager photoAddapter;
    private CircleIndicator circleIndicator;
    private Handler hanlder;
    private Runnable runnable;
    private int currentItem=0;
    private ImageButton btn_find,btn_favorite,btn_notify;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu_, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // **Phần 1: xử lý sự kiện cho ViewPager
        // ánh xạ
        viewPager = view.findViewById(R.id.viewPager_imgAutoChange);
        circleIndicator = view.findViewById(R.id.circle_Indicator);
        btn_find = view.findViewById(R.id.btn_find_movies);
        btn_favorite = view.findViewById(R.id.btn_favorite);
        btn_notify = view.findViewById(R.id.btn_notify);

        btn_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_thongbao.class);
                startActivity(intent);
            }
        });
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_Timkiemphim.class);
                startActivity(intent);
            }
        });
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_Phimyeuthich.class);
                startActivity(intent);
            }
        });

        // khởi tạo adapter cho viewPager và truyền dữ liệu cho addapter
        dataListPhoto();
        photoAddapter = new AddapterViewpager(getContext(), new OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Activity_Thongtinphim.class);
                intent.putExtra("img",mlist_photo_random.get(position).getResource_img());
                intent.putExtra("tenPhim",mlist_photo_random.get(position).getTenPhim());
                intent.putExtra("moTa",mlist_photo_random.get(position).getMoTa());
                intent.putExtra("dienVien",mlist_photo_random.get(position).getDienVien());
                intent.putExtra("ngayPhatHanh",mlist_photo_random.get(position).getNgayPhatHanh());
                intent.putExtra("thoiLuong",mlist_photo_random.get(position).getThoiLuong());
                intent.putExtra("resource_video",mlist_photo_random.get(position).getResource_video());
                startActivity(intent);
            }
        });
        photoAddapter.setData(mlist_photo_random);
        viewPager.setAdapter(photoAddapter);

        circleIndicator.setViewPager(viewPager);
        photoAddapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        auToChangeImage();
        // khởi ta dữ liệu
        dataList_trenDingPhim();
        dataList_newPhim();

        // ánh xạ 2 thanh recyclerView
        recyclerView_trending = view.findViewById(R.id.recyclerView);
        recyclerView_newPhim = view.findViewById(R.id.recyclerView_newPhim);

        // xếp theo chiều ngang nếu muốn đổi thì xử dụng GridLayoutManager(getContext(),2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView_trending.setLayoutManager(layoutManager);

        LinearLayoutManager newMoviesLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false); // 2 cột
        recyclerView_newPhim.setLayoutManager(newMoviesLayoutManager);

        // khởi tạo adapter cho recyclerView_trending
        addapterRecyclerView = new AddapterRecyclerViewFor_homeFr(getContext(), new OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Activity_Thongtinphim.class);
                intent.putExtra("img",mlist_trenDing.get(position).getResource_img());
                intent.putExtra("tenPhim",mlist_trenDing.get(position).getTenPhim());
                intent.putExtra("moTa",mlist_trenDing.get(position).getMoTa());
                intent.putExtra("dienVien",mlist_trenDing.get(position).getDienVien());
                intent.putExtra("ngayPhatHanh",mlist_trenDing.get(position).getNgayPhatHanh());
                intent.putExtra("thoiLuong",mlist_trenDing.get(position).getThoiLuong());
                intent.putExtra("resource_video",mlist_trenDing.get(position).getResource_video());
                startActivity(intent);
            }
        });
        addapterRecyclerView.setData(mlist_trenDing);
        recyclerView_trending.setAdapter(addapterRecyclerView);
        // khởi tạo adapter cho recyclerView_newPhim
        addapterRecyclerView_newPhim = new AddapterRecyclerViewFor_homeFr(getContext(), new OnItemClicklistener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Activity_Thongtinphim.class);
                intent.putExtra("img",mlist_newPhim.get(position).getResource_img());
                intent.putExtra("tenPhim",mlist_newPhim.get(position).getTenPhim());
                intent.putExtra("moTa",mlist_newPhim.get(position).getMoTa());
                intent.putExtra("dienVien",mlist_newPhim.get(position).getDienVien());
                intent.putExtra("ngayPhatHanh",mlist_newPhim.get(position).getNgayPhatHanh());
                intent.putExtra("thoiLuong",mlist_newPhim.get(position).getThoiLuong());
                intent.putExtra("resource_video",mlist_newPhim.get(position).getResource_video());
                startActivity(intent);
            }
        });
        addapterRecyclerView_newPhim.setData(mlist_newPhim);
        recyclerView_newPhim.setAdapter(addapterRecyclerView_newPhim);
    }

    // hàm dữ liệu list ảnh cho phim trending
    private void dataList_trenDingPhim() {
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
                "ShawShank",
                "Money Heist",
                "Fast & Furious 6",
                "SquidGame",
                "Kẻ Giám Hộ"
        };
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
        int resource_video[] = {
                R.raw.movies_aquaman,
                R.raw.movies_fast7,
                R.raw.movies_shawhank,
                R.raw.movies_series_moneyheist,
                R.raw.movies_fast6,
                R.raw.movies_series_squidgame,
                R.raw.movies_kegiamho
        };
        mlist_trenDing = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            mlist_trenDing.add(new Phim(img[i], tenPhim[i],moTa[i],dienVien[i],ngayPhatHanh[i],thoiLuong[i],resource_video[i]));
        }
    }

    // Hàm dữ liệu list ảnh cho new movies
    private void dataList_newPhim() {
        int img[] = {
                R.drawable.movies_fast8,
                R.drawable.movies_fastx,
                R.drawable.movies_sr_squidgame,
                R.drawable.movies_fast9,
                R.drawable.movies_kegiamho
        };
        String tenPhim[] = {
                "Fast & Furious 8",
                "Fast & Furious X",
                "SquidGame",
                "Fast & Furious 9",
                "Kẻ Giám Hộ"};
        String moTa[] = {
                getString(R.string.mota_fast8),
                getString(R.string.mota_fastx),
                getString(R.string.mota_squidgame),
                getString(R.string.mota_fast9),
                getString(R.string.mota_kegiamho)
        };
        String dienVien[] ={
                getString(R.string.dv_fast8),
                getString(R.string.dv_fastx),
                getString(R.string.dv_squidgame),
                getString(R.string.dv_fast9),
                getString(R.string.dv_kegiamho)
        };
        String ngayPhatHanh[] ={
                getString(R.string.rel_fast8),
                getString(R.string.rel_fastx),
                getString(R.string.rel_squidgame),
                getString(R.string.rel_fast9),
                getString(R.string.rel_kegiamho)
        };
        int thoiLuong[]={
                getResources().getInteger(R.integer.time_fast8),
                getResources().getInteger(R.integer.time_fastx),
                getResources().getInteger(R.integer.time_squidgame),
                getResources().getInteger(R.integer.time_fast9),
                getResources().getInteger(R.integer.time_kegiamho)
        };
        int resource_video[] = {
                R.raw.movies_fast8,
                R.raw.movies_fastx,
                R.raw.movies_series_squidgame,
                R.raw.movies_fast9,
                R.raw.movies_kegiamho
        };
        mlist_newPhim = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            mlist_newPhim.add(new Phim(img[i], tenPhim[i],moTa[i],dienVien[i],ngayPhatHanh[i],thoiLuong[i],resource_video[i]));
        }
    }

    // hàm dữ liệu cho list ảnh của ViewPager
    private void dataListPhoto(){
        int img[] = {
                R.drawable.movies_aquaman,
                R.drawable.movies_fastx,
                R.drawable.tvshow_runningman,
                R.drawable.movies_sr_moneyheist,
                R.drawable.movies_shawshank,
                R.drawable.tvshow_gieogigatnay
        };
        String tenPhim[] = {"Aquaman", "Fast and Furious X", "Running Man","Money Heist","Shawshank","Gieo gì gặt nấy"};
        String moTa[] = {
                getString(R.string.mota_aquaman),
                getString(R.string.mota_fastx),
                getString(R.string.mota_runningman),
                getString(R.string.mota_moneyheist),
                getString(R.string.mota_shawshank),
                getString(R.string.mota_gieogigatnay)
        };
        String dienVien[] ={
                getString(R.string.dv_aquaman),
                getString(R.string.dv_fastx),
                getString(R.string.dv_runningman),
                getString(R.string.dv_moneyheist),
                getString(R.string.dv_shawshank),
                getString(R.string.dv_gieogigatnay)
        };
        String ngayPhatHanh[] = {
                getString(R.string.rel_aquaman),
                getString(R.string.rel_fastx),
                getString(R.string.rel_runningman),
                getString(R.string.rel_moneyheist),
                getString(R.string.rel_shawshank),
                getString(R.string.rel_gieogigatnay)
        };
        int thoiLuong[] = {
                getResources().getInteger(R.integer.time_aquaman),
                getResources().getInteger(R.integer.time_fastx),
                getResources().getInteger(R.integer.time_runningman),
                getResources().getInteger(R.integer.time_moneyheist),
                getResources().getInteger(R.integer.time_shawshank),
                getResources().getInteger(R.integer.time_gieogigatnay)
        };
        int resource_video[] = {
                R.raw.movies_aquaman,
                R.raw.movies_fastx,
                R.raw.tvshow_runningman,
                R.raw.movies_series_moneyheist,
                R.raw.movies_shawhank,
                R.raw.tvshow_gieogigatnay
        };
        mlist_photo_random = new ArrayList<>();
        for (int i =0;i<img.length;i++){
            mlist_photo_random.add(new Phim(img[i],tenPhim[i],moTa[i],dienVien[i],ngayPhatHanh[i],thoiLuong[i],resource_video[i]));
        }
    }
    // hàm tự động chuyển ảnh
    private void auToChangeImage(){
        hanlder = new Handler();
        if(mlist_photo_random==null || mlist_photo_random.isEmpty() || viewPager==null){
            return;
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                if(currentItem == mlist_photo_random.size()){
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem++,true);
                hanlder.postDelayed(this,7000);
            }
        };
        hanlder.postDelayed(runnable,7000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(hanlder!=null && runnable !=null){
            hanlder.removeCallbacks(runnable);
        }
    }
}