package furkan.satellite_list.domain.satellite.usecase

import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import kotlinx.coroutines.flow.Flow

interface GetSatelliteUseCase {
    operator fun invoke() : Flow<Resource<List<SatelliteEntity>>>
}

