package com.tsongkha.trademeexample.stories.common.rx

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by rawsond on 8/12/17.
 */
open class CompositeDisposableProvider @Inject constructor() : Provider<CompositeDisposable> {

    override fun get(): CompositeDisposable {
        return CompositeDisposable()
    }
}