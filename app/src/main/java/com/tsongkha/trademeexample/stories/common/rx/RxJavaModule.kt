package com.tsongkha.trademeexample.stories.common.rx

import com.tsongkha.trademeexample.stories.common.PerApp
import dagger.Binds
import dagger.Module

/**
 * Created by rawsond on 8/12/17.
 */
@Module
abstract class RxJavaModule {

    @PerApp
    @Binds
    abstract fun schedulers(defaultSchedulers : DefaultSchedulers) : Schedulers

}