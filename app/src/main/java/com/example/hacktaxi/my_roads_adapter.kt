package com.example.hacktaxi

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class my_roads_adapter(private val context : FragmentActivity?, private val roadList : ArrayList<Road>):
    RecyclerView.Adapter<my_roads_adapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var from: TextView = view.findViewById(R.id.form_item)
        var to: TextView = view.findViewById(R.id.to_item)
        var date: TextView = view.findViewById(R.id.date_item)
        var time: TextView = view.findViewById(R.id.time_item)
        var places: TextView = view.findViewById(R.id.places_item)
        var trash: ImageView = view.findViewById(R.id.trash)
        var vse: TextView = view.findViewById(R.id.vse)
        var number: TextView = view.findViewById(R.id.number_et)
        var driver: TextView = view.findViewById(R.id.driver_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.road_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val road = roadList[position]
        holder.from.text = road.from
        holder.to.text = road.to
        holder.date.text = road.date
        holder.time.text = road.time
        holder.places.text = road.places
        holder.number.text = road.number
        holder.driver.text = road.driver
        holder.trash.visibility = View.VISIBLE
        holder.trash.setOnClickListener {
            road.active = "False"
            holder.vse.visibility = View.VISIBLE
            holder.driver.visibility = View.GONE
            holder.number.visibility = View.GONE
            holder.trash.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return roadList.size
    }
}