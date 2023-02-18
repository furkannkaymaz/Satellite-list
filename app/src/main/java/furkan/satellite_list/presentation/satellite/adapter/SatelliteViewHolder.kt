package furkan.satellite_list.presentation.satellite.adapter

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
            }
        } else {
            binding.apply {
                tvName.text = data.name
                tvStatus.text = isActive(data.active)
                tvName.setTypeFace(TextStyle.NORMAL)
                tvStatus.setTypeFace(TextStyle.NORMAL)
            }
        }

        binding.llContainer.setOnClickListener {
            onItemClick.invoke(data)
        }
    }
}