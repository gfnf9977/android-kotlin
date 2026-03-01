package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val listViewHistory = findViewById<ListView>(R.id.listViewHistory)
        val tvEmpty = findViewById<TextView>(R.id.tvEmpty)

        val dbHelper = DatabaseHelper(this)
        val routesList = dbHelper.getAllRoutes()

        if (routesList.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            listViewHistory.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            listViewHistory.visibility = View.VISIBLE

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, routesList)
            listViewHistory.adapter = adapter
        }
    }
}