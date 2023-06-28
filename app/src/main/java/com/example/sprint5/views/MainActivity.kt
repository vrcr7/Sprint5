package com.example.sprint5.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sprint5.R
import com.example.sprint5.models.Producto
import com.example.sprint5.presenters.ItemPresenter

class MainActivity : AppCompatActivity() {
    private lateinit var itemPresenter: ItemPresenter
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentView) as NavHostFragment
        navController = navHostFragment.navController
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
        itemPresenter= ItemPresenter(this.getSharedPreferences(objeto.nombre, MODE_PRIVATE))
        val bundle=Bundle()
        bundle.putString("url",objeto.urlImagen)
        bundle.putString("nombre",objeto.nombre)
        bundle.putString("descripcion",objeto.descripcion)
        bundle.putInt("precio",objeto.precio)
        bundle.putInt("cantidad",objeto.cantidad)
        itemPresenter.guardarItem(bundle)
    }
}