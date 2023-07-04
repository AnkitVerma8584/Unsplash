package com.example.ankitverma.di

import android.content.Context
import com.example.ankitverma.data.local.DataStore
import com.example.ankitverma.data.remote.Urls
import com.example.ankitverma.data.remote.api.ImagesApi
import com.example.ankitverma.data.remote.repository.FeedsRepositoryImpl
import com.example.ankitverma.domain.repository.FeedsRepository
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
    fun provideImagesApi(): ImagesApi = Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ImagesApi::class.java)

    @Provides
    @Singleton
    fun provideFeedsRepository(imagesApi: ImagesApi): FeedsRepository =
        FeedsRepositoryImpl(imagesApi)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore = DataStore(context)
}
