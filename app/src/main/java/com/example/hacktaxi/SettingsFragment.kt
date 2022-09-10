package com.example.hacktaxi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hacktaxi.databinding.FragmentRoadsBinding
import com.example.hacktaxi.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class SettingsFragment : Fragment() {
    lateinit var databaseReference: DatabaseReference
    lateinit var auth : FirebaseAuth
    var road_list = ArrayList<Road>()
    lateinit var settings_bin: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settings_bin = FragmentSettingsBinding.inflate(layoutInflater)
        settings_bin.name.setText(pref.getString("Name", ""))
        settings_bin.button.setOnClickListener{
            Firebase.auth.signOut()
            val creator = pref.edit()
            creator.remove("Name")
            creator.remove("UserId")
            creator.apply()
            startActivity(Intent(this.context , Auth::class.java))
            activity?.finish()
        }
        settings_bin.myRoads.layoutManager = LinearLayoutManager(activity)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Roads")
        readMessage(databaseReference)
        return settings_bin.root
    }
    private fun readMessage(databaseReference: DatabaseReference){
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                road_list.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children){
                    val user = dataSnapShot.getValue(Road::class.java)
                    if (user!!.driverId == pref.getString("UserId", "-")) {
                        road_list.add(user)
                    }
                }
                val adapter = my_roads_adapter(activity, road_list)
                settings_bin.myRoads.adapter = adapter
            }

            override fun onCancelled(error : DatabaseError) {
                Toast.makeText(activity, "Failed!", Toast.LENGTH_SHORT).show()
            }

        })

    }
    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}