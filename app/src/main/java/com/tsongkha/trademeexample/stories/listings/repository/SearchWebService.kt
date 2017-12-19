package com.tsongkha.trademeexample.stories.listings.repository

import com.tsongkha.trademeexample.stories.listings.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rawsond on 9/12/17.
 */
interface SearchWebService {

    @GET("v1/Search/General.json")
    fun byCategory(@Query("category") category: String,
                   @Query("rows") rows: Int,
                   @Query("photo_size") photoSize : String = "List",
                   @Query("search_string") searchString : String = "") : Observable<SearchResult>
}