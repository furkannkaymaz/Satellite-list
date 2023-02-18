package furkan.satellite_list.data.satellite.repository

import android.content.Context
import furkan.satellite_list.utils.response.Resource
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.data.satellite.sources.SatelliteDataSources
import furkan.satellite_list.utils.response.UIStatus
import furkan.satellite_list.utils.response.readDataFromRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val context: Context,
    private val satelliteDataSources: SatelliteDataSources,
) : SatelliteRepository {
    override suspend fun getSatelliteList(): Resource<List<SatelliteModel>> {

        val data = satelliteDataSources.getSatelliteList()

        return withContext(Dispatchers.IO) {
            if (data.isNotEmpty()){
                Resource.Success(data, UIStatus.SUCCESS)
            }else{
                Resource.Error(context.getString(R.string.errorMessage), UIStatus.ERROR)
            }
        }
    }
}