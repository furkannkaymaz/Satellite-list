package furkan.satellite_list.domain.satellite.base

interface SatelliteMapper<I, O> {
    fun map(input: I): O
}
