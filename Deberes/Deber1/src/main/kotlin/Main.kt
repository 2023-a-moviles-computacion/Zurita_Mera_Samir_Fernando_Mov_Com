import java.io.File

data class Zapato(
    val nombre: String,
    val talla: Int,
    val color: String,
    val precio: Double,
    val disponible: Boolean
)

data class MarcaZapatos(
    val nombre: String,
    val fundacion: String,
    val direccion: String,
    val zapatos: MutableList<Zapato> = mutableListOf()
)

fun main() {
    val marcaZapatosFile = File("marcas.txt")
    val zapatoFile = File("zapatos.txt")

    if (!marcaZapatosFile.exists()) {
        marcaZapatosFile.createNewFile()
    }

    if (!zapatoFile.exists()) {
        zapatoFile.createNewFile()
    }

    var marcasZapatos = leerMarcasZapatos(marcaZapatosFile)

    do {
        println("===== CRUD de Marcas de Zapatos =====")
        println("Seleccione una opción:")
        println("1. Crear una nueva marca de zapatos")
        println("2. Actualizar una marca de zapatos existente")
        println("3. Eliminar una marca de zapatos")
        println("4. Mostrar los zapatos de una marca específica")
        println("5. Mostrar las marcas de zapatos existentes")
        println("6. Salir")
        print("Opción: ")

        val opcion = readLine()?.toIntOrNull()

        when (opcion) {
            1 -> {
                val marcaZapatos = crearMarcaZapatos()
                guardarMarcaZapatos(marcaZapatos, marcaZapatosFile)
                println("Marca de zapatos creada exitosamente.")
                marcasZapatos = leerMarcasZapatos(marcaZapatosFile)
            }
            2 -> {
                if (marcasZapatos.isNotEmpty()) {
                    println("Ingrese el nombre de la marca de zapatos que desea actualizar:")
                    val nombreMarcaZapatos = readLine() ?: ""
                    val marcaZapatosExistente = marcasZapatos.find { it.nombre == nombreMarcaZapatos }

                    if (marcaZapatosExistente != null) {
                        println("Ingrese el nuevo nombre de la marca de zapatos:")
                        val nuevoNombre = readLine() ?: ""

                        println("Ingrese el nuevo año de fundación de la marca de zapatos:")
                        val nuevaFundacion = readLine() ?: ""

                        println("Ingrese la nueva dirección de la marca de zapatos:")
                        val nuevaDireccion = readLine() ?: ""

                        val marcaZapatosActualizada =
                            MarcaZapatos(nuevoNombre, nuevaFundacion, nuevaDireccion, marcaZapatosExistente.zapatos)

                        actualizarMarcaZapatos(marcaZapatosActualizada, marcasZapatos, marcaZapatosFile)
                        println("Marca de zapatos actualizada exitosamente.")
                        marcasZapatos = leerMarcasZapatos(marcaZapatosFile)
                    } else {
                        println("No se encontró la marca de zapatos especificada.")
                    }
                } else {
                    println("No existen marcas de zapatos registradas.")
                }
            }
            3 -> {
                if (marcasZapatos.isNotEmpty()) {
                    println("Ingrese el nombre de la marca de zapatos que desea eliminar:")
                    val nombreMarcaZapatos = readLine() ?: ""
                    val marcaZapatosExistente = marcasZapatos.find { it.nombre == nombreMarcaZapatos }

                    if (marcaZapatosExistente != null) {
                        eliminarMarcaZapatos(marcaZapatosExistente, marcasZapatos, marcaZapatosFile)
                        println("Marca de zapatos eliminada exitosamente.")
                        marcasZapatos = leerMarcasZapatos(marcaZapatosFile)
                    } else {
                        println("No se encontró la marca de zapatos especificada.")
                    }
                } else {
                    println("No existen marcas de zapatos registradas.")
                }
            }
            4 -> {
                if (marcasZapatos.isNotEmpty()) {
                    println("Ingrese el nombre de la marca de zapatos para listar sus zapatos:")
                    val nombreMarcaZapatos = readLine() ?: ""
                    val marcaZapatosSeleccionada =
                        marcasZapatos.find { it.nombre == nombreMarcaZapatos }

                    if (marcaZapatosSeleccionada != null) {
                        println("Zapatos de la marca '${marcaZapatosSeleccionada.nombre}':")
                        marcaZapatosSeleccionada.zapatos.forEach {
                            println(
                                "Nombre: ${it.nombre}, Talla: ${it.talla}, Color: ${it.color}, " +
                                        "Precio: ${it.precio}, Disponible: ${it.disponible}"
                            )
                        }
                    } else {
                        println("No se encontró la marca de zapatos especificada.")
                    }
                } else {
                    println("No existen marcas de zapatos registradas.")
                }
            }
            5 -> {
                if (marcasZapatos.isNotEmpty()) {
                    println("Marcas de zapatos existentes:")
                    marcasZapatos.forEach { println(it.nombre) }
                } else {
                    println("No existen marcas de zapatos registradas.")
                }
            }
            6 -> {
                println("¡Hasta luego!")
            }
            else -> {
                println("Opción inválida. Por favor, seleccione una opción válida.")
            }
        }

        println()
    } while (opcion != 6)
}


