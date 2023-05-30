package com.example.sahne_sende;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class advertise extends AppCompatActivity {

    EditText companyName,companyPhone,gift;
    Button saveAdvertise;
    private HashMap<String, Object> jobData;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);


        companyName = findViewById(R.id.companyName);
        companyPhone = findViewById(R.id.companyPhone);
        gift = findViewById(R.id.gift);
        saveAdvertise = findViewById(R.id.saveAdvertise);

        saveAdvertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = companyName.getText().toString();
                String phone = companyPhone.getText().toString();
                String skills = gift.getText().toString();

                jobData = new HashMap<>();
                jobData.put("Name", name);
                jobData.put("Phone", phone);
                jobData.put("Skills", skills);

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)|| TextUtils.isEmpty(skills)) {
                    Toast.makeText(advertise.this, "Tüm Alanları Doldurunuz", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.collection("JobAds")
                            .add(jobData)
                            .addOnSuccessListener(advertise.this, new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(advertise.this, "Kaydınız oluşturulmuştur.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(advertise.this, artist.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(advertise.this, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(advertise.this, "Kayıt başarısız. Lütfen tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}