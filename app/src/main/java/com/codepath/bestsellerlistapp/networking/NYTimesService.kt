package com.codepath.bestsellerlistapp.networking

import com.codepath.bestsellerlistapp.models.NYTimesAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTimesService {
    @GET("svc/books/v3/lists/{date}/{list}.json") fun getBestSellingBooks(
        @Path("date") date: String?,
        @Path("list") list: String?,
        @Query("api-key") apikey: String?
    ): Call<NYTimesAPIResponse?>?
}