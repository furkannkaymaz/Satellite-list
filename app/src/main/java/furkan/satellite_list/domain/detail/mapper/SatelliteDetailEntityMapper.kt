package furkan.satellite_list.domain.detail.mapper

import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import javax.inject.Inject

class SatelliteDetailEntityMapper @Inject constructor() :
    SatelliteMapper<SatelliteDetailModel, SatelliteDetailEntity> {
    override fun map(input: SatelliteDetailModel): SatelliteDetailEntity {
        return SatelliteDetailEntity(
            input.id!!,
            input.cost_per_launch ?: 0,
            input.first_flight ?: "",
            input.height?: 0,
            input.mass?: 0
        )
    }
}