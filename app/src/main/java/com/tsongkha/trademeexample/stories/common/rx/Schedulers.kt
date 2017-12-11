package com.tsongkha.trademeexample.stories.common.rx

import io.reactivex.Scheduler

/**
 * See https://medium.com/@peter.tackage/an-alternative-to-rxandroidplugins-and-rxjavaplugins-scheduler-injection-9831bbc3dfaf
 * for a discussion of this pattern
 *
 * Created by rawsond on 8/12/17.
 */
interface Schedulers {

    fun main() : Scheduler

    fun io() : Scheduler
}