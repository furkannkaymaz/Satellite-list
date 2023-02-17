package furkan.satellite_list.presentation.satellite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import furkan.satellite_list.utils.response.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import furkan.satellite_list.domain.satellite.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import furkan.satellite_list.domain.satellite.usecase.GetSatelliteUseCase
import furkan.satellite_list.utils.extensions.launchOnIO
import furkan.satellite_list.utils.response.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SatelliteViewModel @Inject constructor(
    private val getSatelliteUseCase: GetSatelliteUseCase,
    private val mapper: SatelliteListMapper<SatelliteEntity, SatelliteUiData>
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<List<SatelliteUiData>>> = MutableStateFlow(Resource.Loading(
        UIStatus.LOADING))

    fun getSatellite(): StateFlow<Resource<List<SatelliteUiData>>> {

        viewModelScope.launchOnIO {
            getSatelliteUseCase.invoke().collectLatest {
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
                    else -> Unit
                }
            }
        }
        return _uiState
    }
}