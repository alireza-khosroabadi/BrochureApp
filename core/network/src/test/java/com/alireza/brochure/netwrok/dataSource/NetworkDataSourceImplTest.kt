import com.alireza.brochure.netwrok.apiService.BrochureApiService
import com.alireza.brochure.netwrok.dataSource.NetworkDataSourceImpl
import com.alireza.brochure.netwrok.model.BrochureListDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class NetworkDataSourceImplTest {

    private val apiService: BrochureApiService = mockk()
    private val dataSource = NetworkDataSourceImpl(apiService)

    @Test
    fun `getBrochureList delegates to apiService and returns response`() = runTest {
        val expectedResponse = Response.success(BrochureListDto())
        coEvery { apiService.getBrochureList() } returns expectedResponse

        val result = dataSource.getBrochureList()
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `getBrochureList returns error response from apiService`() = runTest {
        val errorResponse = Response.error<BrochureListDto>(500, ResponseBody.create(null, "error"))
        coEvery { apiService.getBrochureList() } returns errorResponse

        val result = dataSource.getBrochureList()
        assertEquals(errorResponse, result)
    }
} 