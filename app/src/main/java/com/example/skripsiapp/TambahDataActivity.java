package com.example.skripsiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TambahDataActivity extends AppCompatActivity {

    private ImageView imghp;
    private TextView txtmerk, txttipe, txtketerangan;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        String url = "http://192.168.100.230/sipel_sarpras_bau_al/uploads/getdata.php";

        imghp = (ImageView)findViewById(R.id.foto_komplain);
        txtmerk = (TextView)findViewById(R.id.kd_komplain);
        txttipe = (TextView)findViewById(R.id.tanggal_komplain);
        txtketerangan = (TextView)findViewById(R.id.status_komplain);


        requestQueue = Volley.newRequestQueue(TambahDataActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("handphone");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("tanggal_komplain", json.getString("tanggal_komplain"));
                        map.put("kd_ruangan", json.getString("kd_ruangan"));
                        map.put("foto_komplain", json.getString("foto_komplain"));
                        list_data.add(map);
                    }
                    Glide.with(getApplicationContext())
                            .load("http://192.168.100.230/sipel_sarpras_bau_al/uploads/" + list_data.get(0).get("foto_komplain"))
                            .crossFade()
                            .placeholder(R.mipmap.ic_launcher)
                            .into(imghp);
                    txtmerk.setText(list_data.get(0).get("kd_ruangan"));
                    txttipe.setText(list_data.get(0).get("tanggal_komplain"));
                    txtketerangan.setText(list_data.get(0).get("status_komplain"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahDataActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}