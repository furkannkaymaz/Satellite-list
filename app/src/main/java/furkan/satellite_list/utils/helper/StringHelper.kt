package furkan.satellite_list.utils.helper

import android.graphics.Typeface
import android.widget.TextView
import furkan.satellite_list.R
import furkan.satellite_list.app.MyApplication

fun isSearchable(input: String): Boolean {
    val letterCount = input.count { it.isLetter() }

    return letterCount == 0 || letterCount > 2
}

fun isActive(status: Boolean): String {
    return if (status) {
        MyApplication.instance.getString(R.string.active)
    } else {
        MyApplication.instance.getString(R.string.passive)
    }
}

fun TextView.setTypeFace(style: TextStyle,){
    when (style) {
        TextStyle.NORMAL -> {
            this.setTypeface(null, Typeface.NORMAL)
        }
        TextStyle.BOLD -> {
            this.setTypeface(null, Typeface.BOLD)
        }
    }
}

enum class TextStyle {
    NORMAL,
    BOLD
}

