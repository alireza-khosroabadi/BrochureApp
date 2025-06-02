package com.alireza.brochure.domain.useCase

import com.alireza.brochure.model.brochure.RegularBrochure
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochure.model.baseResult.BaseResult
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FilterBrochureUseCaseTest {

    private val repository: BrochureRepository = FakeBrochureRepository()
    private val useCase = FilterBrochureUseCase(repository)

    @Test
    fun `invoke returns filtered brochures when filterByDistance is true`() = runTest {

        val expectedFilteredBrochures = listOf(
            RegularBrochure("1", "Brochure 1", 3.0, "image1.jpg"),
            RegularBrochure("3", "Brochure 3", 5.0, "image3.jpg")
        )

        val result = useCase.invoke(true)

        assertEquals(BaseResult.Success(expectedFilteredBrochures), result)
    }


    @Test
    fun `invoke returns all brochures when filterByDistance is false`() = runTest {

        val cachedBrochures = repository.getCachedBrochureList()

        val result = useCase.invoke(false)

        assertEquals(BaseResult.Success(cachedBrochures), result)
    }
  
}