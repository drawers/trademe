package com.tsongkha.trademeexample.stories.categories

import android.content.Intent
import android.net.Uri
import com.tsongkha.trademeexample.stories.categories.repository.CategoryRepository
import com.tsongkha.trademeexample.stories.common.RxPresenter
import com.tsongkha.trademeexample.stories.common.rx.CompositeDisposableProvider
import com.tsongkha.trademeexample.stories.common.rx.Schedulers
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

/**
 * Created by rawsond on 8/12/17.
 */
class CategoryPresenter @Inject constructor(private val categoryRepository: CategoryRepository,
                                            compositeDisposableProvider: CompositeDisposableProvider,
                                            schedulers: Schedulers) :
        RxPresenter<CategoryContract.View>(compositeDisposableProvider, schedulers), CategoryContract.Presenter {

    override fun loadCategory(number: String?) {
        val observableCategory: Observable<Category>
        when (number) {
            null -> observableCategory = categoryRepository.root(1)
            else -> observableCategory = categoryRepository.byNumber(number, 1)
        }

        observableCategory
                .flatMap { c -> c.subcategories()?.toObservable() }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribeWith(object : DisposableObserver<Category>() {
                    override fun onComplete() { }

                    override fun onError(e: Throwable) {
                        e.message?.apply {
                            view?.showError(this)
                        }
                    }

                    override fun onNext(category: Category) {
                        view?.showSubcategoryItem(category)
                    }
                })
                .addTo(disposables)
    }

    override fun nextCategory(category: Category) {
        val authority: String = when {
            category.isLeaf -> "listings.trademe.co.nz"
            else -> "categories.trademe.co.nz"
        }
        val uri = Uri.Builder()
                .scheme("tme")
                .authority(authority)
                .appendPath(category.number())
                .build()
        val intent = Intent().setAction(Intent.ACTION_VIEW)
                .setData(uri)
                .putExtra(CategoryContract.EXTRA_CATEGORY_NAME, category.name())
        view?.showNextView(intent)
    }
}