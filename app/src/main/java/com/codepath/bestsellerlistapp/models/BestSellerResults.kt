package com.codepath.bestsellerlistapp.models

import com.google.gson.annotations.SerializedName

class BestSellerResults {
    @SerializedName("list_name")
    var listName: String? = null

    @JvmField
    @SerializedName("books")
    var books: List<BestSellerBook>? = null
}