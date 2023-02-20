package furkan.satellite_list.utils.response

import android.content.Context
import android.content.res.AssetManager
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ReadDataFromRawKtTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = mock(Context::class.java)
        val mockAssetsManager = mock(AssetManager::class.java)
        `when`(context.assets).thenReturn(mockAssetsManager)
    }

    @Test
    fun `readDataFromRaw returns correct data`() {
        val inputStream = "test_data".byteInputStream()
        `when`(context.assets.open("test.json")).thenReturn(inputStream)
        val result = readDataFromAssets(context, "test.json")
        assertEquals("test_data", result)
    }
}