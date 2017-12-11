package com.tsongkha.trademeexample.stories.listings

import com.tsongkha.trademeexample.stories.common.PerScopedContext
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rawsond on 10/12/17.
 */
@Module
abstract class ListingActivityModule {

    @PerScopedContext
    @ContributesAndroidInjector
    abstract fun listingActivity() : ListingActivity
}