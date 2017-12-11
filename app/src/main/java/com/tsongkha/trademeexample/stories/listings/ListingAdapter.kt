package com.tsongkha.trademeexample.stories.listings

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tsongkha.trademeexample.databinding.ListingItemBinding
import javax.inject.Inject

/**
 * Created by rawsond on 8/12/17.
 */
class ListingAdapter @Inject constructor() : RecyclerView.Adapter<ListingViewHolder>() {

    private val listings : MutableList<Listing> = mutableListOf()

    private var layoutInflater : LayoutInflater? = null
    private var context : Context? = null

    override fun getItemCount() : Int {
        return listings.size
    }

    fun add(listing : Listing) {
        val currentPosition = listings.size
        listings.add(listing)
        notifyItemInserted(currentPosition)
    }

    override fun onBindViewHolder(holder: ListingViewHolder?, position: Int) {
        holder?.bind(context, listings.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListingViewHolder {
        if (context == null) context = parent!!.context
        if (layoutInflater == null) layoutInflater = LayoutInflater.from(context)

        return ListingViewHolder(ListingItemBinding.inflate(layoutInflater!!, parent, false))
    }
}