import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alireza.brochure.database.dao.BrochureDao
import com.alireza.brochure.database.dataBase.BrochureAppDataBase
import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.database.entity.StoreLocationEntity
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrochureDaoTest {

    private lateinit var db: BrochureAppDataBase
    private lateinit var dao: BrochureDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BrochureAppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.brochureDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGetBrochure() = runTest {
        val entity = BrochureEntity(
            contentId = "1",
            title = "Test",
            distance = 1.0,
            type = "Type",
            imageUrl = "url",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-01-01",
            validUntil = "2023-12-31",
            orderIndex = 1,
            storeLocation = StoreLocationEntity()
        )
        dao.insert(entity)
        val result = dao.getAllBrochure()
        assertEquals(1, result.size)
        assertEquals("Test", result[0].title)
    }
} 