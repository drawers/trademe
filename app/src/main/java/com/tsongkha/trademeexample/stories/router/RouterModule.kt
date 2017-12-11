package com.tsongkha.trademeexample.stories.router

import com.tsongkha.trademeexample.stories.common.PerScopedContext
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rawsond on 8/12/17.
 */
@Module
abstract class RouterModule {

    @PerScopedContext
    @ContributesAndroidInjector
    abstract fun mainActivity() : RouterActivity
}