package com.tsongkha.trademeexample.stories.listings

import com.tsongkha.trademeexample.stories.common.PerScopedContext
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rawsond on 10/12/17.
 */
@Module
abstract class ListingFragmentModule {

    @PerScopedContext
    @ContributesAndroidInjector(modules = [ListingModule::class])
    abstract fun listingFragment() : ListingFragment
}