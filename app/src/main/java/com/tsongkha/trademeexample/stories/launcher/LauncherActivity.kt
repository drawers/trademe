package com.tsongkha.trademeexample.stories.launcher

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tsongkha.trademeexample.R
import com.tsongkha.trademeexample.stories.categories.CategoryContract

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent()
                .setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse("tme://categories.trademe.co.nz/"))
                .putExtra(CategoryContract.EXTRA_CATEGORY_NAME, getString(R.string.categories))
        startActivity(intent)
        finish()
    }
}
