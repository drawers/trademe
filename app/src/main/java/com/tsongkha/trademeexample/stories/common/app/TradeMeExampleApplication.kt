package com.tsongkha.trademeexample.stories.common.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by rawsond on 8/12/17.
 */
class TradeMeExampleApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().create(this)
        appComponent.inject(this)
        return appComponent
    }
}