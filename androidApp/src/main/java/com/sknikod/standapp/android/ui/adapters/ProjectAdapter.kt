package com.sknikod.standapp.android.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sknikod.standapp.android.R
import com.sknikod.standapp.domain.model.Project

class ProjectAdapter(private val data: List<Project>) :
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val section: TextView
        val text: TextView
        var id:Int?=null
        init {
            // Define click listener for the ViewHolder's View.
            title = view.findViewById(R.id.item_project_title)
            section = view.findViewById(R.id.item_project_section)
            text = view.findViewById(R.id.item_project_text)

            view.findViewById<CardView>(R.id.card).setOnClickListener{
                view.findNavController().navigate(R.id.action_listOfProjects_to_projectItem, args = bundleOf("id" to id))
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_project, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.title.text = data[position].title
        viewHolder.section.text = data[position].section.name
        viewHolder.text.text = data[position].text.substringBefore("---readmore---")
        viewHolder.id=data[position].id
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}