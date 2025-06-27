import com.alireza.brochure.data.jsonParser.BrochureParserFactory
import com.alireza.brochure.data.jsonParser.parser.BrochureParser
import com.alireza.brochure.data.jsonParser.parser.BrochurePremiumParser
import com.alireza.brochure.data.jsonParser.parser.SuperBannerCarouselParser
import org.junit.Assert.*
import org.junit.Test

class BrochureParserFactoryTest {

    @Test
    fun `getParser returns correct parser for brochure`() {
        val parser = BrochureParserFactory.getParser(0, "brochure")
        assertTrue(parser is BrochureParser)
    }

    @Test
    fun `getParser returns correct parser for brochurePremium`() {
        val parser = BrochureParserFactory.getParser(0, "brochurePremium")
        assertTrue(parser is BrochurePremiumParser)
    }

    @Test
    fun `getParser returns correct parser for superBannerCarousel`() {
        val parser = BrochureParserFactory.getParser(0, "superBannerCarousel")
        assertTrue(parser is SuperBannerCarouselParser)
    }

    @Test
    fun `getParser returns null for unknown type`() {
        val parser = BrochureParserFactory.getParser(0, "unknown")
        assertNull(parser)
    }
} 