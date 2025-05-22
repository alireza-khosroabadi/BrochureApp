package com.alireza.brochure.data.localCache

import com.alireza.brochure.domain.model.brochure.BrochureModel

class LocalDataStoreImpl: LocalDataStore {
    private var brochures: List<BrochureModel>? = null

    override fun get(): List<BrochureModel>? = brochures

    override fun set(newBrochures: List<BrochureModel>) {
        brochures = newBrochures
    }

    override fun clear() {
        brochures = null
    }
}