package furkan.satellite_list.domain.satellite.usecase

import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.data.satellite.repository.FakeSatelliteRepository
import furkan.satellite_list.domain.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeGetFilteredSatelliteUseCase @Inject constructor(
    private val fakeSatelliteRepository: FakeSatelliteRepository,
    private val mapper: SatelliteListMapper<SatelliteModel, SatelliteEntity>,
) : GetFilteredSatelliteUseCase {

    override fun invoke(
        keyword: String,
    ): Flow<Resource<List<SatelliteEntity>>> = flow {
        when (val response = fakeSatelliteRepository.getSatelliteList()) {
            is Resource.Success -> {
                val filteredList = response.data?.filter { it.name?.contains(keyword,true) == true }
                emit(Resource.Success(mapper.map(filteredList!!), response.state))
            }
            is Resource.Error -> {
                emit(Resource.Error("Error Message", response.state))
            }
            else -> Unit
        }
    }
}
