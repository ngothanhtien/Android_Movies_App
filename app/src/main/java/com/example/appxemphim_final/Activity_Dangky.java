package com.example.appxemphim_final;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Dangky extends AppCompatActivity {
    private  Button btn_Register;
    private  TextView txt_login;
    private EditText edt_usename;
    private EditText edt_password;
    private EditText edt_CFpassword;
    private EditText edt_email;
    private EditText edt_code;
    private boolean isPasswordVisible = true;
    private ImageButton btn_img_eyes_MK;
    private ImageButton btn_img_eyes_MKCF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangky);
        // ánh xạ
        btn_Register = findViewById(R.id.btn_Register);
        txt_login = findViewById(R.id.txt_login);
        edt_usename = findViewById(R.id.edt_usename);
        edt_password = findViewById(R.id.edt_password);
        edt_CFpassword = findViewById(R.id.edt_CFpassword);
        edt_email = findViewById(R.id.edt_Gmail);
        edt_code = findViewById(R.id.edt_Code);
        btn_img_eyes_MK = findViewById(R.id.btn_img_eyes_MK);
        btn_img_eyes_MKCF = findViewById(R.id.btn_img_eyes_MKCF);
        // bắt sự kiện nút đăng ký
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt_usename.getText().toString();
                String password = edt_password.getText().toString();
                String CFpassword = edt_CFpassword.getText().toString();
                String email = edt_email.getText().toString();
                String code = edt_code.getText().toString();
                int code_int = Integer.parseInt(code);
                QuanLyTaiKhoan accountManager = QuanLyTaiKhoan.getInstance();
                if(username.equals("") || password.equals("") || CFpassword.equals("")){
                    if(username.equals("")){
                        shakeView(edt_usename);
                        edt_usename.setError("Vui lòng nhập tên đăng nhập!");
                        edt_usename.requestFocus();
                        return;
                    }else if(password.equals("")){
                        shakeView(edt_password);
                        edt_password.setError("Vui lòng nhập mật khẩu!");
                        edt_password.requestFocus();
                        return;
                    }else if(CFpassword.equals("")){
                        shakeView(edt_CFpassword);
                        edt_CFpassword.setError("Vui lòng nhập mật khẩu xác nhận!");
                        edt_CFpassword.requestFocus();
                        return;
                    }
                }else if(username.length() >20 || username.length() < 8 || password.length() < 6 || password.length() > 15 || CheckKTDacBiet(username)){
                    if(CheckKTDacBiet(username)){
                        shakeView(edt_usename);
                        edt_usename.setError("Tên đăng nhập không được chứa ký tự đặc biệt!");
                        edt_usename.requestFocus();
                        return;
                    }else if(username.length() >20 || username.length() < 8){
                        shakeView(edt_usename);
                        edt_usename.setError("Tên đăng nhập phải từ 8 đến 20 ký tự!");
                        edt_usename.requestFocus();
                        return;
                    }else if(password.length() < 6 || password.length() > 15){
                        shakeView(edt_password);
                        edt_password.setError("Mật khẩu phải từ 6 đến 15 ký tự!");
                        edt_password.requestFocus();
                        return;
                    }
                }else if(!CFpassword.equals(password)){
                    shakeView(edt_CFpassword);
                    edt_CFpassword.setError("Mật khẩu xác nhận không khớp!");
                    edt_CFpassword.requestFocus();
                    return;
                }else if(edt_email.equals("")){
                    shakeView(edt_email);
                    edt_email.setError("Vui lòng nhập email!");
                    edt_email.requestFocus();
                    return;
                }else if(email.matches(".*[!#$%^&*(),?\":{}|<>].*") || checkKTTA(email)){
                    if(email.matches(".*[!#$%^&*(),?\":{}|<>].*")){
                        shakeView(edt_email);
                        edt_email.setError("Email không được chứa ký tự đặc biệt!");
                        edt_email.requestFocus();
                        return;
                    }else if(checkKTTA(email)){
                        shakeView(edt_email);
                        edt_email.setError("Email không được chứa hơn một ký tự @!");
                        edt_email.requestFocus();
                        return;
                    }
                }else if(!email.endsWith("@gmail.com")){
                    shakeView(edt_email);
                    edt_email.setError("Email phải có đuôi '@gmail.com' !");
                    edt_email.requestFocus();
                    return;
                }else if(accountManager.isGmailExist(email)){
                    shakeView(edt_email);
                    edt_email.setError("Email đã được sử dụng!");
                    edt_email.requestFocus();
                    return;
                }else if(email.length()>30 || email.length()<15){
                    shakeView(edt_email);
                    edt_email.setError("Email phải từ 15 đến 30 ký tự (đã bao gồm @gmail.com)!");
                    edt_email.requestFocus();
                    return;
                }else if(code.length()!=6){
                    shakeView(edt_code);
                    edt_code.setError("Mã khôi phục phải có đúng 6 số!");
                    edt_code.requestFocus();
                    return;
                }else if(code_int==000000){
                    shakeView(edt_code);
                    edt_code.setError("Mã khôi phục không được toàn bộ là 0!");
                    edt_code.requestFocus();
                    return;
                }else{
                    if(accountManager.isAccountExist(username)){
                        shakeView(edt_usename);
                        edt_usename.setError("Tai khoản đã tồn tại!");
                        edt_usename.requestFocus();
                        return;
                    }else{
                        TaiKhoan account = new TaiKhoan(username, password,"","",email,code);
                        if(accountManager.addAccount(account)){
                            edt_usename.setText("");
                            edt_password.setText("");
                            edt_CFpassword.setText("");
                            edt_email.setText("");
                            edt_code.setText("");
                            AlertDialog.Builder alert_dangky = new AlertDialog.Builder(Activity_Dangky.this);
                            alert_dangky.setTitle("Thông báo");
                            alert_dangky.setIcon(R.drawable.icon_notify);
                            alert_dangky.setMessage("Đăng ký thành công!\nUsername: "+username +"\nPassword: "+password+
                                    "\nEmail: "+email+"\nCode: "+code+"\nVui lòng ghi nhớ thông tin tài khoản !!!");
                            alert_dangky.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Activity_Dangky.this, Activity_Dangnhap.class);
                                    startActivity(intent);
                                }
                            });
                            alert_dangky.show();
                        }
                    }
                }
            }
        });
        btn_img_eyes_MK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiMatKhau();
            }
        });
        btn_img_eyes_MKCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiMatKhauCF();
            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Dangky.this, Activity_Dangnhap.class);
                startActivity(intent);
            }
        });
    }
    // làm hàm rung  thanh edit
    public void shakeView(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, 5, -5, 5, -5, 5, -5, 0);
        animator.setDuration(300);
        animator.setInterpolator(new CycleInterpolator(3));
        animator.start();
    }
    // hàm bắt lỗi ký tự đặc biệt
    public boolean CheckKTDacBiet(String username){
        String regex = "^[a-zA-Z0-9]*$";
        return !username.matches(regex);
    }
    public void hienThiMatKhau(){
        // Lưu lại trạng thái hiện tại của truòng mật khẩu
        int cursorPosition = edt_password.getSelectionStart();
        Typeface typeFace = edt_password.getTypeface();
        if (isPasswordVisible) {
            //HIỂN THỊ MẬT KHẨU
            edt_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            btn_img_eyes_MK.setImageResource(R.drawable.icon_eye_on);
            isPasswordVisible = false;
        } else {
            //ẨN MẬT KHẨU
            edt_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            btn_img_eyes_MK.setImageResource(R.drawable.icon_eye_off);
            isPasswordVisible = true;
        }
        edt_password.setTypeface(typeFace);
        edt_password.setSelection(cursorPosition);
    }
    public void hienThiMatKhauCF(){
        // Lưu lại trạng thái hiện tại của truòng mật khẩu
        int cursorPosition = edt_CFpassword.getSelectionStart();
        Typeface typeFace = edt_CFpassword.getTypeface();
        if (isPasswordVisible) {
            //HIỂN THỊ MẬT KHẨU
            edt_CFpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            btn_img_eyes_MKCF.setImageResource(R.drawable.icon_eye_on);
            isPasswordVisible = false;
        } else {
            //ẨN MẬT KHẨU
            edt_CFpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            btn_img_eyes_MKCF.setImageResource(R.drawable.icon_eye_off);
            isPasswordVisible = true;
        }
        edt_CFpassword.setTypeface(typeFace);
        edt_CFpassword.setSelection(cursorPosition);
    }
    public boolean checkKTTA(String gmail){
        int count = 0;
        for(char c: gmail.toCharArray()){
            if(c == '@'){
                count++;
            }
        }
        return count>1;
    }
}