package com.example.Samir_Zurita_ExamenIIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class InicioMarca : AppCompatActivity() {
    val db = Firebase.firestore
    val playlists = db.collection("Playlists")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Marca>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_marca)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        listarPlaylists()
        val btnAnadirPlaylist = findViewById<Button>(R.id.btn_crear_nueva_playlist)
        btnAnadirPlaylist.setOnClickListener {
            val intentAddPlaylist = Intent(this, CrearMarca::class.java)
            startActivity(intentAddPlaylist)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var marcaSeleccionado:Marca = adaptador!!.getItem(idItemSeleccionado)!!

        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${marcaSeleccionado.idMarca}")
                val abrirEditarMarca = Intent(this, EditarMarca::class.java)
                abrirEditarMarca.putExtra("PosPlaylist",marcaSeleccionado)
                startActivity(abrirEditarMarca)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                playlists.document("${marcaSeleccionado.idMarca}").delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-Playlist", "Exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-Playlist","Fallido")
                    }
                listarPlaylists()
                return true
            }

            R.id.mi_canciones -> {
                Log.i("context-menu", "Canciones: ${idItemSeleccionado}")
                val abrirInicioCanciones = Intent(this, InicioZapato::class.java)
                abrirInicioCanciones.putExtra("PosPlaylist",marcaSeleccionado)
                startActivity(abrirInicioCanciones)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listarPlaylists(){
        val lv_playlists = findViewById<ListView>(R.id.lv_playlists_lista)
        playlists.get().addOnSuccessListener{ result ->
            var marcaLista = arrayListOf<Marca>()
            for (document in result) {
                marcaLista.add(
                    Marca(
                        document.id.toString(),
                        document.get("nombrePlaylist").toString(),
                        document.get("descripcionPlaylist").toString(),
                        document.get("anioCreacion").toString().toInt(),
                        document.get("autorPlaylist").toString(),
                        document.get("numCanciones").toString().toInt(),
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                marcaLista
            )
            lv_playlists.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(lv_playlists)

        }.addOnFailureListener{
            Log.i("Error", "Creacion de lista de playlists fallida")
        }
    }

}