fun crearMarcaZapatos(): MarcaZapatos {
    println("Ingrese el nombre de la marca de zapatos:")
    val nombre = readLine() ?: ""

    println("Ingrese el año de fundación de la marca de zapatos:")
    val fundacion = readLine() ?: ""

    println("Ingrese la dirección de la marca de zapatos:")
    val direccion = readLine() ?: ""

    return MarcaZapatos(nombre, fundacion, direccion)
}

fun guardarMarcaZapatos(marcaZapatos: MarcaZapatos, file: File) {
    val serializedMarcaZapatos = serializeMarcaZapatos(marcaZapatos)
    file.appendText("$serializedMarcaZapatos\n")
}

fun leerMarcasZapatos(file: File): List<MarcaZapatos> {
    val marcasZapatos = mutableListOf<MarcaZapatos>()

    if (file.exists()) {
        file.forEachLine { line ->
            val marcaZapatos = deserializeMarcaZapatos(line)
            marcasZapatos.add(marcaZapatos)
        }
    }

    return marcasZapatos
}

fun actualizarMarcaZapatos(marcaZapatos: MarcaZapatos, marcasZapatos: List<MarcaZapatos>, file: File) {
    val indice = marcasZapatos.indexOfFirst { it.nombre == marcaZapatos.nombre }

    if (indice != -1) {
        val marcasZapatosActualizadas = marcasZapatos.toMutableList()
        marcasZapatosActualizadas[indice] = marcaZapatos

        val serializedMarcasZapatos = marcasZapatosActualizadas.joinToString("\n") { serializeMarcaZapatos(it) }
        file.writeText(serializedMarcasZapatos)
    }
}

fun eliminarMarcaZapatos(marcaZapatos: MarcaZapatos, marcasZapatos: List<MarcaZapatos>, file: File) {
    val marcasZapatosMutable = marcasZapatos.toMutableList()
    marcasZapatosMutable.remove(marcaZapatos)

    val serializedMarcasZapatos = marcasZapatosMutable.joinToString("\n") { serializeMarcaZapatos(it) }
    file.writeText(serializedMarcasZapatos)
}

fun serializeMarcaZapatos(marcaZapatos: MarcaZapatos): String {
    val zapatosString = marcaZapatos.zapatos.joinToString(",") { serializeZapato(it) }
    return "${marcaZapatos.nombre}:${marcaZapatos.fundacion}:${marcaZapatos.direccion}:${zapatosString}"
}

fun deserializeMarcaZapatos(string: String): MarcaZapatos {
    val parts = string.split(":")
    if (parts.size < 4) {
        return MarcaZapatos("", "", "")
    }
    val zapatos = parts[3].split(",").map { deserializeZapato(it) }.toMutableList()
    return MarcaZapatos(parts[0], parts[1], parts[2], zapatos)
}

fun serializeZapato(zapato: Zapato): String {
    return "${zapato.nombre}:${zapato.talla}:${zapato.color}:${zapato.precio}:${zapato.disponible}"
}

fun deserializeZapato(string: String): Zapato {
    val parts = string.split(":")
    if (parts.size < 5) {
        return Zapato("", 0, "", 0.0, false)
    }
    val nombre = parts[0]
    val talla = parts[1].toInt()
    val color = parts[2]
    val precio = parts[3].toDouble()
    val disponible = parts[4].toBoolean()
    return Zapato(nombre, talla, color, precio, disponible)
}
