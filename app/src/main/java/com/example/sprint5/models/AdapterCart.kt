package com.example.sprint5.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sprint5.R

//el daptador donde le entregamos la lista y la funcion de estructura
class AdapterCart(private val datos: List<Producto>) : RecyclerView.Adapter<AdapterCart.TuViewHolder>() {

    //funcion donde se pesca el layout model
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_model, parent, false)
        return TuViewHolder(view)
    }

    //para cargar la estructura la cantidad de veces necesarias segun ellargo de la lista entregada de datos
    override fun onBindViewHolder(holder: TuViewHolder, position: Int) {
        val item = datos[position]
        holder.bind(item)
    }
    //contador de datos entregados
    override fun getItemCount(): Int {
        return datos.size
    }

    //adaptador de la estructura a los datos entregados
    inner class TuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val textNombre: TextView= itemView.findViewById(R.id.textNombreModel)
            private val textPrecio: TextView=itemView.findViewById(R.id.textPrecioModel)
            private val textCantidad: TextView=itemView.findViewById(R.id.textCantidad)
            private val imagenModel: ImageView=itemView.findViewById(R.id.imageCartModel)
        fun bind(item: Producto) {
            textNombre.text=item.nombre
            textPrecio.text=item.precio.toString()
            textCantidad.text=item.cantidad.toString()
            Glide.with(itemView).load(item.urlImagen).into(imagenModel)
        }
    }
}
