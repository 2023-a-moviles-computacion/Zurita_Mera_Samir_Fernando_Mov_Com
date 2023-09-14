package com.example.Samir_Zurita_ExamenIIB

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CrearMarca : AppCompatActivity() {

    val db = Firebase.firestore
    val marca = db.collection("Marca")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_marca)
    }

    override fun onStart() {
        super.onStart()

        var txtInNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreP_editar2)
        var txtInDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar)
        var txtInAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar)
        var txtInAutorP = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar)
        var txtInNumCancion = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar)

        var btnCrearPlaylist = findViewById<Button>(R.id.btn_guardar_cambios)
        btnCrearPlaylist.setOnClickListener {
            var playlist = hashMapOf(
                "nombreMarca" to txtInNombreP.text.toString(),
                "descripcionMarca" to txtInDescripcion.text.toString(),
                "anioCreacion" to txtInAnioCreacion.text.toString(),
                "autorMarca" to txtInAutorP.text.toString(),
                "cantidad" to txtInNumCancion.text.toString()
            )

            marca.add(playlist).addOnSuccessListener {
                txtInNombreP.text!!.clear()
                txtInDescripcion.text!!.clear()
                txtInAnioCreacion.text!!.clear()
                txtInAutorP.text!!.clear()
                txtInNumCancion.text!!.clear()
                Toast.makeText(this,"Marca registrada con exito", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Marca","Success")
            }.addOnSuccessListener {
                Log.i("Crear-Marca","Failed")
            }


            val intentAddSucces = Intent(this, InicioMarca::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarMarca = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarMarca.setOnClickListener {
            val intentAddCancel = Intent(this, InicioMarca::class.java)
            startActivity(intentAddCancel)
        }
    }

}