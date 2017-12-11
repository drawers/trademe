package com.tsongkha.trademeexample.stories.listings.repository

import com.tsongkha.trademeexample.stories.listings.Listing
import io.reactivex.Observable

/**
 * Created by rawsond on 10/12/17.
 */
interface ListingRepository {

    fun byCategory(category: String, rows: Int) : Observable<Listing>
}