package ru.radzze.ricmasterstask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import ru.radzze.ricmasterstask.data.Repositories.CamerasRepository
import ru.radzze.ricmasterstask.data.Repositories.CamerasRepositoryImpl
import ru.radzze.ricmasterstask.data.Repositories.DoorsRepository
import ru.radzze.ricmasterstask.data.Repositories.DoorsRepositoryImpl
import ru.radzze.ricmasterstask.data.Repositories.RealmRepository
import ru.radzze.ricmasterstask.data.Repositories.RealmRepositoryImpl
import ru.radzze.ricmasterstask.data.RubetekHttpClient
import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.Door

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideDoorsRepository(httpClient: HttpClient): DoorsRepository {
        return DoorsRepositoryImpl(httpClient)
    }
    @Provides
    fun provideCamerasRepository(httpClient: HttpClient): CamerasRepository {
        return CamerasRepositoryImpl(httpClient)
    }

    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Camera::class, Door::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Provides
    fun provideRealmRepository(realm:Realm):RealmRepository{
        return RealmRepositoryImpl(realm)
    }

}