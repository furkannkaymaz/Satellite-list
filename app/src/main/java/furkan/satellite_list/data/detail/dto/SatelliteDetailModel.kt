package furkan.satellite_list.data.detail.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_details")
data class SatelliteDetailModel(
    @PrimaryKey val id: Int?,
    val cost_per_launch: Int?,
    val first_flight: String?,
    val height: Int?,
    val mass: Int?,
)