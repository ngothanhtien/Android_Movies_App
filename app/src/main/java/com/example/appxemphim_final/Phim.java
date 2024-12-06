package com.example.appxemphim_final;

public class Phim {
    private int resource_img;
    private String tenPhim;
    private String moTa;
    private String dienVien;
    private String ngayPhatHanh;
    private int thoiLuong;
    private int resource_video;

    public Phim(int resource_img, String tenPhim, String moTa, String dienVien, String ngayPhatHanh, int thoiLuong, int resource_video) {
        this.resource_img = resource_img;
        this.tenPhim = tenPhim;
        this.moTa = moTa;
        this.dienVien = dienVien;
        this.ngayPhatHanh = ngayPhatHanh;
        this.thoiLuong = thoiLuong;
        this.resource_video = resource_video;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDienVien() {
        return dienVien;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }

    public String getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public int getResource_video() {
        return resource_video;
    }

    public void setResource_video(int resource_video) {
        this.resource_video = resource_video;
    }
}
