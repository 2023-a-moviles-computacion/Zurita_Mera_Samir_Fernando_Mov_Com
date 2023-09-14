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

class CrearZapato : AppCompatActivity() {

    var marcaSeleccionado = Marca("", "", "", 0, "", 0)
    val db = Firebase.firestore
    val marca = db.collection("Marca")
    val zapato = db.collection("Zapatos")
    var idZapatoSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_zapato)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        marcaSeleccionado = intent.getParcelableExtra<Marca>("posicionMarca")!!
        val MaracSubColeccion = marca.document("${marcaSeleccionado.idMarca}")
            .collection("zapatos")


        var txtNombre = findViewById<TextInputEditText>(R.id.txtIn_nombreC_crear)
        var txtArtista = findViewById<TextInputEditText>(R.id.txtIn_artistaC_crear)
        var txtDuracion = findViewById<TextInputEditText>(R.id.txtIn_duracionC_crear)
        var txtGenero= findViewById<TextInputEditText>(R.id.txtIn_generoC_crear)
        var txtAnioRelease = findViewById<TextInputEditText>(R.id.txtIn_anioC_crear)

        Log.i("posMarca", "${marcaSeleccionado.idMarca}")

        var btnAddZapato= findViewById<Button>(R.id.btn_crear_zapato)
        btnAddZapato.setOnClickListener {
            var zapato = hashMapOf(
                "idPlaylist" to marcaSeleccionado.idMarca,
                "nombre" to txtNombre.text.toString(),
                "descripcion" to txtArtista.text.toString(),
                "cantidad" to txtDuracion.text.toString(),
                "categoria" to txtGenero.text.toString(),
                "anio_lanzamiento" to txtAnioRelease.text.toString()
            )

            MaracSubColeccion.add(zapato).addOnSuccessListener {
                Toast.makeText(this, "Zapato registrado exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("Crear-zapato", "Con exito")
            }.addOnFailureListener {
                Log.i("Crear-zapato", "Fallido")
            }

            val intentAddSucces = Intent(this, InicioZapato::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarZapato = findViewById<Button>(R.id.btn_cancelar_zapato_crear)
        btnCancelarZapato.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posMArca", marcaSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}