package com.example.Samir_Zurita_ExamenIIB

import android.os.Parcel
import android.os.Parcelable

class Zapato (

    var idZapato: String?,
    var idMarca_z:String?,
    var nombreZapato:String?,
    var descripcion_zapato: String?,
    var cantidad: Int?,
    var categoria: String?,
    var anio_lanzamiento:String?

    ): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMarca_z)
        parcel.writeString(idZapato)
        parcel.writeString(nombreZapato)
        parcel.writeString(descripcion_zapato)
        parcel.writeInt(cantidad!!)
        parcel.writeString(categoria)
        parcel.writeString(anio_lanzamiento)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${nombreZapato}"
    }

    companion object CREATOR : Parcelable.Creator<Zapato> {
        override fun createFromParcel(parcel: Parcel): Zapato {
            return Zapato(parcel)
        }

        override fun newArray(size: Int): Array<Zapato?> {
            return arrayOfNulls(size)
        }
    }


}