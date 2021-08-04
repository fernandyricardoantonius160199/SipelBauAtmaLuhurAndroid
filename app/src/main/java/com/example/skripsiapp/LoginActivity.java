package com.example.skripsiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cyd.awesome.material.AwesomeText;
import cyd.awesome.material.FontCharacterMaps;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText ed_username, ed_password;
    TextView txt_masuk, txt_daftar_akun;

    AwesomeText ImgShowHidePassword;
    boolean pwd_status = true;

    BaseApiService baseApiService;
    Context context;
    Sesion sesion;

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        baseApiService = UtilsApi.getAPIService();
        context = this;
        sesion = new Sesion(this);

        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        txt_masuk = findViewById(R.id.txt_masuk);
        txt_daftar_akun = findViewById(R.id.txt_daftar_akun);

        ImgShowHidePassword = (AwesomeText)findViewById(R.id.ImgShowPassword);
        ImgShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwd_status) {
                    ed_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwd_status = false;
                    ImgShowHidePassword.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY);
                    ed_password.setSelection(ed_password.length());
                } else {
                    ed_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pwd_status = true;
                    ImgShowHidePassword.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY_OFF);
                    ed_password.setSelection(ed_password.length());
                }
            }
        });

        txt_daftar_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        txt_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseApiService.loginRequest(ed_username.getText().toString(), ed_password.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){

                                    String id_user = jsonRESULTS.getJSONObject("tbl_user").getString("id_user");
                                    String nama_user = jsonRESULTS.getJSONObject("tbl_user").getString("nama_user");
                                    String email_user = jsonRESULTS.getJSONObject("tbl_user").getString("email_user");
                                    String no_hp_user = jsonRESULTS.getJSONObject("tbl_user").getString("no_hp_user");
                                    String user_user = jsonRESULTS.getJSONObject("tbl_user").getString("user_user");
                                    String pass_user = jsonRESULTS.getJSONObject("tbl_user").getString("pass_user");

                                    sesion.saveSPString(Sesion.IDUSER, id_user);
                                    sesion.saveSPString(Sesion.NMUSER, nama_user);
                                    sesion.saveSPString(Sesion.EMAILUSER, email_user);
                                    sesion.saveSPString(Sesion.NOHPUSER, no_hp_user);
                                    sesion.saveSPString(Sesion.USRUSER, user_user);
                                    sesion.saveSPString(Sesion.PASSUSER, pass_user);
                                    sesion.saveSPBoolean(Sesion.SUDAH_LOGIN, true);

                                    startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(getApplication(), error_message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }  catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });
    }

    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
