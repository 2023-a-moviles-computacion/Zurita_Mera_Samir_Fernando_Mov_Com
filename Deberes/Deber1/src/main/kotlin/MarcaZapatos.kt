data class MarcaZapato(
    val nombre: String,
    val fundacion: String,
    val direccion: String,
    val zapatos: MutableList<Zapato> = mutableListOf()
)
