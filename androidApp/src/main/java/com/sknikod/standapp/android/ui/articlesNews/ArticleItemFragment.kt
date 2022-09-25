package com.sknikod.standapp.android.ui.articlesNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sknikod.standapp.android.R


/**
 * A simple [Fragment] subclass.
 * Use the [ArticleItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleItemFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_item, container, false)
    }


}