package com.bangkit.capstone;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterDataLaporan extends RecyclerView.Adapter<AdapterDataLaporan.MyViewHolder> {

    Context context;
    ArrayList<ModelDataLaporan> list;
    public AdapterDataLaporan(Context context, ArrayList<ModelDataLaporan> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataLaporan.MyViewHolder holder, int position) {
        ModelDataLaporan modelDataLaporan = list.get(position);
        holder.nama.setText(modelDataLaporan.getNama());
        holder.tanggallahir.setText(modelDataLaporan.getTanggallahir());
        holder.jeniskelamin.setText(modelDataLaporan.getJeniskelamin());
        holder.nomortelepon.setText(modelDataLaporan.getNomortelepon());
        holder.alamat.setText(modelDataLaporan.getAlamat());

        holder.tanggalkejadian.setText(modelDataLaporan.getTanggalkejadian());
        holder.jenispelecehan.setText(modelDataLaporan.getJenispelecehan());
        holder.lokasikejadian.setText(modelDataLaporan.getLokasikejadian());
        holder.deskripsipelecehan.setText(modelDataLaporan.getDeskripsipelecehan());
        holder.buktipendukung.setText(modelDataLaporan.getBuktipendukung());
        Glide.with(holder.buktigambar.getContext()).load(modelDataLaporan.getBuktigambar()).into(holder.buktigambar);

    }

    @NonNull
    @Override
    public AdapterDataLaporan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_datalaporan,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama,tanggallahir,jeniskelamin,nomortelepon,alamat,tanggalkejadian,jenispelecehan,lokasikejadian,deskripsipelecehan,buktipendukung;
        ImageView buktigambar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama=itemView.findViewById(R.id.idnama);
            tanggallahir=itemView.findViewById(R.id.idtanggallahir);
            jeniskelamin=itemView.findViewById(R.id.idjeniskelamin);
            nomortelepon=itemView.findViewById(R.id.idnomortelepon);
            alamat=itemView.findViewById(R.id.idalamat);

            tanggalkejadian=itemView.findViewById(R.id.idtanggalkejadian);
            jenispelecehan=itemView.findViewById(R.id.idjenispelecehan);
            lokasikejadian=itemView.findViewById(R.id.idlokasikejadian);
            deskripsipelecehan=itemView.findViewById(R.id.iddeskripsikejadian);
            buktipendukung=itemView.findViewById(R.id.idbuktipendukung);
            buktigambar=itemView.findViewById(R.id.imageurlid);
        }
    }
}
