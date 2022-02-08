package com.sknikod.standapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sknikod.standapp.ui.BottomNavigationBar
import com.sknikod.standapp.ui.Navigation
import com.sknikod.standapp.ui.theme.StandAppTheme
import com.sknikod.standapp.util.SpecifedBottomNavigationItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StandAppTheme {
                val navController=rememberNavController()
                val scaffoldState= rememberScaffoldState()
                val items = listOf(SpecifedBottomNavigationItem.Home,
                    SpecifedBottomNavigationItem.Articles,
                    SpecifedBottomNavigationItem.Projects,
                    SpecifedBottomNavigationItem.User)
                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        if(navController
                        .currentBackStackEntryAsState().value?.destination
                                ?.route in items.map { it.route })
                        BottomNavigationBar(
                            items,
                            navController = navController
                        )
                    }
                ) {
                    Navigation(navController=navController,
                        scaffoldState = scaffoldState)
                }
            }
        }
    }
}
