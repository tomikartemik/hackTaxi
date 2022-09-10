package com.example.hacktaxi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class road_adapter(private val context : FragmentActivity?, private val roadList : ArrayList<Road>):
    RecyclerView.Adapter<road_adapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var from: TextView = view.findViewById(R.id.form_item)
        var to: TextView = view.findViewById(R.id.to_item)
        var date: TextView = view.findViewById(R.id.date_item)
        var time: TextView = view.findViewById(R.id.time_item)
        var places: TextView = view.findViewById(R.id.places_item)
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
    }

    override fun getItemCount(): Int {
        return roadList.size
    }
}
//class road_adapter: ListAdapter<Road, road_adapter.ItemHolder>(ItemComparator())  {
//    class ItemHolder(private val binding: RoadItemBinding) : RecyclerView.ViewHolder(binding.root){
//        var from: TextView = binding.formItem
//        var to: TextView = binding.toItem
//        var date: TextView = binding.dateItem
//        var time: TextView = binding.timeItem
//        var places: TextView = binding.placesItem
//        fun bind(road: Road) = with(binding){
//            from.text = road.from
//            to.text = road.to
//            date.text = road.date
//            time.text = road.time
//            places.text = road.places
//        }
//        companion object{
//            fun create(parent: ViewGroup) : ItemHolder{
//                return ItemHolder(RoadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//            }
//        }
//    }
//    class ItemComparator: DiffUtil.ItemCallback<Road>(){
//        override fun areItemsTheSame(oldItem: Road, newItem: Road): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Road, newItem: Road): Boolean {
//            return  oldItem == newItem
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
//        return ItemHolder.create(parent)
//    }
//
//    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//}
//class road_adapter(private val context : FragmentActivity? , private val roadList : ArrayList<Road>):
//RecyclerView.Adapter<road_adapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : RecyclerView.ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.road_item, parent, false)
//        return RecyclerView.ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {
//        val user = roadList[position]
//        holder.
//    }
//
//    override fun getItemCount() : Int {
//        return roadList.size
//    }
//    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
//        var from: TextView = view.findViewById(R.id.form_item)
//        var to: TextView = view.findViewById(R.id.to_item)
//        var date: TextView = view.findViewById(R.id.date_item)
//        var time: TextView = view.findViewById(R.id.time_item)
//        var places: TextView = view.findViewById(R.id.places_item)
//
//    }
//}