package com.codepath.bestsellerlistapp.models;

import com.google.gson.annotations.SerializedName;

public class NYTimesAPIResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("results")
    public BestSellerResults results;
}
