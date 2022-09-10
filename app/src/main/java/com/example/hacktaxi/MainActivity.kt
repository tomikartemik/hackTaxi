package com.example.hacktaxi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import com.example.hacktaxi.databinding.ActivityMainBinding

lateinit var pref: SharedPreferences
class MainActivity : AppCompatActivity() {
    lateinit var main_binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Text", "Text")
        main_binding = ActivityMainBinding.inflate(layoutInflater)
        pref = getSharedPreferences("user_info", MODE_PRIVATE)
        if (pref.getString("Name", null) == null) {
            startActivity(Intent(this, Auth::class.java))
            finish()
        }
        super.onCreate(savedInstanceState)
        setContentView(main_binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.frame, RoadsFragment.newInstance()).commit()
        main_binding.navBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.roads -> supportFragmentManager.beginTransaction().replace(R.id.frame, RoadsFragment.newInstance()).commit()
                R.id.create -> supportFragmentManager.beginTransaction().replace(R.id.frame, CreateFragment.newInstance()).commit()
                R.id.settings -> supportFragmentManager.beginTransaction().replace(R.id.frame, SettingsFragment.newInstance()).commit()
            }
            true
        }
    }
}