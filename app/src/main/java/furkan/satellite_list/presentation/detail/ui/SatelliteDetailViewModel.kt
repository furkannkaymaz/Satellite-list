package furkan.satellite_list.presentation.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.data.detail.repository.SatelliteDetailRepository
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import furkan.satellite_list.domain.detail.entity.SatellitePositionEntity
import furkan.satellite_list.domain.detail.usecase.GetSatelliteDetailUseCase
import furkan.satellite_list.domain.detail.usecase.GetSatellitePositionUseCase
import furkan.satellite_list.utils.extensions.launchOnIO
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    private val getSatellitePositionUseCase: GetSatellitePositionUseCase,
    private val mapper: SatelliteMapper<SatelliteDetailEntity, SatelliteDetailUiData>,
    private val mapperPosition: SatelliteMapper<SatellitePositionEntity, SatellitePositionUiData>,
    private val satelliteDetailRepository: SatelliteDetailRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<SatelliteDetailUiData>> = MutableStateFlow(
        Resource.Loading(UIStatus.LOADING)
    )

    private val _uiStatePosition: MutableStateFlow<Resource<SatellitePositionUiData>> =
        MutableStateFlow(
            Resource.Loading(UIStatus.LOADING)
        )

    fun getSatelliteDetailAndPosition(id: Int): Pair<StateFlow<Resource<SatelliteDetailUiData>>, StateFlow<Resource<SatellitePositionUiData>>> {
        viewModelScope.launchOnIO {
            getSatelliteDetailUseCase(id).combine(getSatellitePositionUseCase(id)) { detailResult, positionResult ->
                Pair(detailResult, positionResult)
            }.collectLatest { (detailResult, positionResult) ->
                when {
                    detailResult is Resource.Success && positionResult is Resource.Success -> {
                        _uiState.emit(
                            Resource.Success(
                                mapper.map(detailResult.data!!),
                                detailResult.state
                            )
                        )
                        _uiStatePosition.emit(
                            Resource.Success(
                                mapperPosition.map(positionResult.data!!),
                                positionResult.state
                            )
                        )
                    }
                    detailResult is Resource.Error || positionResult is Resource.Error -> {
                        _uiState.emit(Resource.Error("Error Message", detailResult.state))
                        _uiStatePosition.emit(Resource.Error("Error Message", positionResult.state))
                    }
                    else -> {
                        _uiState.emit(Resource.Loading(UIStatus.LOADING))
                        _uiStatePosition.emit(Resource.Loading(UIStatus.LOADING))
                    }
                }

                while (true) {
                    when {
                        detailResult is Resource.Success && positionResult is Resource.Success -> {
                            _uiStatePosition.emit(
                                Resource.Success(
                                    mapperPosition.map(positionResult.data!!),
                                    positionResult.state
                                )
                            )
                        }
                        detailResult is Resource.Error || positionResult is Resource.Error -> {
                            _uiStatePosition.emit(
                                Resource.Error(
                                    "Error Message",
                                    positionResult.state
                                )
                            )
                        }
                        else -> {
                            _uiStatePosition.emit(Resource.Loading(UIStatus.LOADING))
                        }
                    }
                    delay(3000)
                }
            }
        }

        return Pair(_uiState, _uiStatePosition)
    }

    suspend fun addSatelliteDetail(satelliteDetailUiData: SatelliteDetailUiData) {
        satelliteDetailRepository.saveDetail(
            SatelliteDetailModel(
                satelliteDetailUiData.id,
                satelliteDetailUiData.cost_per_launch,
                satelliteDetailUiData.first_flight,
                satelliteDetailUiData.height,
                satelliteDetailUiData.mass
            )
        )
    }
}