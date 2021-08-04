package com.example.skripsiapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Sesion {
    public static final String APP = "SkripsiApp";

    public static final String IDUSER = "idUSER";
    public static final String NMUSER = "nmUSER";
    public static final String EMAILUSER = "emailUSER";
    public static final String NOHPUSER = "nohpUSER";
    public static final String USRUSER = "usrUSER";
    public static final String PASSUSER = "passUSER";

    public static final String KDKOMPLAIN = "kdKOMPLAIN";
    public static final String JENISKOMPLAIN = "jenisKOMPLAIN";
    public static final String ISIKOMPLAIN = "isiKOMPLAIN";
    public static final String JAMKOMPLAIN = "jamKOMPLAIN";
    public static final String TANGGALKOMPLAIN = "tanggalKOMPLAIN";
    public static final String KDRUANGAN = "kdRUANGAN";
    public static final String FOTOKOMPLAIN = "fotoKOMPLAIN";
    public static final String STATUSKOMPLAIN = "statusKOMPLAIN";

    public static final String SUDAH_LOGIN = "SudahLogin";

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    @SuppressLint("CommitPrefEdits")
    public Sesion(Context context){
        sp = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }
    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }
    public String getIduser(){
        return sp.getString(IDUSER, "");
    }
    public String getNmuser(){
        return sp.getString(NMUSER, "");
    }
    public String getEmailuser(){
        return sp.getString(EMAILUSER, "");
    }
    public String getNohpuser(){ return sp.getString(NOHPUSER, ""); }
    public String getUsruser(){ return sp.getString(USRUSER, ""); }
    public String getPassuser(){ return sp.getString(PASSUSER, ""); }

    public String getKdkomplain(){ return sp.getString(KDKOMPLAIN, ""); }
    public String getJeniskomplain(){ return sp.getString(JENISKOMPLAIN, ""); }
    public String getIsikomplain(){ return sp.getString(ISIKOMPLAIN, ""); }
    public String getJamkomplain(){ return sp.getString(JAMKOMPLAIN, ""); }
    public String getTanggalkomplain(){ return sp.getString(TANGGALKOMPLAIN, ""); }
    public String getKdruangan(){ return sp.getString(KDRUANGAN, ""); }
    public String getStatuskomplain(){ return sp.getString(STATUSKOMPLAIN, ""); }
    public Boolean getSudahLogin(){
        return sp.getBoolean(SUDAH_LOGIN, false);
    }


}


