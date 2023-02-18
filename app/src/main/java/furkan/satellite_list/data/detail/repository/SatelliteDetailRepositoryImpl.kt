package furkan.satellite_list.data.detail.repository

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import furkan.satellite_list.utils.response.readDataFromRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteDetailRepositoryImpl @Inject constructor(
    private val context: Context
) : SatelliteDetailRepository {
    override suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailModel?> {
        val gson = Gson()
        val satelliteDetail: List<SatelliteDetailModel> = gson.fromJson(
            readDataFromRaw(context, R.raw.satellite_detail),
            Array<SatelliteDetailModel>::class.java
        ).toList()

        val satelliteMap = satelliteDetail.associateBy { it.id }

        return withContext(Dispatchers.IO) {
            if (satelliteMap.isNotEmpty()){
                Resource.Success(satelliteMap[id], UIStatus.SUCCESS)
            }else{
                Resource.Error(context.getString(R.string.errorMessage), UIStatus.ERROR)
            }
        }
    }
}