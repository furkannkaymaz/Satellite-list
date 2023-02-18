package furkan.satellite_list.domain.satellite.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.domain.base.SatelliteListMapper
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import furkan.satellite_list.domain.satellite.mapper.SatelliteEntityMapper

@Module
@InstallIn(ViewModelComponent::class)
abstract class SatelliteEntityMapperModule {

    @Binds
    abstract fun bindSatelliteEntityMapperModule(satelliteListMapper: SatelliteEntityMapper): SatelliteListMapper<SatelliteModel, SatelliteEntity>

}
