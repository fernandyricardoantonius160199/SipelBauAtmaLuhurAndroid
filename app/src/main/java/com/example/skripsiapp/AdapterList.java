package com.example.skripsiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder>{

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public AdapterList(HistoryActivity historyActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = historyActivity;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load("http://192.168.100.58/sipel_sarpras_bau_al/uploads/" + list_data.get(position).get("foto_komplain"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.foto_komplain);
        holder.kd_komplain.setText(list_data.get(position).get("kd_komplain"));
        holder.tanggal_komplain.setText(list_data.get(position).get("tanggal_komplain"));
        holder.jam_komplain.setText(list_data.get(position).get("jam_komplain"));
        holder.kd_ruangan.setText(list_data.get(position).get("kd_ruangan"));
        holder.isi_komplain.setText(list_data.get(position).get("isi_komplain"));
        holder.jenis_komplain.setText(list_data.get(position).get("jenis_komplain"));
        holder.status_komplain.setText(list_data.get(position).get("status_komplain"));
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kd_komplain, tanggal_komplain, jam_komplain, kd_ruangan, isi_komplain, jenis_komplain, status_komplain;
        ImageView foto_komplain;

        public ViewHolder(View itemView) {
            super(itemView);

            kd_komplain = (TextView) itemView.findViewById(R.id.kd_komplain);
            tanggal_komplain = (TextView) itemView.findViewById(R.id.tanggal_komplain);
            jam_komplain = (TextView) itemView.findViewById(R.id.jam_komplain);
            kd_ruangan = (TextView) itemView.findViewById(R.id.kd_ruangan);
            isi_komplain = (TextView) itemView.findViewById(R.id.isi_komplain);
            jenis_komplain = (TextView) itemView.findViewById(R.id.jenis_komplain);
            status_komplain = (TextView) itemView.findViewById(R.id.status_komplain);
            foto_komplain = (ImageView) itemView.findViewById(R.id.foto_komplain);
        }
    }
}
