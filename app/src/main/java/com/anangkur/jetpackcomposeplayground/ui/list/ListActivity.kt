package com.anangkur.jetpackcomposeplayground.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.anangkur.jetpackcomposeplayground.R
import com.anangkur.jetpackcomposeplayground.model.ListItem

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
                    ListItem(title = "Test 1", desc = "Desc Test 1", image = ""),
                    ListItem(title = "Test 2", desc = "Desc Test 2", image = ""),
                    ListItem(title = "Test 3", desc = "Desc Test 3", image = ""),
                    ListItem(title = "Test 4", desc = "Desc Test 4", image = ""),
                    ListItem(title = "Test 5", desc = "Desc Test 5", image = "")
                ),
                onClick = { listItem -> this.onClickItem(listItem) }
            )
        }
    }

    override fun onClickItem(data: ListItem) {
        Toast.makeText(this, data.title, Toast.LENGTH_SHORT).show()
    }
}