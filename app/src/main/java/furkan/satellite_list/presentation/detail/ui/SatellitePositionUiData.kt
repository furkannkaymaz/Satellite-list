package furkan.satellite_list.presentation.detail.ui

data class SatellitePositionUiData (
    val id: String,
    val positions: List<PositionUiData>
)

data class PositionUiData (
    val posX: Double,
    val posY: Double
)
