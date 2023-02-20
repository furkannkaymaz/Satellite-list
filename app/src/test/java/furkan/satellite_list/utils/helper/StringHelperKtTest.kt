package furkan.satellite_list.utils.helper

import junit.framework.TestCase.*
import org.junit.Test


class StringHelperKtTest {

    @Test
    fun `isSearchable returns true when input is at least 3 characters long`() {
        val inputValue = "test"
        val result = isSearchable(inputValue)
        assertTrue(result)
    }

    @Test
    fun `isSearchable returns false when input is at least 3 characters long`() {
        val input = "te"
        val result = isSearchable(input)
        assertFalse(result)
    }

}