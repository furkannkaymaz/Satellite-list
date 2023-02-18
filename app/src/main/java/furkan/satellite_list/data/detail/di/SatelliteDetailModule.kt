package furkan.satellite_list.data.detail.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import furkan.satellite_list.data.detail.repository.SatelliteDetailRepository
import furkan.satellite_list.data.detail.repository.SatelliteDetailRepositoryImpl
import furkan.satellite_list.data.detail.repository.SatellitePositionRepository
import furkan.satellite_list.data.detail.repository.SatellitePositionRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SatelliteDetailModule {

    @Singleton
    @Provides
    fun provideSatelliteDetailRepository(context: Context): SatelliteDetailRepository {
        return SatelliteDetailRepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun provideSatellitePositionRepository(context: Context): SatellitePositionRepository {
        return SatellitePositionRepositoryImpl(context)
    }
}