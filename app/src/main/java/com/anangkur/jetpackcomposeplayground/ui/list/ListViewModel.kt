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

    companion object {
        const val RESPONSE_OK = "ok"
    }

    lateinit var repository: Repository

    private val listItemMapper = ListItemMapper()

    private val _news = MutableLiveData<List<ArticleModel>>()
    val news: LiveData<List<ListItem>> = _news.map { list ->
        list.map { listItemMapper.mapToUiModel(it) }
    }

    fun getNews() {
        viewModelScope.launch {
            try {
                val response = repository.getNews()
                if (response.status == RESPONSE_OK) {
                    _news.postValue(response.articles)
                } else {
                    _news.postValue(emptyList())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _news.postValue(emptyList())
            }
        }
    }

    fun getNews(callback: (Result<List<ListItem>>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.getNews()
                if (response.status == RESPONSE_OK) {
                    callback(Result.Success(response.articles.map { listItemMapper.mapToUiModel(it) }))
                } else {
                    callback(Result.Error(IllegalArgumentException(response.message)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                callback(Result.Error(e))
            }
        }
    }
}