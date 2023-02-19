package furkan.satellite_list.domain.satellite.usecase

import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.data.satellite.repository.FakeSatelliteRepository
import furkan.satellite_list.domain.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetSatelliteUseCase(
    private val fakeSatelliteRepository: FakeSatelliteRepository,
    private val mapper: SatelliteListMapper<SatelliteModel, SatelliteEntity>
) : GetSatelliteUseCase {
    override fun invoke(): Flow<Resource<List<SatelliteEntity>>> = flow {

        when (val response = fakeSatelliteRepository.getSatelliteList()) {
            is Resource.Success -> {
                emit(Resource.Success(mapper.map(response.data!!), response.state))
            }
            is Resource.Error -> {
                emit(Resource.Error("Error Message", response.state))
            }
            else -> Unit
        }
    }
}