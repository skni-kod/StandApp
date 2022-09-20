package com.sknikod.standapp.android.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sknikod.standapp.android.R
import com.sknikod.standapp.domain.repository.Repository
import com.sknikod.standapp.domain.repository.RepositoryProject
import com.sknikod.standapp.uti.onSuccess
import org.koin.android.ext.android.inject

class ProjectItemFragment : Fragment() {
    private val greeting: RepositoryProject by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        lifecycleScope.launchWhenCreated {
            greeting.getListOfProjects().onSuccess { projects ->

                view?.findViewById<WebView>(R.id.web_view_project)?.loadData(projects.find { it.id==arguments?.getInt("id") }?.text?:"0",null,null)
            }
        }
        return inflater.inflate(R.layout.fragment_project_item, container, false)
    }

}
