package furkan.satellite_list.data.detail.repository

import furkan.satellite_list.data.detail.db.SatelliteDetailDao
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.getSatelliteDetailFakeData
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus

class FakeSatelliteDetailRepository(
    var data: SatelliteDetailModel?,
    var dao: SatelliteDetailDao
) : SatelliteDetailRepository {

    override suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailModel?> {
        data = getSatelliteDetailFakeData(id)
        return if (data?.id != null && id > 0) {
            Resource.Success(data, UIStatus.SUCCESS)
        } else {
            Resource.Error(null, UIStatus.ERROR)
        }
    }

    override suspend fun saveDetail(satelliteDetailDatabaseModel: SatelliteDetailModel) {
        dao.insertSatelliteDetail(satelliteDetailDatabaseModel)
    }
}