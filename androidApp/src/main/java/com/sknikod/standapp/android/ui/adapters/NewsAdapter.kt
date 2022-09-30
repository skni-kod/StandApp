package com.sknikod.standapp.android.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sknikod.standapp.android.R
import com.sknikod.standapp.domain.model.Article

class NewsAdapter(private val data: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val data: TextView
        val text: TextView
        var id: Int? = null
        init {
            title = view.findViewById(R.id.item_article_news_title)
            data = view.findViewById(R.id.item_article_news_data)
            text = view.findViewById(R.id.item_article_news_text)

            view.findViewById<CardView>(R.id.item_article_news_card).setOnClickListener {
                view.findNavController().navigate(R.id.newsItem, args = bundleOf("id" to id))
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article_news, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = data[position].title
        viewHolder.data.text = data[position].creationDate
        viewHolder.text.text = data[position].text
        viewHolder.id = data[position].id
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}
