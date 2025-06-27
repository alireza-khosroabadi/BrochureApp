import androidx.lifecycle.SavedStateHandle
import com.alireza.brochure.brochureDetail.viewModel.BrochureDetailViewModel
import com.alireza.brochure.domain.repository.BrochureRepository
import com.alireza.brochure.model.brochureDetail.BrochureDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BrochureDetailViewModelTest {
    private lateinit var repository: BrochureRepository
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: BrochureDetailViewModel

    @Before
    fun setUp() {
        repository = mockk()
        savedStateHandle = SavedStateHandle(mapOf("brochureId" to "id123"))
        viewModel = BrochureDetailViewModel(savedStateHandle, repository)
    }

    @Test
    fun `loadBrochure sets uiState with brochure detail`() = runTest {
        val detail = BrochureDetail(
            id = "id123",
            title = "Test Brochure",
            distance = 1.0,
            type = "brochure",
            imageUrl = "img.jpg",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = mockk(relaxed = true)
        )
        coEvery { repository.findBrochureById("id123") } returns detail
        viewModel.loadBrochure()
        assertEquals(detail, viewModel.uiState.first())
    }

    @Test
    fun `loadBrochure with null id does not update uiState`() = runTest {
        val nullIdHandle = SavedStateHandle() // no brochureId
        val vm = BrochureDetailViewModel(nullIdHandle, repository)
        vm.loadBrochure()
        assertNull(vm.uiState.first())
    }
} 