package furkan.satellite_list.presentation.satellite.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import furkan.satellite_list.presentation.satellite.adapter.SatelliteAdapter

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Provides
    fun provideSatelliteAdapter(): SatelliteAdapter {
        return SatelliteAdapter()
    }
}