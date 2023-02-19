package furkan.satellite_list.data.detail.repository

import android.content.Context
import furkan.satellite_list.R
import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.data.detail.source.SatellitePositionDataSource
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatellitePositionRepositoryImpl @Inject constructor(
    private val context: Context,
    private val satellitePositionDataSource: SatellitePositionDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SatellitePositionRepository {
    override suspend fun getSatellitePosition(id: Int): Resource<SatellitePositionModel?> {
        return withContext(ioDispatcher) {
            val data = satellitePositionDataSource.getSatellitePositionList()
            val satellite = data[id.toString()]
            if (satellite != null) {
                Resource.Success(satellite, UIStatus.SUCCESS)
            } else {
                Resource.Error(context.getString(R.string.errorMessage), UIStatus.ERROR)
            }
        }
    }
}