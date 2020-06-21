package com.codepath.bestsellerlistapp.networking;

import com.codepath.bestsellerlistapp.models.NYTimesAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTimesService {

    @GET("svc/books/v3/lists/{date}/{list}.json")
    Call<NYTimesAPIResponse> getBestSellingBooks(@Path("date") String date, @Path("list") String list, @Query("api-key") String apikey);
}
