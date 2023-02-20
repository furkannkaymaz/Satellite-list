package furkan.satellite_list.data.detail.source

import android.content.Context
import com.google.gson.Gson
import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.detail.db.SatelliteDetailDao
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.utils.Constants
import furkan.satellite_list.utils.response.readDataFromAssets
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteDetailDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson,
    private val dao: SatelliteDetailDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun getSatelliteDetail(id: Int): Map<Int, SatelliteDetailModel> {
        return withContext(ioDispatcher) {

            val dao = dao.getSatelliteDetailById(id)

            if (dao != null && dao.id == id) {
                return@withContext mapOf(id to dao)
            } else {
                val satelliteDetail: List<SatelliteDetailModel> = gson.fromJson(
                    readDataFromAssets(context, Constants.SATELLITE_DETAIL_JSON),
                    Array<SatelliteDetailModel>::class.java
                ).toList()

                return@withContext satelliteDetail.associateBy { it.id ?: -1 }
            }
        }
    }
}