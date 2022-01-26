package com.sknikod.standapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sknikod.standapp.ui.theme.StandAppTheme
import com.sknikod.standapp.util.SpecifedBottomNavigationItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StandAppTheme {
                val navController=rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(SpecifedBottomNavigationItem.Home,
                            SpecifedBottomNavigationItem.Articles,
                            SpecifedBottomNavigationItem.Projects,
                            SpecifedBottomNavigationItem.User),
                            navController = navController,
                            onItemClick = {navController.navigate(it.route)}
                        )
                    }
                ) {
                    Navigation(navController=navController)
                }
            }
        }
    }
}

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
        composable("projects"){

        }
        composable("user"){

        }
    }
}
@Composable
fun BottomNavigationBar(
    items:List<SpecifedBottomNavigationItem>,
    navController: NavController,
    modifier: Modifier=Modifier,
    onItemClick:(SpecifedBottomNavigationItem)->Unit
){
    BottomNavigation(modifier, backgroundColor = Color.Cyan ){
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
                onItemClick(item)
            }
                )
            }

        }
    }

@Preview
@Composable
fun SimpleComposable() {
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center){
        Text("llo World")

    }
}
