package furkan.satellite_list.domain.satellite.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import furkan.satellite_list.domain.satellite.usecase.GetSatelliteUseCase
import furkan.satellite_list.domain.satellite.usecase.GetSatelliteUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetSatelliteUseCase(getSatelliteUseCase: GetSatelliteUseCaseImpl): GetSatelliteUseCase
}
