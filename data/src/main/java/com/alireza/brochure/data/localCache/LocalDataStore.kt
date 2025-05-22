package com.alireza.brochure.data.localCache

import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.model.brochure.BrochureModel

interface LocalDataStore {
    fun get(): List<BrochureModel>?

    fun set(newBrochures: List<BrochureModel>)

    fun clear()
}