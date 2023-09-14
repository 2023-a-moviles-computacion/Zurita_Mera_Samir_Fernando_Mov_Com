package com.example.Samir_Zurita_ExamenIIB

import android.os.Parcel
import android.os.Parcelable

class Marca (

    var idMarca: String?,
    var nombreMarca: String?,
    var descripcionMarca: String?,
    var anioCreacion: Int?,
    var autorMarca: String?,
    var cantidad: Int?
    ): Parcelable {

    override fun toString(): String {
        return "${nombreMarca}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMarca)
        parcel.writeString(nombreMarca)
        parcel.writeString(descripcionMarca)
        parcel.writeInt(anioCreacion!!)
        parcel.writeString(autorMarca)
        parcel.writeInt(cantidad!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Marca> {
        override fun createFromParcel(parcel: Parcel): Marca {
            return Marca(parcel)
        }

        override fun newArray(size: Int): Array<Marca?> {
            return arrayOfNulls(size)
        }
    }

}