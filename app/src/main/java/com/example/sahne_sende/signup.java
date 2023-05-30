package com.example.sahne_sende;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    EditText artistEmail,artistPassword,artistPasswordAgain;
    String email, password, passwordAgain;
    private FirebaseAuth mAuth;
    Button artistSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        artistEmail = findViewById(R.id.artistEmail);
        artistPassword = findViewById(R.id.artistPassword);
        artistPasswordAgain = findViewById(R.id.artistPasswordAgain);
        artistSignup = findViewById(R.id.artistSignup);
        mAuth = FirebaseAuth.getInstance();


        artistSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = artistEmail.getText().toString();
                password = artistPassword.getText().toString();
                passwordAgain = artistPasswordAgain.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(passwordAgain)) {
                    Toast.makeText(signup.this, "Tüm Alanları Doldurunuz", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(passwordAgain)){
                    Toast.makeText(signup.this, "Parolalar eşleşmiyor!", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(signup.this, "Kayıt başarıyla tamamlandı.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(signup.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });

    }
}