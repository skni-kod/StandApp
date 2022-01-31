package com.sknikod.standapp.di

import com.sknikod.standapp.data.remote.StandappApi
import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideStandappApi():StandappApi{
        return Retrofit.Builder()
            .baseUrl(StandappApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StandappApi::class.java)
    }
    @Singleton
    @Provides
    fun provideStandappRepository(
        api:StandappApi
    ):StandappRepositoryImpl{
        return StandappRepositoryImpl(api)
    }

}