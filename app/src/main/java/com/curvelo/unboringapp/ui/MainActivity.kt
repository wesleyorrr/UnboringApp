package com.curvelo.unboringapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.curvelo.unboringapp.R

import com.curvelo.unboringapp.viewmodel.MainViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var txtView: TextView
    private lateinit var viewModel: MainViewModel
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtView = findViewById(R.id.txtView)
        val randomButton: Button = findViewById(R.id.randomButton)
        progressBar = findViewById(R.id.progressBar)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        randomButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.getRandomActivity()
        }
        progreessView()
        observeData()

    }

    private fun progreessView() {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

    }

    private fun observeData() {
        viewModel.activityData.observe(this, Observer { activity ->
            txtView.text = activity
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            txtView.text = errorMessage
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            txtView.visibility = if (isLoading) View.GONE else View.VISIBLE
        })

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            val activity = txtView.text.toString()
            saveActivity(activity)
            startActivity(Intent(this, SavedActivities::class.java))
        }
    }

    private fun saveActivity(activity: String) {
        val sharedPref = getSharedPreferences("SavedActivities", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val activitiesSet = sharedPref.getStringSet("activities", HashSet())?.toMutableSet()
        activitiesSet?.add(activity)
        editor.putStringSet("activities", activitiesSet)
        editor.apply()
    }
}