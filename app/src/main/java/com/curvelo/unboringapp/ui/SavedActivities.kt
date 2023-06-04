package com.curvelo.unboringapp.ui


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curvelo.unboringapp.R
import com.curvelo.unboringapp.adapter.SavedActivitiesAdapter

class SavedActivities : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_activities)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSavedActivities)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val savedActivities = getSavedActivities()
        val adapter = SavedActivitiesAdapter(savedActivities)
        recyclerView.adapter = adapter
    }

    private fun getSavedActivities(): List<String> {
        val sharedPref = getSharedPreferences("SavedActivities", Context.MODE_PRIVATE)
        val activitiesSet = sharedPref.getStringSet("activities", HashSet())
        return activitiesSet?.toList() ?: emptyList()
    }

    fun goBackToMainActivity(view: View) {
        finish()
    }

}

