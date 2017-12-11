package com.tsongkha.trademeexample.stories.listings

import android.os.Bundle
import com.tsongkha.trademeexample.R
import com.tsongkha.trademeexample.stories.categories.CategoryContract
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by rawsond on 10/12/17.
 */
class ListingActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        val intent = getIntent()
        val uri = intent.data
        val category = uri.lastPathSegment
        val args = Bundle().apply { putString(CategoryContract.ARG_CATEGORY_NUMBER, category) }
        supportFragmentManager.beginTransaction()
                .add(R.id.listing_fragment_container, ListingFragment.instantiate(args))
                .commit()
    }
}