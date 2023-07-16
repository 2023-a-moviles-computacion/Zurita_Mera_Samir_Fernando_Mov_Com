package com.example.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnZapatos = findViewById<Button>(R.id.btnZapatos)
        val btnMarcas = findViewById<Button>(R.id.btnMarcas)

        btnZapatos.setOnClickListener {
            val intent = Intent(this, ZapatosActivity::class.java)
            startActivity(intent)
        }

        btnMarcas.setOnClickListener {
            val intent = Intent(this, MarcasActivity::class.java)
            startActivity(intent)
        }
    }
}
