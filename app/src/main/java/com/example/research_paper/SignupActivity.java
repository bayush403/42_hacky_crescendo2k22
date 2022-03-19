package com.example.research_paper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private EditText emailIDSU,passwordSU;
    private Button Signup;
    private TextView txtLogin;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailIDSU = findViewById(R.id.editTextTextEmailAddress2);
        passwordSU = findViewById(R.id.editTextTextPassword2);
        Signup = findViewById(R.id.button2);
        txtLogin = findViewById(R.id.textView3);
        mFirebaseAuth = FirebaseAuth.getInstance();
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emID = emailIDSU.getText().toString();
                String pass = passwordSU.getText().toString();
                createSignUp(emID,pass);
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void createSignUp(String emID, String pass) {
        if(TextUtils.isEmpty(emID)){
            emailIDSU.setError("Please enter an email ID");
            emailIDSU.requestFocus();
        }
        else if(TextUtils.isEmpty(pass)){
            passwordSU.setError("Please enter a valid password");
            passwordSU.requestFocus();
        }
        else{
            mFirebaseAuth.createUserWithEmailAndPassword(emID,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(SignupActivity.this,"SignUp unsuccessful, please try again",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                    }
                }
            });
        }
    }
    }