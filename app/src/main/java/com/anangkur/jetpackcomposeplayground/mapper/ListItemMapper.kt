package com.anangkur.jetpackcomposeplayground.mapper

import com.anangkur.jetpackcomposeplayground.data.remote.model.ArticleModel
import com.anangkur.jetpackcomposeplayground.model.ListItem

class ListItemMapper: Mapper<ListItem, ArticleModel> {
    override fun mapToUiModel(data: ArticleModel): ListItem {
        return ListItem(
            title = data.title ?: "",
            desc = data.content ?: "",
            image = data.urlToImage ?: ""
        )
    }
}