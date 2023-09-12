package com.example.cunduribrayan_examenb1

class Registros {

    companion object{

        var arregloPlaylist_Cancion = arrayListOf<Playlist_Cancion>()

        init {
            // Playlists

            EquipoBaseDeDatos.TablaPlaylist!!.crearPlaylist(1,"Exitos","lo mejor","2023","Brayan","14")

            EquipoBaseDeDatos.TablaPlaylist!!.crearPlaylist(2,"Clasicos","Solo Temazos","2022","Samuel","15")

            EquipoBaseDeDatos.TablaPlaylist!!.crearPlaylist(3,"Rolitas","inolvidables","2022","Saul","16")


            // Canciones
            EquipoBaseDeDatos.TablaPlaylist!!.crearCancion(1,"No longer bound","Forrest Frank","3","Trap","2020")

            EquipoBaseDeDatos.TablaPlaylist!!.crearCancion(2,"Most High","MTM Isaiah","3","Trap","2023")

            EquipoBaseDeDatos.TablaPlaylist!!.crearCancion(3,"Emmanuel","Kelo","3","Pop","2021")


            // Playlists y Canciones
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(1, 1,2)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(2, 1, 3)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(3,2, 3)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(4,2,1)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(5,2,2)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(6,3,2)
            )

        }

    }
}