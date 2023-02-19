package furkan.satellite_list.data.detail.dto

data class SatellitePositionModel (
    val id: String?,
    val positions: List<PositionModel?>
)

data class PositionModel (
    val posX: Double?,
    val posY: Double?
)
