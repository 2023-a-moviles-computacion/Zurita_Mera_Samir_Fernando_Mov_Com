package com.example.cunduribrayan_examenb1

class BBaseDeDatosMemoria {

    companion object{
        var arregloPlaylist = arrayListOf<Playlist>()
        var arregloCancion = arrayListOf<Cancion>()
        var arregloPlaylist_Cancion = arrayListOf<Playlist_Cancion>()

        init {
            // Playlist
            arregloPlaylist.add(
                Playlist(1,"Temazos", "clasicos",2020,"Brayan Cunduri",14)
            )
            arregloPlaylist.add(
                Playlist(2,"Letras", "crazy",2019,"Samuel Cunduri",15)
            )
            arregloPlaylist.add(
                Playlist(3,"Amazing", "TopTen",2021,"Roberto Peres",17)
            )

            // Canciones
            arregloCancion.add(
                Cancion(1,"Repent","MTM Isaiah",3,"Regueton",2023)
            )
            arregloCancion.add(
                Cancion(2,"Calling","Swae Lee",4,"Regueton",2019)
            )
            arregloCancion.add(
                Cancion(3,"Everything","Hulvey",3,"Regueton",2022)
            )
           /*
            // Playlists y Canciones
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(1, "Calling", 1,2)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(2, "Everything", 1, 3)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(3, "Everything",2, 3)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(4, "Repent",2,1)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(5, "Calling",2,2)
            )
            arregloPlaylist_Cancion.add(
                Playlist_Cancion(6, "Calling",3,2)
            )*/

        }

    }

}