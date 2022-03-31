package com.sachinsandbhor.articles.presentation.articlelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachinsandbhor.articles.data.api.RetrofitInstance
import com.sachinsandbhor.articles.data.entity.ArticlesEntity
import kotlinx.coroutines.launch

private const val TAG="MainViewModel"
class MainViewModel: ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading:LiveData<Boolean>
        get() = _isLoading

    private val _topArticles = MutableLiveData<ArticlesEntity>()
    val topArticles: LiveData<ArticlesEntity>
        get() = _topArticles

    fun getTopArticles() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchArticles = RetrofitInstance.articlesApi.getTopArticles()
            _topArticles.value = fetchArticles
            _isLoading.value = false
        }
    }
}