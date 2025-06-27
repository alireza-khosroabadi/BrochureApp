import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alireza.brochure.brochureDetail.screen.BrochureDetailScreenContentPreview
import com.alireza.brochure.model.brochureDetail.BrochureDetail
import com.alireza.brochure.model.brochureDetail.StoreLocation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrochureDetailScreenTest {

    private val storeLocation = StoreLocation(city = "Tehran", latitude = 0.0, longitude = 0.0, street = "Sattarkhan", streetNumber = "17", zipCode = "1234")

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsTitleAndImage_whenBrochureDetailIsPresent() {
        val detail = BrochureDetail(
            id = "id1",
            title = "Test Brochure Detail",
            distance = 1.0,
            type = "brochure",
            imageUrl = "img.jpg",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = storeLocation
        )
        composeTestRule.setContent {
            BrochureDetailScreenContentPreview(detail)
        }
        composeTestRule.onNodeWithText("Test Brochure Detail").assertIsDisplayed()
    }

    @Test
    fun showsNotFound_whenNoDetail() {
        composeTestRule.setContent {
            BrochureDetailScreenContentPreview(null)
        }
        composeTestRule.onNodeWithText("Not Found").assertIsDisplayed()
    }
} 