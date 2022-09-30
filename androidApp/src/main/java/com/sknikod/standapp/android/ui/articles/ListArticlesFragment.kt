package com.sknikod.standapp.android.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sknikod.standapp.android.R
import com.sknikod.standapp.android.ui.adapters.ArticleAdapter
import com.sknikod.standapp.android.uti.FragmentBase
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ListArticlesFragment : FragmentBase() {
    private val viewModel: ArticleViewModel by inject()
    private val articleList = viewModel.listArticles
    lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_articles, container, false)
        val rvContacts = view.findViewById<RecyclerView>(R.id.rv_articles)
        if (articleList.value !is com.sknikod.standapp.uti.Result.Success) {
            viewModel.getListProjects()
        }
        lifecycleScope.launch {
            articleList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {
                when (it) {
                    is com.sknikod.standapp.uti.Result.Success ->
                        {
                            adapter = ArticleAdapter(articleList.value.value ?: listOf())
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
