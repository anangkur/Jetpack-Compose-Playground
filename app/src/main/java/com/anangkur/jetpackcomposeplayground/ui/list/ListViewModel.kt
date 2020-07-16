package com.anangkur.jetpackcomposeplayground.ui.list

import androidx.lifecycle.*
import com.anangkur.jetpackcomposeplayground.data.Repository
import com.anangkur.jetpackcomposeplayground.data.remote.model.ArticleModel
import com.anangkur.jetpackcomposeplayground.mapper.ListItemMapper
import com.anangkur.jetpackcomposeplayground.model.ListItem
import kotlinx.coroutines.launch
import com.anangkur.jetpackcomposeplayground.model.Result
import com.anangkur.jetpackcomposeplayground.model.error

class ListViewModel: ViewModel() {

    companion object {
        const val RESPONSE_OK = "ok"
    }

    lateinit var repository: Repository

    private val listItemMapper = ListItemMapper()

    private val _news = MutableLiveData<Result<List<ArticleModel>>>()
    val news: LiveData<Result<List<ListItem>>> = _news.map { result ->
        if (result is Result.Success) {
            Result.Success(result.data.map { listItemMapper.mapToUiModel(it) })
        } else {
            Result.Error(result.error())
        }
    }

    fun getNews() {
        viewModelScope.launch {
            try {
                val response = repository.getNews()
                if (response.status == RESPONSE_OK) {
                    _news.postValue(Result.Success(response.articles))
                } else {
                    _news.postValue(Result.Error(IllegalArgumentException(response.message)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _news.postValue(Result.Error(e))
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