package furkan.satellite_list.data.detail.db

import androidx.room.Database
import androidx.room.RoomDatabase
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel

@Database(entities = [SatelliteDetailModel::class], version = 2)
abstract class SatelliteDetailDatabase : RoomDatabase() {

    abstract fun satelliteDetailDao(): SatelliteDetailDao
}