package com.example.amigos.metromate;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletActivity extends AppCompatActivity {
    private int balance = 0;
    private TextView wallet_textview;
    private Button addMoney_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        wallet_textview = findViewById(R.id.wallet_textview);

        Intent walletIntent = getIntent();
        if (walletIntent.hasExtra("addedMoney")) {
            String received_balance = walletIntent.getStringExtra("addedMoney");
            Log.e("receivedBalance: ",received_balance );
            if (received_balance != null) {
                int int_received_balance = Integer.parseInt(received_balance);
                balance += int_received_balance;
                Log.e("Balance: ", String.valueOf(balance));
                wallet_textview.setText(String.valueOf(balance));
            }
        }

        addMoney_button = findViewById(R.id.addMoney_button);
        addMoney_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wallet_add = new Intent(WalletActivity.this, AddMoneyActivity.class);
                startActivity(wallet_add);
            }
        });


        //wallet_textview.setText(String.format(String.valueOf(balance)));

        addMoney_button = findViewById(R.id.addMoney_button);
        addMoney_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wallet_add = new Intent(WalletActivity.this, AddMoneyActivity.class);
                wallet_add.putExtra("balance",balance);
                startActivity(wallet_add);
            }
        });

    }
}
