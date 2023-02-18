package furkan.satellite_list.data.satellite.sources

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.utils.response.readDataFromRaw
import javax.inject.Inject

class SatelliteDataSources @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
    fun getSatelliteList(): List<SatelliteModel> {
        return gson.fromJson(
            readDataFromRaw(context, R.raw.satellite),
            Array<SatelliteModel>::class.java
        ).toList()
    }
}