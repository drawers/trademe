package com.tsongkha.trademeexample.stories.listings

import android.util.Log
import com.tsongkha.trademeexample.stories.common.RxPresenter
import com.tsongkha.trademeexample.stories.common.rx.CompositeDisposableProvider
import com.tsongkha.trademeexample.stories.common.rx.Schedulers
import com.tsongkha.trademeexample.stories.listings.repository.ListingRepository
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * Created by rawsond on 10/12/17.
 */
class ListingPresenter @Inject constructor(private val listingRepository: ListingRepository,
                                           compositeDisposableProvider: CompositeDisposableProvider,
                                           schedulers: Schedulers) :
        RxPresenter<ListingContract.View>(compositeDisposableProvider, schedulers),
        ListingContract.Presenter {

    companion object {
        private const val TAG = "ListingPresenter"

        private const val MAX_LISTINGS = 20
    }

    override fun loadListings(categoryName: String?) {
        if (categoryName.isNullOrEmpty()) return

        listingRepository.byCategory(categoryName!!, MAX_LISTINGS)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribeWith(object : DisposableObserver<Listing>() {
                    override fun onComplete() {}

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "Error", e)
                    }

                    override fun onNext(l: Listing) {
                        view?.showListingItem(l)
                    }
                }).addTo(disposables)
    }
}