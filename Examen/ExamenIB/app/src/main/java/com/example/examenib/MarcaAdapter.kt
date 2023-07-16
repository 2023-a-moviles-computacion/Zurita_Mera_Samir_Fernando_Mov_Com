package com.example.examenib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.Marca
import com.example.examenib.R

class MarcaAdapter : RecyclerView.Adapter<MarcaAdapter.ViewHolder>() {
    private val marcas: MutableList<Marca> = mutableListOf()
    var onItemClick: ((Marca) -> Unit)? = null
    var onItemDeleteClick: ((Marca) -> Unit)? = null
    var onItemUpdateClick: ((Marca) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val ubicacionTextView: TextView = itemView.findViewById(R.id.ubicacionTextView)
        val tipoTextView: TextView = itemView.findViewById(R.id.tipoTextView)
        val anioFundacionTextView: TextView = itemView.findViewById(R.id.anioFundacionTextView)
        val gamaTextView: TextView = itemView.findViewById(R.id.gamaTextView)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(marcas[adapterPosition])
            }

            val btnDelete: Button = itemView.findViewById(R.id.btnDeleteMarca)
            btnDelete.setOnClickListener {
                onItemDeleteClick?.invoke(marcas[adapterPosition])
            }

            val btnUpdate: Button = itemView.findViewById(R.id.btnUpdateMarca)
            btnUpdate.setOnClickListener {
                onItemUpdateClick?.invoke(marcas[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_marca, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marca = marcas[position]

        holder.nombreTextView.text = marca.nombre
        holder.ubicacionTextView.text = marca.ubicacion
        holder.tipoTextView.text = marca.tipo
        holder.anioFundacionTextView.text = "Fundada en ${marca.anioFundacion}"
        holder.gamaTextView.text = "Gama: ${marca.gama}"
    }

    override fun getItemCount(): Int {
        return marcas.size
    }

    fun setMarcas(marcas: List<Marca>) {
        this.marcas.clear()
        this.marcas.addAll(marcas)
        notifyDataSetChanged()
    }
}
