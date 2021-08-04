package com.example.skripsiapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("android/login.php")
    Call<ResponseBody> loginRequest(@Field("user_user") String user_user,
                                    @Field("pass_user") String pass_user);

    @FormUrlEncoded
    @POST("android/register.php")
    Call<ResponseBody> registerRequest(@Field("nama_user") String nama_user,
                                       @Field("email_user") String email_user,
                                       @Field("no_hp_user") String no_hp_user,
                                       @Field("user_user") String user_user,
                                       @Field("pass_user") String pass_user);

    @FormUrlEncoded
    @POST("android/komplain.php")
    Call<ResponseBody> komplainRequest(@Field("id_user") String id_user,
                                       @Field("jenis_komplain") String jenis_komplain,
                                       @Field("isi_komplain") String isi_komplain,
                                       @Field("kd_ruangan") String kd_ruangan);



    @FormUrlEncoded
    @POST("android/SemuaLaporanKomplain.php")
    Call<ResponseBody> pelaporanRequest(@Field("kd_komplain") String kd_komplain,
                                        @Field("id_user") String id_user,
                                        @Field("jenis_komplain") String jenis_komplain,
                                        @Field("jam_komplain") String jam_komplain,
                                        @Field("tanggal_komplain") String tanggal_komplain,
                                        @Field("kd_ruangan") String kd_ruangan,
                                        @Field("isi_komplain") String isi_komplain,
                                        @Field("status_komplain") String status_komplain);

}

