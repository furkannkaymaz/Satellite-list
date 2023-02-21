package furkan.satellite_list.data.detail.repository

import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.getSatelliteDetailFakeData
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus

class FakeSatelliteDetailRepository(var data: SatelliteDetailModel?) : SatelliteDetailRepository {

    override suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailModel?> {
        data = getSatelliteDetailFakeData(id)
        return if (data?.id!! >= 0 ) {
            Resource.Success(data, UIStatus.SUCCESS)
        } else {
            Resource.Error(null, UIStatus.SUCCESS)
        }
    }

    override suspend fun saveDetail(satelliteDetailDatabaseModel: SatelliteDetailModel) {}
}