package furkan.satellite_list.data.detail.repository

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.data.detail.source.SatellitePositionDataSource
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import furkan.satellite_list.utils.response.readDataFromRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatellitePositionRepositoryImpl @Inject constructor(
    private val context: Context,
    private val satellitePositionDataSource: SatellitePositionDataSource
): SatellitePositionRepository {
    override suspend fun getSatellitePosition(id: Int): Resource<SatellitePositionModel?> {

        val data = satellitePositionDataSource.getSatellitePositionList()

        return withContext(Dispatchers.IO) {
            if (data.isNotEmpty()){
                Resource.Success(data[id.toString()], UIStatus.SUCCESS)
            }else{
                Resource.Error(context.getString(R.string.errorMessage), UIStatus.ERROR)
            }
        }
    }
}