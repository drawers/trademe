package com.tsongkha.trademeexample.stories.listings.repository

import com.tsongkha.trademeexample.stories.common.PerScopedContext
import dagger.Binds
import dagger.Module

/**
 * Created by rawsond on 10/12/17.
 */
@Module(includes = [SearchWebServiceModule::class])
abstract class ListingRepositoryModule {

    @Binds
    @PerScopedContext
    abstract fun listingRepository(defaultListingRepository: DefaultListingRepository) : ListingRepository
}