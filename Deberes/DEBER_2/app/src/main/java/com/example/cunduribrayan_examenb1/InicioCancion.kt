package com.example.cunduribrayan_examenb1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

class InicioCancion : AppCompatActivity() {

    var idItemSeleccionado = 0
    var idPlaylisT = 0
    var playlistPos = 0
    var itemS = 0

    //var listaCanciones = arrayListOf<String>()
    //var idP_C = arrayListOf<Int>()

    var resultAnadirCancion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                playlistPos = data?.getIntExtra("posicionPlaylist",0)!!
            }
        }

    }

    var resultEditarCancion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                playlistPos = data?.getIntExtra("posicionPlaylist",0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_cancion)
    }

    fun listViewCanciones():ArrayList<Cancion> {
        var listaIDCanciones = arrayListOf<Int>()

        Registros.arregloPlaylist_Cancion.forEachIndexed { indice: Int, playlistCancion: Playlist_Cancion ->
            if (playlistCancion.idPlaylist == idPlaylisT) {
                listaIDCanciones.add(playlistCancion.idCancion)
            }
        }
        var listaCanciones = arrayListOf<Cancion>()
        EquipoBaseDeDatos.TablaPlaylist!!.listarCanciones()
            .forEachIndexed { indice: Int, cancion: Cancion ->
                for (i in listaIDCanciones) {
                    if (i == cancion.idCancion) {
                        listaCanciones.add(cancion)
                    }
                }
            }
        return listaCanciones
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        //listaCanciones = arrayListOf()
        //idP_C = arrayListOf()

        playlistPos = intent.getIntExtra("posicionEditar",1)

        //val nombrePlaylist_cancion = findViewById<TextView>(R.id.tv_nombreP_C)

        EquipoBaseDeDatos.TablaPlaylist!!.listarPlaylists().forEachIndexed{ indice: Int, playlist : Playlist ->
            if (indice == playlistPos){
                val txtNombrePlaylist=findViewById<TextView>(R.id.tv_nombreP_C)
                txtNombrePlaylist.setText("Playlist: "+playlist.nombrePlaylist)
                idPlaylisT=playlist.idPlaylist
            }
        }

        /*BBaseDeDatosMemoria.arregloPlaylist_Cancion.forEachIndexed{ indice: Int, playlist_cancion : Playlist_Cancion ->
            if (idPlaylisT == playlist_cancion.idPlaylist){
                listaCanciones.add(playlist_cancion.nombreP_C.toString())
                idP_C.add(playlist_cancion.idPlaylist_Cancion)
            }
        }*/

        val listViewCancion = findViewById<ListView>(R.id.lv_canciones_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewCanciones()
        )
        listViewCancion.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearCancion = findViewById<Button>(R.id.btn_crear_cancion2)
        btnCrearCancion.setOnClickListener {
            abrirActividadAddCancion(CrearCancion::class.java)
        }

        val btnVolverCancion = findViewById<Button>(R.id.btn_volver_cancion)
        btnVolverCancion.setOnClickListener {
            val intentAtrasCancion = Intent(this, InicioPlaylist::class.java)
            startActivity(intentAtrasCancion)
        }


        this.registerForContextMenu(listViewCancion)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val listViewCancion = findViewById<ListView>(R.id.lv_canciones_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewCanciones()
        )
        listViewCancion.adapter = adaptador
        adaptador.notifyDataSetChanged()

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cancion, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemS = id
        val idR=listViewCanciones()[id].idCancion
        idItemSeleccionado = idR//idP_C.elementAt(id)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarCancion -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarCancion(EditarCancion::class.java)
                return true
            }
            R.id.mi_eliminarCancion -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                EquipoBaseDeDatos.TablaPlaylist!!.eliminarCanciones(idItemSeleccionado)
                val listViewCancion = findViewById<ListView>(R.id.lv_canciones_lista)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listViewCanciones()
                )
                listViewCancion.adapter = adaptador
                adaptador.notifyDataSetChanged()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarCancion(
        clase: Class<*>
    ) {
        val intentEditarCancion = Intent(this, clase)
        intentEditarCancion.putExtra("cancion", idItemSeleccionado)
        intentEditarCancion.putExtra("posicionPlaylisteditar",playlistPos)
        resultEditarCancion.launch(intentEditarCancion)
    }

    fun abrirActividadAddCancion(
        clase: Class<*>
    ) {
        val intentAddNewCancion = Intent(this, clase)
        intentAddNewCancion.putExtra("posicionPlaylist",playlistPos)
        Log.i("positionSend","${playlistPos}")
        resultAnadirCancion.launch(intentAddNewCancion)
    }

    /*fun eliminarCancion(
        idCancionAeliminar: Int
    ){
        val listViewCancion = findViewById<ListView>(R.id.lv_canciones_lista)

        var aux_P_C = arrayListOf<Playlist_Cancion>()

        BBaseDeDatosMemoria.arregloPlaylist_Cancion.forEach{ playlist_cancion:Playlist_Cancion ->
            if(idCancionAeliminar != playlist_cancion.idPlaylist_Cancion){
                aux_P_C.add(playlist_cancion)
            }
        }

        BBaseDeDatosMemoria.arregloPlaylist_Cancion = aux_P_C

        listaCanciones.removeAt(itemS)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaCanciones
        )
        listViewCancion .adapter = adaptador
        adaptador.notifyDataSetChanged()
    }*/

}