package furkan.satellite_list.domain.detail.usecase

import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.data.detail.repository.FakeSatelliteDetailRepository
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetSatelliteDetailUseCase(
    private val fakeSatelliteDetailRepository: FakeSatelliteDetailRepository,
    private val mapper: SatelliteMapper<SatelliteDetailModel, SatelliteDetailEntity>
) : GetSatelliteDetailUseCase {

    override fun invoke(id: Int): Flow<Resource<SatelliteDetailEntity?>> = flow {
        when (val response = fakeSatelliteDetailRepository.getSatelliteDetail(id)) {
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