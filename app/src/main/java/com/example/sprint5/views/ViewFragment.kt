package com.example.sprint5.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint5.R
import com.example.sprint5.models.AdapterView
import com.example.sprint5.models.Producto
import com.example.sprint5.presenters.AdapterViewPresenter

public open class ViewFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: AdapterView
    val llenadoLista=AdapterViewPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_view, container, false)
        cargarRecicler(view)
        val boton:Button=view.findViewById(R.id.botonViewCart)
        boton.setOnClickListener{view->
            llamarMarioFrag()
        }
        return view
    }

    fun cambiarFragmentItem(item:Producto){

            navController = requireActivity().findNavController(R.id.navHostFragmentView)

           val bundle = llenadoLista.recuperarBundle(item)

           navController.navigate(R.id.action_viewFragment_to_itemFragment, bundle)

    }

    fun cargarRecicler(view:View){
        recyclerView = view.findViewById(R.id.recyclerViewView)
        recyclerView.layoutManager = GridLayoutManager(this.requireContext(),2)

        itemAdapter = AdapterView(llenadoLista.llenarLista(this.requireContext())) { selectedItem ->
            cambiarFragmentItem(selectedItem)
        }

        recyclerView.adapter = itemAdapter
    }

    fun llamarMarioFrag(){
        navController = requireActivity().findNavController(R.id.navHostFragmentView)

        navController.navigate(R.id.action_viewFragment_to_cartFragment)
    }
}