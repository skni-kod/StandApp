package com.sknikod.standapp.android.ui.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sknikod.standapp.android.uti.changeList
import com.sknikod.standapp.domain.model.Project
import com.sknikod.standapp.domain.repository.RepositoryProject
import com.sknikod.standapp.uti.Result
import com.sknikod.standapp.uti.onFailure
import com.sknikod.standapp.uti.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(private val repositoryProject: RepositoryProject) : ViewModel() {
    private val _listProjects = MutableStateFlow<Result<List<Project>>>(
        Result.Init()
    )
    val listProjects: StateFlow<Result<List<Project>>> = _listProjects
    private val _project = MutableStateFlow<Result<Project>>(
        Result.Init()
    )
    val project: StateFlow<Result<Project>> = _project
    fun getListProjects() {
        viewModelScope.launch {
            _listProjects.emit(Result.Loading())
            repositoryProject.getListOfProjects().onSuccess { list ->
                _listProjects.emit(
                    Result.Success(
                        list.toMutableList().changeList {
                            it.copy(
                                text = it.text.substringBefore("---readmore---").plus("...")
                            )
                        }
                    )
                )
            }.onFailure {
                _listProjects.emit(Result.Error(it))
            }
        }
    }
    fun getProject(id: Int) {
        viewModelScope.launch {
            _project.emit(Result.Loading())
            repositoryProject.getProject(id).onSuccess { item ->
                _project.emit(
                    Result.Success(
                        item.copy(

                            text = item.text.replace("---readmore---", "")
                        )

                    )
                )
            }.onFailure {
                _project.emit(Result.Error(it))
            }
        }
    }
}
