package furkan.satellite_list.presentation.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import furkan.satellite_list.domain.detail.entity.SatellitePositionEntity
import furkan.satellite_list.domain.detail.usecase.GetSatelliteDetailUseCase
import furkan.satellite_list.domain.detail.usecase.GetSatellitePositionUseCase
import furkan.satellite_list.utils.extensions.launchOnIO
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    private val getSatellitePositionUseCase: GetSatellitePositionUseCase,
    private val mapper: SatelliteMapper<SatelliteDetailEntity, SatelliteDetailUiData>,
    private val mapperPosition: SatelliteMapper<SatellitePositionEntity, SatellitePositionUiData>
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<SatelliteDetailUiData>> = MutableStateFlow(
        Resource.Loading(UIStatus.LOADING))

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

    private val _uiStatePosition: MutableStateFlow<Resource<SatellitePositionUiData>> = MutableStateFlow(
        Resource.Loading(UIStatus.LOADING))

    fun getSatellitePosition(id: Int): StateFlow<Resource<SatellitePositionUiData>> {

        viewModelScope.launchOnIO {
            getSatellitePositionUseCase(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _uiStatePosition.emit(Resource.Success(mapperPosition.map(it.data!!), it.state))
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
        return _uiStatePosition
    }
}