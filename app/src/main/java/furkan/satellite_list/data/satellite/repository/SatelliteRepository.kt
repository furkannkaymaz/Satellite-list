package furkan.satellite_list.data.satellite.repository

import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.data.satellite.dto.SatelliteModel

interface SatelliteRepository {
    suspend fun getSatelliteList() : Resource<List<SatelliteModel>>
}