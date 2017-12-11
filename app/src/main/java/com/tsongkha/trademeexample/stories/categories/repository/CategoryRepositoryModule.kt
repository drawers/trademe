package com.tsongkha.trademeexample.stories.categories.repository

import com.tsongkha.trademeexample.stories.common.PerApp
import dagger.Binds
import dagger.Module

/**
 * Created by rawsond on 8/12/17.
 */
@Module(includes = [CategoryWebServiceModule::class])
abstract class CategoryRepositoryModule {

    @Binds
    @PerApp
    abstract fun categoryRepository(webCategoryRepository: WebCategoryRepository) : CategoryRepository
}