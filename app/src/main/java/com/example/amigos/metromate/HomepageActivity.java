package com.example.amigos.metromate;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomepageActivity extends AppCompatActivity {

    RelativeLayout container;
    BottomNavigationView bnView;
    DatabaseReference databaseReference;

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        bnView = findViewById(R.id.bnView);
        container = findViewById(R.id.container);

        databaseReference = FirebaseDatabase.getInstance().getReference("ride-posts");

        bnView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                loadFrag(new HomeFragment(), false);
            } else if (id == R.id.nav_bookings) {
                loadFrag(new BookingsFragment(), false);
            } else if (id == R.id.nav_map) {
                loadFrag(new MapFragment(), false);
            }
            else {
                loadFrag(new MyAccountFragment(), false);
            }

            return true;
        });
        bnView.setSelectedItemId(R.id.nav_home);


    }

    public BottomNavigationView getBottomNavigationView() {
        return bnView;
    }


    public void loadFrag(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag) {
            ft.add(R.id.container, fragment);
        } else {
            ft.replace(R.id.container, fragment);
        }
        ft.commit();
    }

}