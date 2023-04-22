package com.example.amigos.metromate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ArrayList<OffersCardModel> arrOffers = new ArrayList<OffersCardModel>();
    RecyclerView offersRecycler;

    private AutoCompleteTextView booking_from;
    private AutoCompleteTextView booking_to;
    private Button booking_findRoute_button;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        offersRecycler = view.findViewById(R.id.offersRecycler);

        offersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        arrOffers.add(new OffersCardModel("AQUA LINE","Fast Trains will not stop at stations: Sector 144, Sector 145, Sector 146 and Sector 147."));
        arrOffers.add(new OffersCardModel("FAST TRAINS","Fast Trains will not be available on weekends and national holidays."));
        arrOffers.add(new OffersCardModel("BYOB","Kindly practice the habit of Bring your own Bag for eco-friendly Shopping."));
        arrOffers.add(new OffersCardModel("BEWARE OF POCKET-PICKERS","Keep your belongings safe and secured and be alert."));
        arrOffers.add(new OffersCardModel("METRO-HOURS","Metro services are active from 06:00 to 22:00 IST."));

        OffersAdaptor adapter = new OffersAdaptor(getContext(), arrOffers);
        offersRecycler.setAdapter(adapter);

        AutoCompleteTextView booking_from = (AutoCompleteTextView)view.findViewById(R.id.booking_from);
        AutoCompleteTextView booking_to = (AutoCompleteTextView)view.findViewById(R.id.booking_to);
        booking_findRoute_button = view.findViewById(R.id.booking_findRoute_button);

        ArrayList<String> stationsList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open("StationsList.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                stationsList.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            //Log.e(TAG, "Error reading StationsList.txt", e);
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, stationsList);

        booking_from.setAdapter(adapter1);
        booking_to.setAdapter(adapter1);

        booking_from.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                booking_from.setText(selection);
            }
        });

        booking_to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                booking_to.setText(selection);
            }
        });

        booking_findRoute_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String source = booking_from.getText().toString();
                String destination = booking_to.getText().toString();

                String fromStation = booking_from.getText().toString();
                String toStation = booking_to.getText().toString();

                fromStation.replace(" ","%20");
                toStation.replace(" ","%20");

                Methods methods = MetroApi.getRetrofitInstance(fromStation,toStation).create(Methods.class);
                Call<Model> call = methods.getAllData(fromStation, toStation);

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        if (response.isSuccessful()) {
                            Model model = response.body();
                            if (model != null) {
                                int status = Integer.parseInt(model.getStatus());
                                if (status == 200) {
                                    Intent intent = new Intent(getActivity(), StationsActivity.class);
                                    intent.putExtra("source",source);
                                    intent.putExtra("destination", destination);
                                    intent.putExtra("Line1", response.body().getLine1());
                                    intent.putExtra("Line2", response.body().getLine2());
                                    intent.putExtra("LineEnds", response.body().getLineEnds());
                                    intent.putExtra("Interchange", response.body().getInterchange());
                                    intent.putExtra("Path", response.body().getPath());
                                    intent.putExtra("Time", response.body().getTime());

                                    assert response.body() != null;
                                    ArrayList<String> line1 = response.body().getLine1();
                                    ArrayList<String> line2 = response.body().getLine2();
                                    ArrayList<String> interchange = response.body().getInterchange();
                                    ArrayList<String> path = response.body().getPath();
                                    String time = response.body().getTime();

                                    int var_time = (int) Math.ceil(Double.parseDouble(time));
                                    int fare = 0;

                                    if (var_time>=55) {
                                        fare=60;
                                    }
                                    else if (var_time>=40) {
                                        fare=50;
                                    }
                                    else if (var_time>=30) {
                                        fare=40;
                                    }
                                    else if (var_time>=20) {
                                        fare=30;
                                    }
                                    else if (var_time>=10) {
                                        fare=20;
                                    }
                                    else if (var_time<=10) {
                                        fare=10;
                                    }

                                    intent.putExtra("fare",fare);

                                    startActivity(intent);



                                } else if (status == 204) {
                                    Toast.makeText(getContext(), "Same Source and Destination", Toast.LENGTH_SHORT).show();
                                } else if (status == 400) {
                                    Toast.makeText(getContext(), "Undefined Source or Destination", Toast.LENGTH_SHORT).show();
                                } else if (status == 4061) {
                                    Toast.makeText(getContext(), "Invalid Source", Toast.LENGTH_SHORT).show();
                                } else if (status == 4062) {
                                    Toast.makeText(getContext(), "Invalid Destination", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        //Log.e(TAG, "onFailure "+t.getMessage());

                    }
                });
            }
        });

        return view;
    }
}