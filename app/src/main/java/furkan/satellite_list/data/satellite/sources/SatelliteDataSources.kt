package furkan.satellite_list.data.satellite.sources

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.utils.Constants
import furkan.satellite_list.utils.response.readDataFromAssets
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteDataSources @Inject constructor(
    private val context: Context,
    private val gson: Gson,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun getSatelliteList(): List<SatelliteModel> {
        return withContext(ioDispatcher) {
            return@withContext gson.fromJson(
                readDataFromAssets(context, Constants.SATELLITE_JSON),
                Array<SatelliteModel>::class.java
            ).toList()
        }
    }
}