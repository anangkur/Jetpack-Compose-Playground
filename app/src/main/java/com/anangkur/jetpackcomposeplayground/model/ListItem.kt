package com.anangkur.jetpackcomposeplayground.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(
    val title: String,
    val image: String,
    val desc: String,
    val author: String
): Parcelable