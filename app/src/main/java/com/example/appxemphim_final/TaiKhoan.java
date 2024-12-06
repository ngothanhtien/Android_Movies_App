package com.example.appxemphim_final;

public class TaiKhoan {
    private String userName;
    private String password;
    private String sdt;
    private String diaChi;
    private String gmail;
    private String code;
    private boolean trangThai;

    public TaiKhoan(String userName, String password, String sdt, String diaChi, String gmail, String code) {
        this.userName = userName;
        this.password = password;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.gmail = gmail;
        this.code = code;
        this.trangThai = true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}

