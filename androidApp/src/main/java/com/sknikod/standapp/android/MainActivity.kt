package com.sknikod.standapp.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sknikod.standapp.android.ui.projects.ProjectViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.topAppBar))
        setUpAppBar()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setupWithNavController(navController)
    }

    private fun setUpAppBar() {
        supportActionBar?.title = " "
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        topAppBar.setNavigationOnClickListener { _ ->
            navController.navigateUp()
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (listOf(
                    R.id.listOfProjects,
                    R.id.listOfArticles,
                    R.id.listOfNews,
                    R.id.accountFragment
                ).any { it == destination.id }
            ) {
                supportActionBar?.hide()
                // supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }
}
