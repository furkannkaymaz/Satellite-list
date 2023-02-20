package furkan.satellite_list.utils.response

import android.content.Context
import android.content.res.Resources
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
        val resources = mock(Resources::class.java)
        `when`(context.resources).thenReturn(resources)
    }

    @Test
    fun `readDataFromRaw returns correct data`() {
        // arrange
        val inputStream = "test_data".byteInputStream()
        val resId = 1
        `when`(context.resources.openRawResource(resId)).thenReturn(inputStream)
        val result = readDataFromRaw(context, resId)
        assertEquals("test_data", result)
    }
}