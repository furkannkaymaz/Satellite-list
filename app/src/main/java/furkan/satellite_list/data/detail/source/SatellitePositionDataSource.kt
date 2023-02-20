package furkan.satellite_list.data.detail.source

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.utils.Constants
import furkan.satellite_list.utils.response.readDataFromAssets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatellitePositionDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
   suspend fun getSatellitePositionList(): Map<String, SatellitePositionModel> {
        return withContext(Dispatchers.IO){
            val satelliteDetail: List<SatellitePositionModel> = gson.fromJson(
                readDataFromAssets(context, Constants.POSITIONS_JSON),
                Array<SatellitePositionModel>::class.java
            ).toList()

            return@withContext satelliteDetail.associateBy { it.id.toString() }
        }
    }
}