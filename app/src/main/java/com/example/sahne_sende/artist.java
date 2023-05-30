package com.example.sahne_sende;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class artist extends AppCompatActivity {

    Button createAd;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> list = new ArrayList<String>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        createAd = findViewById(R.id.createAd);
        ListView listView = findViewById(R.id.artistList);

        createAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(artist.this, advertise.class);
                startActivity(intent);
            }
        });

        db.collection("Artists")
                .get()
                .addOnCompleteListener(artist.this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document: task.getResult()){
                                list.add("\n\n" + "Ad Soyad : " + document.getString("Name") + "\nYetenekler : " + document.getString("Skills") + "\nTelefon Numarası : " + document.getString("Phone") + "\nE-mail : " + document.getString("Email") + "\n\n");
                                Log.d("artist", "onComplete" + list);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(artist.this, android.R.layout.simple_list_item_1, list);
                            listView.setAdapter(arrayAdapter);
                        }
                        else{
                            Toast.makeText(artist.this, "Lütfen tekrar deneyin!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}