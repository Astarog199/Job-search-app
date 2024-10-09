package com.example.jobsearchapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.jobsearchapp.ui.home.data.HomeDataMapper
import com.example.jobsearchapp.ui.home.data.HomeDomainMapper
import com.example.jobsearchapp.ui.home.data.HomeLocalDataSource
import com.example.jobsearchapp.ui.home.data.HomeApiService
import com.example.jobsearchapp.ui.home.data.HomeRemoteDataSource
import com.example.jobsearchapp.ui.home.data.HomeRepositoryImpl
import com.example.jobsearchapp.ui.home.domain.ConsumeOffersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {
    lateinit var applicationContext: Context

    private var homeRepositorySingleton: HomeRepositoryImpl? = null
    private var retrofitSingleton: Retrofit? = null



     fun provideConsumeOffersUseCase() : ConsumeOffersUseCase{
        return ConsumeOffersUseCase(homeRepository = provideHomeRepository())
    }


    //data
    private fun provideRetrofit(): Retrofit {
        val local = retrofitSingleton

        return local ?: run {
            val newRetrofit = Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl("https://drive.usercontent.google.com/u/0/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            retrofitSingleton = newRetrofit
            newRetrofit
        }
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun provideDataApiService() : HomeApiService {
        return provideRetrofit().create(HomeApiService::class.java)
    }


    private fun provideHomeRepository(): HomeRepositoryImpl {
        val local = homeRepositorySingleton

        return local ?: run {
            val newHomeRepository = HomeRepositoryImpl(
                homeRemoteDataSource = provideHomeRemoteDataSource(),
                localDataSource = provideHomeLocalDataSource(),
                dataMapper = provideDataMapper(),
                domainMapper = provideDomainMapper(),
                coroutineDispatcher = provideIOCoroutineDispatcher()
            )
            homeRepositorySingleton = newHomeRepository
            newHomeRepository
        }
    }

    private fun provideHomeRemoteDataSource() : HomeRemoteDataSource {
        return HomeRemoteDataSource(apiService = provideDataApiService())
    }
    private fun provideDataMapper() : HomeDataMapper {
        return HomeDataMapper()
    }

    private fun provideDomainMapper() : HomeDomainMapper {
        return HomeDomainMapper()
    }

    private fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    private fun provideHomeLocalDataSource(): HomeLocalDataSource {
        return HomeLocalDataSource (
            dataStore = applicationContext.appDataStore
        )
    }

    private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = "start_app")
}