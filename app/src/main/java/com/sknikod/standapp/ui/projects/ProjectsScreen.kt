package com.sknikod.standapp.ui.projects


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.model.Section
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel = hiltViewModel(),
    navController: NavController
) {
    val loadStatus:Boolean=viewModel.dataOfProjects.collectAsState().value.loading
    val dataOfProjects = viewModel.dataOfProjects.collectAsState().value.dataToDisplayOnScreen
    val dataOfSections = viewModel.dataOfSections.collectAsState().value.dataToDisplayOnScreen

    LaunchedEffect(key1 = true){
            viewModel.loadProjects()
            viewModel.loadSections()

    }
        Box(
            modifier = Modifier,
            contentAlignment=Alignment.TopCenter
        ) {
            SwipeRefresh(        state = rememberSwipeRefreshState(isRefreshing = loadStatus),
            onRefresh = { viewModel.loadProjects() }){
            Column(
                modifier = Modifier
                    .padding(16.dp)

            ) {
                Sections(sections = dataOfSections)
                LazyColumn(
                    modifier = Modifier
                    ) {
                    items(dataOfProjects.size) { i ->
                        val info = dataOfProjects[i]
                        ProjectCard(info) { navController.navigate("projects/${info.id}") }
                    }
                }
            }
        }


        }
}
@Composable
fun Sections(sections: List<Section>
){
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(sections.size) {i->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start=15.dp,bottom = 15.dp)
                    .clickable {
                        selectedIndex = i
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        // this is basic condition for selected chip index
                        if (selectedIndex == i) Color.Cyan
                        else Color.Transparent
                    )
                    .padding(7.dp)
                    .border(1.dp, color = Color.Black)
                    .padding(3.dp)
                    .size(200.dp,50.dp)
            ) {
                Text(text = sections[i].name, color = Color.Black,
                    fontSize = 13.sp, textAlign =  TextAlign.Center)
            }
        }
    }
}

@Composable
fun ProjectCard(
    project:ProjectItem,
    onItemClick:()->Unit
){
    val reg=Regex("---readmore---")
    val id=reg.find (project.text)
    val shortInfo= id?.range?.let { project.text.substring(0, it.first) }

    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        elevation = 10.dp
    ) {

        Column( modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            Text(modifier = Modifier.align(Alignment.End),
                fontSize=10.sp,
                text=project.section.name)
            Text(textAlign =  TextAlign.Left,
                color = Color.Black,
                fontSize = 16.sp,
                text = project.title)
        Row() {
            if (shortInfo != null) {
                Text(
                    textAlign = TextAlign.Left,
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    text = shortInfo
                )
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
    val text:String= viewModel.infoProject.collectAsState().value.dataToDisplayOnScreen
    LaunchedEffect(key1 = true){
        navController.currentBackStackEntry?.arguments?.getInt("projectId")
            ?.let { viewModel.loadTheProject(it) }
    }



    Scaffold(scaffoldState = scaffoldState, bottomBar = {},topBar ={
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Row(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to projects",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                        .clickable {
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
