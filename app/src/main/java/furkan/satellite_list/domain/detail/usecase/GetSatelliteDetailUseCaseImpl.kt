package furkan.satellite_list.domain.detail.usecase

import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.data.detail.repository.SatelliteDetailRepository
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.data.satellite.repository.SatelliteRepository
import furkan.satellite_list.domain.base.SatelliteListMapper
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatelliteDetailUseCaseImpl @Inject constructor(
    private val satelliteDetailRepository: SatelliteDetailRepository,
    private val mapper: SatelliteMapper<SatelliteDetailModel, SatelliteDetailEntity>
) : GetSatelliteDetailUseCase {
    override fun invoke(id: Int): Flow<Resource<SatelliteDetailEntity?>> =   flow{
        when (val response = satelliteDetailRepository.getSatelliteDetail(id)) {
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
