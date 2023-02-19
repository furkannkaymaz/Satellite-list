package furkan.satellite_list.presentation.satellite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import furkan.satellite_list.databinding.ItemSatelliteBinding
import furkan.satellite_list.presentation.base.BaseAdapter
import furkan.satellite_list.presentation.satellite.ui.SatelliteUiData
import furkan.satellite_list.utils.adapter.getDiffUtilCallBack

class SatelliteAdapter() :
    BaseAdapter<SatelliteUiData, RecyclerView.ViewHolder>(
        getDiffUtilCallBack()
    ) {

    private var onClick: ((SatelliteUiData) -> Unit)? = null
    private lateinit var bindingItemSatelliteBinding: ItemSatelliteBinding

    override fun bindView(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SatelliteViewHolder).bind(currentList[position]) {
            onClick?.invoke(it)
        }
        holder.configureView(position == itemCount - 1)
    }

    fun setOnClick(onViewClick: (SatelliteUiData) -> Unit) {
        onClick = onViewClick
    }

    override fun createView(
        context: Context,
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        bindingItemSatelliteBinding = ItemSatelliteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SatelliteViewHolder(bindingItemSatelliteBinding)
    }
}