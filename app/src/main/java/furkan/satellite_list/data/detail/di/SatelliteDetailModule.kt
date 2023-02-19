package furkan.satellite_list.data.detail.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.detail.db.SatelliteDetailDao
import furkan.satellite_list.data.detail.repository.SatelliteDetailRepository
import furkan.satellite_list.data.detail.repository.SatelliteDetailRepositoryImpl
import furkan.satellite_list.data.detail.repository.SatellitePositionRepository
import furkan.satellite_list.data.detail.repository.SatellitePositionRepositoryImpl
import furkan.satellite_list.data.detail.source.SatelliteDetailDataSource
import furkan.satellite_list.data.detail.source.SatellitePositionDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SatelliteDetailModule {

    @Singleton
    @Provides
    fun provideSatelliteDetailRepository(
        @ApplicationContext context: Context,
        satelliteDetailDataSource: SatelliteDetailDataSource,
        satelliteDetailDao: SatelliteDetailDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): SatelliteDetailRepository {
        return SatelliteDetailRepositoryImpl(
            context,
            satelliteDetailDataSource,
            satelliteDetailDao,
            ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideSatellitePositionRepository(
        @ApplicationContext context: Context,
        satellitePositionDataSource: SatellitePositionDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): SatellitePositionRepository {
        return SatellitePositionRepositoryImpl(context, satellitePositionDataSource, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideSatellitePositionSources(
        @ApplicationContext context: Context,
        gson: Gson
    ): SatellitePositionDataSource {
        return SatellitePositionDataSource(context, gson)
    }

    @Singleton
    @Provides
    fun provideSatelliteDetailSources(
        @ApplicationContext context: Context,
        gson: Gson,
        dao: SatelliteDetailDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): SatelliteDetailDataSource {
        return SatelliteDetailDataSource(context, gson, dao, ioDispatcher)
    }
}