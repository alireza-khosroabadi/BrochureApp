import com.alireza.brochure.data.jsonParser.parser.SuperBannerCarouselParser
import com.alireza.brochure.database.entity.BaseBrochure
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.junit.Assert.*
import org.junit.Test

class SuperBannerCarouselParserTest {

    @Test
    fun `parse valid SuperBannerCarouselDto array returns SuperBannerWrapperEntity`() {
        val json = """
            [
                {
                    "content": {
                        "id": "banner1",
                        "publishedFrom": "2023-01-01",
                        "publishedUntil": "2023-12-31",
                        "clickUrl": "http://example.com",
                        "imageUrl": "http://example.com/image.png"
                    }
                }
            ]
        """.trimIndent()
        val jsonElement: JsonElement = Json.parseToJsonElement(json)
        val parser = SuperBannerCarouselParser(index = 0, type = "group1")
        val result = parser.parse(jsonElement)
        assertTrue(result is BaseBrochure.SuperBannerWrapperEntity)
        assertEquals("banner1", (result as BaseBrochure.SuperBannerWrapperEntity).entity.first().id)
    }

    @Test
    fun `parse invalid json returns null`() {
        val json = """[ { "invalid": "data" } ]"""
        val jsonElement: JsonElement = Json.parseToJsonElement(json)
        val parser = SuperBannerCarouselParser(index = 0, type = "group1")
        val result = parser.parse(jsonElement)
        assertNull(result)
    }
} 