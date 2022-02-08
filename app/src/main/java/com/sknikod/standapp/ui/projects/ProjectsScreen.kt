package com.sknikod.standapp.ui.projects

import android.text.Html
import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel = hiltViewModel(),
    navController: NavController
) {
    val int:Boolean=viewModel.projectsState.collectAsState().value.loading
    LaunchedEffect(key1 = true){
            viewModel.loadProjects()
    }
        Box(
            modifier = Modifier
        ) {SwipeRefresh(        state = rememberSwipeRefreshState(isRefreshing = int),
            onRefresh = { viewModel.loadProjects() }){
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
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clickable(onClick = {
                                    navController
                                        .navigate("projects/${info.id.toInt()}")
                                }),
                            elevation = 10.dp
                        ) {
                            Column(
                                modifier = Modifier.padding(15.dp)
                            ) {
                                Text(
                                    buildAnnotatedString {
                                        append("welcome to ")
                                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                                        ) {
                                            append("Jetpack Compose Playground")
                                        }
                                    }
                                )
                                Text(text = info.title.toString())

                            }
                        }


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
    val text:String=viewModel.infoProject.collectAsState().value.toString()


    LaunchedEffect(key1 = true){
        navController.currentBackStackEntry?.arguments?.getInt("projectId")
            ?.let { viewModel.loadSpecifedProject(it) }
    }

    Scaffold(scaffoldState = scaffoldState, bottomBar = {},topBar ={
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
    }) {
    Box{
        Text(text)

    }
    }
}
