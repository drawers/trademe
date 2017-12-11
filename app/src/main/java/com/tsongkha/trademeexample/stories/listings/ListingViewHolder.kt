package com.tsongkha.trademeexample.stories.listings

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tsongkha.trademeexample.databinding.ListingItemBinding

/**
 * See https://medium.com/google-developers/android-data-binding-recyclerview-db7c40d9f0e4
 * for a discussion of this pattern by George Mount
 *
 * Created by rawsond on 8/12/17.
 */
class ListingViewHolder(internal val binding : ListingItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context : Context?, listing : Listing) {
        binding.listing = listing
        binding.executePendingBindings()
        Picasso.with(context)
                .load(listing.pictureHref())
                .into(binding.listingIv)
    }
}