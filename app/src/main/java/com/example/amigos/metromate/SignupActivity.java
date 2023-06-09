package com.example.amigos.metromate;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button signup_button;
    TextInputLayout signup_fullName, signup_email, signup_phoneN, signup_password, signup_confirm_password;
    TextView login_redirect;

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
        if (currentUser != null) {
            Toast.makeText(this, "User Already Exists!", Toast.LENGTH_SHORT).show();
            Intent new_intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(new_intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        signup_fullName = findViewById(R.id.signup_name);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        signup_confirm_password = findViewById(R.id.signup_confirm_password);
        signup_phoneN = findViewById(R.id.signup_phoneN);

        signup_button = findViewById(R.id.signup_button);

        login_redirect = findViewById(R.id.login_redirect);
        login_redirect.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        signup_button.setOnClickListener(v -> {
            String name, email, phone, password, confirm_password;
            double balance;
            name = signup_fullName.getEditText().getText().toString().trim();
            email = signup_email.getEditText().getText().toString().trim();
            phone =signup_phoneN.getEditText().getText().toString().trim();
            password = signup_password.getEditText().getText().toString();
            confirm_password = signup_confirm_password.getEditText().getText().toString();
            balance = 69;

            if (TextUtils.isEmpty(name)) {
                signup_fullName.setError("Please provide Name");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                signup_email.setError("Please provide Email");
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                signup_phoneN.setError("Please provide Mobile Number");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                signup_password.setError("Please provide Password");
                return;
            }

            if (TextUtils.isEmpty(confirm_password)) {
                signup_confirm_password.setError("Please Confirm Password");
                return;
            }

            else if (password.length() < 8) {
                signup_password.setError("Password should be at least 8 characters long.");
                return;
            }

            if (phone.length() != 10) {
                Toast.makeText(this, "Phone number should be 10 digits.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm_password)) {
                signup_password.setError("Passwords do not match.");
                signup_confirm_password.setError("Passwords do not match.");
                return;
            }

            else {

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String uid = user.getUid();

                                    User signup = new User(name, email, phone, balance);

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference usersRef = database.getReference("users").child(user.getUid());

                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name).build();

                                    user.updateProfile(profileUpdates);

                                    usersRef.setValue(signup);

                                    Toast.makeText(SignupActivity.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(SignupActivity.this, "User Already Exists!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
