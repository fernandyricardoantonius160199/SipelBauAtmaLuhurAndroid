package com.example.skripsiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SucessRegisterActivity extends AppCompatActivity {
    TextView btn_selanjutnya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess_register);
        getSupportActionBar().hide();

        btn_selanjutnya = findViewById(R.id.btn_selanjutnya);

        btn_selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotowelcome = new Intent(SucessRegisterActivity.this, WelcomeActivity.class);
                startActivity(gotowelcome);
            }
        });
    }
}