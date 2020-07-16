package com.anangkur.jetpackcomposeplayground.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.ui.core.setContent
import com.anangkur.jetpackcomposeplayground.R
import com.anangkur.jetpackcomposeplayground.data.remote.NewsApi
import com.anangkur.jetpackcomposeplayground.data.remote.RemoteRepository
import com.anangkur.jetpackcomposeplayground.model.ListItem
import com.anangkur.jetpackcomposeplayground.model.succeeded
import com.anangkur.jetpackcomposeplayground.model.successOr
import com.anangkur.jetpackcomposeplayground.ui.detail.DetailActivity

class ListActivity : AppCompatActivity(), ListActionListener {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ListActivity::class.java))
        }
    }

    lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.repository = RemoteRepository(newsApi = NewsApi.getApiService)

        setContent {
            refreshableStateComponent(
                appTitle = getString(R.string.app_name),
                viewModel = viewModel,
                onClick = { onBackPressed() }
            )
        }

        viewModel.getNews()
    }

    override fun onClickItem(data: ListItem) {
        DetailActivity.startActivity(this, data)
    }
}