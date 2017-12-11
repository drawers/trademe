package com.tsongkha.trademeexample.stories.categories.repository

import com.tsongkha.trademeexample.stories.common.PerApp
import com.tsongkha.trademeexample.stories.common.PerScopedContext
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by rawsond on 8/12/17.
 */
@Module
class CategoryWebServiceModule {

    @Module
    companion object {

        @JvmStatic
        @PerApp
        @Provides
        fun categoryWebService(retrofit : Retrofit) : CategoryWebService {
            return retrofit.create(CategoryWebService::class.java)
        }
    }
}