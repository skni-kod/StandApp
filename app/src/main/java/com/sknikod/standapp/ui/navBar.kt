package com.sknikod.standapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.sknikod.standapp.ui.projects.ProjectsScreen
import com.sknikod.standapp.util.SpecifedBottomNavigationItem

@Composable
fun Navigation(navController: NavHostController){
    NavHost(
        navController=navController,
        "home"
    ){
        composable("home"){
            SimpleComposable()
        }
        composable("articles"){

        }
        composable("articles/{articleId}",
            arguments = listOf(
                navArgument("articleId"){
                    type= NavType.IntType
                })
        ) {
            val rememberedId = remember {
                it.arguments?.getInt("articleId")
            }
        }
        composable("projects"){
            ProjectsScreen()
        }
        composable("projects/{projectId}",
            arguments = listOf(
                navArgument("projectId"){
                    type= NavType.IntType
                }
            )){
            val rememberedId = remember {
                it.arguments?.getInt("projectId")
            }
        }
        composable("user"){

        }
    }
}
@Composable
fun BottomNavigationBar(
    items:List<SpecifedBottomNavigationItem>,
    navController: NavController,
    modifier: Modifier = Modifier
){
    BottomNavigation(modifier ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach{
                item->BottomNavigationItem(
            icon = { Icon(item.icon, contentDescription = null) },
            label = { Text(item.name) },
            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
            onClick = {
                navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true
                    restoreState = true
                }

            }
        )
        }

    }
}

@Preview
@Composable
fun SimpleComposable() {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = "Home View",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}
