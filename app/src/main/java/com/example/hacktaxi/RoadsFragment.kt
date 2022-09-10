package com.example.hacktaxi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hacktaxi.databinding.FragmentRoadsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class RoadsFragment : Fragment() {
    lateinit var adapter: road_adapter
    lateinit var databaseReference: DatabaseReference
    lateinit var auth : FirebaseAuth
    lateinit var roads_bin: FragmentRoadsBinding
    var road_list = ArrayList<Road>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        roads_bin = FragmentRoadsBinding.inflate(inflater)
        roads_bin.rcView.layoutManager = LinearLayoutManager(activity)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Roads")
        readMessage(databaseReference)
        return roads_bin.root

    }

    private fun readMessage(databaseReference: DatabaseReference){
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot : DataSnapshot) {
                road_list.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children){
                    val user = dataSnapShot.getValue(Road::class.java)
                    if (user != null) {
                        road_list.add(user)
                    }
                }
                val adapter = road_adapter(activity, road_list)
                roads_bin.rcView.adapter = adapter
            }

            override fun onCancelled(error : DatabaseError) {
                Toast.makeText(activity, "Failed!", Toast.LENGTH_SHORT).show()
            }

        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = RoadsFragment()
    }
}