package com.kursatkumsuz.bootcampfinalprojecttravelapp.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.local.TripDao
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.local.TripDatabase
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ImageApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository.*
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.*
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Constants.Companion.BASE_URL
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Constants.Companion.PIXABAY_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

// Add Qualifier annotation  to distinguish retrofit
@Qualifier
annotation class TravelRetrofit

@Qualifier
annotation class ImageRetrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides room database
     * @return RoomDatabase
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        TripDatabase::class.java,
        "trip_database"
    ).build()

    /**
     * Provides TripDao
     * @return [TripDao]
     */
    @Provides
    @Singleton
    fun provideDao(database: TripDatabase) = database.tripDao()

    /**
     * Provides Retrofit for TravelApi
     * @return [Retrofit]
     */
    @Provides
    @Singleton
    @TravelRetrofit
    fun provideTravelRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    /**
     * Provides Retrofit for ImageApi
     * @return [Retrofit]
     */
    @Provides
    @Singleton
    @ImageRetrofit
    fun provideImageRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(PIXABAY_BASE_URL)
        .build()

    /**
     * Provides Api Service
     * @param retrofit for create an Implementation of API endpoint by the service interface
     * @return [TravelApiService]
     */
    @Provides
    @Singleton
    fun provideTravelService(@TravelRetrofit retrofit: Retrofit): TravelApiService =
        retrofit.create(TravelApiService::class.java)

    /**
     * Provides Api Service
     * @param retrofit for create an Implementation of API endpoint by the service interface
     * @return [ImageApiService]
     */
    @Provides
    @Singleton
    fun provideImageService(@ImageRetrofit retrofit: Retrofit): ImageApiService =
        retrofit.create(ImageApiService::class.java)

    /**
     * Provides HomeRepository
     * @param [apiService] for [HomeRepositoryImp]
     * @return [HomeRepositoryImp]
     */
    @Provides
    @Singleton
    fun provideTravelRepository(apiService: TravelApiService): TravelRepository {
        return TravelRepositoryImp(apiService)
    }

    /**
     * Provides TripRepository
     * @param [apiService] for [TripRepositoryImp]
     * @param [dao] for [TripRepositoryImp]
     * @return [TripRepositoryImp]
     */
    @Provides
    @Singleton
    fun provideTripRepository(apiService: TravelApiService, dao: TripDao): TripRepository {
        return TripRepositoryImp(apiService, dao)
    }

    /**
     * Provides SearchImageRepository
     * @param [apiService] for [ImageRepositoryImp]
     * @return [ImageRepositoryImp]
     */
    @Provides
    @Singleton
    fun provideSearchImageRepository(apiService: ImageApiService): ImageRepository {
        return ImageRepositoryImp(apiService)
    }

    /**
     * Provides Glide for Image load
     * @param [context] for [Glide]
     */
    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.pic_glide_default)
                .error(R.drawable.pic_glide_default)
        )
}

