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

public class RegisterActivity extends AppCompatActivity {
    Button btnReg;
    EditText editTextEmail, editTextPassword;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        btnReg = findViewById(R.id.reg_btnReg);
        auth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.reg_edtEmail);
        editTextPassword = findViewById(R.id.reg_password);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(email.length() > 0 && password.length() > 0) {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(com.example.phanhuyquynh_happy.RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()) {
                                        Toast.makeText(com.example.phanhuyquynh_happy.RegisterActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(com.example.phanhuyquynh_happy.RegisterActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(com.example.phanhuyquynh_happy.RegisterActivity.this, com.example.phanhuyquynh_happy.MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });

                }


            }
        });
    }
}
