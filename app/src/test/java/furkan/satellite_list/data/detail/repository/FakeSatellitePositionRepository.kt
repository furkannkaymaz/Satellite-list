package furkan.satellite_list.data.detail.repository

import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus

class FakeSatellitePositionRepository(private var data: SatellitePositionModel) : SatellitePositionRepository {
    override suspend fun getSatellitePosition(id: Int): Resource<SatellitePositionModel?> {
        return when {
            data.id == null -> {
                Resource.Error("Error", UIStatus.ERROR)
            }
            else -> {
                Resource.Success(data, UIStatus.SUCCESS)
            }
        }
    }
}