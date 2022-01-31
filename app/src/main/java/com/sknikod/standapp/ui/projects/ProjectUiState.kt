package com.sknikod.standapp.ui.projects

import com.sknikod.standapp.domain.model.ProjectItem

data class ProjectUiState(
    val dataToDisplayOnScreen: List<ProjectItem> = emptyList(),
    val loading: Boolean = false
){}
