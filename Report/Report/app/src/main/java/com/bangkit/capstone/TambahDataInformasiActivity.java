package com.bangkit.capstone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class TambahDataInformasiActivity extends AppCompatActivity {
    EditText judulinformasi,tanggalinformasi,deskripsiinformasi;
    ImageView uploadgambar, previewgambar;
    private static final int PICK_FROM_GALLERY = 1;
    Button submitinformasi,backinformasi;
    ProgressBar progressBar;
    Uri imageurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_informasi);

        judulinformasi=findViewById(R.id.addjudulinformasi);
        tanggalinformasi=findViewById(R.id.addtanggalinformasi);
        deskripsiinformasi=findViewById(R.id.adddeskripsiinformasi);
        progressBar=findViewById(R.id.progressbarinformasi);
        previewgambar=findViewById(R.id.previewuploadgambarinformasi);
        progressBar.setVisibility(View.INVISIBLE);

        uploadgambar=findViewById(R.id.uploadgambarinformasi);
        uploadgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ActivityCompat.checkSelfPermission(TambahDataInformasiActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(TambahDataInformasiActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);}
                    else {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, PICK_FROM_GALLERY);}
                }catch (Exception e) {e.printStackTrace();}}});

        backinformasi=findViewById(R.id.add_backinformasi);
        backinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InformasiActivity.class));
                finish();}});

        submitinformasi=findViewById(R.id.add_submitinformasi);
        submitinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert(imageurl);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK && data != null) {
            imageurl = data.getData();
            previewgambar.setImageURI(imageurl);
        }
    }
    private void processinsert(Uri imageurl) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("datainformasi");
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
                        map.put("judulinformasi",judulinformasi.getText().toString());
                        map.put("tanggalinformasi",tanggalinformasi.getText().toString());
                        map.put("gambarinformasi",imageurl.toString());
                        map.put("deskripsiinformasi",deskripsiinformasi.getText().toString());
                        reference.child(smodel).setValue(map);

                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(TambahDataInformasiActivity.this,"Sukses Mengirim data informasi", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(TambahDataInformasiActivity.this,"Gagal mengirim data informasi", Toast.LENGTH_SHORT).show();
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