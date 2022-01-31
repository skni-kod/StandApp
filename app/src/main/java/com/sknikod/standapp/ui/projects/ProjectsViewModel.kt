package com.sknikod.standapp.ui.projects

import androidx.lifecycle.ViewModel
import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val repository:StandappRepositoryImpl
): ViewModel()
{

}
