package com.example.hacktaxi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

lateinit var firebaseAuth: FirebaseAuth
lateinit var pref: SharedPreferences

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val button : Button = view.findViewById(R.id.button)
        button.setOnClickListener{
            firebaseAuth.signOut()
            val creator = pref.edit()
            creator.remove("Name")
            creator.remove("UserId")
            creator.apply()
            startActivity(Intent(this.context , MainActivity::class.java))
            activity?.finish()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}