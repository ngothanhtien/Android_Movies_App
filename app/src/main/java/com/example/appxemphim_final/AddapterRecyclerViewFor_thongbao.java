    package com.example.appxemphim_final;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.List;

    import de.hdodenhof.circleimageview.CircleImageView;

    public class AddapterRecyclerViewFor_thongbao extends RecyclerView.Adapter<AddapterRecyclerViewFor_thongbao.myViewHoder> {
        Context context;
        List<Phim_thongbao> mListMovie;

        public AddapterRecyclerViewFor_thongbao(Context context) {
            this.context = context;
        }
        public void setData(List<Phim_thongbao> listPhim){
            this.mListMovie = listPhim;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public myViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_thongbao,parent,false);
            return new myViewHoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHoder holder, int position) {
            Phim_thongbao phim = mListMovie.get(position);
            if(phim == null){
                return;
            }
            holder.img_phim.setImageResource(phim.getResource_img());
            holder.tenPhim.setText(phim.getTenPhim());
            holder.theLoai.setText(phim.getTheLoai());
            holder.ngayRaMat.setText(phim.getNgayPhatHanh());
        }

        @Override
        public int getItemCount() {
            if(mListMovie != null){
                return mListMovie.size();
            }
            return 0;
        }

        public class myViewHoder extends RecyclerView.ViewHolder{
            private ImageView img_phim;
            private TextView tenPhim;
            private TextView theLoai;
            private TextView ngayRaMat;
            public myViewHoder(@NonNull View itemView) {
                super(itemView);
                img_phim = itemView.findViewById(R.id.img_circle_thongbao);
                tenPhim = itemView.findViewById(R.id.txt_tenphim_thongbao);
                theLoai = itemView.findViewById(R.id.txt_theloai_thongbao);
                ngayRaMat = itemView.findViewById(R.id.txt_ramat_thongbao);
            }
        }
    }
