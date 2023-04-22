package com.example.amigos.metromate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletActivity extends AppCompatActivity {
    private int balance = 100;
    private int received_balance = 0;
    private TextView wallet_textview;
    private Button addMoney_button;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        wallet_textview = findViewById(R.id.wallet_textview);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent walletIntent = getIntent();
        if (walletIntent.hasExtra("addedMoney")) {
            received_balance = walletIntent.getIntExtra("addedMoney", 0);
            balance += received_balance;
            wallet_textview.setText("" + balance);
            mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("balance").setValue(balance);
        }

        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    balance = snapshot.getValue(Integer.class);
                    wallet_textview.setText(""+balance);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("WalletActivity", "Failed to read value.", error.toException());
            }
        });


        addMoney_button = findViewById(R.id.addMoney_button);
        addMoney_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wallet_add = new Intent(WalletActivity.this, AddMoneyActivity.class);
                startActivity(wallet_add);
            }
        });

        //wallet_textview.setText(balance);

        addMoney_button = findViewById(R.id.addMoney_button);
        addMoney_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wallet_add = new Intent(WalletActivity.this, AddMoneyActivity.class);
                wallet_add.putExtra("balance",balance);
                startActivity(wallet_add);
                finish();
            }
        });

    }
}
