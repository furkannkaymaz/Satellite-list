package furkan.satellite_list.data.detail.dto

data class SatelliteDetailModel(
    val id: Int,
    val cost_per_launch: Int,
    val first_flight: String,
    val height: Int,
    val mass: Int,
)