package com.example.hacktaxi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.hacktaxi.databinding.FragmentSettingsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsFragment : Fragment() {
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
        return settings_bin.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}