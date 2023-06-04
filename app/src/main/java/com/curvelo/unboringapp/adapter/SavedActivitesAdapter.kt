package com.curvelo.unboringapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.curvelo.unboringapp.R

class SavedActivitiesAdapter(
    private val activities: List<String>) : RecyclerView.Adapter<SavedActivitiesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_saved_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activities[position]
        holder.textViewActivity.text = activity



    }

    override fun getItemCount(): Int {
        return activities.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewActivity: TextView = itemView.findViewById(R.id.textViewActivity)

    }
}