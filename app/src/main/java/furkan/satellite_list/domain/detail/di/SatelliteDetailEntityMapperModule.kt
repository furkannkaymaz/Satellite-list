package furkan.satellite_list.domain.detail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.domain.base.SatelliteMapper
import furkan.satellite_list.domain.detail.entity.SatelliteDetailEntity
import furkan.satellite_list.domain.detail.mapper.SatelliteDetailEntityMapper

@Module
@InstallIn(ViewModelComponent::class)
abstract class SatelliteDetailEntityMapperModule {

    @Binds
    abstract fun bindSatelliteDetailEntityMapperModule(satelliteListMapper: SatelliteDetailEntityMapper): SatelliteMapper<SatelliteDetailModel, SatelliteDetailEntity>

}
