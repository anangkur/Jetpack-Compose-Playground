package com.anangkur.jetpackcomposeplayground.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.anangkur.jetpackcomposeplayground.R
import com.anangkur.jetpackcomposeplayground.model.ListItem
import com.anangkur.jetpackcomposeplayground.ui.detail.DetailActivity

class ListActivity : AppCompatActivity(), ListActionListener {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            listScreenContent(
                appTitle = getString(R.string.app_name),
                data = listOf(
                    ListItem(title = "Test 1", desc = "Desc Test 1", image = "image test"),
                    ListItem(title = "Test 2", desc = "Desc Test 2", image = "image test"),
                    ListItem(title = "Test 3", desc = "Desc Test 3", image = "image test"),
                    ListItem(title = "Test 4", desc = "Desc Test 4", image = "image test"),
                    ListItem(title = "Test 5", desc = "Desc Test 5", image = "image test")
                ),
                onClick = { listItem -> this.onClickItem(listItem) }
            )
        }
    }

    override fun onClickItem(data: ListItem) {
        DetailActivity.startActivity(this, data)
    }
}