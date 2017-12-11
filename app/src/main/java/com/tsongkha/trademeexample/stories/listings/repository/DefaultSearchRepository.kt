package com.tsongkha.trademeexample.stories.listings.repository

import com.tsongkha.trademeexample.stories.listings.SearchResult
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rawsond on 9/12/17.
 */
class DefaultSearchRepository @Inject constructor(private val delegate: SearchWebService) : SearchRepository {

    override fun byCategory(category: String, rows: Int): Observable<SearchResult> {
        return delegate.byCategory(category, rows)
    }
}