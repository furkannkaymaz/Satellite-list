package furkan.satellite_list.presentation.satellite.adapter

import furkan.satellite_list.databinding.ItemSatelliteBinding
import furkan.satellite_list.presentation.base.BaseViewHolder
import furkan.satellite_list.presentation.satellite.ui.SatelliteUiData

class SatelliteViewHolder(
    private val binding : ItemSatelliteBinding
) : BaseViewHolder<SatelliteUiData>(binding) {

    override fun bind(data: SatelliteUiData, onItemClick: ((SatelliteUiData) -> Unit)) {
        super.bind(data, onItemClick)

        binding.tvName.text = data.name
        binding.tvStatus.text = data.active.toString()

        binding.llContainer.setOnClickListener {
            onItemClick.invoke(data)
        }
    }
}