package com.tsongkha.trademeexample.stories.categories

import android.support.v7.widget.RecyclerView
import com.tsongkha.trademeexample.stories.categories.repository.CategoryRepositoryModule
import com.tsongkha.trademeexample.stories.common.PerScopedContext
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by rawsond on 8/12/17.
 */
@Module
abstract class CategoryModule {

    @Binds
    @PerScopedContext
    abstract fun categoryPresenter(presenter : CategoryPresenter) : CategoryContract.Presenter

    @Binds
    @PerScopedContext
    abstract fun categoryInteractor(interactor: CategoryInteractor) : CategoryContract.Interactor

}