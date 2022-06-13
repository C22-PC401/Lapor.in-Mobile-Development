package com.bangkit.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KomunitasActivity extends AppCompatActivity {

    ImageView addgroup;
    RecyclerView recyclerView;
    ArrayList<ModelDataKomunitas> list;
    DatabaseReference databaseReference;
    AdapterDataKomunitas adapterDataKomunitas;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(KomunitasActivity.this, MainActivityAdmin.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komunitas);

        addgroup=findViewById(R.id.addgroupid);
        addgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KomunitasActivity.this,TambahDataKomunitas.class);
                startActivity(intent);}});

        recyclerView = findViewById(R.id.recyclerviewidkomunitas);
        databaseReference = FirebaseDatabase.getInstance().getReference("datakomunitas");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterDataKomunitas = new AdapterDataKomunitas(this,list);
        recyclerView.setAdapter(adapterDataKomunitas);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelDataKomunitas modelDataKomunitas = dataSnapshot.getValue(ModelDataKomunitas.class);
                    list.add(modelDataKomunitas);
                }
                adapterDataKomunitas.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}