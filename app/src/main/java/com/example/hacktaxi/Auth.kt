package com.example.hacktaxi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Auth : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val login_et : EditText = findViewById(R.id.login)
        val pass_et : EditText = findViewById(R.id.password)
        val login_btn : TextView = findViewById(R.id.li_btn)
        val sign_btn : TextView = findViewById(R.id.si_btn)
        login_btn.setOnClickListener{
            auth = FirebaseAuth.getInstance()
            val login = login_et.text.toString()
            val pass = pass_et.text.toString()
            if(login == "") {
                Toast.makeText(this , "Вы не ввели имя" , Toast.LENGTH_SHORT).show()
            }
            if(pass == "") {
                Toast.makeText(this , "Вы не ввели пароль" , Toast.LENGTH_SHORT).show()
            } else {
                //database(ename)
                //addUser(ename)
                //startActivity(Intent(this , HomeActivity::class.java))
                auth.createUserWithEmailAndPassword(login+"@gmail.com", pass)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val currentUser = auth.currentUser
                            val userId:String = currentUser!!.uid
                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)
                            var hashMap:HashMap<String, String> = HashMap()
                            hashMap.put("userId", userId)
                            hashMap.put("username", login)
                            databaseReference.setValue(hashMap).addOnCompleteListener(this){
                                if (it.isSuccessful){
                                    Toast.makeText(this, userId , Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, MainActivity::class.java))
                                    val creator = pref.edit()
                                    creator.putString("Name", login)
                                    creator.putString("UserId", userId)
                                    creator.apply()
                                    Log.d("Name", pref.getString("Name", null).toString())
                                    Log.d("UserId", pref.getString("UserId", null).toString())
                                    finish()
                                }
                            }
                        }else{
                            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}