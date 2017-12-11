package com.tsongkha.trademeexample.stories.categories

import android.content.Intent

/**
 * Created by rawsond on 8/12/17.
 */
interface CategoryContract {

    companion object {

        val EXTRA_CATEGORY_NAME = "EXTRA_CATEGORY_NAME"

        val ARG_CATEGORY_NUMBER = "ARG_CATEGORY_NUMBER"
    }

    interface Presenter : com.tsongkha.trademeexample.stories.common.Presenter<View> {
        fun loadCategory(number: String?)

        fun nextCategory(category: Category)
    }

    interface View {
        fun showSubcategoryItem(category: Category)

        fun showNextView(intent : Intent)

        fun showError(errorMessage: String)
    }

    interface Interactor {

        fun onCategoryClick(category: Category)
    }
}