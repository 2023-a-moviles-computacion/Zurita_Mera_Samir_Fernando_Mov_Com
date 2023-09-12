package com.example.cunduribrayan_examenb1

import android.os.Parcel
import android.os.Parcelable

class Playlist_Cancion (

    val idPlaylist_Cancion: Int,
    //var nombreP_C: String?,
    val idPlaylist: Int,
    val idCancion: Int

    ): Parcelable  {

    //override fun toString(): String {
      //  return "${nombreP_C}"
    //}

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        //parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPlaylist_Cancion)
        //parcel.writeString(nombreP_C)
        parcel.writeInt(idPlaylist)
        parcel.writeInt(idCancion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Playlist_Cancion> {
        override fun createFromParcel(parcel: Parcel): Playlist_Cancion {
            return Playlist_Cancion(parcel)
        }

        override fun newArray(size: Int): Array<Playlist_Cancion?> {
            return arrayOfNulls(size)
        }
    }

}