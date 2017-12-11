package com.tsongkha.trademeexample.stories.categories

import javax.inject.Inject

/**
 * Created by rawsond on 8/12/17.
 */
class CategoryInteractor @Inject constructor(private val presenter : CategoryContract.Presenter): CategoryContract.Interactor {

    override fun onCategoryClick(category: Category) {
        presenter.nextCategory(category)
    }
}