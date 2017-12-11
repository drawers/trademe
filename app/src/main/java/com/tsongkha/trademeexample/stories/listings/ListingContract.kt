package com.tsongkha.trademeexample.stories.listings

/**
 * Created by rawsond on 10/12/17.
 */
interface ListingContract {

    interface Presenter : com.tsongkha.trademeexample.stories.common.Presenter<View> {

        fun loadListings(categoryName : String?)
    }

    interface View {

        fun showListingItem(listing : Listing)
    }
}