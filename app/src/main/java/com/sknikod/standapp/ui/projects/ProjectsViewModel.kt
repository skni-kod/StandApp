package com.sknikod.standapp.ui.projects

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl;
import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.use_case.GetProjects

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val projects: GetProjects
): ViewModel()
{
    private val _projectsState = mutableStateOf(ProjectUiState())
    val projectsState: State<ProjectUiState> = _projectsState

    private var currentJob: Job? = null
    fun load(){
        _projectsState.value=projectsState.value.copy(
            loading=true)
        currentJob = viewModelScope.launch {
            val result=projects()
            _projectsState.value=projectsState.value.copy(
                dataToDisplayOnScreen = result.data ?: emptyList(),
                loading = false
            )
        }

    }

}
