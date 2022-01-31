package com.sknikod.standapp.ui.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel = hiltViewModel()
) {
    viewModel.load()
        Box(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(viewModel.projectsState.value.dataToDisplayOnScreen.size) { i ->
                        val info = viewModel.projectsState.value.dataToDisplayOnScreen[i]
                        if(i > 0) {
                            Spacer(modifier = Modifier.height(1.dp))
                        }
                        Text(text = info.title.toString())
                    }
                }
            }

        }



}