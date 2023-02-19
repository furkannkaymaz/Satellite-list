package furkan.satellite_list.utils.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import furkan.satellite_list.databinding.ViewSearchBinding

class CustomSearchView(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    private val binding: ViewSearchBinding =
        ViewSearchBinding.inflate(LayoutInflater.from(context), this, true)

    fun handleSearchText(search: (String) -> Unit) {
        binding.etSearch.addTextChangedListener {
            search.invoke(it.toString())
        }
    }
}