package com.example.cunduribrayan_examenb1

import android.os.Parcel
import android.os.Parcelable

class Cancion (

    var idCancion: Int,
    var nombreCancion:String?,
    var artista: String?,
    var duracion: Int?,
    var genero: String?,
    var anioRelease:Int?

    ): Parcelable {

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
        parcel.writeInt(idCancion)
        parcel.writeString(nombreCancion)
        parcel.writeString(artista)
        parcel.writeInt(duracion!!)
        parcel.writeString(genero)
        parcel.writeInt(anioRelease!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${nombreCancion}"
    }

    companion object CREATOR : Parcelable.Creator<Cancion> {
        override fun createFromParcel(parcel: Parcel): Cancion {
            return Cancion(parcel)
        }

        override fun newArray(size: Int): Array<Cancion?> {
            return arrayOfNulls(size)
        }
    }


}