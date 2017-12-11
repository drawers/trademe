package com.tsongkha.trademeexample.stories.listings.repository

import com.tsongkha.trademeexample.stories.listings.SearchResult
import io.reactivex.Observable

/**
 * Created by rawsond on 9/12/17.
 */
interface SearchRepository {

    fun byCategory(category: String, rows: Int) : Observable<SearchResult>
}