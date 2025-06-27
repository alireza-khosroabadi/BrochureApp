import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.database.entity.StoreLocationEntity
import com.alireza.brochure.data.mapper.toRegularBrochure
import com.alireza.brochure.data.mapper.toPremiumBrochure
import com.alireza.brochure.data.mapper.toBrochureDetail
import com.alireza.brochure.model.brochure.PremiumBrochure
import com.alireza.brochure.model.brochure.RegularBrochure
import org.junit.Assert.*
import org.junit.Test

class BrochureEntityMapperTest {
    @Test
    fun `toRegularBrochure maps BrochureEntity to RegularBrochure correctly`() {
        val entity = BrochureEntity(
            contentId = "id1",
            title = "Regular",
            distance = 2.0,
            type = "brochure",
            imageUrl = "img1.jpg",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = StoreLocationEntity()
        )
        val model: RegularBrochure = entity.toRegularBrochure() as RegularBrochure
        assertEquals("id1", model.id)
        assertEquals("Regular", model.name)
        assertEquals(2.0, model.distance, 0.01)
        assertEquals("img1.jpg", model.imageUrl)
    }

    @Test
    fun `toPremiumBrochure maps BrochureEntity to PremiumBrochure correctly`() {
        val entity = BrochureEntity(
            contentId = "id2",
            title = "Premium",
            distance = 3.0,
            type = "brochurePremium",
            imageUrl = "img2.jpg",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 2,
            storeLocation = StoreLocationEntity()
        )
        val model = entity.toPremiumBrochure() as PremiumBrochure
        assertEquals("id2", model.id)
        assertEquals("Premium", model.name)
        assertEquals(3.0, model.distance, 0.01)
        assertEquals("img2.jpg", model.imageUrl)
    }

    @Test
    fun `toBrochureDetail maps BrochureEntity to BrochureDetail correctly`() {
        val entity = BrochureEntity(
            contentId = "id3",
            title = "Detail",
            distance = 4.0,
            type = "brochure",
            imageUrl = "img3.jpg",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 3,
            storeLocation = StoreLocationEntity(city = "Tehran")
        )
        val detail = entity.toBrochureDetail()
        assertEquals("id3", detail.id)
        assertEquals("Detail", detail.title)
        assertEquals(4.0, detail.distance, 0.01)
        assertEquals("brochure", detail.type)
        assertEquals("img3.jpg", detail.imageUrl)
        assertEquals(3, detail.orderIndex)
        assertEquals("Tehran", detail.storeLocation.city)
    }
} 