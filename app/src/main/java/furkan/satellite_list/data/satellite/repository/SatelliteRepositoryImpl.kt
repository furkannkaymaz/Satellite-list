package furkan.satellite_list.data.satellite.repository

import android.content.Context
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.R
import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.data.satellite.sources.SatelliteDataSources
import furkan.satellite_list.utils.response.UIStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val context: Context,
    private val satelliteDataSources: SatelliteDataSources,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SatelliteRepository {
    override suspend fun getSatelliteList(): Resource<List<SatelliteModel>> {
        return withContext(ioDispatcher) {
            val data = satelliteDataSources.getSatelliteList()

            if (data.all { it.id != null }) {
                Resource.Success(data, UIStatus.SUCCESS)
            } else {
                Resource.Error(
                    context.getString(R.string.errorMessage),
                    UIStatus.ERROR
                )
            }
        }
    }
}