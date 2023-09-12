package com.example.cunduribrayan_examenb1

import android.os.Parcel
import android.os.Parcelable

class Playlist (

    var idPlaylist: Int,
    var nombrePlaylist: String?,
    var descripcionPlaylist: String?,
    var anioCreacion: Int?,
    var autorPlaylist: String?,
    var numCanciones: Int?
    ): Parcelable {

    override fun toString(): String {
        return "${nombrePlaylist}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPlaylist)
        parcel.writeString(nombrePlaylist)
        parcel.writeString(descripcionPlaylist)
        parcel.writeInt(anioCreacion!!)
        parcel.writeString(autorPlaylist)
        parcel.writeInt(numCanciones!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Playlist> {
        override fun createFromParcel(parcel: Parcel): Playlist {
            return Playlist(parcel)
        }

        override fun newArray(size: Int): Array<Playlist?> {
            return arrayOfNulls(size)
        }
    }

}