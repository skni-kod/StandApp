package com.sknikod.standapp.android.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.composethemeadapter.MdcTheme
import com.mukesh.MarkDown
import com.sknikod.standapp.android.R
import com.sknikod.standapp.android.uti.FragmentBase
import org.koin.android.ext.android.inject

class ProjectItemFragment : FragmentBase() {
    private val viewModel: ProjectViewModel by inject()
    private val projectList = viewModel.listProjects



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project, null, false)

        val markdown = view?.findViewById<ComposeView>(R.id.web_view_project)
        lifecycleScope.launchWhenCreated {
            viewModel.getListProjects()

            projectList.collect { data ->
                when (data) {
                    is com.sknikod.standapp.uti.Result.Success -> {
                        val projectItem = data.value?.find {
                            it.id == arguments?.getInt(
                                "id"
                            )
                        }
                        view?.rootView?.findViewById<MaterialToolbar>(R.id.topAppBar)?.title = projectItem?.title
                        markdown?.apply {
                            // Dispose of the Composition when the view's LifecycleOwner is destroyed
                            setViewCompositionStrategy(
                                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
                            )
                            setContent {
                                // In Compose world
                                MdcTheme {
                                    MarkDown(
                                        text = projectItem?. text?.replace("---readmore---"," ") ?: "0",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
        return view
    }


}
