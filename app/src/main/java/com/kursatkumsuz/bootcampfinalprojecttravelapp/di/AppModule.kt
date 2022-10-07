package com.kursatkumsuz.bootcampfinalprojecttravelapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository.*
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.*
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService): HomeRepository {
        return HomeRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(apiService: ApiService): SearchRepository {
        return SearchRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideGuideRepository(apiService: ApiService): GuideRepository {
        return GuideRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideBookmarkRepository(apiService: ApiService): BookmarkRepository {
        return BookmarkRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideSearchDetailRepository(apiService: ApiService) : SearchResultRepository {
        return SearchResultRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(apiService: ApiService) : DetailRepository {
        return DetailRepositoryImp(apiService)
    }


    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
}
