package furkan.satellite_list.domain.satellite.usecase

import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.data.satellite.repository.SatelliteRepository
import furkan.satellite_list.domain.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilteredSatelliteUseCaseImpl @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    private val mapper: SatelliteListMapper<SatelliteModel, SatelliteEntity>,
) : GetFilteredSatelliteUseCase {

    override fun invoke(
        keyword: String,
    ): Flow<Resource<List<SatelliteEntity>>> = flow {
        when (val response = satelliteRepository.getSatelliteList()) {
            is Resource.Success -> {
                val filteredList = response.data?.filter { it.name!!.contains(keyword,true) }
                emit(Resource.Success(mapper.map(filteredList!!), response.state))
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
