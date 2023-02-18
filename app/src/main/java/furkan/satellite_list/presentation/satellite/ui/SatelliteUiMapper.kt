package furkan.satellite_list.presentation.satellite.ui

import furkan.satellite_list.domain.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import javax.inject.Inject

class SatelliteUiMapper @Inject constructor() :
    SatelliteListMapper<SatelliteEntity, SatelliteUiData> {
    override fun map(input: List<SatelliteEntity>): List<SatelliteUiData> {
        return input.map {
            SatelliteUiData(
                it.id,
                it.active,
                it.name
            )
        }
    }
}