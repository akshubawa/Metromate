package com.example.amigos.metromate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MetroApi {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://us-central1-delhimetroapi.cloudfunctions.net/";

    public static Retrofit getRetrofitInstance(String encodedFrom, String encodedTo) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Methods methods = retrofit.create(Methods.class);
        Call<Model> call = methods.getAllData(encodedFrom, encodedTo);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
            }
        });

        return retrofit;
    }
}
