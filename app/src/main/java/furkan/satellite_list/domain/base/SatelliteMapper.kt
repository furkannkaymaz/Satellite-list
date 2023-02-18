package furkan.satellite_list.domain.base

interface SatelliteMapper<I, O> {
    fun map(input: I): O
}
