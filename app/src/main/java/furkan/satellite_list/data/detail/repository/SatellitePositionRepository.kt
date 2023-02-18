package furkan.satellite_list.data.detail.repository

import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.utils.response.Resource

interface SatellitePositionRepository {
    suspend fun getSatellitePosition(id : Int) : Resource<SatellitePositionModel?>
}