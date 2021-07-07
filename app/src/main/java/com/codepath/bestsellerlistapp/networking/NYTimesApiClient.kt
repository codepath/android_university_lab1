package com.codepath.bestsellerlistapp.networking

import com.codepath.bestsellerlistapp.models.BestSellerBook
import com.codepath.bestsellerlistapp.models.NYTimesAPIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

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
</Your> */
// TODO: Replace the below API key with your own generated key
private const val API_KEY = "<YOUR-API-KEY-GOES-HERE>"
class NYTimesApiClient {
    private val nyTimesService: NYTimesService

    init {
        val retrofit = Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        nyTimesService = retrofit.create(NYTimesService::class.java)
    }
    fun getBestSellersList(booksListResponse: CallbackResponse<List<BestSellerBook>>) {

        // this hard codes to only the current date's hardcover fiction best selling books
        // see https://developer.nytimes.com/docs/books-product/1/overview for more information on API documentation
        val current = nyTimesService.getBestSellingBooks("current", "hardcover-fiction", API_KEY)
        current?.enqueue(object : Callback<NYTimesAPIResponse?> {
            override fun onResponse(call: Call<NYTimesAPIResponse?>, response: Response<NYTimesAPIResponse?>) {
                val model = response.body()
                val books = model?.results?.books
                if (response.isSuccessful && books != null) {
                    booksListResponse.onSuccess(books)
                } else {
                    booksListResponse.onFailure(Throwable("error with response code " + response.code() + " " + response.message()))
                }
            }

            override fun onFailure(call: Call<NYTimesAPIResponse?>, t: Throwable) {
                booksListResponse.onFailure(t)
            }
        })
    }
}