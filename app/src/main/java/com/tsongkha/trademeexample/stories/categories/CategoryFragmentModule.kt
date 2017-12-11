package com.tsongkha.trademeexample.stories.categories

import com.tsongkha.trademeexample.stories.common.PerScopedContext
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rawsond on 8/12/17.
 */
@Module
abstract class CategoryFragmentModule {

    @PerScopedContext
    @ContributesAndroidInjector(modules = [CategoryModule::class])
    abstract fun categoryFragment() : CategoryFragment
}