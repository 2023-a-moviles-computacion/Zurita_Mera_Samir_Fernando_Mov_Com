package com.example.examenib
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.Toast




class ZapatosActivity : AppCompatActivity() {
    private lateinit var zapatoAdapter: ZapatoAdapter
    private lateinit var zapatos: MutableList<Zapato>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_zapato)

        zapatos = obtenerListaZapatos().toMutableList()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewZapatos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        zapatoAdapter = ZapatoAdapter()
        recyclerView.adapter = zapatoAdapter

        zapatoAdapter.setZapatos(zapatos)

        // Configurar los botones y los métodos de CRUD
        configurarBotones()
    }

    private fun obtenerListaZapatos(): List<Zapato> {
        return listOf(
            Zapato("Marca 1", "Rojo", 10, 50.0, "Zapato de cuero"),
            Zapato("Marca 2", "Azul", 5, 60.0, "Zapato deportivo"),
            Zapato("Marca 3", "Negro", 8, 70.0, "Zapato elegante")
        )
    }

    private fun configurarBotones() {
        zapatoAdapter.onItemClick = { zapato ->
            verZapatoSeleccionado(zapato)
        }

        zapatoAdapter.onItemDeleteClick = { zapato ->
            eliminarZapato(zapato)
        }

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            agregarZapato()
        }

        zapatoAdapter.onItemUpdateClick = { zapato ->
            actualizarZapato(zapato)
        }
        val btnMarcas = findViewById<Button>(R.id.btnMarcas)
        btnMarcas.setOnClickListener {
            // Iniciar la navegación hacia MarcasActivity
            val intent = Intent(this, MarcasActivity::class.java)
            startActivity(intent)
        }


    }

    private fun verZapatoSeleccionado(zapato: Zapato) {
        // Mostrar un Toast con los detalles del zapato seleccionado
        val mensaje = "Zapato seleccionado:\n" +
                "Color: ${zapato.color}\n" +
                "Stock: ${zapato.stock}\n" +
                "Costo: $${zapato.costo}\n" +
                "Descripción: ${zapato.descripcion}"
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun eliminarZapato(zapato: Zapato) {
        zapatos.remove(zapato)
        zapatoAdapter.setZapatos(zapatos)
    }

    private fun agregarZapato() {
        val nuevoZapato = Zapato("Nueva Marca", "Verde", 15, 80.0, "Zapato nuevo")
        zapatos.add(nuevoZapato)
        zapatoAdapter.setZapatos(zapatos)
    }

    private fun actualizarZapato(zapato: Zapato) {
        // Mostrar un Toast indicando que se actualizará el zapato
        val mensaje = "Actualizando zapato: ${zapato.color}"
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}