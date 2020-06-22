package com.codepath.bestsellerlistapp.models;

import com.google.gson.annotations.SerializedName;

public class BestSellerBook {

   @SerializedName("rank")
   public int rank;

   @SerializedName("title")
   public String title;

   @SerializedName("author")
   public String author;

   @SerializedName("book_image")
   public String bookImageUrl;

   @SerializedName("publisher")
   public String publisher;

   @SerializedName("description")
   public String description;

   @SerializedName("amazon_product_url")
   public String amazonUrl;
}
