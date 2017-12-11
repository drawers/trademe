package com.tsongkha.trademeexample.stories.categories

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tsongkha.trademeexample.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

/**
 * Created by rawsond on 8/12/17.
 */
class CategoryFragment : DaggerFragment(), CategoryContract.View {

    companion object {
        const val TAG = "CATEGORY"

        fun instantiate(args: Bundle): CategoryFragment {
            val frag = CategoryFragment()
            frag.arguments = args
            return frag
        }
    }

    @Inject lateinit var presenter: CategoryContract.Presenter
    @Inject lateinit var interactor: CategoryContract.Interactor
    @Inject lateinit var adapter: CategoryAdapter

    var actionBar: ActionBar? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this)
        category_rv.adapter = adapter
        category_rv.setHasFixedSize(true)
        category_rv.layoutManager = LinearLayoutManager(context)
        category_rv.layoutManager.isAutoMeasureEnabled = false
    }

    override fun onResume() {
        super.onResume()
        val categoryNumber = arguments?.getString(CategoryContract.ARG_CATEGORY_NUMBER)
        if (adapter.itemCount == 0) {
            presenter.loadCategory(categoryNumber)
        }
        actionBar = (activity as AppCompatActivity).supportActionBar
    }

    override fun showSubcategoryItem(category: Category) {
        adapter.add(category)
    }

    override fun showNextView(intent: Intent) {
        startActivity(intent)
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT)
                .show()
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }
}