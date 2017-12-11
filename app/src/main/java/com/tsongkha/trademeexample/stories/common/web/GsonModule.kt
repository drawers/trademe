package com.tsongkha.trademeexample.stories.common.web

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tsongkha.trademeexample.stories.categories.GsonAdaptersCategory
import com.tsongkha.trademeexample.stories.common.PerApp
import com.tsongkha.trademeexample.stories.listings.GsonAdaptersListing
import com.tsongkha.trademeexample.stories.listings.GsonAdaptersSearchResult
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rawsond on 8/12/17.
 */
@Module
class GsonModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerApp
        fun gson() : Gson {
            return GsonBuilder()
                    .registerTypeAdapterFactory(GsonAdaptersCategory())
                    .registerTypeAdapterFactory(GsonAdaptersListing())
                    .registerTypeAdapterFactory(GsonAdaptersSearchResult())
                    .create()
        }

        @JvmStatic
        @Provides
        @PerApp
        fun gsonConverterFactory(gson : Gson) : GsonConverterFactory {
            return GsonConverterFactory.create(gson)
        }
    }
}