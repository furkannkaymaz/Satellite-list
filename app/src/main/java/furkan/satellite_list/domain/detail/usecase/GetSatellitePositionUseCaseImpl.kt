package furkan.satellite_list.domain.detail.usecase

import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.data.detail.repository.SatellitePositionRepository
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatellitePositionEntity
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatellitePositionUseCaseImpl @Inject constructor(
    private val satellitePositionRepository: SatellitePositionRepository,
    private val mapper: SatelliteMapper<SatellitePositionModel, SatellitePositionEntity>
) : GetSatellitePositionUseCase {
    override fun invoke(id: Int): Flow<Resource<SatellitePositionEntity?>> = flow {
        when (val response = satellitePositionRepository.getSatellitePosition(id)) {
            is Resource.Success -> {
                emit(Resource.Success(response.data?.let { mapper.map(it) }, response.state))
            }
            is Resource.Error -> {
                emit(Resource.Error("Error", response.state))
            }
            is Resource.Loading -> {
                emit(Resource.Loading(response.state))
            }
        }
    }

}