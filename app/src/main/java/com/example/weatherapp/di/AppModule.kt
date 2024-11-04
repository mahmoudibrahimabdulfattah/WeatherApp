package com.example.weatherapp.di


import android.content.Context
import androidx.room.Room
import com.example.weatherapp.DataSource.Local.LastSearchDao
import com.example.weatherapp.DataSource.Local.WeatherDatabase
import com.example.weatherapp.DataSource.Remote.WeatherApi
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.Repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "e596afa2e59a8b309e0274eecf086256"

    @Provides
    @Singleton
    fun provideApiKey(): String = API_KEY

    @Provides
    @Singleton
    fun provideWeatherDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLastSearchDao(database: WeatherDatabase): LastSearchDao {
        return database.lastSearchDao()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(client: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi,
        dao: LastSearchDao,
        apiKey: String
    ): WeatherRepository {
        return WeatherRepositoryImpl(api, dao, apiKey)
    }
}