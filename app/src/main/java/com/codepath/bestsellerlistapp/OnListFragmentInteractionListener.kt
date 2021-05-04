package com.codepath.bestsellerlistapp

import com.codepath.bestsellerlistapp.models.BestSellerBook

interface OnListFragmentInteractionListener {
    fun onItemClick(item: BestSellerBook)
}