package com.example.cunduribrayan_examenb1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperPlaylist_Cancion(
    contexto: Context?,
) : SQLiteOpenHelper(contexto, "moviles", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador:ArrayList<String> = arrayListOf(
            """
               CREATE TABLE CANCION(
               id INTEGER PRIMARY KEY,
               nombreCancion VARCHAR(50),
               artista VARCHAR(50),
               duracion VARCHAR(50),
               genero VARCHAR(50),
               anioRelease VARCHAR(50)
               );
             ""","""
               CREATE TABLE PLAYLIST(
               id INTEGER PRIMARY KEY,
               nombrePlaylist VARCHAR(50),
               descripcionPlaylist VARCHAR(50),
               anioCreacion VARCHAR(50),
               autorPlaylist VARCHAR(50),
               numCanciones VARCHAR(50)
               );
            """)
        for (i in scriptSQLCrearTablaEntrenador){
            db!!.execSQL(i)
        }
        Log.i("creart", "Playlists")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun crearPlaylist(id:Int,nombrePlaylist:String, descripcionP:String,anioCreacion:String,
                    autorPlaylist:String,numCanciones:String ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id",id)
        valoresAGuardar.put("nombrePlaylist", nombrePlaylist)
        valoresAGuardar.put("descripcionPlaylist", descripcionP)
        valoresAGuardar.put("anioCreacion", anioCreacion)
        valoresAGuardar.put("autorPlaylist", autorPlaylist)
        valoresAGuardar.put("numCanciones", numCanciones)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "PLAYLIST",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true

    }

    fun listarPlaylists(): ArrayList<Playlist>{
        var lista = arrayListOf<Playlist>()
        var playlist: Playlist?
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM PLAYLIST"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                playlist=Playlist(0,"","",0,"",0)
                playlist!!.idPlaylist = resultadoConsultaLectura.getInt(0)
                playlist.nombrePlaylist= resultadoConsultaLectura.getString(1)
                playlist.descripcionPlaylist= resultadoConsultaLectura.getString(2)
                playlist.anioCreacion= resultadoConsultaLectura.getString(3).toInt()
                playlist.autorPlaylist= resultadoConsultaLectura.getString(4)
                playlist.numCanciones= resultadoConsultaLectura.getString(5).toInt()
                lista.add(playlist)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }
    fun actualizarPlaylist(idA:Int,nombrePlaylist:String, descripcionP:String,anioCreacion:String,
                           autorPlaylist:String,numCanciones:String ):Boolean{
        var lista= EquipoBaseDeDatos.TablaPlaylist!!.listarPlaylists()
        val id=lista[idA].idPlaylist.toString()
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombrePlaylist", nombrePlaylist)
        valoresAActualizar.put("descripcionPlaylist", descripcionP)
        valoresAActualizar.put("anioCreacion", anioCreacion)
        valoresAActualizar.put("autorPlaylist", autorPlaylist)
        valoresAActualizar.put("numCanciones", numCanciones)
        val resultadoActualizacion = conexionEscritura
            .update(
                "PLAYLIST", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }
    fun eliminarPlaylists(id:Int):Boolean{
        var lista= EquipoBaseDeDatos.TablaPlaylist!!.listarPlaylists()
        val idR=lista[id].idPlaylist.toString()
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("PLAYLIST","id=?", arrayOf(idR))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
    fun crearCancion(id:Int, nombreC:String,artistaC:String, duracion: String,
                     genero: String, anioRelease: String):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id",id)
        valoresAGuardar.put("nombreCancion", nombreC)
        valoresAGuardar.put("artista", artistaC)
        valoresAGuardar.put("duracion", duracion)
        valoresAGuardar.put("genero", genero)
        valoresAGuardar.put("anioRelease", anioRelease)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "CANCION",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true

    }
    fun listarCanciones(): ArrayList<Cancion>{
        var lista = arrayListOf<Cancion>()
        var cancion: Cancion?
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM CANCION"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                cancion= Cancion(0,"","",0,"",0)
                cancion!!.idCancion= resultadoConsultaLectura.getInt(0)
                cancion.nombreCancion= resultadoConsultaLectura.getString(1)
                cancion.artista= resultadoConsultaLectura.getString(2)
                cancion.duracion= resultadoConsultaLectura.getString(3).toInt()
                cancion.genero= resultadoConsultaLectura.getString(4)
                cancion.anioRelease= resultadoConsultaLectura.getString(5).toInt()
                lista.add(cancion)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }

    fun actualizarCancion(id:Int, nombreC:String,artistaC:String, duracion: String,
                          genero: String, anioRelease: String ):Boolean{

        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreCancion", nombreC)
        valoresAActualizar.put("artista", artistaC)
        valoresAActualizar.put("duracion", duracion)
        valoresAActualizar.put("genero", genero)
        valoresAActualizar.put("anioRelease", anioRelease)
        val resultadoActualizacion = conexionEscritura
            .update(
                "CANCION", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }
    fun eliminarCanciones(id:Int):Boolean{
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("CANCION","id=?", arrayOf(id.toString()))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

}