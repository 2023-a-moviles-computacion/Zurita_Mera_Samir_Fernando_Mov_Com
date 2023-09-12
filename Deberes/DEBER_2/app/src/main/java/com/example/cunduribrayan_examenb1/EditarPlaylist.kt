package com.example.cunduribrayan_examenb1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarPlaylist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_editar_playlist)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionPlaylist = intent.getIntExtra("posicionEditar",1)

        val editarNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreP_editar2)
        val editarDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar2)
        val editarAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar2)
        val editarAutorPlaylist = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar2)
        val editarNumC = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar2)

        EquipoBaseDeDatos.TablaPlaylist!!.listarPlaylists().forEachIndexed{ indice: Int, playlist : Playlist ->

            if (indice == posicionPlaylist){
                editarNombreP.setText(playlist.nombrePlaylist.toString())
                editarDescripcion.setText(playlist.descripcionPlaylist.toString())
                editarAnioCreacion.setText(playlist.anioCreacion.toString())
                editarAutorPlaylist.setText(playlist.autorPlaylist.toString())
                editarNumC.setText(playlist.numCanciones.toString())
            }
        }

        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios2)
        btnGuardarCambios.setOnClickListener {
            //BBaseDeDatosMemoria.arregloPlaylist.forEachIndexed{ indice: Int, playlist: Playlist ->
              //  if (indice == posicionPlaylist){

                    var nombrePlaylist = editarNombreP.text.toString()
                    var descripcionPlaylist = editarDescripcion.text.toString()
                    var anioCreacion = editarAnioCreacion.text.toString().toInt()
                    var autorPlaylist = editarAutorPlaylist.text.toString()
                    var numCanciones = editarNumC.text.toString().toInt()
                    EquipoBaseDeDatos.TablaPlaylist!!.actualizarPlaylist(posicionPlaylist,
                        nombrePlaylist,descripcionPlaylist,anioCreacion.toString(),autorPlaylist,numCanciones.toString())
               // }
            val intentEditSucces = Intent(this, InicioPlaylist::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_editar2)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, InicioPlaylist::class.java)
            startActivity(intentEditCancel)
        }

    }

}