import com.alireza.brochure.database.dao.BrochureDao
import com.alireza.brochure.database.dao.SuperBannerDao
import com.alireza.brochure.database.dataBase.BrochureAppDataBase
import com.alireza.brochure.database.dataSource.RoomDataSource
import com.alireza.brochure.database.entity.BrochureEntity
import com.alireza.brochure.database.entity.StoreLocationEntity
import com.alireza.brochure.database.entity.SuperBannerEntity
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RoomDataSourceTest {

    private lateinit var dataSource: RoomDataSource
    private val db: BrochureAppDataBase = mockk()
    private val brochureDao: BrochureDao = mockk()
    private val superBannerDao: SuperBannerDao = mockk()

    @Before
    fun setUp() {
        coEvery { db.brochureDao() } returns brochureDao
        coEvery { db.superBannerDao() } returns superBannerDao
        dataSource = RoomDataSource(db)
    }

    @Test
    fun `getBrochureList returns data from dao`() = runTest {
        val expected = listOf(
            BrochureEntity(
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
        )
        coEvery { brochureDao.getAllBrochure() } returns expected

        val result = dataSource.getBrochureList()
        assertEquals(expected, result)
    }

    @Test
    fun `saveBrochure calls insert on dao`() = runTest {
        val brochure = BrochureEntity(
            contentId = "2",
            title = "Another",
            distance = 2.0,
            type = "Type2",
            imageUrl = "url2",
            publishedFrom = "2023-02-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-02-01",
            validUntil = "2023-12-31",
            orderIndex = 2,
            storeLocation = StoreLocationEntity()
        )
        coEvery { brochureDao.insert(brochure) } returns Unit
        dataSource.saveBrochure(brochure)
        coVerify { brochureDao.insert(brochure) }
    }

    @Test
    fun `deleteAllBrochure calls clearTable on dao`() = runTest {
        coEvery { brochureDao.clearTable() } returns Unit
        dataSource.deleteAllBrochure()
        coVerify { brochureDao.clearTable() }
    }

    @Test
    fun `findBrochureById returns correct entity`() = runTest {
        val brochure = BrochureEntity(
            contentId = "3",
            title = "FindMe",
            distance = 3.0,
            type = "Type3",
            imageUrl = "url3",
            publishedFrom = "2023-03-01",
            publishedUntil = "2023-12-31",
            validFrom = "2023-03-01",
            validUntil = "2023-12-31",
            orderIndex = 3,
            storeLocation = StoreLocationEntity()
        )
        coEvery { brochureDao.findBrochureById("3") } returns brochure
        val result = dataSource.findBrochureById("3")
        assertEquals(brochure, result)
    }

    @Test
    fun `saveSuperBanner calls insertAll on superBannerDao`() = runTest {
        val banners = listOf(
            SuperBannerEntity(
                id = "banner1",
                publishedFrom = "2023-01-01",
                publishedUntil = "2023-12-31",
                clickUrl = "https://example.com",
                imageUrl = "https://example.com/image.png",
                orderIndex = 1,
                groupId = "group1"
            )
        )
        coEvery { superBannerDao.insertAll(banners) } returns Unit
        dataSource.saveSuperBanner(banners)
        coVerify { superBannerDao.insertAll(banners) }
    }

    @Test
    fun `getSuperBanner returns banners from superBannerDao`() = runTest {
        val banners = listOf(
            SuperBannerEntity(
                id = "banner2",
                publishedFrom = "2023-01-01",
                publishedUntil = "2023-12-31",
                clickUrl = "https://example.com/2",
                imageUrl = "https://example.com/image2.png",
                orderIndex = 2,
                groupId = "group2"
            )
        )
        coEvery { superBannerDao.getAll() } returns banners
        val result = dataSource.getSuperBanner()
        assertEquals(banners, result)
    }

    @Test
    fun `getBrochureList returns empty when dao returns empty`() = runTest {
        coEvery { brochureDao.getAllBrochure() } returns emptyList()
        val result = dataSource.getBrochureList()
        assertEquals(0, result.size)
    }

    @Test
    fun `saveBrochureList with duplicates calls insertAll`() = runTest {
        val brochures = listOf(
            BrochureEntity(
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
            ),
            BrochureEntity(
                contentId = "1",
                title = "Test Duplicate",
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
        )
        coEvery { brochureDao.insertAll(brochures) } returns Unit
        dataSource.saveBrochureList(brochures)
        coVerify { brochureDao.insertAll(brochures) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
} 