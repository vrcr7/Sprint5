package com.example.sprint5.views

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint5.R
import com.example.sprint5.databinding.FragmentCartBinding
import com.example.sprint5.models.AdapterCart
import com.example.sprint5.models.AdapterView
import com.example.sprint5.models.Producto
import com.example.sprint5.presenters.AdapterViewPresenter
import com.example.sprint5.presenters.ItemPresenter


class CartFragment : Fragment() {
    private lateinit var cartBinding: FragmentCartBinding
    private lateinit var navController: NavController
    private lateinit var itemPresenter: ItemPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: AdapterCart
    private val lista:MutableList<Producto> = mutableListOf()
    private val listaFinal:MutableList<Producto> = mutableListOf()
    val llenadoLista= AdapterViewPresenter()
    var total=0
    lateinit var textoTotal:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        cartBinding= FragmentCartBinding.inflate(inflater, container, false)
        textoTotal=cartBinding.textTotal
        cargarRecicler(cartBinding.root)
        val botoncito=cartBinding.botonItemVolver
        val botonFinal=cartBinding.botonPagar

        botonFinal.setOnClickListener{view->
            val alertDialog = AlertDialog.Builder(this.requireContext())
                .setTitle("Pago de Compra")
                .setMessage("Gracias por su compra")
                .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
                .create()

            alertDialog.show()
            vaciarCarro()
            volverMain()

        }
        botoncito.setOnClickListener { view->
            volverMain()
        }
        return cartBinding.root
    }

    fun cargarRecicler(view:View){
        recyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        lista.addAll(llenadoLista.llenarLista(this.requireContext()))
        for (objeto in lista){

            if(objeto.cantidad>0){
                total += (objeto.precio * objeto.cantidad)
                listaFinal.add(objeto)
            }
        }
        textoTotal.text=total.toString()
        itemAdapter = AdapterCart(listaFinal)

        recyclerView.adapter = itemAdapter
    }

    fun volverMain(){
        navController = requireActivity().findNavController(R.id.navHostFragmentView)

        navController.navigate(R.id.action_cartFragment_to_viewFragment)

    }
fun vaciarCarro(){
    val producto1= Producto("https://upload.wikimedia.org/wikipedia/commons/a/ad/Museo_del_Bicentenario_-_Zapatos_de_N%C3%A9stor_Kirchner.jpg","zapato varón","zapatos para varon formal",20000,0)
    val producto2= Producto("https://cdn.shopify.com/s/files/1/0271/4252/0921/products/30062085AmarrarCasualBillCafeParaHombre_5179ecf3-9359-48a5-946c-09f20a9d6220_grande.jpg?v=1632772756","Zapato Varón Casual","Zapato varón cómodo de cuero",25000,0)
    val producto3= Producto("https://home.ripley.cl/store/Attachment/WOP/D308/2000391323434/2000391323434_2.jpg","Zapato Dama Casual","Zapato casual de dama, cuero con taco",24000,0)
    val producto4= Producto("https://cdn.shopify.com/s/files/1/0588/7459/3447/products/P-QIU279_negro-2.webp?v=1678258071&width=750","Zapato Mujer Elegante","Zapato para mujer formal charol, taco aguja",20000,0)
    guardardatos(producto1)
    guardardatos(producto2)
    guardardatos(producto3)
    guardardatos(producto4)
}

fun guardardatos(objeto:Producto){
    itemPresenter= ItemPresenter(this.requireContext().getSharedPreferences(objeto.nombre,AppCompatActivity.MODE_PRIVATE))
    val bundle=Bundle()
    bundle.putString("url",objeto.urlImagen)
    bundle.putString("nombre",objeto.nombre)
    bundle.putString("descripcion",objeto.descripcion)
    bundle.putInt("precio",objeto.precio)
    bundle.putInt("cantidad",objeto.cantidad)
    itemPresenter.guardarItem(bundle)
}


}