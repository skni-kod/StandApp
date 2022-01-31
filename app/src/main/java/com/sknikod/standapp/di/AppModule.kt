package com.sknikod.standapp.di

import com.sknikod.standapp.data.remote.StandappApi
import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl
import com.sknikod.standapp.domain.use_case.GetArticle
import com.sknikod.standapp.domain.use_case.GetArticles
import com.sknikod.standapp.domain.use_case.GetProjects
import com.sknikod.standapp.domain.use_case.GetProject
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
    @Singleton
    @Provides
    fun provideGetProjectUserCase(repo:StandappRepositoryImpl):GetProject{
        return GetProject(repo)
    }
    @Singleton
    @Provides
    fun provideGetProjectsUserCase(repo:StandappRepositoryImpl):GetProjects{
        return GetProjects(repo)
    }
    @Singleton
    @Provides
    fun provideGetArticlesUserCase(repo:StandappRepositoryImpl): GetArticles {
        return GetArticles(repo)
    }
    @Singleton
    @Provides
    fun provideGetArticleUserCase(repo:StandappRepositoryImpl): GetArticle {
        return GetArticle(repo)
    }
}