package com.tsongkha.trademeexample.stories.common

/**
 * Created by rawsond on 11/12/17.
 */
abstract class AbstractPresenter<V> constructor() : Presenter<V> {

    protected var view : V? = null

    override fun takeView(view: V) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }
}