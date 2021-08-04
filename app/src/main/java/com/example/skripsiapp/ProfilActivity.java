package com.example.skripsiapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {
    TextView iduser, nama, email, hp, user, pass;

    Sesion sesion;
    BaseApiService baseApiService;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Profil");
        ab.setElevation(0);

        sesion = new Sesion(this);
        baseApiService = UtilsApi.getAPIService();
        context = this;

        iduser = findViewById(R.id.iduser);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        hp = findViewById(R.id.hp);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);

        nama.setText(sesion.getNmuser());
        iduser.setText(sesion.getIduser());
        user.setText(sesion.getUsruser());
        pass.setText(sesion.getPassuser());
        email.setText(sesion.getEmailuser());
        hp.setText(sesion.getNohpuser());

    }
}