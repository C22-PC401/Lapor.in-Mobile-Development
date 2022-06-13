package com.bangkit.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InformasiActivity extends AppCompatActivity {
    ImageView addinformasi;
    RecyclerView recyclerView;
    ArrayList<ModelDataInformasi> list;
    DatabaseReference databaseReference;
    AdapterDataInformasi adapterDataInformasi;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InformasiActivity.this, MainActivityAdmin.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);

        addinformasi=findViewById(R.id.idadddatainformasi);
        addinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformasiActivity.this,TambahDataInformasiActivity.class);
                startActivity(intent);}});

        recyclerView = findViewById(R.id.recyclerviewidinformasi);
        databaseReference = FirebaseDatabase.getInstance().getReference("datainformasi");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterDataInformasi = new AdapterDataInformasi(this,list);
        recyclerView.setAdapter(adapterDataInformasi);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelDataInformasi modelDataInformasi = dataSnapshot.getValue(ModelDataInformasi.class);
                    list.add(modelDataInformasi);
                }
                adapterDataInformasi.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}