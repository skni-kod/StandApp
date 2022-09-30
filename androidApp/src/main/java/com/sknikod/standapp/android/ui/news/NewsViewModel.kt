package com.sknikod.standapp.android.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sknikod.standapp.android.uti.changeList
import com.sknikod.standapp.domain.model.Article
import com.sknikod.standapp.domain.repository.RepositoryArticle
import com.sknikod.standapp.uti.Result
import com.sknikod.standapp.uti.onFailure
import com.sknikod.standapp.uti.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repositoryArticle: RepositoryArticle) : ViewModel() {
    private val _listNews = MutableStateFlow<Result<List<Article>>>(
        Result.Init()
    )
    val listNews: StateFlow<Result<List<Article>>> = _listNews
    private val _news = MutableStateFlow<Result<Article>>(
        Result.Init()
    )
    val news: StateFlow<Result<Article>> = _news
    fun getListProjects() {
        viewModelScope.launch {
            _listNews.emit(Result.Loading())
            repositoryArticle.getListOfArticles().onSuccess { list ->
                _listNews.emit(
                    Result.Success(
                        list.toMutableList().changeList {
                            it.copy(
                                creationDate = it.creationDate.substringBefore("T"),
                                text = it.text.substringBefore("---readmore---").plus("...")
                            )
                        }
                    )
                )
            }.onFailure {
                _listNews.emit(Result.Error(it))
            }
        }
    }
    fun getProject(id: Int) {
        viewModelScope.launch {
            _news.emit(Result.Loading())
            repositoryArticle.getArticle(id).onSuccess { item ->
                _news.emit(
                    Result.Success(
                        item.copy(

                            text = item.text.replace("---readmore---", "")
                        )

                    )
                )
            }.onFailure {
                _news.emit(Result.Error(it))
            }
        }
    }
}
