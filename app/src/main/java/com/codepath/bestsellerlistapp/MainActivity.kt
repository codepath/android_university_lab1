package com.codepath.bestsellerlistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codepath.bestsellerlistapp.R.id
import com.codepath.bestsellerlistapp.R.layout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(id.content, BestSellerBooksFragment(), null).commit()
    }
}