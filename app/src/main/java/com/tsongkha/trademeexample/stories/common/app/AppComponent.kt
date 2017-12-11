package com.tsongkha.trademeexample.stories.common.app

import com.tsongkha.trademeexample.stories.categories.CategoryFragmentModule
import com.tsongkha.trademeexample.stories.categories.repository.CategoryRepositoryModule
import com.tsongkha.trademeexample.stories.router.RouterModule
import com.tsongkha.trademeexample.stories.common.PerApp
import com.tsongkha.trademeexample.stories.common.web.WebModule
import com.tsongkha.trademeexample.stories.listings.ListingActivityModule
import com.tsongkha.trademeexample.stories.listings.ListingFragmentModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by rawsond on 8/12/17.
 */
@Component(modules = [AppModule::class,
    WebModule::class,
    RouterModule::class,
    CategoryRepositoryModule::class,
    CategoryFragmentModule::class,
    ListingActivityModule::class,
    ListingFragmentModule::class,
    AndroidSupportInjectionModule::class])

@PerApp
interface AppComponent : AndroidInjector<TradeMeExampleApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TradeMeExampleApplication>()

}