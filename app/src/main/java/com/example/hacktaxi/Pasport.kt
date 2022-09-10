package com.example.hacktaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hacktaxi.databinding.ActivityPasportBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Pasport : AppCompatActivity() {
    lateinit var pasport_bin: ActivityPasportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pasport_bin = ActivityPasportBinding.inflate(layoutInflater)
        setContentView(pasport_bin.root)
        pasport_bin.regP.setOnClickListener{
            val name = pasport_bin.nameP.text.toString()
            val secondName = pasport_bin.secondNameP.text.toString()
            val patronymic = pasport_bin.otchP.text.toString()
            val bDay = pasport_bin.bdayP.text.toString()
            val number = pasport_bin.number.text.toString()
            if(name != null && secondName != null && patronymic != null && bDay != null && number != null){
                addPasport(name, secondName, patronymic, bDay, number)
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addPasport(name: String, secondName: String, patronymic: String, bDay: String, number: String){
        var reference: DatabaseReference? = FirebaseDatabase.getInstance().getReference("Users")
        var hashMap: HashMap<String, String> = HashMap()
        hashMap.put("number", number)
        hashMap.put("name", name)
        hashMap.put("secondName", secondName)
        hashMap.put("patronymic", patronymic)
        hashMap.put("b_day", bDay)
        val creator = pref.edit()
        creator.putString("Number", number)
        creator.putString("DriverName", name)
        creator.apply()
        reference!!.child(pref.getString("UserId", "").toString()).push().setValue(hashMap)

    }
}