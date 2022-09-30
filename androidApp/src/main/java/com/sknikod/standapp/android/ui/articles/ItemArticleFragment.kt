package com.sknikod.standapp.android.ui.articles

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
import com.sknikod.standapp.domain.model.Article
import org.koin.android.ext.android.inject

class ItemArticleFragment : FragmentBase() {
    private val viewModel: ArticleViewModel by inject()
    private val article = viewModel.article

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article, null, false)

        lifecycleScope.launchWhenCreated {
            viewModel.getProject(
                arguments?.getInt(
                    "id"
                ) ?: 0
            )

            article.collect { data ->
                when (data) {
                    is com.sknikod.standapp.uti.Result.Success -> {
                        setUpView(view, data)
                    }

                    else -> {}
                }
            }
        }
        return view
    }
    private fun setUpView(view: View, article: com.sknikod.standapp.uti.Result.Success<Article>) {
        val markdown = view.findViewById<ComposeView>(R.id.web_view_article)
        val projectItem = article.value
        view.rootView?.findViewById<MaterialToolbar>(R.id.topAppBar)?.title = projectItem?.title
        markdown?.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            setContent {
                // In Compose world
                MdcTheme {
                    MarkDown(
                        text = projectItem?. text ?: "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
