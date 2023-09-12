package com.example.cunduribrayan_examenb1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearPlaylist : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombrePlaylist = ""
    var descripcionPlaylist= ""
    var anioCreacion= ""
    var autorPlaylist = ""
    var numCanciones = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_playlist)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
        var longitudListaEntreador = EquipoBaseDeDatos.TablaPlaylist!!.listarPlaylists().lastIndex
        EquipoBaseDeDatos.TablaPlaylist!!.listarPlaylists().forEachIndexed{ indice: Int, playlist : Playlist ->
            Log.i("testExamen","${playlist.idPlaylist} -> ${playlist.nombrePlaylist}")
            if (indice == longitudListaEntreador){
                lastId = playlist.idPlaylist
            }
        }

        nextId = lastId+1

        var txtInNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreP_editar2)
        var txtInDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar)
        var txtInAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar)
        var txtInAutorP = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar)
        var txtInNumCancion = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar)

        var btnCrearPlaylist = findViewById<Button>(R.id.btn_guardar_cambios)
        btnCrearPlaylist.setOnClickListener {
            nombrePlaylist = txtInNombreP.text.toString()
            descripcionPlaylist = txtInDescripcion.text.toString()
            anioCreacion= txtInAnioCreacion.text.toString()
            autorPlaylist= txtInAutorP.text.toString()
            numCanciones= txtInNumCancion.text.toString()

            EquipoBaseDeDatos.TablaPlaylist!!.crearPlaylist(nextId, nombrePlaylist, descripcionPlaylist,anioCreacion,autorPlaylist,numCanciones)
            val intentAddSucces = Intent(this, InicioPlaylist::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarPlaylist = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarPlaylist.setOnClickListener {
            val intentAddCancel = Intent(this, InicioPlaylist::class.java)
            startActivity(intentAddCancel)
        }
    }

}