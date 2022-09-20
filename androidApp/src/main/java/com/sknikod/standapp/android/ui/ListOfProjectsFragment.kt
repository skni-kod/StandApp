package com.sknikod.standapp.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sknikod.standapp.android.MainActivity
import com.sknikod.standapp.android.R
import com.sknikod.standapp.android.ui.adapters.ProjectAdapter
import com.sknikod.standapp.domain.repository.RepositoryProject
import com.sknikod.standapp.uti.onSuccess
import org.koin.android.ext.android.inject

class ListOfProjectsFragment : Fragment() {
    private val greeting: RepositoryProject by inject()
    lateinit var  adapter :ProjectAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_projects, container, false)
        val rvContacts =view.findViewById<RecyclerView>(R.id.rvProjects)
        (activity as MainActivity).setViabilityTitle(boolean = false)
        lifecycleScope.launchWhenCreated {

            greeting.getListOfProjects().onSuccess { projects ->
                adapter= ProjectAdapter(projects)
                rvContacts?.adapter=adapter
                rvContacts?.layoutManager = LinearLayoutManager(context)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}
