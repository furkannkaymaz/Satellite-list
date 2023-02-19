package furkan.satellite_list.domain.detail.usecase

import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.flow.Flow

interface GetSatelliteDetailUseCase {
    operator fun invoke(id : Int) : Flow<Resource<SatelliteDetailEntity?>>
}

