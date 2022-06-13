package com.bangkit.capstone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class LaporActivity extends AppCompatActivity {
    EditText nama,tanggallahir,jeniskelamin,nomortelepon,alamat,
            tanggalkejadian, jenispelecehan, lokasikejadian, deskripsipelecehan, buktipendukung;
    ImageView uploadgambar, previewgambar;
    private static final int PICK_FROM_GALLERY = 1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Uri imageurl;
    ProgressBar progressBar;
    Button submit,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor_data);


        nama=findViewById(R.id.addnama);
        tanggallahir=findViewById(R.id.addtanggallahir);
        jeniskelamin=findViewById(R.id.addjeniskelamin);
        nomortelepon=findViewById(R.id.addnomortelpon);
        alamat=findViewById(R.id.addalamat);

        tanggalkejadian=findViewById(R.id.addtanggalkejadian);
        jenispelecehan=findViewById(R.id.addjenispelecehan);
        lokasikejadian=findViewById(R.id.addlokasikejadian);
        deskripsipelecehan=findViewById(R.id.adddeskripsipelecehan);
        buktipendukung=findViewById(R.id.addbuktipendukung);
        progressBar=findViewById(R.id.progressbar);
        previewgambar=findViewById(R.id.previewuploadgambar);
        progressBar.setVisibility(View.INVISIBLE);

        uploadgambar=findViewById(R.id.uploadgambar);
        uploadgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ActivityCompat.checkSelfPermission(LaporActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(LaporActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);}
                    else {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, PICK_FROM_GALLERY);}
                }catch (Exception e) {e.printStackTrace();}}});


        back=findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivityAdmin.class));
                finish();}});

        submit=findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToFirebase(imageurl);}});}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK && data != null) {
            imageurl = data.getData();
            previewgambar.setImageURI(imageurl);
        }
    }
    private void uploadImageToFirebase(Uri imageurl) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("datalaporan");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference file = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageurl));
        file.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri imageurl) {
                        ImageModel model = new ImageModel(imageurl.toString());
                        String smodel = reference.push().getKey();
                        Map<String,Object> map=new HashMap<>();
                        map.put("nama",nama.getText().toString());
                        map.put("tanggallahir",tanggallahir.getText().toString());
                        map.put("jeniskelamin",jeniskelamin.getText().toString());
                        map.put("nomortelepon",nomortelepon.getText().toString());
                        map.put("alamat",alamat.getText().toString());

                        map.put("tanggalkejadian",tanggalkejadian.getText().toString());
                        map.put("jenispelecehan",jenispelecehan.getText().toString());
                        map.put("lokasikejadian",lokasikejadian.getText().toString());
                        map.put("deskripsipelecehan",deskripsipelecehan.getText().toString());
                        map.put("buktipendukung",buktipendukung.getText().toString());
                        map.put("buktigambar",imageurl.toString());
                        reference.child(smodel).setValue(map);

                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LaporActivity.this,"Sukses Mengupload", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LaporActivity.this,"Gagal Mengupload", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private String getFileExtension(Uri imageurl) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap map=MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(contentResolver.getType(imageurl));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Penyimpanan di izinkan", Toast.LENGTH_LONG).show();
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_FROM_GALLERY);}
        else {
            Toast.makeText(this, "Penyimpanan tidak di izinkan", Toast.LENGTH_LONG).show();
        }


    }
}