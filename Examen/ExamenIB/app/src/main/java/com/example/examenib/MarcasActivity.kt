package com.example.examenib
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.R
import com.example.examenib.Marca
import android.widget.Button
import android.widget.Toast

class MarcasActivity : AppCompatActivity() {
    private lateinit var marcaAdapter: MarcaAdapter
    private lateinit var marcas: MutableList<Marca>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_marca)

        marcas = obtenerListaMarcas().toMutableList()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMarcas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        marcaAdapter = MarcaAdapter()
        recyclerView.adapter = marcaAdapter

        marcaAdapter.setMarcas(marcas)

        // Configurar los botones y los métodos de CRUD
        configurarBotones()
    }

    private fun obtenerListaMarcas(): List<Marca> {
        return listOf(
            Marca("Marca 1", "Ubicación 1", "Tipo 1", "2021", "Gama 1"),
            Marca("Marca 2", "Ubicación 2", "Tipo 2", "2020", "Gama 2"),
            Marca("Marca 3", "Ubicación 3", "Tipo 3", "2019", "Gama 3")
        )
    }

    private fun configurarBotones() {
        marcaAdapter.onItemClick = { marca ->
            verMarcaSeleccionada(marca)
        }

        marcaAdapter.onItemDeleteClick = { marca ->
            eliminarMarca(marca)
        }

        val btnAgregar = findViewById<Button>(R.id.btnDeleteZapato)
        btnAgregar.setOnClickListener {
            agregarMarca()
        }

        marcaAdapter.onItemUpdateClick = { marca ->
            actualizarMarca(marca)
        }
        val btnZapatos = findViewById<Button>(R.id.btnUpdateZapato)
        btnZapatos.setOnClickListener {
            // Iniciar la navegación hacia ZapatosActivity
            val intent = Intent(this, ZapatosActivity::class.java)
            startActivity(intent)
        }

    }

    private fun verMarcaSeleccionada(marca: Marca) {
        // Mostrar un Toast con los detalles de la marca seleccionada
        val mensaje = "Marca seleccionada:\n" +
                "Nombre: ${marca.nombre}\n" +
                "Ubicación: ${marca.ubicacion}\n" +
                "Tipo: ${marca.tipo}\n" +
                "Año de Fundación: ${marca.anioFundacion}\n" +
                "Gama: ${marca.gama}"
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun eliminarMarca(marca: Marca) {
        marcas.remove(marca)
        marcaAdapter.setMarcas(marcas)
    }

    private fun agregarMarca() {
        val nuevaMarca = Marca("Nueva Marca", "Nueva Ubicación", "Nuevo Tipo", "2022", "Nueva Gama")
        marcas.add(nuevaMarca)
        marcaAdapter.setMarcas(marcas)
    }

    private fun actualizarMarca(marca: Marca) {
        // Mostrar un Toast indicando que se actualizará la marca
        val mensaje = "Actualizando marca: ${marca.nombre}"
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}