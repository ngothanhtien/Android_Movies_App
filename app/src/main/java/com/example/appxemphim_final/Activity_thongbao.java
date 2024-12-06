package com.example.appxemphim_final;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity_thongbao extends AppCompatActivity {
    private AddapterRecyclerViewFor_thongbao addapterRecyclerViewForThongbaophimmoi,addapterRecyclerViewForThongbaosapra;
    private RecyclerView recyclerView_phimmoi,recyclerView_phimsapra;
    private ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbao);
        EdgeToEdge.enable(this);
        // ánh xạ
        recyclerView_phimmoi = findViewById(R.id.rcv_thongbao_phimmoi);
        recyclerView_phimsapra = findViewById(R.id.rcv_thongbao_sapra);
        btn_back = findViewById(R.id.btn_back_thongbao);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView_phimmoi.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView_phimsapra.setLayoutManager(layoutManager2);

        addapterRecyclerViewForThongbaophimmoi = new AddapterRecyclerViewFor_thongbao(Activity_thongbao.this);
        addapterRecyclerViewForThongbaophimmoi.setData(getDaTaMovie_phimmoi());
        recyclerView_phimmoi.setAdapter(addapterRecyclerViewForThongbaophimmoi);

        addapterRecyclerViewForThongbaosapra = new AddapterRecyclerViewFor_thongbao(Activity_thongbao.this);
        addapterRecyclerViewForThongbaosapra.setData(getDaTaMovie_sapra());
        recyclerView_phimsapra.setAdapter(addapterRecyclerViewForThongbaosapra);

    }
    public List<Phim_thongbao> getDaTaMovie_phimmoi() {
        List<Phim_thongbao> mlist_phimmoi = new ArrayList<>();
        int img[] = {
                R.drawable.phimmoira_cuoixuyenbiengioi,
                R.drawable.phimmoira_doibanhocyeu,
                R.drawable.phimmoira_robothoangda,
                R.drawable.phimmoira_venomkeocuoi,
                R.drawable.phimmoira_vosigiacdau2
        };
        String tenPhim[] = {
                "Cười Xuyên Biên Giới",
                "Đôi Bạn Học Yêu",
                "Robot Hoang Dã",
                "Venom: Kèo cuối",
                "Võ Sĩ Giác Đầu II"
        };
        String theLoai [] = {
                "Hài Hước",
                "Tình Cảm",
                "Hoạt Hình",
                "Hành động",
                "Thần Thoại"
        };
        String ngayPhatHanh[] = {
                "15-11-2024",
                "08-11-2024",
                "11-10-2024",
                "25-10-2024",
                "15-11-2024"
        };
        for(int i=0;i<img.length;i++){
            mlist_phimmoi.add(new Phim_thongbao(img[i],tenPhim[i],theLoai[i],ngayPhatHanh[i]));
        }

        return mlist_phimmoi;
    }
    public List<Phim_thongbao> getDaTaMovie_sapra() {
        List<Phim_thongbao> mlist_phimsapra = new ArrayList<>();
        int img[] = {
                R.drawable.phimsapra_congtubaclieu,
                R.drawable.phimsapra_linhmieu,
                R.drawable.phimsapra_vuasutu,
                R.drawable.phimsapra_wicked,
                R.drawable.phimsapra_nhimsonic3
        };
        String tenPhim[] = {
                "Công Tử Bạc Liêu",
                "Linh Miêu",
                "Vua Sư Tử",
                "Wicked",
                "Nhím Sonic III"
        };
        String theLoai [] = {
                "Hài, Tâm lý",
                "Kinh Dị",
                "Gia Đình, Phiêu Lưu",
                "Nhạc Kịch, Thần Thoại",
                "Hài Hước,Gia Đình"
        };
        String ngayPhatHanh[] = {
                "06-12-2024",
                "22-11-2024",
                "20-12-2024",
                "22-11-2024",
                "27-12-2024"
        };
        for(int i=0;i<img.length;i++){
            mlist_phimsapra.add(new Phim_thongbao(img[i],tenPhim[i],theLoai[i],ngayPhatHanh[i]));
        }
        return mlist_phimsapra;
    }
}