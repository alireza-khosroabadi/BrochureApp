import com.alireza.brochure.data.mapper.toBrochureEntity
import com.alireza.brochure.data.mapper.toSuperBannerEntity
import com.alireza.brochure.netwrok.model.BrochureDto
import com.alireza.brochure.netwrok.model.SuperBannerCarouselDto
import org.junit.Assert.*
import org.junit.Test

class BrochureDtoMapperTest {
    @Test
    fun `toBrochureEntity maps BrochureDto to BrochureEntity correctly`() {
        val dto = BrochureDto(
            contentId = "id123",
            title = "Test Brochure",
            distance = 5.0,
            brochureImage = "img.jpg",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31"
        )
        val wrapper = dto.toBrochureEntity(index = 2, type = "brochure")
        val entity = wrapper.entity
        assertEquals("id123", entity.contentId)
        assertEquals("Test Brochure", entity.title)
        assertEquals(5.0, entity.distance, 0.01)
        assertEquals("img.jpg", entity.imageUrl)
        assertEquals("2023-01-01", entity.publishedFrom)
        assertEquals("2023-12-31", entity.publishedUntil)
        assertEquals("2023-01-01", entity.validFrom)
        assertEquals("2023-12-31", entity.validUntil)
        assertEquals(2, entity.orderIndex)
        assertEquals("brochure", entity.type)
    }

    @Test
    fun `toSuperBannerEntity maps SuperBannerCarouselDto to SuperBannerEntity correctly`() {
        val dto = SuperBannerCarouselDto(
            id = "banner1",
            imageUrl = "img.png",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            clickUrl = "http://click.me"
        )
        val entity = dto.toSuperBannerEntity(index = 3, groupId = "groupA")
        assertEquals("banner1", entity.id)
        assertEquals("img.png", entity.imageUrl)
        assertEquals("2023-01-01", entity.publishedFrom)
        assertEquals("2023-12-31", entity.publishedUntil)
        assertEquals(3, entity.orderIndex)
        assertEquals("http://click.me", entity.clickUrl)
        assertEquals("groupA", entity.groupId)
    }
} 