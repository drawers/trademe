package com.tsongkha.trademeexample.stories.common

import com.tsongkha.trademeexample.stories.common.rx.CompositeDisposableProvider
import com.tsongkha.trademeexample.stories.common.rx.Schedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * A presenter ready for RxJava operations
 *
 * Created by rawsond on 11/12/17.
 */
abstract class RxPresenter<V> constructor(private val compositeDisposableProvider: CompositeDisposableProvider, protected val schedulers: Schedulers)
    : AbstractPresenter<V>() {

    protected lateinit var disposables: CompositeDisposable

    override fun takeView(view: V) {
        super.takeView(view)
        disposables = compositeDisposableProvider.get()
    }

    override fun dropView() {
        disposables.clear()
        super.dropView()
    }
}