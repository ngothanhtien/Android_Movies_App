package com.example.appxemphim_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Khoidau extends AppCompatActivity {
    private Button btn_pass;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khoidau);
        btn_pass = findViewById(R.id.btn_pass);
        btn_login = findViewById(R.id.btn_login);

        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Khoidau.this, Activity_Khachvanglai.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Khoidau.this, Activity_Dangnhap.class);
                startActivity(intent);
            }
        });
    }
}