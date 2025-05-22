package com.alireza.brochure.data.localCache

import com.alireza.brochure.data.fakeData.brochureList
import com.alireza.brochure.domain.model.brochure.BrochureModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import kotlin.test.Test

class LocalDataStoreImplTest {
    private lateinit var localDataStore: LocalDataStoreImpl

    @Before
    fun setUp() {
        localDataStore = LocalDataStoreImpl()
    }

    @Test
    fun `get returns null when data is not set`() {
        val result = localDataStore.get()
        assertNull(result)
    }

    @Test
    fun `set stores data correctly`() {
        val brochures = brochureList
        localDataStore.set(brochures as List<BrochureModel>)
        val result = localDataStore.get()
        assertEquals(brochures, result)
    }

    @Test
    fun `get returns stored data`() {
        val brochures = brochureList
        localDataStore.set(brochures as List<BrochureModel>)
        val result = localDataStore.get()
        assertEquals(brochures, result)
    }

    @Test
    fun `clear removes stored data`() {
        val brochures = brochureList
        localDataStore.set(brochures as List<BrochureModel>)
        localDataStore.clear()
        val result = localDataStore.get()
        assertNull(result)
    }

}