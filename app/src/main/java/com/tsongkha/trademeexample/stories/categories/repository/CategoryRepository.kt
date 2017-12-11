package com.tsongkha.trademeexample.stories.categories.repository

import com.tsongkha.trademeexample.stories.categories.Category
import io.reactivex.Observable

/**
 * Created by rawsond on 8/12/17.
 */
interface CategoryRepository {

    fun byNumber(number : String, depth: Int) : Observable<Category>

    fun root(depth: Int) : Observable<Category>
}