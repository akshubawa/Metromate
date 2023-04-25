package com.example.amigos.metromate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class BookingsFragment extends Fragment {

    DatabaseReference databaseReference;
    LinkedList<BookingsObject> usersBookings;
    RecyclerView bookingsRecyclerView;

    private static final int MAX_SIZE = 10;
    public BookingsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference("BookingsPosts");
        usersBookings = new LinkedList<>();
        bookingsRecyclerView = view.findViewById(R.id.bookingsRecyclerView);

        // Set the adapter and layout manager for the RecyclerView here
        bookingsRecyclerView.setAdapter(new BookingsPostAdapter(usersBookings));
        bookingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersBookings.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BookingsObject bookingsObject = dataSnapshot.getValue(BookingsObject.class);
                    if (bookingsObject.getId().equals(userId)) {
                        usersBookings.addFirst(bookingsObject);
                    }
                }
                if (usersBookings.size()>MAX_SIZE)
                    usersBookings.removeLast();

                // Update the adapter with the new data
                bookingsRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

