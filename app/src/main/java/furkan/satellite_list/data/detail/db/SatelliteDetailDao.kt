package furkan.satellite_list.data.detail.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel

@Dao
interface SatelliteDetailDao  {

    @Query("SELECT * FROM satellite_details WHERE id = :id")
    suspend fun getSatelliteDetailById(id: Int?): SatelliteDetailModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailModel)
}