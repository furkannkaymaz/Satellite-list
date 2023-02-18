package furkan.satellite_list.domain.detail.entity

data class SatelliteDetailEntity(
    val id: Int,
    val cost_per_launch: Int,
    val first_flight: String,
    val height: Int,
    val mass: Int,
)