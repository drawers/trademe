package com.tsongkha.trademeexample.stories.listings.repository

import com.tsongkha.trademeexample.stories.listings.Listing
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

/**
 * Created by rawsond on 10/12/17.
 */
class DefaultListingRepository @Inject constructor(private val searchWebService: SearchWebService) : ListingRepository{

    override fun byCategory(category: String, rows: Int): Observable<Listing> {
        return searchWebService.byCategory(category, rows)
                .flatMap { it.listings().toObservable() }
    }
}