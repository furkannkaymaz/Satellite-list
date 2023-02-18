package furkan.satellite_list.data.detail.repository

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import furkan.satellite_list.utils.response.readDataFromRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatellitePositionRepositoryImpl @Inject constructor(
    private val context: Context
): SatellitePositionRepository {
    override suspend fun getSatellitePosition(id: Int): Resource<SatellitePositionModel?> {
        val gson = Gson()
        val satelliteDetail: List<SatellitePositionModel> = gson.fromJson(
            readDataFromRaw(context, R.raw.position),
            Array<SatellitePositionModel>::class.java
        ).toList()

        val satellitePositionMap = satelliteDetail.associateBy { it.id }

        return withContext(Dispatchers.IO) {
            if (satellitePositionMap.isNotEmpty()){
                Resource.Success(satellitePositionMap[id.toString()], UIStatus.SUCCESS)
            }else{
                Resource.Error(context.getString(R.string.errorMessage), UIStatus.ERROR)
            }
        }
    }
}