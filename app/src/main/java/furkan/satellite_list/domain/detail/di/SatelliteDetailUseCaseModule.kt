package furkan.satellite_list.domain.detail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import furkan.satellite_list.domain.detail.usecase.GetSatelliteDetailUseCase
import furkan.satellite_list.domain.detail.usecase.GetSatelliteDetailUseCaseImpl
import furkan.satellite_list.domain.detail.usecase.GetSatellitePositionUseCase
import furkan.satellite_list.domain.detail.usecase.GetSatellitePositionUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface SatelliteDetailUseCaseModule {

    @Binds
    fun bindGetSatelliteDetailUseCase(getSatelliteUseCase: GetSatelliteDetailUseCaseImpl): GetSatelliteDetailUseCase

    @Binds
    fun bindGetSatellitePositionUseCase(getSatellitePositionUseCase: GetSatellitePositionUseCaseImpl): GetSatellitePositionUseCase

}
