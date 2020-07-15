package com.anangkur.jetpackcomposeplayground.ui.list

import com.anangkur.jetpackcomposeplayground.model.ListItem

interface ListActionListener {
    fun onClickItem(data: ListItem)
}