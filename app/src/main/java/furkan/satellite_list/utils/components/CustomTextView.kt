package furkan.satellite_list.utils.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import furkan.satellite_list.R

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        gravity = Gravity.CENTER_VERTICAL
    }

    override fun setTextSize(size: Float) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    @SuppressLint("ResourceType")
    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
        setTextColor(ContextCompat.getColor(context, R.color.black))
    }

    override fun setTextSize(unit: Int, size: Float) {
        super.setTextSize(unit, size)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
    }


}