package com.sknikod.standapp.android.ui.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sknikod.standapp.domain.model.Project
import com.sknikod.standapp.domain.repository.RepositoryProject
import com.sknikod.standapp.uti.onFailure
import com.sknikod.standapp.uti.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(private val repositoryProject: RepositoryProject) : ViewModel() {
    private val _listProjects = MutableStateFlow<com.sknikod.standapp.uti.Result<List<Project>>>(
        com.sknikod.standapp.uti.Result.Init()
    )
    val listProjects: StateFlow<com.sknikod.standapp.uti.Result<List<Project>>> = _listProjects

    fun getListProjects() {
        viewModelScope.launch {
            _listProjects.emit(com.sknikod.standapp.uti.Result.Loading())
            repositoryProject.getListOfProjects().onSuccess {
                _listProjects.emit(com.sknikod.standapp.uti.Result.Success(it))
            }.onFailure {
                _listProjects.emit(com.sknikod.standapp.uti.Result.Error(it))
            }
        }
    }
}
