package com.example.coordinatorlayoutmax

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.rv_main)
        rv.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_demo, parent, false)) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            }

            override fun getItemCount(): Int = 16
        }
    }
}