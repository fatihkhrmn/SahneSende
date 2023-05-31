package com.example.sahne_sende;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class jobs extends AppCompatActivity {

    Button createProfile;
    ImageButton backJobs;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> list = new ArrayList<String>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        createProfile = findViewById(R.id.createProfile);
        backJobs = findViewById(R.id.backJobs);
        ListView listView = findViewById(R.id.jobList);

        backJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jobs.this, home.class);
                startActivity(intent);
            }
        });

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jobs.this, profile.class);
                startActivity(intent);
            }
        });
        db.collection("JobAds")
                .get()
                .addOnCompleteListener(jobs.this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document: task.getResult()){
                                list.add("\n\n" + "Kurum Adı : " + document.getString("Name") + "\nAranan Nitelikler : " + document.getString("Skills") + "\nTelefon Numarası : " + document.getString("Phone") + "\n\n");
                                Log.d("artist", "onComplete" + list);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(jobs.this, android.R.layout.simple_list_item_1, list);
                            listView.setAdapter(arrayAdapter);
                        }
                        else{
                            Toast.makeText(jobs.this, "Lütfen tekrar deneyin!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}