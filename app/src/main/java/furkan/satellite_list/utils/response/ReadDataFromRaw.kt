package furkan.satellite_list.utils.response

import android.content.Context
import androidx.annotation.RawRes
import furkan.satellite_list.R
import java.io.BufferedReader
import java.io.InputStreamReader

fun readDataFromRaw(context: Context, @RawRes res: Int): String {
    val inputStream = context.resources.openRawResource(res)
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val jsonString = StringBuilder().apply {
        bufferedReader.forEachLine { append(it) }
    }.toString()
    return jsonString
}