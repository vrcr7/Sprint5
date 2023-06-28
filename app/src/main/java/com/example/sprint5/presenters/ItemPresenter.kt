package com.example.sprint5.presenters

import android.content.SharedPreferences
import android.os.Bundle
import com.example.sprint5.models.PreferenceManager

open class ItemPresenter(indice:SharedPreferences):PreferenceManager(indice) {
    override fun cargarItem(): Bundle {
        return super.cargarItem()
    }

    override fun guardarItem(bundle: Bundle) {
        super.guardarItem(bundle)
    }

    override fun deletePreferences() {
        super.deletePreferences()
    }
}