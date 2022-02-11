package com.sknikod.standapp.ui.projects

import com.sknikod.standapp.domain.model.ProjectItem

data class ProjectUiState<T>(
    val dataToDisplayOnScreen: T,
    val loading: Boolean = false
){}
