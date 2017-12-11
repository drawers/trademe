package com.tsongkha.trademeexample.stories.categories.repository

import com.tsongkha.trademeexample.stories.categories.Category
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by rawsond on 7/12/17.
 */
interface CategoryWebService {

    @GET("v1/Categories/{number}.json")
    fun byNumber(@Path("number") number : String, @Query("depth") depth: Int) : Observable<Category>

    @GET("v1/Categories.json")
    fun root(@Query("depth") depth: Int) : Observable<Category>
}