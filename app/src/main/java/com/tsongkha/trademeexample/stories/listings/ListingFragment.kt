package com.tsongkha.trademeexample.stories.listings

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import com.tsongkha.trademeexample.R
import com.tsongkha.trademeexample.stories.categories.CategoryContract.Companion.ARG_CATEGORY_NUMBER
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_listing.*
import javax.inject.Inject


/**
 * Created by rawsond on 8/12/17.
 */
class ListingFragment : DaggerFragment(), ListingContract.View {

    companion object {
        const val TAG = "LISTING"

        fun instantiate(args : Bundle) : ListingFragment {
            val frag = ListingFragment()
            frag.arguments = args
            return frag
        }
    }

    @Inject lateinit var presenter : ListingContract.Presenter
    @Inject lateinit var adapter : ListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this)
        listing_rv.adapter = adapter
        listing_rv.setHasFixedSize(true)
        listing_rv.layoutManager = LinearLayoutManager(context)
        listing_rv.layoutManager.isAutoMeasureEnabled = false
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.listing, menu);

        val search = menu!!.findItem(R.id.listing_search)

        val sv = search.actionView as SearchView
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(activity, "Search submitted for : " + query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        val categoryNumber = arguments?.getString(ARG_CATEGORY_NUMBER)
        if (adapter.itemCount == 0) {
            presenter.loadListings(categoryNumber)
        }
    }


    override fun showListingItem(listing: Listing) {
        adapter.add(listing)
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }
}