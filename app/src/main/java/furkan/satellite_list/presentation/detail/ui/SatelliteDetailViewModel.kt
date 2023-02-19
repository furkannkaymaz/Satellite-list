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
import furkan.satellite_list.presentation.satellite.ui.SatelliteUiData
import furkan.satellite_list.utils.extensions.launchOnIO
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.*
import java.util.concurrent.TimeUnit
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

    fun getSatelliteDetail(id: Int): StateFlow<Resource<SatelliteDetailUiData>> {

        viewModelScope.launchOnIO {
            getSatelliteDetailUseCase(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _uiState.emit(Resource.Success(mapper.map(it.data!!), it.state))
                    }
                    is Resource.Error -> {
                        _uiState.emit(
                            Resource.Error(
                                it.message,
                                it.state
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _uiState.emit(
                            Resource.Loading(
                                UIStatus.LOADING
                            )
                        )
                    }

                }
            }
        }
        return _uiState
    }

    private val _uiStatePosition: MutableStateFlow<Resource<SatellitePositionUiData>> =
        MutableStateFlow(
            Resource.Loading(UIStatus.LOADING)
        )

    fun getSatellitePosition(id: Int): StateFlow<Resource<SatellitePositionUiData>> {

        viewModelScope.launchOnIO {
            getSatellitePositionUseCase(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _uiStatePosition.emit(
                            Resource.Success(
                                mapperPosition.map(it.data!!),
                                it.state
                            )
                        )
                    }
                    is Resource.Error -> {
                        _uiStatePosition.emit(
                            Resource.Error(
                                it.message,
                                it.state
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _uiStatePosition.emit(
                            Resource.Loading(
                                UIStatus.LOADING
                            )
                        )
                    }
                }
                while (true) {
                    delay(3000)
                    when (it) {
                        is Resource.Success -> {
                            _uiStatePosition.emit(
                                Resource.Success(
                                    mapperPosition.map(it.data!!),
                                    it.state
                                )
                            )
                        }
                        is Resource.Error -> {
                            _uiStatePosition.emit(
                                Resource.Error(
                                    it.message,
                                    it.state
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _uiStatePosition.emit(
                                Resource.Loading(
                                    UIStatus.LOADING
                                )
                            )
                        }
                    }
                }
            }
        }
        return _uiStatePosition
    }

    suspend fun addSatelliteDetail(satelliteDetailUiData: SatelliteDetailUiData) {
        satelliteDetailRepository.saveDetail(
            SatelliteDetailModel(
                satelliteDetailUiData.id,
                satelliteDetailUiData.cost_per_launch,
                satelliteDetailUiData.first_flight,
                satelliteDetailUiData.height,
                satelliteDetailUiData.mass
            ))
    }
}