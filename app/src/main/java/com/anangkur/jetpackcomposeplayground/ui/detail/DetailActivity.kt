package com.anangkur.jetpackcomposeplayground.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.anangkur.jetpackcomposeplayground.model.ListItem

class DetailActivity: AppCompatActivity() {

    companion object {

        /**
         * TODO using Parcelize object
         * cannot using parcelize object for now due to this error:
         * java.lang.AbstractMethodError: abstract method "void android.os.Parcelable.writeToParcel(android.os.Parcel, int)"
         */

        private const val EXTRA_DETAIL_TITLE = "extra-detail-title"
        private const val EXTRA_DETAIL_DESC = "extra-detail-desc"
        private const val EXTRA_DETAIL_IMAGE = "extra-detail-image"

        fun startActivity(context: Context, data: ListItem) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_TITLE, data.title)
                putExtra(EXTRA_DETAIL_DESC, data.desc)
                putExtra(EXTRA_DETAIL_IMAGE, data.image)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = ListItem(
            title = intent.getStringExtra(EXTRA_DETAIL_TITLE) ?: "",
            image = intent.getStringExtra(EXTRA_DETAIL_IMAGE) ?: "",
            desc = intent.getStringExtra(EXTRA_DETAIL_DESC) ?: ""
        )

        setContent {
            detailScreenContent(
                screenTitle = data.title,
                onNavigationClick = { onBackPressed() },
                data = data
            )
        }
    }
}