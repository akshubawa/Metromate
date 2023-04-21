package com.example.amigos.metromate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods {
    @GET("route-get")
    Call<Model> getAllData(@Query("from") String fromStation, @Query("to") String toStation);

}

