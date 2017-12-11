package com.tsongkha.trademeexample.stories.listings.repository

import com.tsongkha.trademeexample.stories.common.PerScopedContext
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by rawsond on 10/12/17.
 */
@Module
class SearchWebServiceModule {

    @Module
    companion object {

        @JvmStatic
        @PerScopedContext
        @Provides
        fun searchWebService(retrofit: Retrofit) : SearchWebService{
            return retrofit.create(SearchWebService::class.java)
        }
    }
}