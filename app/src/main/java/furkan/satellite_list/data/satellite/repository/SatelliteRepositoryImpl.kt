package furkan.satellite_list.data.satellite.repository

import android.content.Context
import furkan.satellite_list.utils.response.Resource
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.utils.response.UIStatus
import furkan.satellite_list.utils.response.readDataFromRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val context: Context
) : SatelliteRepository {
    override suspend fun getSatelliteList(): Resource<List<SatelliteModel>> {

        val gson = Gson()
        val satelliteList: List<SatelliteModel> = gson.fromJson(
            readDataFromRaw(context, R.raw.satellite),
            Array<SatelliteModel>::class.java
        ).toList()

        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(satelliteList, UIStatus.SUCCESS)
            } catch (throwable: Throwable) {
                Resource.Error("Something Went Wrong", UIStatus.ERROR)
            }
        }
    }
}