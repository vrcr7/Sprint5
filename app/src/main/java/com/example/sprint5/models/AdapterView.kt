package com.example.sprint5.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sprint5.R
import com.example.sprint5.presenters.AdapterViewPresenter
import com.example.sprint5.views.ViewFragment


//el daptador donde le entregamos la lista y la funcion de estructura
class AdapterView(private val datos: List<Producto>, private val onItemClick:(Producto)->Unit) : RecyclerView.Adapter<AdapterView.TuViewHolder>() {

    //funcion donde se pesca el layout model
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_model, parent, false)
        return TuViewHolder(view)
    }

    //para cargar la estructura la cantidad de veces necesarias segun ellargo de la lista entregada de datos
    override fun onBindViewHolder(holder: TuViewHolder, position: Int) {
        val item = datos[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }
    //contador de datos entregados
    override fun getItemCount(): Int {
        return datos.size
    }

    //adaptador de la estructura a los datos entregados
    inner class TuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView =itemView.findViewById(R.id.titleTextView)
        private val precioTextView: TextView =itemView.findViewById(R.id.priceTextView)
        private val imageItemView: ImageView=itemView.findViewById(R.id.imageViewModel)
        fun bind(item: Producto) {
            nombreTextView.text=item.nombre
            precioTextView.text="$"+item.precio.toString()
            Glide.with(itemView)
                .load(item.urlImagen)
                .into(imageItemView)
        }
    }
}
