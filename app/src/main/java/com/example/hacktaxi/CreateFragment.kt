package com.example.hacktaxi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hacktaxi.databinding.FragmentCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.collections.HashMap


class CreateFragment : Fragment() {
    lateinit var create_bin: FragmentCreateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        create_bin = FragmentCreateBinding.inflate(inflater)
        create_bin.createBtn.setOnClickListener{
            createEvent(
                create_bin.date.text.toString(),
                create_bin.time.text.toString(),
                create_bin.places.text.toString(),
                create_bin.from.text.toString(),
                create_bin.to.text.toString()
            )
            create_bin.date.setText("")
            create_bin.time.setText("")
            create_bin.places.setText("")
            create_bin.from.setText("")
            create_bin.to.setText("")
        }
        return create_bin.root
    }

    private fun createEvent(date: String, time: String, places: String, from: String, to: String){
        var reference: DatabaseReference? = FirebaseDatabase.getInstance().getReference()
        var hashMap: HashMap<String, String> = HashMap()
        hashMap.put("date", date)
        hashMap.put("time", time)
        hashMap.put("places", places)
        hashMap.put("from", from)
        hashMap.put("to", to)
        reference!!.child("Roads").push().setValue(hashMap)

    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateFragment()
    }
}