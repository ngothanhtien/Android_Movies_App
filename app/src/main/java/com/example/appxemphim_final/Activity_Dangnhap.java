package com.example.appxemphim_final;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Activity_Dangnhap extends AppCompatActivity {

    private EditText edt_username;
    private EditText edt_password;
    private ImageButton btn_img_eyes;
    private Button bt_login;
    private boolean isPasswordVisible = true;
    private TextView txt_register, txt_information;
    private TextView txt_pass;
    private TextView txt_quenMK;
    private QuanLyTaiKhoan accountManager = QuanLyTaiKhoan.getInstance();
    private int count_code = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangnhap);
        // ánh xạ các thành phần trong layout
        bt_login = findViewById(R.id.bt_login);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_img_eyes = findViewById(R.id.imageButton);
        txt_register = findViewById(R.id.txt_register);
        txt_information = findViewById(R.id.txt_information);
        txt_pass = findViewById(R.id.txt_pass);
        txt_quenMK = findViewById(R.id.txt_quenMK);

        // Tài khoản có sẵn
        TaiKhoan myAccount = new TaiKhoan("thanhtien","14062004","0905125994","PhuYen, VietNam","ngothanhtien1406@gmail.com","140604");
        if (!accountManager.isAccountExist(myAccount.getUserName())) {
            accountManager.addAccount(myAccount);
        }
        // bắt sự kiện nút đăng nhập
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_current = edt_username.getText().toString();
                String password_current = edt_password.getText().toString();
                if(username_current.equals("") || password_current.equals("")){
                    if(username_current.equals("")){
                        shakeView(edt_username);
                        edt_username.setError("Vui lòng nhập tên đăng nhập!");
                        edt_username.requestFocus();
                    }else if(password_current.equals("")){
                        shakeView(edt_password);
                        edt_password.setError("Vui lòng nhập mật khẩu!");
                        edt_password.requestFocus();
                    }
                } else if(accountManager.isAccountExist(username_current)){
                    TaiKhoan account = accountManager.getAccount(username_current);
                    if(account!=null && account.getPassword().equals(password_current)){
                        AlertDialog.Builder alert_dangnhap = new AlertDialog.Builder(Activity_Dangnhap.this);
                        alert_dangnhap.setTitle("Thông báo");
                        alert_dangnhap.setIcon(R.drawable.icon_notify);
                        alert_dangnhap.setMessage("Đăng nhập thành công!");
                        alert_dangnhap.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Activity_Dangnhap.this, MainActivity.class);
                                intent.putExtra("username",username_current);
                                startActivity(intent);
                                edt_password.setText("");
                            }
                        });
                        alert_dangnhap.show();
                    }else{
                        shakeView(edt_password);
                        edt_password.setError("Mật khẩu không chính xác!");
                        edt_password.requestFocus();
                    }
                } else{
                    shakeView(edt_username);
                    edt_username.setError("Tài khoản không tồn tại!");
                    edt_username.requestFocus();
                }
            }
        });

        txt_quenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alBuilder_quenMK = new AlertDialog.Builder(Activity_Dangnhap.this);
                alBuilder_quenMK.setIcon(R.drawable.icon_account_manage);
                alBuilder_quenMK.setTitle("Nhập Gmail của tài khoản cần tìm");

                LinearLayout layout = new LinearLayout(Activity_Dangnhap.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(50, 40, 50, 40);

                EditText edt_email_check = new EditText(Activity_Dangnhap.this);
                edt_email_check.setHint("Nhập email");
                edt_email_check.setTextColor(ContextCompat.getColor(Activity_Dangnhap.this,R.color.white));
                edt_email_check.setHintTextColor(ContextCompat.getColor(Activity_Dangnhap.this,R.color.text_hint));
                edt_email_check.setBackgroundResource(R.drawable.custom_edit);
                edt_email_check.setTextSize(20);
                edt_email_check.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_user_white,0,0,0);
                edt_email_check.setPadding(24,20,24,20);
                edt_email_check.setCompoundDrawablePadding(10);
                edt_email_check.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                layout.addView(edt_email_check);

                alBuilder_quenMK.setView(layout);
                alBuilder_quenMK.setPositiveButton("Tìm",null);
                alBuilder_quenMK.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog_quenMk = alBuilder_quenMK.create();
                dialog_quenMk.show();

                dialog_quenMk.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email_check = edt_email_check.getText().toString();
                        count_code = 0;
                        if(edt_email_check.getText().toString().equals("")){
                            shakeView(edt_email_check);
                            edt_email_check.setError("Vui lòng nhập email!");
                            edt_email_check.requestFocus();
                        }else if(!accountManager.isGmailExist(email_check)){
                            shakeView(edt_email_check);
                            edt_email_check.setError("Email không tồn tại trong danh sách!");
                            edt_email_check.requestFocus();
                        }else {
                            if (accountManager.ischeckTrangThai(email_check) == false) {
                                AlertDialog.Builder alert_block = new AlertDialog.Builder(Activity_Dangnhap.this);
                                alert_block.setIcon(R.drawable.icon_cam);
                                alert_block.setTitle("Tài khoản này đã bị vô hiệu hóa việc tìm kiếm!!");
                                alert_block.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        dialog_quenMk.dismiss();
                                    }
                                });
                                alert_block.show();
                            } else {
                                AlertDialog.Builder alBuilder_code = new AlertDialog.Builder(Activity_Dangnhap.this);
                                alBuilder_code.setIcon(R.drawable.icon_confirm_number);
                                alBuilder_code.setTitle("Nhập mã xác nhận");

                                LinearLayout layout = new LinearLayout(Activity_Dangnhap.this);
                                layout.setOrientation(LinearLayout.VERTICAL);
                                layout.setPadding(50, 40, 50, 40);

                                EditText edt_code = new EditText(Activity_Dangnhap.this);
                                edt_code.setHint("Nhập mã code");
                                edt_code.setTextColor(ContextCompat.getColor(Activity_Dangnhap.this,R.color.white));
                                edt_code.setHintTextColor(ContextCompat.getColor(Activity_Dangnhap.this,R.color.text_hint));
                                edt_code.setBackgroundResource(R.drawable.custom_edit);
                                edt_code.setTextSize(20);
                                edt_code.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_user_white,0,0,0);
                                edt_code.setPadding(24,20,24,20);
                                edt_code.setCompoundDrawablePadding(10);
                                edt_code.setInputType(InputType.TYPE_CLASS_NUMBER);
                                layout.addView(edt_code);

                                alBuilder_code.setView(layout);
                                alBuilder_code.setPositiveButton("Xác Nhận", null);
                                alBuilder_code.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });

                                AlertDialog dialog = alBuilder_code.create();
                                dialog.show();

                                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String code_check = edt_code.getText().toString();
                                        if (count_code == 3) {
                                            accountManager.ischangeTrangThai(email_check);
                                            AlertDialog.Builder alert_limit = new AlertDialog.Builder(Activity_Dangnhap.this);
                                            alert_limit.setIcon(R.drawable.icon_cam);
                                            alert_limit.setTitle("Giới hạn thay đổi");
                                            alert_limit.setMessage("Bạn đã nhập sai mã code quá 3 lần!!");
                                            alert_limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                    dialog.dismiss();
                                                    dialog_quenMk.dismiss();
                                                }
                                            });
                                            alert_limit.show();
                                        } else {
                                            if (!accountManager.isCodeForEmailExist(email_check, code_check)) {
                                                shakeView(edt_code);
                                                edt_code.setError("Mã code không chính xác!\nNhập sai 3 lần sẽ bị vô hiệu hóa tìm kiếm tài khoản!");
                                                edt_code.requestFocus();
                                                count_code++;
                                            } else {
                                                TaiKhoan account = accountManager.getAccountGmail(email_check);
                                                AlertDialog.Builder alert_success = new AlertDialog.Builder(Activity_Dangnhap.this);
                                                alert_success.setTitle("Thành Công");
                                                alert_success.setIcon(R.drawable.icon_done_black);
                                                alert_success.setMessage("Thông tin tài khoản của gmail: " + email_check
                                                        + "\nTên đăng nhập: " + account.getUserName() + "\nMật khẩu: " + account.getPassword());
                                                alert_success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                        dialog_quenMk.dismiss();
                                                        dialog.dismiss();
                                                    }
                                                });
                                                alert_success.show();
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
        // bắt sự kiện img_button_eyes
        btn_img_eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiMatKhau();
            }
        });
        txt_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Dangnhap.this, Activity_Khachvanglai.class);
                startActivity(intent);
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Dangnhap.this, Activity_Dangky.class);
                startActivity(intent);
            }
        });
        // bắt sự kiện khi nhất vào txt_information
        txt_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(Activity_Dangnhap.this);
                alertdialog.setTitle("INFORMATION");
                alertdialog.setIcon(R.drawable.icon_person);
                alertdialog.setMessage("Name                : NGÔ THÀNH TIẾN\nEmail                 : ngothanhtien1406@gmail.com\nPhone Number: 0905125***\nAdress              : Việt Nam");
                alertdialog.show();
            }
        });
    }
    // phương thức làm rung thanh edt khi chưa nhập thông tin hoặc nhập sai
    public void shakeView(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, 5, -5, 5, -5, 5, -5, 0);
        animator.setDuration(300);
        animator.setInterpolator(new CycleInterpolator(3));
        animator.start();
    }
    public void hienThiMatKhau(){
        // Lưu lại trạng thái hiện tại của truòng mật khẩu
        int cursorPosition = edt_password.getSelectionStart();
        Typeface typeFace = edt_password.getTypeface();
        if (isPasswordVisible) {
            //HIỂN THỊ MẬT KHẨU
            edt_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            btn_img_eyes.setImageResource(R.drawable.icon_eye_on);
            isPasswordVisible = false;
        } else {
            //ẨN MẬT KHẨU
            edt_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            btn_img_eyes.setImageResource(R.drawable.icon_eye_off);
            isPasswordVisible = true;
        }
        edt_password.setTypeface(typeFace);
        edt_password.setSelection(cursorPosition);
    }
}