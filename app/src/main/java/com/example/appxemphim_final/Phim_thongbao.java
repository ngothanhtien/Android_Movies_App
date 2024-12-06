package com.example.appxemphim_final;

public class Phim_thongbao {
        private int resource_img;
        private String tenPhim;
        private String theLoai;
        private String ngayPhatHanh;

    public Phim_thongbao(int resource_img, String tenPhim, String theLoai, String ngayPhatHanh) {
        this.resource_img = resource_img;
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public int getResource_img() {
        return resource_img;
    }

    public void setResource_img(int resource_img) {
        this.resource_img = resource_img;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }
}
