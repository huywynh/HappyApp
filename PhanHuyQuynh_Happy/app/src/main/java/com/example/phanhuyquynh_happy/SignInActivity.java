package com.example.phanhuyquynh_happy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button btnSignin;
    EditText edtEmail, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

        auth = FirebaseAuth.getInstance();
        btnSignin = findViewById(R.id.signin_btnSignin);
        edtEmail = findViewById(R.id.signin_edtEmail);
        edtPassword = findViewById(R.id.signin_edtPassword);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(email.length() > 0 && password.length() > 0) {
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(com.example.phanhuyquynh_happy.SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(!task.isSuccessful()) {
                                            Toast.makeText(com.example.phanhuyquynh_happy.SignInActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Intent intent = new Intent(com.example.phanhuyquynh_happy.SignInActivity.this, com.example.phanhuyquynh_happy.AfterLogin.class);
                                            startActivity(intent);
                                        }
                                }
                            });
                }
            }
        });


    }
}