package com.example.cunduribrayan_examenb1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearCancion : AppCompatActivity() {

    var nextIdCancion = 0
    var lastIdCancion = 0

    var nextIdP_C=0
    var lastIdP_C=0
    var idCancionSeleccionada = 0
    var playlistPos = 0
    var idPlaylisT = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cancion)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        playlistPos = intent.getIntExtra("posicionPlaylist",-1)
        Log.i("posPlaylist","${playlistPos}")

        /*BBaseDeDatosMemoria.arregloPlaylist.forEachIndexed{ indice: Int, playlist : Playlist ->

            if (indice == playlistPos){
                idPlaylisT = playlist.idPlaylist
            }
        }*/

        EquipoBaseDeDatos.TablaPlaylist!!.listarPlaylists().forEachIndexed{ indice: Int, playlist : Playlist ->
            if (indice == playlistPos){
                idPlaylisT = playlist.idPlaylist
            }
        }

        //var longLCancion = BBaseDeDatosMemoria.arregloPlaylist_Cancion.lastIndex
        var longLCancion = EquipoBaseDeDatos.TablaPlaylist!!.listarCanciones().lastIndex

        EquipoBaseDeDatos.TablaPlaylist!!.listarCanciones().forEachIndexed{ indice: Int, cancion : Cancion ->
            Log.i("testExamen","${cancion.idCancion} -> ${cancion.nombreCancion}")
            if (indice == longLCancion){
                lastIdCancion = cancion.idCancion
            }
        }

        nextIdCancion = lastIdCancion+1

        var longP_C = Registros.arregloPlaylist_Cancion.lastIndex
        Registros.arregloPlaylist_Cancion.forEachIndexed{indice: Int, e_j:Playlist_Cancion ->
            if(indice==longP_C){
                lastIdP_C=e_j.idPlaylist_Cancion
            }

        }
        nextIdP_C=lastIdP_C+1

        var txtNombre = findViewById<TextInputEditText>(R.id.txtIn_nombreC_crear)
        var txtArtista = findViewById<TextInputEditText>(R.id.txtIn_artistaC_crear)
        var txtDuracion = findViewById<TextInputEditText>(R.id.txtIn_duracionC_crear)
        var txtGenero= findViewById<TextInputEditText>(R.id.txtIn_generoC_crear)
        var txtAnioRelease = findViewById<TextInputEditText>(R.id.txtIn_anioC_crear)

        var btnAddCancion= findViewById<Button>(R.id.btn_crear_cancion)
        btnAddCancion.setOnClickListener {
            var nombre = txtNombre.text.toString()
            var artista= txtArtista.text.toString()
            var duracion= txtDuracion.text.toString()
            var genero= txtGenero.text.toString()
            var anioRealease= txtAnioRelease.text.toString()
            Registros.arregloPlaylist_Cancion.add(
                Playlist_Cancion(nextIdP_C,idPlaylisT, nextIdCancion)
            )
            EquipoBaseDeDatos.TablaPlaylist!!.crearCancion(nextIdCancion,nombre,artista,duracion,genero,anioRealease)
            respuesta()
        }

        var btnCancelarCancion = findViewById<Button>(R.id.btn_cancelar_cancion_crear)
        btnCancelarCancion.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionPlaylist",playlistPos)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}