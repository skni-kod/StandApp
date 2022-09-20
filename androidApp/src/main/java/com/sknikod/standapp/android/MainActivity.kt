package com.sknikod.standapp.android

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setupWithNavController(navController)


    }

    override fun setTitle(title: CharSequence?) {
        findViewById<TextView>(R.id.title).text=title
    }
    fun setViabilityTitle(boolean: Boolean){
        if(boolean)  findViewById<TextView>(R.id.title).visibility= View.VISIBLE
        else findViewById<TextView>(R.id.title).visibility= View.GONE

    }

}
