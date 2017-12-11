package com.tsongkha.trademeexample.stories.router

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionSet
import android.view.Gravity
import com.tsongkha.trademeexample.R
import com.tsongkha.trademeexample.stories.categories.CategoryContract
import com.tsongkha.trademeexample.stories.categories.CategoryFragment
import com.tsongkha.trademeexample.stories.listings.ListingFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class RouterActivity : DaggerAppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    companion object {
        val TAG = RouterActivity::class.java.simpleName
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.addOnBackStackChangedListener(this)
        when {
            savedInstanceState == null -> {
                onNewIntent(intent) //trigger routing logic for very first launch
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val path = intent?.data?.lastPathSegment

        val bundle = Bundle().apply {
            putString(CategoryContract.ARG_CATEGORY_NUMBER, path)
        }
        val frag = CategoryFragment.instantiate(bundle)
        val addToBackStack = main_category_container.childCount != 0 //don't add to backstack for very first fragment

        replaceWithTransition(R.id.main_category_container, frag, path.orEmpty(), addToBackStack)
    }

    fun replaceWithTransition(containerId: Int, fragment: Fragment, tag: String, addToBackStack: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.enterTransition = TransitionSet().addTransition(Slide(Gravity.END)).addTransition(Fade(Fade.IN))
            fragment.reenterTransition = TransitionSet().addTransition(Slide(Gravity.START)).addTransition(Fade(Fade.OUT))
            fragment.returnTransition = TransitionSet().addTransition(Slide(Gravity.END)).addTransition(Fade(Fade.IN))
            fragment.exitTransition = TransitionSet().addTransition(Slide(Gravity.START)).addTransition(Fade(Fade.OUT))
            fragment.allowEnterTransitionOverlap = false
            fragment.allowReturnTransitionOverlap = false
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }

    //trigger changes in the backstack of the master fragment to load the detail fragment
    override fun onBackStackChanged() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) return

        if (main_listing_container == null) return
        //we don't have to react to anything on the backstack in the case where there is no container for listings

        val path = getSupportFragmentManager().getBackStackEntryAt(count - 1).name

        val bundle = Bundle().apply {
            putString(CategoryContract.ARG_CATEGORY_NUMBER, path)
        }

        replaceWithTransition(R.id.main_listing_container, ListingFragment.instantiate(bundle), ListingFragment.TAG, false)
    }
}
