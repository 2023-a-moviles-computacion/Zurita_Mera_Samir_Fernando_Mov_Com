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

class EditarZapato : AppCompatActivity() {

    var marcaSeleccionado = Marca("", "", "", 0, "", 0)
    var zapatoSeleccionado = Zapato("","", "", "", 0, "", "")
    val db = Firebase.firestore
    val marcas = db.collection("marcas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_zapato)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        marcaSeleccionado = intent.getParcelableExtra<Marca>("posicionMarcateditar")!!
        zapatoSeleccionado = intent.getParcelableExtra<Zapato>("zapato")!!

        val txtNombreC = findViewById<TextInputEditText>(R.id.txtIn_nombreC_editar)
        val txtartistaC = findViewById<TextInputEditText>(R.id.txtIn_artistaC_editar)
        val txtduracionC = findViewById<TextInputEditText>(R.id.txtIn_duracionC_editar)
        val txtgeneroC = findViewById<TextInputEditText>(R.id.txtIn_generoC_editar)
        val txtanioreleaseC = findViewById<TextInputEditText>(R.id.txtIn_anioC_editar)

                txtNombreC.setText(zapatoSeleccionado.nombreZapato)
                txtartistaC.setText(zapatoSeleccionado.descripcion_zapato)
                txtduracionC.setText(zapatoSeleccionado.cantidad.toString())
                txtgeneroC.setText(zapatoSeleccionado.categoria)
                txtanioreleaseC.setText(zapatoSeleccionado.anio_lanzamiento)


        val btnEditarZapato = findViewById<Button>(R.id.btn_editar_zapato)
        btnEditarZapato.setOnClickListener {
            marcas.document("${marcaSeleccionado.idMarca}")
                .collection("Canciones")
                .document("${zapatoSeleccionado.idMarca_z}")
                .update(
                    "nombreCancion" , txtNombreC.text.toString(),
                    "artista" , txtartistaC.text.toString(),
                    "duracion" , txtduracionC.text.toString(),
                    "genero" , txtgeneroC.text.toString(),
                    "anioRelease" , txtanioreleaseC.text.toString()
                )

            Toast.makeText(this,"Cancion actualizada exitosamente", Toast.LENGTH_SHORT).show()
            val intentEditSucces = Intent(this, InicioZapato::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_cancion_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionMarcaEditar",marcaSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}