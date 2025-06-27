import com.alireza.brochure.netwrok.apiService.BrochureApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class BrochureApiServiceIntegrationTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: BrochureApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
        apiService = retrofit.create(BrochureApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getBrochureList parses response correctly`() = runTest {
        val jsonResponse = """
            {
                "_embedded": null,
                "_links": null,
                "page": null
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(jsonResponse).setResponseCode(200))

        val response = apiService.getBrochureList()
        assertEquals(true, response.isSuccessful)
        assertEquals(null, response.body()?.embedded)
    }

    @Test
    fun `getBrochureList handles malformed JSON gracefully`() = runTest {
        val malformedJson = "{" // not a valid JSON
        mockWebServer.enqueue(MockResponse().setBody(malformedJson).setResponseCode(200))

        assertThrows(Exception::class.java) {
            runBlocking {
                apiService.getBrochureList()
            }
        }
    }

    @Test
    fun `getBrochureList handles unexpected status code`() = runTest {
        val jsonResponse = """
            {
                "_embedded": null,
                "_links": null,
                "page": null
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(jsonResponse).setResponseCode(404))

        val response = apiService.getBrochureList()
        assertFalse(response.isSuccessful)
        assertNull(response.body())
        assertEquals(404, response.code())
    }

    @Test
    fun `getBrochureList sends correct GET request`() = runTest {
        val jsonResponse = """
            {
                "_embedded": null,
                "_links": null,
                "page": null
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(jsonResponse).setResponseCode(200))

        apiService.getBrochureList()
        val recordedRequest = mockWebServer.takeRequest()
        assertEquals("/shelf.json", recordedRequest.path)
        assertEquals("GET", recordedRequest.method)
    }
} 