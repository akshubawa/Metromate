package com.example.amigos.metromate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class AddMoneyActivity extends AppCompatActivity {
    private TextInputLayout addMoney_amount;
    private Button addedMoney_button;
    private int balance_added;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        addMoney_amount = findViewById(R.id.addMoney_amount);
        addedMoney_button = findViewById(R.id.addedMoney_button);

        Intent wallet_add = getIntent();
        if (wallet_add.hasExtra("balance")) {
            String balance_string = wallet_add.getStringExtra("balance");
            if (balance_string != null) {
                balance_added = Integer.parseInt(balance_string);
            }
        }
        else balance_added = 0;
        addedMoney_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = addMoney_amount.getEditText().getText().toString().trim();
                Log.e("money: ", money);
                int addedMoney = balance_added + Integer.parseInt(money);
                Log.e( "addedMoney ", String.valueOf(addedMoney));
                if(TextUtils.isEmpty(money)) {
                    addMoney_amount.setError("Please enter a valid amount");
                    return;
                }
                //int addedMoney = Integer.parseInt(money);
                Intent walletIntent = new Intent(getApplicationContext(), WalletActivity.class);
                walletIntent.putExtra("addedMoney",addedMoney);
                startActivity(walletIntent);
            }
        });


    }
}