package com.example.sahne_sende;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class profile extends AppCompatActivity {

    EditText artistName,artistPhone,artistEmail,artistSkills;
    Button saveProfile;
    ImageButton backProf;
    private HashMap<String, Object> profileData;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        artistName = findViewById(R.id.artistName);
        artistPhone = findViewById(R.id.artistPhone);
        artistEmail = findViewById(R.id.artistEmail);
        artistSkills = findViewById(R.id.artistSkills);
        saveProfile = findViewById(R.id.saveProfile);
        backProf = findViewById(R.id.backProf);

        backProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, jobs.class);
                startActivity(intent);
            }
        });

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = artistName.getText().toString();
                String phone = artistPhone.getText().toString();
                String email = artistEmail.getText().toString();
                String skills = artistSkills.getText().toString();

                profileData = new HashMap<>();
                profileData.put("Name", name);
                profileData.put("Phone", phone);
                profileData.put("Email", email);
                profileData.put("Skills", skills);

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)||TextUtils.isEmpty(email) || TextUtils.isEmpty(skills)) {
                    Toast.makeText(profile.this, "Tüm Alanları Doldurunuz", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.collection("Artists")
                            .add(profileData)
                            .addOnSuccessListener(profile.this, new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(profile.this, "Kaydınız oluşturulmuştur.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(profile.this, jobs.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(profile.this, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(profile.this, "Kayıt başarısız. Lütfen tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

    }
}