package com.tsongkha.trademeexample.stories.common.web

import com.tsongkha.trademeexample.stories.common.PerApp
import com.tsongkha.trademeexample.stories.common.rx.RxJavaModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rawsond on 8/12/17.
 */
@Module(includes = [GsonModule::class, OkHttpModule::class, RxJavaModule::class])
class WebModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerApp
        fun retrofit(client: OkHttpClient,
                     gsonConverterFactory: GsonConverterFactory): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(Servers.ROOT_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(gsonConverterFactory)
                    .build()
        }
    }
}