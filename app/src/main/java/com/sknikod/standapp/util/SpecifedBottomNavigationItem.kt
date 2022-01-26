package com.sknikod.standapp.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

//data class BottomNavigationItem(val name: String, val route:String)
sealed class SpecifedBottomNavigationItem(val name: String, val route:String, val icon: ImageVector){
    object Home:SpecifedBottomNavigationItem("Home","home",Icons.Filled.Home)
    object Articles:SpecifedBottomNavigationItem("Articles","articles",Icons.Filled.Info)
    object Projects:SpecifedBottomNavigationItem("Projects","projects",Icons.Filled.Star)
    object User:SpecifedBottomNavigationItem("User","user",Icons.Filled.Person)


}
