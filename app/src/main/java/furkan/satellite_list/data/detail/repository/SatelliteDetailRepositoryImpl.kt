package furkan.satellite_list.data.detail.repository

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.R
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.data.detail.source.SatelliteDetailDataSource
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import furkan.satellite_list.utils.response.readDataFromRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteDetailRepositoryImpl @Inject constructor(
    private val context: Context,
    private val satelliteDetailDataSource: SatelliteDetailDataSource,
) : SatelliteDetailRepository {
    override suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailModel?> {

        val data = satelliteDetailDataSource.getSatelliteDetail()

        return withContext(Dispatchers.IO) {
            if (data.isNotEmpty()){
                Resource.Success(data[id], UIStatus.SUCCESS)
            }else{
                Resource.Error(context.getString(R.string.errorMessage), UIStatus.ERROR)
            }
        }
    }
}