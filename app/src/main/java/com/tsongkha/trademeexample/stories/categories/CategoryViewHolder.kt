package com.tsongkha.trademeexample.stories.categories

import android.support.v7.widget.RecyclerView
import com.tsongkha.trademeexample.databinding.CategoryItemBinding

/**
 * See https://medium.com/google-developers/android-data-binding-recyclerview-db7c40d9f0e4
 * for a discussion of this pattern by George Mount
 *
 * Created by rawsond on 8/12/17.
 */
class CategoryViewHolder(internal val binding : CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(category : Category, categoryViewModel : CategoryViewModel, interactor : CategoryContract.Interactor) {
        binding.category = category
        binding.categoryViewModel = categoryViewModel
        binding.interactor = interactor
        binding.executePendingBindings()
    }
}