package com.sknikod.standapp.android.ui.projects

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sknikod.standapp.android.MainActivity
import com.sknikod.standapp.android.R
import com.sknikod.standapp.android.ui.adapters.ProjectAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class ListOfProjectsFragment : Fragment() {
    private val viewModel: ProjectViewModel  by inject()
    private val projectList = viewModel.listProjects
    lateinit var adapter: ProjectAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_projects, container, false)
        val rvContacts = view.findViewById<RecyclerView>(R.id.rvProjects)
        (activity as MainActivity).setViabilityTitle(boolean = false)
        if(projectList.value !is com.sknikod.standapp.uti.Result.Success)
            viewModel.getListProjects()
        lifecycleScope.launch {
            projectList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect{
                when(it){
                  is  com.sknikod.standapp.uti.Result.Success->
                      {
                          adapter = ProjectAdapter(projectList.value.value ?: listOf())
                          rvContacts?.adapter = adapter
                          rvContacts?.layoutManager = LinearLayoutManager(context)
                      }

                    else -> {}
                }
            }
        }


        return view
    }


}
