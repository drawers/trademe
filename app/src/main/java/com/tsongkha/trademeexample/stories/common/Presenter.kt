package com.tsongkha.trademeexample.stories.common

/**
 * Created by rawsond on 8/12/17.
 */
interface Presenter<in V> {

    fun takeView(view : V)

    fun dropView()
}