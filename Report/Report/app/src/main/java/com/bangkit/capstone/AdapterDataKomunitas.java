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
import android.widget.Toast;

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

public class AdapterDataKomunitas extends RecyclerView.Adapter<AdapterDataKomunitas.MyViewHolder> {
    boolean gabung = false;
    Context context;
    ArrayList<ModelDataKomunitas> list;

    public AdapterDataKomunitas(Context context, ArrayList<ModelDataKomunitas> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataKomunitas.MyViewHolder holder, final int position) {
        ModelDataKomunitas modelDataKomunitas = list.get(position);
        holder.namagroup.setText(modelDataKomunitas.getNamagroup());
        Glide.with(holder.icongroup.getContext()).load(modelDataKomunitas.getIcongroup()).into(holder.icongroup);
        holder.joingroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gabung == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.icongroup.getContext());
                    builder.setMessage("Apakah ingin bergabung di group ?\n" + modelDataKomunitas.getNamagroup());
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gabung=true;
                            Glide.with(holder.joingroup.getContext()).load(R.drawable.ic_baseline_verified_24).into(holder.joingroup);
                            Toast.makeText(context, "Sukses bergabung ke group " + modelDataKomunitas.getNamagroup(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
                else if (gabung==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.icongroup.getContext());
                    builder.setMessage("Apakah ingin keluar di group ?\n" + modelDataKomunitas.getNamagroup());
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gabung=false;
                            Glide.with(holder.joingroup.getContext()).load(R.drawable.ic_baseline_group_add_24).into(holder.joingroup);
                            Toast.makeText(context, "Sukses keluar ke group " + modelDataKomunitas.getNamagroup(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    @NonNull
    @Override
    public AdapterDataKomunitas.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_komunitas,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView namagroup;
        ImageView icongroup,joingroup;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namagroup=itemView.findViewById(R.id.idnamegoup);
            joingroup=itemView.findViewById(R.id.idaddgroup);
            icongroup=itemView.findViewById(R.id.icongroup);

        }
    }
}
