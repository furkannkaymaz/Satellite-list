package furkan.satellite_list.data.satellite.sources

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.utils.response.readDataFromRaw
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
                readDataFromRaw(context, R.raw.satellite),
                Array<SatelliteModel>::class.java
            ).toList()
        }
    }
}