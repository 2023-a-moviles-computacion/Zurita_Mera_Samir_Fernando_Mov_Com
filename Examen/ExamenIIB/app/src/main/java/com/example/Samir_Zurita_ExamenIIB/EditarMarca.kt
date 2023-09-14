package com.example.Samir_Zurita_ExamenIIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarMarca : AppCompatActivity() {

    var marcaSeleccionado = Marca("", "", "", 0, "", 0)
    val db = Firebase.firestore
    val marcas = db.collection("marcas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_editar_marcas)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        marcaSeleccionado = intent.getParcelableExtra<Marca>("PosMarca")!!

        val editarNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreP_editar2)
        val editarDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar2)
        val editarAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar2)
        val editarAutorPlaylist = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar2)
        val editarNumC = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar2)

        editarNombreP.setText(marcaSeleccionado.nombreMarca)
        editarDescripcion.setText(marcaSeleccionado.descripcionMarca.toString())
        editarAnioCreacion.setText(marcaSeleccionado.anioCreacion.toString())
        editarAutorPlaylist.setText(marcaSeleccionado.autorMarca.toString())
        editarNumC.setText(marcaSeleccionado.cantidad.toString())


        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios2)
        btnGuardarCambios.setOnClickListener {
            marcas.document("${marcaSeleccionado.idMarca}").update(
                "nombrePlaylist" , editarNombreP.text.toString(),
                "descripcionPlaylist" , editarDescripcion.text.toString(),
                "anioCreacion" , editarAnioCreacion.text.toString(),
                "autorPlaylist" , editarAutorPlaylist.text.toString(),
                "numCanciones" , editarNumC.text.toString()
            )
            Toast.makeText(this,"Playlist actualizado exitosamente", Toast.LENGTH_SHORT).show()

            val intentEditSucces = Intent(this, InicioMarca::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_editar2)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, InicioMarca::class.java)
            startActivity(intentEditCancel)
        }

    }

}