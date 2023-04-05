package com.mungai.intothepotter_verse.di

import android.app.Application
import androidx.room.Room
import com.mungai.intothepotter_verse.common.Constants
import com.mungai.intothepotter_verse.data.local.PotterVerseDatabase
import com.mungai.intothepotter_verse.data.remote.ApiService
import com.mungai.intothepotter_verse.data.repository.PotterVerseRepositoryImpl
import com.mungai.intothepotter_verse.domain.repository.PotterVerseRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @IoDispatcher
    @Provides
    fun providesIoDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }


    @Singleton
    @Provides
    fun providesOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    // To control how data is serialized and deserialized in retrofit network calls.
    @Singleton
    @Provides
    fun providesConverterFactory(): MoshiConverterFactory {
        val moshi =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Singleton
    @Provides
    fun providesHpApiService(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesPotterVerseDatabase(application: Application): PotterVerseDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = PotterVerseDatabase::class.java,
            name = Constants.DATABASE
        ).build()
    }

    @Singleton
    @Provides
    fun providesPotterVerseRepository(
        apiService: ApiService,
        database: PotterVerseDatabase
    ): PotterVerseRepository {
        return PotterVerseRepositoryImpl(apiService = apiService, database = database)
    }
}