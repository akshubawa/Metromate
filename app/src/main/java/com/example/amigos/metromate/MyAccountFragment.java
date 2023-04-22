package com.example.amigos.metromate;

import android.content.Intent;
import android.net.Uri;
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
    private Button recharge_button;
    private Button callSupport_button;
    private Button feedback_button;
    private Button aboutUs_button;
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
                BottomNavigationView bnView = ((HomepageActivity) getActivity()).getBottomNavigationView();
                bnView.setSelectedItemId(R.id.nav_bookings);
            }
        });

        recharge_button = view.findViewById(R.id.recharge_button);
        recharge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.dmrcsmartcard.com/Citrus_Payment/QuickTopUp.aspx";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        callSupport_button = view.findViewById(R.id.callSupport_button);
        callSupport_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91 7088839967"));
                startActivity(intent);
            }
        });

        feedback_button = view.findViewById(R.id.feedback_button);
        feedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: akshubawa70@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for MetroMate");
                startActivity(Intent.createChooser(intent, "Send Email"));

            }
        });

        aboutUs_button = view.findViewById(R.id.aboutUs_button);
        aboutUs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AboutUsActivity.class);
                startActivity(intent);
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