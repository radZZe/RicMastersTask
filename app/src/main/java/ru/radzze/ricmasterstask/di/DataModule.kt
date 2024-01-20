package ru.radzze.ricmasterstask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import ru.radzze.ricmasterstask.data.Repositories.DoorsRepository
import ru.radzze.ricmasterstask.data.Repositories.DoorsRepositoryImpl
import ru.radzze.ricmasterstask.data.RubetekHttpClient

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideDoorsRepository(httpClient: HttpClient): DoorsRepository {
        return DoorsRepositoryImpl(httpClient)
    }
}