package com.hasangurbuz.breeds.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.hasangurbuz.breeds.data.local.BreedDatabase
import com.hasangurbuz.breeds.data.remote.BreedApi
import com.hasangurbuz.breeds.data.repository.BreedRepository
import com.hasangurbuz.breeds.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideBreedRepository(
        api: BreedApi, db: BreedDatabase
    ) = BreedRepository(api, db)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideBreedApi(retrofit: Retrofit): BreedApi {
        return retrofit.create(BreedApi::class.java)

    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): BreedDatabase {
        return Room.databaseBuilder(
            app,
            BreedDatabase::class.java,
            BreedDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()

    }
}