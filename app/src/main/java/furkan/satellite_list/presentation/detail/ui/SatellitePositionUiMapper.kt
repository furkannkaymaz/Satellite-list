package furkan.satellite_list.presentation.detail.ui

import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatellitePositionEntity
import javax.inject.Inject

class SatellitePositionUiMapper @Inject constructor() :
    SatelliteMapper<SatellitePositionEntity, SatellitePositionUiData> {
    override fun map(input: SatellitePositionEntity): SatellitePositionUiData {
        return SatellitePositionUiData(
            input.id,
            input.positions.map {
                PositionUiData(it.posX,it.posY)
            }
        )
    }
}