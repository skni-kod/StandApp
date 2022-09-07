package com.sknikod.standapp.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sknikod.standapp.domain.repository.Repository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val greeting: Repository by inject()
        lifecycleScope.launchWhenCreated {
            Log.d("test", greeting.getListOfProjects().joinToString { it.title })
        }
    }
}
