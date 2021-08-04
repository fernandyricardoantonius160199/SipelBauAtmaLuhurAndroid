package com.example.skripsiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
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

public class RegisterActivity extends AppCompatActivity {
    EditText ed_nama, ed_email, ed_hp, ed_user, ed_pass;
    TextView txt_daftar;

    AwesomeText ImgShowHidePassword;
    boolean pwd_status = true;

    Sesion sesion;
    BaseApiService baseApiService;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        sesion = new Sesion(this);
        baseApiService = UtilsApi.getAPIService();

        ed_nama = findViewById(R.id.ed_nama);
        ed_email = findViewById(R.id.ed_email);
        ed_hp = findViewById(R.id.ed_hp);
        ed_user = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_pass);
        txt_daftar = findViewById(R.id.txt_daftar);

        ImgShowHidePassword = (AwesomeText)findViewById(R.id.ImgShowPassword);
        ImgShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwd_status) {
                    ed_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                   pwd_status = false;
                   ImgShowHidePassword.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY);
                   ed_pass.setSelection(ed_pass.length());
               } else {
                    ed_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pwd_status = true;
                    ImgShowHidePassword.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY_OFF);
                   ed_pass.setSelection(ed_pass.length());
               }
            }
        });

        txt_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseApiService.registerRequest(ed_nama.getText().toString(), ed_email.getText().toString(),
                        ed_hp.getText().toString(), ed_user.getText().toString(),ed_pass.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){

                                    startActivity(new Intent(RegisterActivity.this, SucessRegisterActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                    String message = jsonRESULTS.getString("message");
                                    Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

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
}
