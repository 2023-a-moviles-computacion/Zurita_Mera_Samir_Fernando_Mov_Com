package com.example.Samir_Zurita_ExamenIIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciar = findViewById<Button>(R.id.btn_iniciar)
        btnIniciar.setOnClickListener{
            val intent = Intent(this, InicioMarca::class.java)
            startActivity(intent)
        }

    }
}