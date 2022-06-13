package com.bangkit.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivityUser extends AppCompatActivity {
    CardView informasi,komunitas,lapor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        informasi=findViewById(R.id.iditeminformasi);
        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityUser.this,InformasiActivity.class);
                startActivity(intent);}});

        komunitas=findViewById(R.id.iditemkomunitas);
        komunitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityUser.this,KomunitasActivity.class);
                startActivity(intent);}});

        lapor=findViewById(R.id.iditemlaporkan);
        lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityUser.this,LaporActivity.class);
                startActivity(intent);}});

    }
}