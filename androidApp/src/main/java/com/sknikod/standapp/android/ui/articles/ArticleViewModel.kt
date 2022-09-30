package com.sknikod.standapp.android.ui.articles

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

class ArticleViewModel(private val repositoryArticle: RepositoryArticle) : ViewModel() {
    private val _listArticles = MutableStateFlow<Result<List<Article>>>(
        Result.Init()
    )
    val listArticles: StateFlow<Result<List<Article>>> = _listArticles
    private val _article = MutableStateFlow<Result<Article>>(
        Result.Init()
    )
    val article: StateFlow<Result<Article>> = _article
    fun getListProjects() {
        viewModelScope.launch {
            _listArticles.emit(Result.Loading())
            repositoryArticle.getListOfArticles().onSuccess { list ->
                _listArticles.emit(
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
                _listArticles.emit(Result.Error(it))
            }
        }
    }
    fun getProject(id: Int) {
        viewModelScope.launch {
            _article.emit(Result.Loading())
            repositoryArticle.getArticle(id).onSuccess { item ->
                _article.emit(
                    Result.Success(
                        item.copy(

                            text = item.text.replace("---readmore---", "")
                        )

                    )
                )
            }.onFailure {
                _listArticles.emit(Result.Error(it))
            }
        }
    }
}
