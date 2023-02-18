package furkan.satellite_list.domain.detail.usecase

import furkan.satellite_list.domain.detail.entity.SatellitePositionEntity
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.flow.Flow

interface GetSatellitePositionUseCase {
    operator fun invoke(id : Int) : Flow<Resource<SatellitePositionEntity?>>
}