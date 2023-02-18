package furkan.satellite_list.domain.detail.entity

data class SatellitePositionEntity (
    val id: String,
    val positions: List<PositionEntityModel>
)

data class PositionEntityModel (
    val posX: Double,
    val posY: Double
)
