package furkan.satellite_list.presentation.satellite.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import furkan.satellite_list.domain.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import furkan.satellite_list.presentation.satellite.ui.SatelliteUiData
import furkan.satellite_list.presentation.satellite.ui.SatelliteUiMapper

@Module
@InstallIn(ViewModelComponent::class)
abstract class SatelliteUiMapperModule {

    @Binds
    abstract fun bindSatelliteUiMapper(satelliteUiMapper: SatelliteUiMapper): SatelliteListMapper<SatelliteEntity, SatelliteUiData>
}


