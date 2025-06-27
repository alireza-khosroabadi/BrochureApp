import com.alireza.brochure.data.mapper.ErrorMapper
import com.alireza.brochure.model.appError.AppError
import io.mockk.mockk
import kotlinx.coroutines.TimeoutCancellationException
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

class ErrorMapperTest {
    @Test
    fun `fromThrowable maps IOException to NoInternet`() {
        val error = ErrorMapper.fromThrowable(IOException())
        assertEquals(AppError.NoInternet, error)
    }

    @Test
    fun `fromThrowable maps TimeoutCancellationException to Timeout`() {
        val timeoutException = mockk<TimeoutCancellationException>(relaxed = true)
        val error = ErrorMapper.fromThrowable(timeoutException)
        assertEquals(AppError.Timeout, error)
    }

    @Test
    fun `fromThrowable maps unknown exception to Unknown`() {
        val error = ErrorMapper.fromThrowable(IllegalStateException("fail"))
        assertTrue(error is AppError.Unknown)
        assertEquals("fail", (error as AppError.Unknown).message)
    }
} 