package com.example.examenib
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.Zapato
import com.example.examenib.R

class ZapatoAdapter : RecyclerView.Adapter<ZapatoAdapter.ViewHolder>() {
    private val zapatos: MutableList<Zapato> = mutableListOf()
    var onItemClick: ((Zapato) -> Unit)? = null
    var onItemDeleteClick: ((Zapato) -> Unit)? = null
    var onItemUpdateClick: ((Zapato) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorTextView: TextView = itemView.findViewById(R.id.colorTextView)
        val stockTextView: TextView = itemView.findViewById(R.id.stockTextView)
        val costoTextView: TextView = itemView.findViewById(R.id.costoTextView)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)
        val btnDelete: Button = itemView.findViewById(R.id.btnDeleteZapato)
        val btnUpdate: Button = itemView.findViewById(R.id.btnUpdateZapato)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_zapato, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zapato = zapatos[position]

        holder.colorTextView.text = zapato.color
        holder.stockTextView.text = "Stock: ${zapato.stock}"
        holder.costoTextView.text = "Costo: $${zapato.costo}"
        holder.descripcionTextView.text = zapato.descripcion

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(zapato)
        }

        holder.btnDelete.setOnClickListener {
            onItemDeleteClick?.invoke(zapato)
        }

        holder.btnUpdate.setOnClickListener {
            onItemUpdateClick?.invoke(zapato)
        }
    }

    override fun getItemCount(): Int {
        return zapatos.size
    }

    fun setZapatos(zapatos: List<Zapato>) {
        this.zapatos.clear()
        this.zapatos.addAll(zapatos)
        notifyDataSetChanged()
    }
}