package furkan.satellite_list.data.detail.source

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.utils.response.readDataFromRaw
import javax.inject.Inject

class SatellitePositionDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
    fun getSatellitePositionList(): Map<String, SatellitePositionModel> {
        val satelliteDetail: List<SatellitePositionModel> = gson.fromJson(
            readDataFromRaw(context, R.raw.position),
            Array<SatellitePositionModel>::class.java
        ).toList()

        return satelliteDetail.associateBy { it.id }
    }
}