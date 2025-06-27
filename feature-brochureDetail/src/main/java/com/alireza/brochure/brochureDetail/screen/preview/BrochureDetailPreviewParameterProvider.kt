import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alireza.brochure.model.brochureDetail.BrochureDetail
import com.alireza.brochure.model.brochureDetail.StoreLocation

class BrochureDetailPreviewParameterProvider : PreviewParameterProvider<BrochureDetail> {
    override val values = sequenceOf(
        BrochureDetail(
            id = "id1",
            title = "Preview Brochure Vertical",
            distance = 1.0,
            type = "brochure",
            imageUrl = "img.jpg",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = storeLocation
        ),
        BrochureDetail(
            id = "id2",
            title = "Another Brochure",
            distance = 2.0,
            type = "brochure",
            imageUrl = "img2.jpg",
            publishedFrom = "2023-02-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-02-01",
            validUntil = "2023-12-31",
            orderIndex = 2,
            storeLocation = storeLocation
        )
    )
}

internal val storeLocation = StoreLocation(city = "Tehran", latitude = 0.0, longitude = 0.0, street = "Sattarkhan", streetNumber = "17", zipCode = "1234")