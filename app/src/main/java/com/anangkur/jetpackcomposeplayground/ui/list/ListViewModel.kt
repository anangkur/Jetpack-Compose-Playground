package com.anangkur.jetpackcomposeplayground.ui.list

import androidx.lifecycle.*
import com.anangkur.jetpackcomposeplayground.data.Repository
import com.anangkur.jetpackcomposeplayground.data.remote.model.ArticleModel
import com.anangkur.jetpackcomposeplayground.mapper.ListItemMapper
import com.anangkur.jetpackcomposeplayground.model.ListItem
import kotlinx.coroutines.launch
import com.anangkur.jetpackcomposeplayground.model.Result
import com.anangkur.jetpackcomposeplayground.model.succeeded
import com.anangkur.jetpackcomposeplayground.model.successOr

class ListViewModel: ViewModel() {

    lateinit var repository: Repository

    private val listItemMapper = ListItemMapper()

    private val _news = MutableLiveData<Result<List<ArticleModel>>>()
    val news: LiveData<List<ListItem>> = _news.map {
        if (it.succeeded) {
            it.successOr(emptyList()).map { item -> listItemMapper.mapToUiModel(item) }
        } else {
            emptyList()
        }
    }

    fun getNews() {
        viewModelScope.launch {
            repository.getNews {
                _news.postValue(it)
            }
        }
    }
}