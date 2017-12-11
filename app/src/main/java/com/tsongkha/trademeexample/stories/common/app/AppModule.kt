package com.tsongkha.trademeexample.stories.common.app

import android.content.Context
import com.tsongkha.trademeexample.stories.common.Base
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule

/**
 * See https://github.com/google/dagger/issues/832
 * for a discussion of this pattern
 *
 * Created by rawsond on 9/12/17.
 */
@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {

    @Binds
    @Base
    abstract fun baseContext(app : TradeMeExampleApplication) : Context
}