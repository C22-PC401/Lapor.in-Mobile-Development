package com.bangkit.capstone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterDataInformasi extends RecyclerView.Adapter<AdapterDataInformasi.MyViewHolder> {
    Context context;
    ArrayList<ModelDataInformasi> list;


    public AdapterDataInformasi(Context context, ArrayList<ModelDataInformasi> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterDataInformasi.MyViewHolder holder, final int position) {
        ModelDataInformasi modelDataInformasi = list.get(position);
        holder.judulinformasi.setText(modelDataInformasi.getJudulinformasi());
        holder.tanggalinformasi.setText(modelDataInformasi.getTanggalinformasi());
        Glide.with(holder.gambarinformasi.getContext()).load(modelDataInformasi.getGambarinformasi()).into(holder.gambarinformasi);
        holder.deskripsiinformasi.setText(modelDataInformasi.getDeskripsiinformasi());
        /*holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.judulinformasi.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_tambah_data_informasi))
                        .setExpanded(true, 1200)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText judulinformasi=myview.findViewById(R.id.addjudulinformasi);
                final EditText tanggalinformasi=myview.findViewById(R.id.addtanggalinformasi);
                final EditText deskripsiinformasi=myview.findViewById(R.id.adddeskripsiinformasi);

                Button submit=myview.findViewById(R.id.add_submitinformasi);
                judulinformasi.setText(modelDataInformasi.getJudulinformasi());
                tanggalinformasi.setText(modelDataInformasi.getTanggalinformasi());
                deskripsiinformasi.setText(modelDataInformasi.getDeskripsiinformasi());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("judulinformasi", judulinformasi.getText().toString());
                        map.put("tanggalinformasi",tanggalinformasi.getText().toString());
                        map.put("deskripsiinformasi",deskripsiinformasi.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("datainformasi")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {dialogPlus.dismiss();}}).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {dialogPlus.dismiss();}
                                });}});}
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.judulinformasi.getContext());
                builder.setMessage("Yakin Menghapus ?\n"+holder.judulinformasi);

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("datainformasi")
                                .child(getRef(position).getKey()).removeValue();}
                });
                builder.setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

         */
    }

    @NonNull
    @Override
    public AdapterDataInformasi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_informasi,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView judulinformasi,tanggalinformasi,deskripsiinformasi;
        ImageView gambarinformasi,edit,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            judulinformasi=itemView.findViewById(R.id.idjudulinformasi);
            tanggalinformasi=itemView.findViewById(R.id.idtanggalinformasi);
            deskripsiinformasi=itemView.findViewById(R.id.iddeskripsiinformasi);
            gambarinformasi=itemView.findViewById(R.id.previergambarinformasi);
            edit=itemView.findViewById(R.id.editicon);
            delete=itemView.findViewById(R.id.deleteicon);
        }
    }
}
