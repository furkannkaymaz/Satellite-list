package furkan.satellite_list.presentation.detail.ui

import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import javax.inject.Inject

class SatelliteDetailUiMapper @Inject constructor() :
    SatelliteMapper<SatelliteDetailEntity, SatelliteDetailUiData> {

    override fun map(input: SatelliteDetailEntity): SatelliteDetailUiData {
        return SatelliteDetailUiData(
            input.id,
            input.cost_per_launch,
            input.first_flight,
            input.height,
            input.mass,
        )
    }
}