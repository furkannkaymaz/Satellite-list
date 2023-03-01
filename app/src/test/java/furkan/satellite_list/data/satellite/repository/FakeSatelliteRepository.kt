package furkan.satellite_list.data.satellite.repository

import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus

class FakeSatelliteRepository(private var data: List<SatelliteModel>) : SatelliteRepository {
    override suspend fun getSatelliteList(): Resource<List<SatelliteModel>> {
        return when {
            data.any { it.id == null } -> {
                Resource.Error("Error", UIStatus.ERROR)
            }
            else -> {
                Resource.Success(data, UIStatus.SUCCESS)
            }
        }
    }
}