package com.taaj.firebasedemoproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.taaj.firebasedemoproject.ForgotPasswordActivity;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    TextView tvRegisterHere,txtForgotPassword;
    Button btnLogin;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPass);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);
        txtForgotPassword = findViewById(R.id.txt_forgot_pass);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });

        tvRegisterHere.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        });

        txtForgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
        });

    }


    private void loginUser(){
        String email = etLoginEmail.getText().toString();
        String pass  = etLoginPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            etLoginEmail.setError("please enter your email");
            etLoginEmail.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            etLoginPassword.setError("please enter your password");
            etLoginPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isComplete()){
                      Toast.makeText(getApplicationContext(), "User Logged in successfully", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(LoginActivity.this,MainActivity.class));
                  }else{
                      Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                  }
                }
            });
        }

    }
}
