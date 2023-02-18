package furkan.satellite_list.data.satellite.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import furkan.satellite_list.data.satellite.repository.SatelliteRepository
import furkan.satellite_list.data.satellite.repository.SatelliteRepositoryImpl
import furkan.satellite_list.data.satellite.sources.SatelliteDataSources
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SatelliteModule {

    @Singleton
    @Provides
    fun provideSatelliteRepository(context: Context,satelliteDataSources: SatelliteDataSources): SatelliteRepository {
        return SatelliteRepositoryImpl(context,satelliteDataSources)
    }

    @Singleton
    @Provides
    fun provideSatelliteDataSources(
        context: Context,
        gson: Gson
    ): SatelliteDataSources {
        return SatelliteDataSources(context, gson)
    }
}