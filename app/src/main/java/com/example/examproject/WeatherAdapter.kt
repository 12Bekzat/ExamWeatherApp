package com.example.examproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.awt.font.TextAttribute

class WeatherAdapter(private val weathers: ArrayList<String>) : RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val weather :TextView = view.findViewById(R.id.weather_city_name) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item,parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: String = weathers[position]
        holder.weather.text = item
    }


}