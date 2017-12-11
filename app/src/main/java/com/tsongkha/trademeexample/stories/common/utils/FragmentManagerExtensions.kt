package com.tsongkha.trademeexample.stories.common.utils

import android.support.v4.app.FragmentManager

/**
 * Created by rawsond on 11/12/17.
 */
fun FragmentManager.isBackStackEmpty() : Boolean {
    return this.backStackEntryCount == 0
}