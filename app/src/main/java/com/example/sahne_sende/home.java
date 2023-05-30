package com.example.sahne_sende;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    ImageButton artistButton, jobsButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    artistButton = findViewById(R.id.artistButton);
    jobsButton = findViewById(R.id.jobsButton);

    artistButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(home.this, artist.class);
            startActivity(intent);

        }
    });

    jobsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(home.this, jobs.class);
            startActivity(intent);

        }
    });
    }
}