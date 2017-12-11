package com.tsongkha.trademeexample.stories.common.web

import android.content.Context
import com.tsongkha.trademeexample.stories.common.Base
import com.tsongkha.trademeexample.stories.common.PerApp
import com.tsongkha.trademeexample.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by rawsond on 8/12/17.
 */
@Module
class OkHttpModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerApp
        fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                         plainTextAuthenticator: PlainTextAuthenticator): OkHttpClient {
            val builder = OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .authenticator(plainTextAuthenticator)

            return builder.build()
        }

        @JvmStatic
        @Provides
        @PerApp
        fun httpLoggingInterceptor() : HttpLoggingInterceptor {
            val level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
            return HttpLoggingInterceptor()
                    .apply{ setLevel(level) }
        }
    }
}