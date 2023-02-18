package furkan.satellite_list.domain.detail.mapper

import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.PositionEntityModel
import furkan.satellite_list.domain.detail.entity.SatellitePositionEntity
import javax.inject.Inject

class SatellitePositionEntityMapper @Inject constructor() :
    SatelliteMapper<SatellitePositionModel, SatellitePositionEntity> {
    override fun map(input: SatellitePositionModel): SatellitePositionEntity {
        return SatellitePositionEntity(
            input.id,
            input.positions.map {
                PositionEntityModel(it.posX,it.posY)
            }
        )
    }
}