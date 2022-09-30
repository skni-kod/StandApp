package com.sknikod.standapp.android.ui.news

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
import com.sknikod.standapp.android.ui.articles.ArticleViewModel
import com.sknikod.standapp.android.uti.FragmentBase
import org.koin.android.ext.android.inject

class NewsItemFragment : FragmentBase() {
    private val viewModel: ArticleViewModel by inject()
    private val projectArticle = viewModel.listArticles

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, null, false)

        val markdown = view?.findViewById<ComposeView>(R.id.web_view_news)
        lifecycleScope.launchWhenCreated {
            viewModel.getListProjects()

            projectArticle.collect { data ->
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
                                        text = projectItem?. text?.replace("---readmore---", " ") ?: "0",
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
