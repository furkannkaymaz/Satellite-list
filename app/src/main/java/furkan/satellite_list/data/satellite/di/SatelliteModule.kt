package furkan.satellite_list.data.satellite.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import furkan.satellite_list.data.satellite.repository.SatelliteRepository
import furkan.satellite_list.data.satellite.repository.SatelliteRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SatelliteModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSatelliteRepository(context: Context): SatelliteRepository {
        return SatelliteRepositoryImpl(context)
    }
}