package furkan.satellite_list.data.detail.source

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.utils.response.readDataFromRaw
import javax.inject.Inject

class SatelliteDetailDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
    fun getSatelliteDetail(): Map<Int, SatelliteDetailModel> {
        val gson = Gson()
        val satelliteDetail: List<SatelliteDetailModel> = gson.fromJson(
            readDataFromRaw(context, R.raw.satellite_detail),
            Array<SatelliteDetailModel>::class.java
        ).toList()

        return satelliteDetail.associateBy { it.id }
    }
}