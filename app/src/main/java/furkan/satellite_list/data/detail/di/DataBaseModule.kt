package furkan.satellite_list.data.detail.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import furkan.satellite_list.data.detail.db.SatelliteDetailDao
import furkan.satellite_list.data.detail.db.SatelliteDetailDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideSatelliteDetailDatabase(@ApplicationContext context: Context): SatelliteDetailDatabase {
        return Room.databaseBuilder(
            context,
            SatelliteDetailDatabase::class.java, "satellite_detail_db"
        ).build()
    }

    @Provides
    fun provideSatelliteDetailDao(satelliteDetailDatabase: SatelliteDetailDatabase): SatelliteDetailDao {
        return satelliteDetailDatabase.satelliteDetailDao()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}