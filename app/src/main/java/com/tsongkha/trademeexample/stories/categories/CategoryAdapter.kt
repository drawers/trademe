package com.tsongkha.trademeexample.stories.categories

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tsongkha.trademeexample.databinding.CategoryItemBinding
import javax.inject.Inject

/**
 * Created by rawsond on 8/12/17.
 */
class CategoryAdapter @Inject constructor(private val viewModelFactory : CategoryViewModelFactory,
                                          private val interactor : CategoryContract.Interactor) : RecyclerView.Adapter<CategoryViewHolder>() {

    val categories : MutableList<Category> = mutableListOf()

    override fun getItemCount() : Int {
        return categories.size
    }

    fun add(category : Category) {
        val currentPosition = categories.size
        categories.add(category)
        notifyItemInserted(currentPosition)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder?, position: Int) {
        val category = categories.get(position)
        val categoryViewModel = viewModelFactory.create(category)
        holder?.bind(category, categoryViewModel, interactor)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent!!.context), parent, false))
    }
}