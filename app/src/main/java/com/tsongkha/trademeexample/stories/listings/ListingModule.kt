package com.tsongkha.trademeexample.stories.listings

import com.tsongkha.trademeexample.stories.common.PerScopedContext
import com.tsongkha.trademeexample.stories.listings.repository.ListingRepositoryModule
import dagger.Binds
import dagger.Module

/**
 * Created by rawsond on 8/12/17.
 */
@Module(includes = [ListingRepositoryModule::class])
abstract class ListingModule {

    @Binds
    @PerScopedContext
    abstract fun listingPresenter(presenter : ListingPresenter) : ListingContract.Presenter
}