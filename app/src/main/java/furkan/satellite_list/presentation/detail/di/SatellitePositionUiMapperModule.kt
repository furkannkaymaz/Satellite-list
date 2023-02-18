package furkan.satellite_list.presentation.detail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatellitePositionEntity
import furkan.satellite_list.presentation.detail.ui.SatellitePositionUiData
import furkan.satellite_list.presentation.detail.ui.SatellitePositionUiMapper

@Module
@InstallIn(ViewModelComponent::class)
abstract class SatellitePositionUiMapperModule {

    @Binds
    abstract fun bindSatellitePositionUiMapper(satellitePositionUiMapper: SatellitePositionUiMapper): SatelliteMapper<SatellitePositionEntity, SatellitePositionUiData>
}
