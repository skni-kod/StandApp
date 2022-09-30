package com.sknikod.standapp.android.ui.news

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
import com.sknikod.standapp.android.ui.adapters.NewsAdapter
import com.sknikod.standapp.android.uti.FragmentBase
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ListNewsFragment : FragmentBase() {
    private val viewModel: NewsViewModel by inject()
    private val articleList = viewModel.listNews
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            articleList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect { listArticles ->
                when (listArticles) {
                    is com.sknikod.standapp.uti.Result.Success ->
                        {
                            adapter = NewsAdapter(
                                articleList.value.value?.filter { it.group != "News" } ?: listOf()
                            )
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
