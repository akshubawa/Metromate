package com.example.amigos.metromate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyAccountFragment extends Fragment {

    private TextView display_name;
    private Button wallet_button;
    private Button signout_button;
    private Button myBookings_button;
    public MyAccountFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        display_name = view.findViewById(R.id.display_name);
        String display_fullName = user.getDisplayName();
        display_fullName = display_fullName.toUpperCase();
        display_name.setText(display_fullName);

        wallet_button = view.findViewById(R.id.wallet_button);
        wallet_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMoneyIntent = new Intent(getContext(), WalletActivity.class);
                startActivity(addMoneyIntent);
            }
        });

        myBookings_button = view.findViewById(R.id.myBookings_button);
        myBookings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingsFragment bookingsFragment = new BookingsFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,bookingsFragment).commit();

                bottomNavigationView.setSelectedItemId(R.id.nav_bookings);
            }
        });

        signout_button = view.findViewById(R.id.signout_button);
        signout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}