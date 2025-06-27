package com.alireza.brochure.data.jsonParser.parser

import com.alireza.brochure.model.brochure.PremiumBrochure
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import kotlin.test.Test
import com.alireza.brochure.database.entity.BaseBrochure
import kotlinx.serialization.json.JsonElement

class BrochurePPremiumParserTest {

    private val parser = BrochurePremiumParser(0, "premium")
    private val json = Json { ignoreUnknownKeys = true }


    @Test
    fun `parse valid json returns BrochureWrapperEntity with correct fields`() {
        val jsonString = """
            {
                "id": 1,
                "contentId": "content123",
                "title": "Premium Brochure",
                "distance": 10.5,
                "brochureImage": "image.jpg"
            }
        """.trimIndent()

        val jsonElement = json.parseToJsonElement(jsonString)
        val result = parser.parse(jsonElement)

        assertTrue(result is BaseBrochure.BrochureWrapperEntity)
        val entity = (result as BaseBrochure.BrochureWrapperEntity).entity
        assertEquals("content123", entity.contentId)
        assertEquals("Premium Brochure", entity.title)
        assertEquals(10.5, entity.distance, 0.01)
        assertEquals("image.jpg", entity.imageUrl)
    }

    @Test
    fun `parse valid BrochureDto json returns BrochureWrapperEntity`() {
        val json = """
            {
                "contentId": "123",
                "title": "Test Brochure",
                "distance": 10.5,
                "type": "brochure",
                "brochureImage": "http://example.com/image.png",
                "publishedFrom": "2023-01-01",
                "publishedUntil": "2023-12-31",
                "validFrom": "2023-01-01",
                "validUntil": "2023-12-31"
            }
        """.trimIndent()
        val jsonElement: JsonElement = Json.parseToJsonElement(json)
        val parser = BrochureParser(index = 0, type = "brochure")
        val result = parser.parse(jsonElement)
        assertTrue(result is BaseBrochure.BrochureWrapperEntity)
        assertEquals("Test Brochure", (result as BaseBrochure.BrochureWrapperEntity).entity.title)
    }

    @Test
    fun `parse invalid json returns null`() {
        val json = """{ "invalid": "data" }"""
        val jsonElement: JsonElement = Json.parseToJsonElement(json)
        val parser = BrochureParser(index = 0, type = "brochure")
        val result = parser.parse(jsonElement)
        assertNull(result)
    }
}