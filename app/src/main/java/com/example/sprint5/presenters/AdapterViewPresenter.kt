package com.example.sprint5.presenters


import android.content.Context
import android.os.Bundle
import com.example.sprint5.models.Producto
import com.example.sprint5.models.llenadoListaView


open class AdapterViewPresenter:llenadoListaView() {
    override fun llenarLista(context:Context): List<Producto> {
        return super.llenarLista(context)
    }

    override fun recuperarBundle(item: Producto): Bundle {
        return super.recuperarBundle(item)
    }

}