package com.example.movilescomp2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun ir_actividad(clase: Class <*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}