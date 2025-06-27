import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alireza.brochure.database.dao.SuperBannerDao
import com.alireza.brochure.database.dataBase.BrochureAppDataBase
import com.alireza.brochure.database.entity.SuperBannerEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SuperBannerDaoTest {

    private lateinit var db: BrochureAppDataBase
    private lateinit var dao: SuperBannerDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BrochureAppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.superBannerDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGetSuperBanner() = runBlocking {
        val entity = SuperBannerEntity(
            id = "banner1",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            clickUrl = "https://example.com",
            imageUrl = "https://example.com/image.png",
            orderIndex = 1,
            groupId = "group1"
        )
        dao.insertAll(listOf(entity))
        val result = dao.getAll()
        assertEquals(1, result.size)
        assertEquals("banner1", result[0].id)
    }

    @Test
    fun getByGroup_returnsCorrectBanners() = runBlocking {
        val entity1 = SuperBannerEntity(
            id = "banner1",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            clickUrl = "https://example.com/1",
            imageUrl = "https://example.com/image1.png",
            orderIndex = 1,
            groupId = "group1"
        )
        val entity2 = SuperBannerEntity(
            id = "banner2",
            publishedFrom = "2023-01-01",
            publishedUntil = "2023-12-31",
            clickUrl = "https://example.com/2",
            imageUrl = "https://example.com/image2.png",
            orderIndex = 2,
            groupId = "group2"
        )
        dao.insertAll(listOf(entity1, entity2))
        val group1Banners = dao.getByGroup("group1")
        assertEquals(1, group1Banners.size)
        assertEquals("banner1", group1Banners[0].id)
    }
} 