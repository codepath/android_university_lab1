package com.codepath.bestsellerlistapp.networking;

import com.codepath.bestsellerlistapp.models.BestSellerBook;
import com.codepath.bestsellerlistapp.models.NYTimesAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This code represents the networking layer of the application,
 * Other than updating the API key, you will NOT need to touch this code for the lab,
 * However it may be useful to reference the logic for your future projects
 *
 *
 * IMPORTANT INSTRUCTIONS BELOW ===========================================================
 * TODO: You have to update API_KEY variable with your own NY-Times developer api key, see
 * https://developer.nytimes.com/get-started to create your own developer account,
 * after copy and paste the API key under your Account -> Apps -> <Your App> -> API Keys
 */
public class NYTimesApiClient {

    // TODO: Replace the below API key with your own generated key
    private static final String API_KEY = "9HzaGUdGN4fsLzHApI9JI7IqBck8JAXK";
    private NYTimesService nyTimesService;

    public NYTimesApiClient() {q
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nyTimesService = retrofit.create(NYTimesService.class);
    }

    public void getBestSellersList(final CallbackResponse<List<BestSellerBook>> booksListResponse) {

        // this hard codes to only the current date's hardcover fiction best selling books
        // see https://developer.nytimes.com/docs/books-product/1/overview for more information on API documentation
        Call<NYTimesAPIResponse> current = nyTimesService.getBestSellingBooks("current", "hardcover-fiction", API_KEY);
        current.enqueue(new Callback<NYTimesAPIResponse>() {
            @Override
            public void onResponse(Call<NYTimesAPIResponse> call, Response<NYTimesAPIResponse> response) {
                NYTimesAPIResponse model = response.body();
                if (response.isSuccessful() && model != null) {
                    booksListResponse.onSuccess(model.results.books);
                } else {
                    booksListResponse.onFailure(new Throwable("error with response code " + response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<NYTimesAPIResponse> call, Throwable t) {
                booksListResponse.onFailure(t);
            }
        });
    }
}
