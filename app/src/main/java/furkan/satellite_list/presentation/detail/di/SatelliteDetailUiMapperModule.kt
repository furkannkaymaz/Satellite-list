package furkan.satellite_list.presentation.detail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import furkan.satellite_list.presentation.detail.ui.SatelliteDetailUiData
import furkan.satellite_list.presentation.detail.ui.SatelliteDetailUiMapper

@Module
@InstallIn(ViewModelComponent::class)
abstract class SatelliteDetailUiMapperModule {

    @Binds
    abstract fun bindSatelliteUiMapper(satelliteDetailUiMapper: SatelliteDetailUiMapper): SatelliteMapper<SatelliteDetailEntity, SatelliteDetailUiData>
}


