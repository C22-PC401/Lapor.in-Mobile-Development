package com.bangkit.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataLaporanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ModelDataLaporan> list;
    DatabaseReference databaseReference;
    AdapterDataLaporan adapterDataLaporan;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataLaporanActivity.this, MainActivityAdmin.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_laporan);

        recyclerView = findViewById(R.id.recyclerviewiddatalaporan);
        databaseReference = FirebaseDatabase.getInstance().getReference("datalaporan");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterDataLaporan = new AdapterDataLaporan(this,list);
        recyclerView.setAdapter(adapterDataLaporan);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelDataLaporan modelDataLaporan = dataSnapshot.getValue(ModelDataLaporan.class);
                    list.add(modelDataLaporan);
                }
                adapterDataLaporan.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}