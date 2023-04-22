package com.example.amigos.metromate;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView sign_up_redirect;
    TextInputLayout login_email, login_password;
    FirebaseAuth mAuth;
    Button login_button;

    public class App extends Application {
        @Override
        public void onCreate() {
            super.onCreate();

            FirebaseApp.initializeApp(this);
        }
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent2 = new Intent(getApplicationContext(), HomepageActivity.class);
            startActivity(intent2);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);

        sign_up_redirect = findViewById(R.id.sign_up_redirect);

        sign_up_redirect.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
        login_button.setOnClickListener(v -> {
            String email,password;
            email = login_email.getEditText().getText().toString().trim();
            password = login_password.getEditText().getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(LoginActivity.this, "Please Provide Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Please Provide Password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                Intent intent3 = new Intent(getApplicationContext(), HomepageActivity.class);
                                startActivity(intent3);
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}


