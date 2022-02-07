package com.sknikod.standapp.ui.projects

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel = hiltViewModel(),
    navController: NavController
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
                        Text(text = info.title.toString(),
                            modifier = Modifier.clickable(
                            onClick = {
                                navController.navigate("projects/${info.id.toInt()}")})

                        )
                    }
                }
            }
        }


        }
}
@Composable
fun ProjectsScreenItem(
    viewModel: ProjectsViewModel = hiltViewModel(),
    navController: NavController,
    scaffoldState: ScaffoldState
){
    LaunchedEffect(key1 = true){
        navController.currentBackStackEntry?.arguments?.getInt("projectId")
            ?.let { viewModel.loadSpecifedProject(it) }
    }
    Scaffold(scaffoldState = scaffoldState,topBar ={
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Row(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to projects",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Wstecz")
            }
        }
    } ) {
    Box{

    }
    }
}
