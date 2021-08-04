package com.example.skripsiapp;

public class UtilsApi {

    public static BaseApiService getAPIService(){

        return RetrofitClient.getClient("http://192.168.100.58/sipel_sarpras_bau_al/").create(BaseApiService.class);

    }
}
