package com.alireza.brochure.data.jsonParser.parser

import com.alireza.brochure.domain.model.brochure.PremiumBrochure
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import kotlin.test.Test

class BrochurePPremiumParserTest {

    private val parser = BrochurePremiumParser()
    private val json = Json { ignoreUnknownKeys = true }


    @Test
    fun `parse valid json returns PremiumBrochure`() {
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

        assertEquals(
           PremiumBrochure("1", "Premium Brochure", 10.5, "image.jpg"),
            result
        )
    }

    @Test
    fun `parse invalid json returns null`() {
        val jsonString = """
            {
                "id": "invalid",
                "title": "Premium Brochure",
                "distance": 10.5,
                "brochureImage": "image.jpg"
            }
        """.trimIndent()

        val jsonElement = json.parseToJsonElement(jsonString)
        val result = parser.parse(jsonElement)

        assertNull(result)
    }


}