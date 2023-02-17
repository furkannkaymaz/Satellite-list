package furkan.satellite_list.domain.satellite.mapper

import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.domain.satellite.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import javax.inject.Inject

class SatelliteEntityMapper @Inject constructor() : SatelliteListMapper<SatelliteModel, SatelliteEntity> {
    override fun map(input: List<SatelliteModel>): List<SatelliteEntity> {
        return input.map {
            SatelliteEntity(
                it.id,
                it.active,
                it.name
            )
        }
    }
}