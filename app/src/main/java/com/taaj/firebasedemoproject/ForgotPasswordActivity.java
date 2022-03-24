package com.taaj.firebasedemoproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity  extends AppCompatActivity {
    EditText etEmail;
    Button resetPasswordBtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.etForgotPassEmail);
        resetPasswordBtn = findViewById(R.id.resetPasswordButton);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar_forgot);

        resetPasswordBtn.setOnClickListener(view -> {
            resetPassword();
        });

    }


    public void resetPassword(){
        String email = etEmail.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setText("enter valid email");
            etEmail.requestFocus();
            return;
        }



    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "please check your email to reset password", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                progressBar.setVisibility(View.VISIBLE);
            }
            else{
                Toast.makeText(getApplicationContext(), "password reset failed try again", Toast.LENGTH_SHORT).show();
            }
        }
    });

    }
}
