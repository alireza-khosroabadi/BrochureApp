package com.alireza.brochure.data.localCache

import com.alireza.brochure.model.brochure.BrochureModel

interface LocalDataStore {
    fun get(): List<BrochureModel>?

    fun set(newBrochures: List<BrochureModel>)

    fun clear()
}