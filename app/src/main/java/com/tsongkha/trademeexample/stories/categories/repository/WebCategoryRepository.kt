package com.tsongkha.trademeexample.stories.categories.repository

import com.tsongkha.trademeexample.stories.categories.Category
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rawsond on 8/12/17.
 */
class WebCategoryRepository @Inject constructor(private val delegate: CategoryWebService) : CategoryRepository {

    override fun byNumber(number: String, depth: Int): Observable<Category> {
        return delegate.byNumber(number, depth)
    }

    override fun root(depth: Int): Observable<Category> {
        return delegate.root(depth)
    }
}