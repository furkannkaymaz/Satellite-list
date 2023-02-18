package furkan.satellite_list.domain.satellite.usecase

import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.flow.Flow

interface GetFilteredSatelliteUseCase {
    operator fun invoke(keyword : String) : Flow<Resource<List<SatelliteEntity>>>
}