package com.example.hacktaxi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        pref = getSharedPreferences("user_info", MODE_PRIVATE)
        if (pref.getString("Name", null) == null) {
            startActivity(Intent(this, Auth::class.java))
            finish()
        }
//        }else{
//            supportFragmentManager.beginTransaction().replace(R.id.frame, SettingsFragment.newInstance()).commit()
//        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}