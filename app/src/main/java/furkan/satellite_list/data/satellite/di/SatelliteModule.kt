package furkan.satellite_list.data.satellite.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import furkan.satellite_list.app.di.IoDispatcher
import furkan.satellite_list.data.satellite.repository.SatelliteRepository
import furkan.satellite_list.data.satellite.repository.SatelliteRepositoryImpl
import furkan.satellite_list.data.satellite.sources.SatelliteDataSources
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SatelliteModule {

    @Singleton
    @Provides
    fun provideSatelliteRepository(
        @ApplicationContext context: Context,
        satelliteDataSources: SatelliteDataSources,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): SatelliteRepository {
        return SatelliteRepositoryImpl(context, satelliteDataSources, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideSatelliteDataSources(
        @ApplicationContext context: Context,
        gson: Gson,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): SatelliteDataSources {
        return SatelliteDataSources(context, gson, ioDispatcher)
    }
}