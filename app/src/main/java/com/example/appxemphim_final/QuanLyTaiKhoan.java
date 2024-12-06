package com.example.appxemphim_final;

import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiKhoan {
    private static QuanLyTaiKhoan instance;
    private List<TaiKhoan> accountList;
    private QuanLyTaiKhoan() {
        // Khởi tạo danh sách tài khoản
        accountList = new ArrayList<>();
    }
    public static QuanLyTaiKhoan getInstance() {
        if (instance == null) {
            instance = new QuanLyTaiKhoan();
        }
        return instance;
    }
    // Kiểm tra tài khoản đã tồn tại chưa
    public boolean isAccountExist(String username) {
        for (TaiKhoan acc : accountList) {
            if (acc.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }
    // Kiểm tra email đã được dùng để tạo chưa
    public boolean isGmailExist(String gmail) {
        for (TaiKhoan acc : accountList) {
            if (acc.getGmail().equals(gmail)) {
                return true;
            }
        }
        return false;
    }
    // Kiểm tra mã code có trùng với gmail hiện tại không
    public boolean isCodeForEmailExist(String email,String code) {
        for (TaiKhoan acc : accountList) {
            if (acc.getGmail().equals(email) && acc.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
    // Thêm tài khoản mới
    public boolean addAccount(TaiKhoan account) {
        if (!isAccountExist(account.getUserName())) {
            accountList.add(account);
            return true;
        }
        return false;
    }
    // thay trang thai
    public boolean ischangeTrangThai(String email) {
        for(TaiKhoan acc : accountList){
            if(acc.getGmail().equals(email)){
                acc.setTrangThai(!acc.isTrangThai());
                return true;
            }
        }
        return false;
    }
    public boolean ischeckTrangThai(String email) {
        for(TaiKhoan acc : accountList){
            if(acc.getGmail().equals(email)){
                return acc.isTrangThai();
            }
        }
        return false;
    }
    // lấy ac bằng tài khoản
    public TaiKhoan getAccount(String username){
        for (TaiKhoan acc : accountList) {
            if (acc.getUserName().equals(username)) {
                return acc;
            }
        }
        return null;
    }
    // lấy ac bằng gmail
    public TaiKhoan getAccountGmail(String gmail){
        for (TaiKhoan acc : accountList) {
            if (acc.getGmail().equals(gmail)) {
                return acc;
            }
        }
        return null;
    }
    public void deleteAccount(String username) {
        for (TaiKhoan acc : accountList) {
            if (acc.getUserName().equals(username)) {
                accountList.remove(acc);
            }
        }
    }

}
