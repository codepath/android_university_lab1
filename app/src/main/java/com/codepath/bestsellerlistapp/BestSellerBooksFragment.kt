package com.codepath.bestsellerlistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bestsellerlistapp.R.layout
import com.codepath.bestsellerlistapp.models.BestSellerBook
import com.codepath.bestsellerlistapp.networking.CallbackResponse
import com.codepath.bestsellerlistapp.networking.NYTimesApiClient

/**
 * A fragment representing a list of Items.
 */
class BestSellerBooksFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout.fragment_best_seller_books_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        val nyTimesApiClient = NYTimesApiClient()
        nyTimesApiClient.getBestSellersList(object : CallbackResponse<List<BestSellerBook>> {
            override fun onSuccess(models: List<BestSellerBook>) {
                progressBar.hide()
                recyclerView.adapter = BestSellerBooksRecyclerViewAdapter(models, this@BestSellerBooksFragment)
                Log.d("BestSellerBooksFragment", "response successful")
            }

            override fun onFailure(error: Throwable?) {
                progressBar.hide()
                error?.message?.let { message ->
                    Log.e("BestSellerBooksFragment", message)
                }
            }
        })
    }

    override fun onItemClick(item: BestSellerBook) {
        Toast.makeText(context, "test", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(columnCount: Int): BestSellerBooksFragment {
            val fragment = BestSellerBooksFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}