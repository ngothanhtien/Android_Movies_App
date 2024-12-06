package com.example.appxemphim_final;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_Taikhoan extends Fragment {
    private TextView txt_username;
    private TextView txt_password;
    private Button btn_doiTK,btn_doiMK,btn_exit,btn_ThemSoDt,btn_ThemDC,btn_save;
    private TextView txt_sdt,txt_diachi,txt_gmail,txt_code;
    private int countChange_TK = 0,countChange_MK = 0,countChange_sdt = 0,countChange_diachi;
    private boolean checkSave = false,checkEditChange = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan_, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ánh xạ
        btn_doiTK = view.findViewById(R.id.btn_doiTK);
        btn_doiMK = view.findViewById(R.id.btn_doiMK);
        btn_exit = view.findViewById(R.id.btn_exit);
        btn_ThemSoDt = view.findViewById(R.id.btn_ThemSoDt);
        btn_ThemDC = view.findViewById(R.id.btn_ThemDC);
        btn_save = view.findViewById(R.id.btn_saveAccount);

        txt_username = view.findViewById(R.id.txt_username);
        txt_password = view.findViewById(R.id.txt_password);
        txt_sdt = view.findViewById(R.id.txt_sdt);
        txt_diachi = view.findViewById(R.id.txt_diachi);
        txt_gmail = view.findViewById(R.id.txt_gmail);
        txt_code = view.findViewById(R.id.txt_code);

        // lấy thông tin tài khoản
        Bundle bundle = getArguments();
        String username = "";
        if (bundle != null) {
            username = bundle.getString("username");
        }
        // khai báo accountManager và tạo 1 tài khoản để lấy thông tin từ tên đăng nhập
        QuanLyTaiKhoan accountManager = QuanLyTaiKhoan.getInstance();
        TaiKhoan account = accountManager.getAccount(username);
        if(account == null){
            return;
        }
        // day thong tin len cac text
        txt_username.setText(account.getUserName());
        txt_password.setText(account.getPassword());
        txt_sdt.setText(account.getSdt());
        txt_diachi.setText(account.getDiaChi());
        txt_gmail.setText(account.getGmail());
        txt_code.setText(account.getCode());

        final String finalUsername = username;
        final String finalPassword = account.getPassword();
        final String finalSdt = account.getSdt();
        final String finalDiaChi = account.getDiaChi();
        final String finalGmail = account.getGmail();
        final String finalCode = account.getCode();

        btn_doiTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countChange_TK >=3){
                    // Thông báo người dùng chỉ có thể thay đổi tài khoản một lần
                    AlertDialog.Builder alert_limit = new AlertDialog.Builder(getActivity());
                    alert_limit.setIcon(R.drawable.icon_cam);
                    alert_limit.setTitle("Giới hạn thay đổi");
                    alert_limit.setMessage("Bạn chỉ được phép thay đổi tên tài khoản ba lần!");
                    alert_limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert_limit.show();
                }else{
                AlertDialog.Builder alert_doiTK = new AlertDialog.Builder(getActivity());
                alert_doiTK.setIcon(R.drawable.icon_change);
                alert_doiTK.setTitle("Thay đổi tên tài khoản!");

                // tạo 1 thanh input để truyền vào
                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(80, 20, 80, 20);

                EditText edt_TK = new EditText(getActivity());
                edt_TK.setHint("Nhập tên tài khoản mới");
                edt_TK.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
                edt_TK.setHintTextColor(ContextCompat.getColor(getActivity(),R.color.text_hint));
                edt_TK.setBackgroundResource(R.drawable.custom_edit);
                edt_TK.setTextSize(20);
                edt_TK.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_user_white,0,0,0);
                edt_TK.setPadding(24,20,24,20);
                edt_TK.setCompoundDrawablePadding(10);
                edt_TK.setInputType(InputType.TYPE_CLASS_TEXT);
                layout.addView(edt_TK);

                alert_doiTK.setView(layout);
                alert_doiTK.setPositiveButton("Thay đổi",null);
                alert_doiTK.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        countChange_TK=0;
                    }
                });
                AlertDialog dialog = alert_doiTK.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String newusername = edt_TK.getText().toString();
                        if(newusername.equals("") || CheckKTDacBiet(newusername)){
                            if(newusername.equals("")){
                                shakeView(edt_TK);
                                edt_TK.setError("Vui lòng nhập tên tài khoản mới!");
                                edt_TK.requestFocus();
                            }else if(CheckKTDacBiet(newusername)){
                                shakeView(edt_TK);
                                edt_TK.setError("Tên tài khoản không được chứa ký tự đặc biệt!");
                                edt_TK.requestFocus();
                            }
                            return;
                        }else if(newusername.length()>20 || newusername.length() < 8  ){
                            shakeView(edt_TK);
                            edt_TK.setError("Tài khoản phải từ 8-20 ký tự!");
                            edt_TK.requestFocus();
                            return;
                        }else if(accountManager.isAccountExist(newusername)){
                            shakeView(edt_TK);
                            edt_TK.setError("Tài khoản đã tồn tại!");
                            edt_TK.requestFocus();
                            return;
                        }else{
                            checkEditChange = true;
                            txt_username.setText(newusername);
                            dialog.dismiss();
                            AlertDialog.Builder alert_success = new AlertDialog.Builder(getActivity());
                            alert_success.setTitle("Thành Công");
                            alert_success.setIcon(R.drawable.icon_done_black);
                            alert_success.setMessage("Tên tài khoản đã đưọc thay đổi thành công!");
                            alert_success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            alert_success.show();
                            countChange_TK++;
                        }
                    }
                });
                }
            }
        });
        btn_doiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countChange_MK >=3){
                    // Thông báo người dùng chỉ có thể thay đổi mật khẩu một lần
                    AlertDialog.Builder alert_limit = new AlertDialog.Builder(getActivity());
                    alert_limit.setIcon(R.drawable.icon_cam);
                    alert_limit.setTitle("Giới hạn thay đổi");
                    alert_limit.setMessage("Bạn chỉ được phép thay đổi mật khẩu ba lần!");
                    alert_limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert_limit.show();
                }else {
                    AlertDialog.Builder alert_doiMK = new AlertDialog.Builder(getActivity());
                    alert_doiMK.setIcon(R.drawable.icon_change);
                    alert_doiMK.setTitle("Thay đổi mật khẩu!");

                    // tạo layout chứa 2 edt
                    LinearLayout layout = new LinearLayout(getActivity());
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.setPadding(80, 20, 80, 20);

                    // tạo 1 thanh input để truyền vào
                    EditText edt_oldPassword = new EditText(getActivity());
                    edt_oldPassword.setHint("Nhập mật khẩu cũ");
                    edt_oldPassword.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
                    edt_oldPassword.setHintTextColor(ContextCompat.getColor(getActivity(),R.color.text_hint));
                    edt_oldPassword.setBackgroundResource(R.drawable.custom_edit);
                    edt_oldPassword.setTextSize(20);
                    edt_oldPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_user_white,0,0,0);
                    edt_oldPassword.setPadding(24,20,24,20);
                    edt_oldPassword.setCompoundDrawablePadding(10);
                    edt_oldPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

                    EditText edt_newPassword = new EditText(getActivity());
                    edt_newPassword.setHint("Nhập mật khẩu mới");
                    edt_newPassword.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
                    edt_newPassword.setHintTextColor(ContextCompat.getColor(getActivity(),R.color.text_hint));
                    edt_newPassword.setBackgroundResource(R.drawable.custom_edit);
                    edt_newPassword.setTextSize(20);
                    edt_newPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_user_white,0,0,0);
                    edt_newPassword.setPadding(24,20,24,20);
                    edt_newPassword.setCompoundDrawablePadding(10);
                    edt_newPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

                    layout.addView(edt_oldPassword);
                    layout.addView(edt_newPassword);

                    alert_doiMK.setView(layout);

                    alert_doiMK.setPositiveButton("Thay đổi", null);
                    alert_doiMK.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            countChange_MK=0;
                        }
                    });
                    AlertDialog dialog = alert_doiMK.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String oldPassword = edt_oldPassword.getText().toString();
                            String newPassword = edt_newPassword.getText().toString();
                            if (oldPassword.equals("") || newPassword.equals("")) {
                                if (oldPassword.equals("")) {
                                    shakeView(edt_oldPassword);
                                    edt_oldPassword.setError("Vui lòng nhập mật khẩu cũ!");
                                    edt_oldPassword.requestFocus();
                                } else if (newPassword.equals("")) {
                                    shakeView(edt_newPassword);
                                    edt_newPassword.setError("Vui lòng nhập mật khẩu mới!");
                                    edt_newPassword.requestFocus();
                                }
                                return;
                            } else if (!oldPassword.equals(finalPassword)) {
                                shakeView(edt_oldPassword);
                                edt_oldPassword.setError("Mật khẩu cũ không chính xác!");
                                edt_oldPassword.requestFocus();
                                return;
                            } else if (newPassword.length() < 6 || newPassword.length() > 15) {
                                shakeView(edt_newPassword);
                                edt_newPassword.setError("Mật khẩu mới phải từ 6-15 ký tự!");
                                edt_newPassword.requestFocus();
                                return;
                            } else if (newPassword.equals(finalPassword)) {
                                shakeView(edt_newPassword);
                                edt_newPassword.setError("Mật khẩu mới không được trùng với mật khẩu cũ!");
                                edt_newPassword.requestFocus();
                                return;
                            } else {
                                checkEditChange = true;
                                txt_password.setText(newPassword);
                                dialog.dismiss();
                                AlertDialog.Builder alert_success = new AlertDialog.Builder(getContext());
                                alert_success.setTitle("Thành Công");
                                alert_success.setIcon(R.drawable.icon_done_black);
                                alert_success.setMessage("Mật khẩu đã đưọc thay đổi thành công!");
                                alert_success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                countChange_MK++;
                                alert_success.show();
                            }
                        }
                    });

                }
            }
        });
        btn_ThemSoDt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countChange_sdt >= 3) {
                    // Thông báo người dùng chỉ có thể thay đổi tài khoản một lần
                    AlertDialog.Builder alert_limit = new AlertDialog.Builder(getActivity());
                    alert_limit.setIcon(R.drawable.icon_cam);
                    alert_limit.setTitle("Giới hạn thay đổi");
                    alert_limit.setMessage("Bạn chỉ được phép thay đổi số điện thoại ba lần!");
                    alert_limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert_limit.show();
                } else {
                    AlertDialog.Builder alert_themsodt = new AlertDialog.Builder(getActivity());
                    alert_themsodt.setIcon(R.drawable.icon_phone_contact);
                    alert_themsodt.setTitle("Thêm số điện thoại liên hệ");

                    LinearLayout layout = new LinearLayout(getActivity());
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.setPadding(70, 20, 70, 20);

                    EditText edt_sdt = new EditText(getActivity());
                    edt_sdt.setHint("Nhập số điện thoại");
                    edt_sdt.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
                    edt_sdt.setHintTextColor(ContextCompat.getColor(getActivity(),R.color.text_hint));
                    edt_sdt.setBackgroundResource(R.drawable.custom_edit);
                    edt_sdt.setTextSize(20);
                    edt_sdt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_user_white,0,0,0);
                    edt_sdt.setPadding(24,20,24,20);
                    edt_sdt.setCompoundDrawablePadding(10);
                    edt_sdt.setInputType(InputType.TYPE_CLASS_PHONE);
                    layout.addView(edt_sdt);

                    alert_themsodt.setView(layout);

                    alert_themsodt.setPositiveButton("Lưu", null);
                    alert_themsodt.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog = alert_themsodt.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String sdt_edt = edt_sdt.getText().toString().trim();
                            String sdt_check = txt_sdt.getText().toString();
                            if (sdt_edt.equals("")) {
                                shakeView(edt_sdt);
                                edt_sdt.setError("Vui lòng nhập số điện thoại!");
                                edt_sdt.requestFocus();
                                return;
                            } else if (sdt_edt.length() != 10) {
                                shakeView(edt_sdt);
                                edt_sdt.setError("Số điện thoại phải có đủ 10 số!");
                                edt_sdt.requestFocus();
                                return;
                            } else if (!sdt_edt.startsWith("0")) {
                                shakeView(edt_sdt);
                                edt_sdt.setError("Số điện thoại phải được bắt đầu bằng 0!");
                                edt_sdt.requestFocus();
                                return;
                            } else if (sdt_check.equals(sdt_edt)) {
                                shakeView(edt_sdt);
                                edt_sdt.setError("Số điện thoại mới không được trùng với số điện thoại cũ!");
                                edt_sdt.requestFocus();
                                return;
                            } else {
                                    checkEditChange = true;
                                    txt_sdt.setText(sdt_edt);
                                    dialog.dismiss();
                                    AlertDialog.Builder alert_success = new AlertDialog.Builder(getActivity());
                                    alert_success.setTitle("Thành Công");
                                    alert_success.setIcon(R.drawable.icon_done_black);
                                    alert_success.setMessage(txt_sdt.getText().toString().equals("") ? "Thêm số điện thoại liên hệ thành công!":"Thay đổi số điện thoại liên hệ thành công!");
                                    alert_success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    alert_success.show();
                                    countChange_sdt++;
                            }
                        }
                    });
                }
            }
        });
        btn_ThemDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countChange_diachi >=3){
                    // Thông báo người dùng chỉ có thể thay đổi tài khoản một lần
                    AlertDialog.Builder alert_limit = new AlertDialog.Builder(getActivity());
                    alert_limit.setIcon(R.drawable.icon_cam);
                    alert_limit.setTitle("Giới hạn thay đổi");
                    alert_limit.setMessage("Bạn chỉ được phép thay đổi địa chỉ ba lần!");
                    alert_limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert_limit.show();
                }else {
                    AlertDialog.Builder alert_themsodt = new AlertDialog.Builder(getActivity());
                    alert_themsodt.setIcon(R.drawable.icon_location);
                    alert_themsodt.setTitle("Thêm địa chỉ liên hệ");

                    LinearLayout layout = new LinearLayout(getActivity());
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.setPadding(90, 20, 90, 20);

                    EditText edt_diachi = new EditText(getActivity());
                    edt_diachi.setHint("Vd: Da Nang,Viet Nam");
                    edt_diachi.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
                    edt_diachi.setHintTextColor(ContextCompat.getColor(getActivity(),R.color.text_hint));
                    edt_diachi.setBackgroundResource(R.drawable.custom_edit);
                    edt_diachi.setTextSize(20);
                    edt_diachi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_user_white,0,0,0);
                    edt_diachi.setPadding(24,20,24,20);
                    edt_diachi.setCompoundDrawablePadding(10);
                    edt_diachi.setInputType(InputType.TYPE_CLASS_TEXT);
                    layout.addView(edt_diachi);

                    alert_themsodt.setView(layout);

                    alert_themsodt.setPositiveButton("Lưu", null);
                    alert_themsodt.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog = alert_themsodt.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String diachi_edt = edt_diachi.getText().toString().trim();
                            String diachi_check = txt_diachi.getText().toString();
                            if (diachi_edt.equals("")) {
                                shakeView(edt_diachi);
                                edt_diachi.setError("Vui lòng nhập địa chỉ!");
                                edt_diachi.requestFocus();
                            } else if (diachi_edt.length() >= 22) {
                                shakeView(edt_diachi);
                                edt_diachi.setError("Địa chỉ quá dài!");
                                edt_diachi.requestFocus();
                            } else if (diachi_check.equals(diachi_edt)) {
                                shakeView(edt_diachi);
                                edt_diachi.setError("Địa chỉ mới không được trùng với địa chỉ hiện tại!");
                                edt_diachi.requestFocus();
                            } else {
                                    checkEditChange = true;
                                    txt_diachi.setText(diachi_edt);
                                    dialog.dismiss();
                                    AlertDialog.Builder alert_success = new AlertDialog.Builder(getActivity());
                                    alert_success.setTitle("Thành Công");
                                    alert_success.setIcon(R.drawable.icon_done_black);
                                    alert_success.setMessage(txt_diachi.getText().toString().equals("")?"Thêm địa chỉ liên hệ thành công!":"Thay đổi địa chỉ liên hệ thành công!");
                                    alert_success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    alert_success.show();
                                    countChange_diachi++;
                            }
                        }
                    });
                }
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newusername = txt_username.getText().toString();
                String newpassword = txt_password.getText().toString();
                String newsdt = txt_sdt.getText().toString();
                String newdiachi = txt_diachi.getText().toString();
                String newgmail = txt_gmail.getText().toString();
                String newcode = txt_code.getText().toString();
                checkSave = true;
                if(!newusername.equals(finalUsername) || !newpassword.equals(finalPassword) || !newsdt.equals(finalSdt)||
                    !newdiachi.equals(finalDiaChi)){
                    AlertDialog.Builder alert_success = new AlertDialog.Builder(getActivity());
                    alert_success.setTitle("Thông báo");
                    alert_success.setIcon(R.drawable.icon_done_black);
                    alert_success.setMessage("Bạn có chắc chắn muốn lưu thông tin thay đổi ?");
                    alert_success.setPositiveButton("Thay Đổi",null);
                    alert_success.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog = alert_success.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            accountManager.deleteAccount(finalUsername);
                            QuanLyTaiKhoan accountManager = QuanLyTaiKhoan.getInstance();
                            accountManager.addAccount(new TaiKhoan(newusername,newpassword,newsdt,newdiachi,newgmail,newcode));
                            dialog.dismiss();
                            AlertDialog.Builder alert_success = new AlertDialog.Builder(getActivity());
                            alert_success.setTitle("Thành Công");
                            alert_success.setIcon(R.drawable.icon_done_black);
                            alert_success.setMessage("Lưu thông tin thay đổi thành công!");
                            alert_success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            alert_success.show();
                        }
                    });
                }else{
                    AlertDialog.Builder alert_success = new AlertDialog.Builder(getActivity());
                    alert_success.setTitle("Thông báo");
                    alert_success.setIcon(R.drawable.icon_notify);
                    alert_success.setMessage("Không có thay đổi mới nào trên thông tin tài khoản!");
                    alert_success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert_success.show();
                }
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder_out_main = new AlertDialog.Builder(getActivity());
                builder_out_main.setIcon(R.drawable.icon_logout);
                builder_out_main.setTitle("Thông báo đăng xuất");
                builder_out_main.setMessage("Bạn có chắc chắn muốn thoát khỏi ứng dụng này!");
                builder_out_main.setPositiveButton("Đăng xuất", null);
                builder_out_main.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog_main = builder_out_main.create();
                dialog_main.show();
                dialog_main.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkSave != true && checkEditChange == true) {
                            AlertDialog.Builder alert_save_TH1 = new AlertDialog.Builder(getActivity());
                            alert_save_TH1.setTitle("Thông báo");
                            alert_save_TH1.setIcon(R.drawable.icon_notify);
                            alert_save_TH1.setMessage("Đã có sự thay đổi thông tin trong tài khoản và chưa được lưu.\nBạn có muốn lưu và thoát không? ");
                            alert_save_TH1.setPositiveButton("Lưu & Thoát", null);
                            alert_save_TH1.setNegativeButton("Không & Thoát", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(getContext(), Activity_Dangnhap.class);
                                    startActivity(intent);
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog dialog = alert_save_TH1.create();
                            dialog.show();
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String newusername = txt_username.getText().toString();
                                    String newpassword = txt_password.getText().toString();
                                    String newsdt = txt_sdt.getText().toString();
                                    String newdiachi = txt_diachi.getText().toString();
                                    String newgmail = txt_gmail.getText().toString();
                                    String newcode = txt_code.getText().toString();

                                    accountManager.deleteAccount(finalUsername);
                                    QuanLyTaiKhoan accountManager = QuanLyTaiKhoan.getInstance();
                                    accountManager.addAccount(new TaiKhoan(newusername, newpassword, newsdt, newdiachi, newgmail, newcode));
                                    Intent intent = new Intent(getContext(), Activity_Dangnhap.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                        }else if((checkSave!=true && checkEditChange!=true) || (checkSave==true && checkEditChange!=true)|| (checkSave==true && checkEditChange==true)){
                            Intent intent = new Intent(getContext(), Activity_Dangnhap.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
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
}