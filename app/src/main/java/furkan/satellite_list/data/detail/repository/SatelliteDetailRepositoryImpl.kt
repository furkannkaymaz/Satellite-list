package furkan.satellite_list.data.detail.repository

import android.content.Context
import furkan.satellite_list.R
import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.detail.db.SatelliteDetailDao
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.data.detail.source.SatelliteDetailDataSource
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteDetailRepositoryImpl @Inject constructor(
    private val context: Context,
    private val satelliteDetailDataSource: SatelliteDetailDataSource,
    private val satelliteDao: SatelliteDetailDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SatelliteDetailRepository {
    override suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailModel?> {
        val data = satelliteDetailDataSource.getSatelliteDetail(id)
        return withContext(ioDispatcher) {
            val satellite = data[id]
            if (satellite != null) {
                Resource.Success(satellite, UIStatus.SUCCESS)
            } else {
                Resource.Error(context.getString(R.string.errorMessage), UIStatus.ERROR)
            }
        }
    }

    override suspend fun saveDetail(satelliteDetailDatabaseModel: SatelliteDetailModel) {
        withContext(ioDispatcher) {
            satelliteDao.insertSatelliteDetail(satelliteDetailDatabaseModel)
        }
    }
}