package com.example.skripsiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Sesion sesion;
    BaseApiService baseApiService;
    Context context;

    LinearLayout profil, pelaporan, riwayat, info, logout;
    TextView nama, iduser, hp;

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        sesion = new Sesion(this);
        baseApiService = UtilsApi.getAPIService();
        context = this;

        profil = findViewById(R.id.profil);
        pelaporan = findViewById(R.id.pelaporan);
        info = findViewById(R.id.info);
        riwayat = findViewById(R.id.riwayat);
        logout = findViewById(R.id.logout);
        nama = findViewById(R.id.nama);
        hp = findViewById(R.id.hp);
        iduser = findViewById(R.id.iduser);

        nama.setText(sesion.getNmuser());
        iduser.setText(sesion.getIduser());
        hp.setText(sesion.getNohpuser());

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfilActivity.class));
            }
        });

        pelaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PelaporanActivity.class));
            }
        });

        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah anda ingin keluar dari aplikasi ?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sesion.saveSPBoolean(sesion.SUDAH_LOGIN, false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackPressed() {
        //new AlertDialog.Builder(this);
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            sesion.saveSPBoolean(sesion.SUDAH_LOGIN, true);
            super.onBackPressed();
            return;
            //startActivity(new Intent(MainActivity.this, LoginActivity.class)
            //.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else {
            Toast.makeText(getBaseContext(), "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();

    }
}
