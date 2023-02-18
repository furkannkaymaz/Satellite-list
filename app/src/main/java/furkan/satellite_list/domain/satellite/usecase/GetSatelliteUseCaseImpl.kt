package furkan.satellite_list.domain.satellite.usecase

import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.data.satellite.repository.SatelliteRepository
import furkan.satellite_list.domain.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatelliteUseCaseImpl @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    private val mapper: SatelliteListMapper<SatelliteModel, SatelliteEntity>
) : GetSatelliteUseCase {
    override fun invoke(): Flow<Resource<List<SatelliteEntity>>> =
        flow {
            when (val response = satelliteRepository.getSatelliteList()) {
                is Resource.Success<*> -> {
                    emit(Resource.Success(mapper.map(response.data!!), response.state))
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
