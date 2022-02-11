package com.sknikod.standapp.ui.projects

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.model.Section
import com.sknikod.standapp.domain.use_case.GetProject

import com.sknikod.standapp.domain.use_case.GetProjects
import com.sknikod.standapp.domain.use_case.GetSections

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.sknikod.standapp.util.NetworkResult


@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val useCaseProjects: GetProjects,
    private val useCaseProject: GetProject,
    private val useCaseSections: GetSections
): ViewModel() {
    private val _dataOfProjects = MutableStateFlow(ProjectUiState<List<ProjectItem>>(emptyList()))
    val dataOfProjects: StateFlow<ProjectUiState<List<ProjectItem>>> = _dataOfProjects

    private val _infoProject = MutableStateFlow(ProjectUiState(""))
    val infoProject: StateFlow<ProjectUiState<String>> = _infoProject

    private val _dataOfSections = MutableStateFlow(ProjectUiState<List<Section>>(emptyList()))
    val dataOfSections: StateFlow<ProjectUiState<List<Section>>> = _dataOfSections

    private var currentJob: Job? = null
    fun loadProjects() {
        _dataOfProjects.value = dataOfProjects.value.copy(
            loading = true
        )
        currentJob = viewModelScope.launch {
            when (val result = useCaseProjects()) {
                is NetworkResult.Success -> {
                    //Log.e("Test", "test1");
                    _dataOfProjects.value = dataOfProjects.value.copy(
                        dataToDisplayOnScreen = result.data ?: emptyList(),
                        loading = false
                    )

                }
                else -> {
                    Log.e("Test", result.exception.toString())
                    _dataOfProjects.value = dataOfProjects.value.copy(
                        dataToDisplayOnScreen = result.data ?: emptyList(),
                        loading = false
                    )
                }


            }

        }
    }

    fun loadTheProject(id: Int) {
        /* _infoProject.value= _dataOfProjects.value
            .dataToDisplayOnScreen.elementAt(id).toString()*/
        _infoProject.value = infoProject.value.copy(
            loading = true
        )

        currentJob = viewModelScope.launch {
            when (val result = useCaseProject(id)) {
                is NetworkResult.Success -> {
                    _infoProject.value = infoProject.value.copy(
                        dataToDisplayOnScreen = result.data?.text ?: "",
                        loading = false
                    )


                }
                else -> {
                    //TODO
                    // ADD ERROR CATCH EXAMPLE SNCAK
                    _infoProject.value = infoProject.value.copy(
                        dataToDisplayOnScreen = result.data?.text ?: "",
                        loading = false
                    )
                }


            }

        }
    }

    fun loadSections() {
        _dataOfSections.value = dataOfSections.value.copy(
            loading = true
        )

        currentJob = viewModelScope.launch {
            when (val result = useCaseSections()) {
                is NetworkResult.Success -> {
                    _dataOfSections.value = dataOfSections.value.copy(
                        dataToDisplayOnScreen = result.data ?: emptyList(),
                        loading = false
                    )


                }
                else -> {
                    //TODO
                    // ADD ERROR CATCH EXAMPLE SNCAK
                    _dataOfSections.value = dataOfSections.value.copy(
                        dataToDisplayOnScreen = result.data ?: emptyList(),
                        loading = false
                    )
                }


            }
        }
    }
}
