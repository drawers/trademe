package com.tsongkha.trademeexample.stories.common.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by rawsond on 8/12/17.
 */
class DefaultSchedulers @Inject constructor(): Schedulers {

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }
}