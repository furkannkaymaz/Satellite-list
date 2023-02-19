package furkan.satellite_list.presentation.satellite.adapter

import android.view.View
import androidx.core.content.ContextCompat
import furkan.satellite_list.R
import furkan.satellite_list.databinding.ItemSatelliteBinding
import furkan.satellite_list.presentation.base.BaseViewHolder
import furkan.satellite_list.presentation.satellite.ui.SatelliteUiData
import furkan.satellite_list.utils.helper.TextStyle
import furkan.satellite_list.utils.helper.isActive
import furkan.satellite_list.utils.helper.setTypeFace

class SatelliteViewHolder(
    private val binding: ItemSatelliteBinding
) : BaseViewHolder<SatelliteUiData>(binding) {

    override fun bind(data: SatelliteUiData, onItemClick: ((SatelliteUiData) -> Unit)) {
        super.bind(data, onItemClick)

        if (isActive(data.active) == binding.tvName.context.getString(R.string.active)) {
            binding.apply {
                tvName.text = data.name
                tvStatus.text = isActive(data.active)
                tvName.setTypeFace(TextStyle.BOLD)
                tvStatus.setTypeFace(TextStyle.BOLD)
                ivStatus.setImageResource(R.drawable.ic_active)
            }
        } else {
            binding.apply {
                tvName.text = data.name
                tvStatus.text = isActive(data.active)
                tvName.setTypeFace(TextStyle.NORMAL)
                tvName.setTextColor(ContextCompat.getColor(binding.tvName.context, R.color.grayText));
                tvStatus.setTextColor(ContextCompat.getColor(binding.tvName.context, R.color.grayText));
                tvStatus.setTypeFace(TextStyle.NORMAL)
                ivStatus.setImageResource(R.drawable.ic_passive)
            }
        }

        binding.llContainer.setOnClickListener {
            onItemClick.invoke(data)
        }
    }

    fun configureView(isLastPosition: Boolean) {
        if (isLastPosition) {
            binding.view.visibility = View.GONE
        }
    }
}