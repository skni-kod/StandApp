package com.sknikod.standapp.ui.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel = hiltViewModel()
) {
    val int:Boolean=viewModel.projectsState.collectAsState().value.loading


    LaunchedEffect(key1 = true){
            viewModel.loadData()

    }
        Box(
            modifier = Modifier
        ) {SwipeRefresh(        state = rememberSwipeRefreshState(isRefreshing = int),
            onRefresh = { viewModel.loadData() }){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)

            ) {
                Text(text = "sas${int}")
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


}