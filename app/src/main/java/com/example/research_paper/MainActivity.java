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

public class MainActivity extends AppCompatActivity {
    private EditText emailID,password;
    private Button btnSignUp;
    private FirebaseAuth mFirebaseAuth;
    private TextView txtSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailID = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        btnSignUp = findViewById(R.id.button);
        txtSignUp = findViewById(R.id.textView2);
        mFirebaseAuth = FirebaseAuth.getInstance();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailID.getText().toString();
                String pwd = password.getText().toString();
                createLogin(email,pwd);
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }

    private void createLogin(String email, String pwd) {
        if(TextUtils.isEmpty(email)){
            emailID.setError("Please enter an email ID");
            emailID.requestFocus();
        }
        else if(TextUtils.isEmpty(pwd)){
            password.setError("Please enter a valid password");
            password.requestFocus();
        }
        else{
            mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Login unsuccessful, please try again",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                }
            });
        }
    }
}
