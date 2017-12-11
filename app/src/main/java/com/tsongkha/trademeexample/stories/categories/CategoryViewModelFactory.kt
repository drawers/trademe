package com.tsongkha.trademeexample.stories.categories

import android.view.View
import javax.inject.Inject

/**
 * This class gives us an extra layer of indirection between the model layer (Category)
 * and the View. In particular, it helps us avoid code like
 * android:visibility = @{category.isLeaf() ? View.VISIBLE : View.GONE} inside the XML
 *
 * If the conversion between model layer and view layer were more complicated then the
 * unit testing against this class would be even more beneficial
 *
 * Created by rawsond on 8/12/17.
 */
class CategoryViewModelFactory @Inject constructor() {

    fun create(category: Category) : CategoryViewModel {
        val name = category.name()
        val chevronVisibility = if (category.isLeaf) View.GONE else View.VISIBLE
        return CategoryViewModel(name, chevronVisibility)
    }
}