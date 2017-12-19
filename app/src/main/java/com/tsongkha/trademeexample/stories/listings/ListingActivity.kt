package com.tsongkha.trademeexample.stories.listings

import android.os.Bundle
import com.tsongkha.trademeexample.R
import com.tsongkha.trademeexample.stories.categories.CategoryContract
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by rawsond on 10/12/17.
 */
class ListingActivity : DaggerAppCompatActivity() {

    companion object {
        val TAG = "Listing"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        actionBar

        val intent = getIntent()
        val uri = intent.data
        val category = uri.lastPathSegment
        val args = Bundle().apply { putString(CategoryContract.ARG_CATEGORY_NUMBER, category) }
        if (supportFragmentManager.findFragmentByTag(TAG) != null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.listing_fragment_container, ListingFragment.instantiate(args), TAG)
                    .commit()
        }
    }
}