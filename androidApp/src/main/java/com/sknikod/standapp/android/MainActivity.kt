package com.sknikod.standapp.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sknikod.standapp.domain.repository.Repository
import com.sknikod.standapp.uti.onFailure
import com.sknikod.standapp.uti.onSuccess
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val greeting: Repository by inject()
        lifecycleScope.launchWhenCreated {
            Log.d("test", "it.joinToString { ")
            greeting.getListOfProjects().onSuccess { Log.d("test", it.random().text) }
        }
    }
}
