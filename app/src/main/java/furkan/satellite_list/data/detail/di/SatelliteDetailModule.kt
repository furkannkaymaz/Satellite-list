package furkan.satellite_list.data.detail.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import furkan.satellite_list.data.detail.repository.SatelliteDetailRepository
import furkan.satellite_list.data.detail.repository.SatelliteDetailRepositoryImpl
import furkan.satellite_list.data.detail.repository.SatellitePositionRepository
import furkan.satellite_list.data.detail.repository.SatellitePositionRepositoryImpl
import furkan.satellite_list.data.detail.source.SatelliteDetailDataSource
import furkan.satellite_list.data.detail.source.SatellitePositionDataSource
import furkan.satellite_list.data.satellite.sources.SatelliteDataSources
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SatelliteDetailModule {

    @Singleton
    @Provides
    fun provideSatelliteDetailRepository(
        context: Context,
        satelliteDetailDataSource: SatelliteDetailDataSource
    ): SatelliteDetailRepository {
        return SatelliteDetailRepositoryImpl(context, satelliteDetailDataSource)
    }

    @Singleton
    @Provides
    fun provideSatellitePositionRepository(
        context: Context,
        satellitePositionDataSource: SatellitePositionDataSource
    ): SatellitePositionRepository {
        return SatellitePositionRepositoryImpl(context, satellitePositionDataSource)
    }

    @Singleton
    @Provides
    fun provideSatellitePositionSources(
        context: Context,
        gson: Gson
    ): SatellitePositionDataSource {
        return SatellitePositionDataSource(context, gson)
    }

    @Singleton
    @Provides
    fun provideSatelliteDetailSources(
        context: Context,
        gson: Gson
    ): SatelliteDetailDataSource {
        return SatelliteDetailDataSource(context, gson)
    }
}