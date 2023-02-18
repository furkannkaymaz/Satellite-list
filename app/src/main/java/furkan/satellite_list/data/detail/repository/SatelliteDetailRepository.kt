package furkan.satellite_list.data.detail.repository

import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.utils.response.Resource

interface SatelliteDetailRepository {
    suspend fun getSatelliteDetail(id : Int) : Resource<SatelliteDetailModel?>
}