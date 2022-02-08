package com.sknikod.standapp.ui.projects

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sknikod.standapp.domain.use_case.GetProject

import com.sknikod.standapp.domain.use_case.GetProjects

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.sknikod.standapp.util.NetworkResult
import kotlinx.coroutines.flow.update


@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val projects: GetProjects,
    private val project: GetProject
): ViewModel()
{
    private val _projectsState = MutableStateFlow(ProjectUiState())
    val projectsState: StateFlow<ProjectUiState> = _projectsState

    private val  _infoProject =MutableStateFlow(String())
    val infoProject: StateFlow<String> = _infoProject

    private var currentJob: Job? = null
    fun loadProjects() {
        _projectsState.value = projectsState.value.copy(
            loading = true
        )
        currentJob = viewModelScope.launch {
            when (val result = projects()) {
                is NetworkResult.Success -> {
                    //Log.e("Test", "test1");
                    _projectsState.value = projectsState.value.copy(
                        dataToDisplayOnScreen = result.data ?: emptyList(),
                        loading = false
                    )

                }
                else -> {Log.e("Test", result.exception.toString())
                    _projectsState.value = projectsState.value.copy(
                        dataToDisplayOnScreen = result.data ?: emptyList(),
                        loading = false
                    )
                }


            }

        }
    }
    fun loadSpecifedProject(id:Int){
        currentJob = viewModelScope.launch {
            when (val result = project(id)) {
                is NetworkResult.Success -> {
                    //Debug
                    //Log.e("Test", "test21${result.data?.id}");
                    _infoProject.value=result.data?.text.toString()

                }
                else -> {Log.e("Test", result.exception.toString())
                    _projectsState.value = projectsState.value.copy(
                        dataToDisplayOnScreen =  emptyList(),
                        loading = false
                    )
                }


            }

        }
    }
}
