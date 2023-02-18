package furkan.satellite_list.domain.detail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatellitePositionEntity
import furkan.satellite_list.domain.detail.mapper.SatellitePositionEntityMapper

@Module
@InstallIn(ViewModelComponent::class)
abstract class SatellitePositionEntityMapperModule {

    @Binds
    abstract fun bindSatellitePositionEntityMapperModule(satellitePositionMapper: SatellitePositionEntityMapper): SatelliteMapper<SatellitePositionModel, SatellitePositionEntity>

}