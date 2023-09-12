package com.example.cunduribrayan_examenb1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarCancion : AppCompatActivity() {

    var playlistPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cancion)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        //val idPlaylist_Cancion = intent.getIntExtra("cancion",1)
        playlistPos = intent.getIntExtra("posicionPlaylisteditar",1)

        val txtNombreC = findViewById<TextInputEditText>(R.id.txtIn_nombreC_editar)
        val txtartistaC = findViewById<TextInputEditText>(R.id.txtIn_artistaC_editar)
        val txtduracionC = findViewById<TextInputEditText>(R.id.txtIn_duracionC_editar)
        val txtgeneroC = findViewById<TextInputEditText>(R.id.txtIn_generoC_editar)
        val txtanioreleaseC = findViewById<TextInputEditText>(R.id.txtIn_anioC_editar)


        // var idPlaYlist: Int = 0
        var idCancion = intent.getIntExtra("cancion",1)

        /*BBaseDeDatosMemoria.arregloPlaylist_Cancion.forEachIndexed{ indice: Int, playlist_cancion : Playlist_Cancion ->
            if (idPlaylist_Cancion == playlist_cancion.idPlaylist_Cancion){
                txtNombreC.setText(playlist_cancion.nombreP_C)
                idPlaYlist = playlist_cancion.idCancion
            }
        }*/

        EquipoBaseDeDatos.TablaPlaylist!!.listarCanciones().forEachIndexed{ indice: Int, cancion : Cancion ->
            if (cancion.idCancion == idCancion){
                txtNombreC.setText(cancion.nombreCancion)
                txtartistaC.setText(cancion.artista)
                txtduracionC.setText(cancion.duracion.toString())
                txtgeneroC.setText(cancion.genero)
                txtanioreleaseC.setText(cancion.anioRelease.toString())
            }
        }

        val btnEditarCancion = findViewById<Button>(R.id.btn_editar_cancion)
        btnEditarCancion.setOnClickListener {
            /*BBaseDeDatosMemoria.arregloPlaylist_Cancion.forEachIndexed{ indice: Int, playlist_cancion: Playlist_Cancion ->
                if (idPlaylist_Cancion == playlist_cancion.idPlaylist_Cancion){
                    Log.i("editar","${txtNombreC.text.toString()}")
                    playlist_cancion.nombreP_C = (txtNombreC.text.toString())
                }
            }*/
            //BBaseDeDatosMemoria.arregloCancion.forEachIndexed{ indice: Int, cancion: Cancion ->
              //  if(idPlaYlist==cancion.idCancion){
                    var nombreCancion=txtNombreC.text.toString()
                    var artista=txtartistaC.text.toString()
                    var duracion=txtduracionC.text.toString()
                    var genero=txtgeneroC.text.toString()
                    var anioRelease=txtanioreleaseC.text.toString()
            EquipoBaseDeDatos.TablaPlaylist!!.actualizarCancion(idCancion,nombreCancion,artista,duracion,genero,anioRelease)
                //}

            //}

            respuesta()
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_cancion_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionPlaylisteditar",playlistPos)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}