package com.example.jobsearchapp.ui.common

import android.content.Context
import com.example.jobsearchapp.App
import com.example.jobsearchapp.ui.common.data.CommonApiService
import com.example.jobsearchapp.ui.common.data.CommonDataMapper
import com.example.jobsearchapp.ui.common.data.CommonDomainMapper
import com.example.jobsearchapp.ui.common.data.CommonLocalDataSource
import com.example.jobsearchapp.ui.common.data.CommonRemoteDataSource
import com.example.jobsearchapp.ui.common.data.CommonRepositoryImpl
import com.example.jobsearchapp.ui.common.data.room.CommonDao
import com.example.jobsearchapp.ui.common.domain.ChangeFavoriteStateUseCase
import com.example.jobsearchapp.ui.common.domain.ConsumeVacanciesCardUseCase
import com.example.jobsearchapp.ui.common.domain.ConsumeVacanciesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {

    private var retrofitSingleton: Retrofit? = null
    private var commonRepositorySingleton: CommonRepositoryImpl? = null
    lateinit var applicationContext: Context

    fun provideConsumeVacanciesUseCase(): ConsumeVacanciesUseCase {
        return ConsumeVacanciesUseCase(repository = provideRepository())
    }

    fun provideChangeFavoriteStateUseCase(): ChangeFavoriteStateUseCase {
        return ChangeFavoriteStateUseCase(repository = provideRepository())
    }

    fun provideConsumeVacanciesCardUseCase(): ConsumeVacanciesCardUseCase {
        return ConsumeVacanciesCardUseCase(repository = provideRepository())
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

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

    private fun provideApiService(): CommonApiService {
        return provideRetrofit().create(CommonApiService::class.java)
    }


    private fun provideRoom(): CommonDao {
        return (applicationContext as App).db.commonDao()
    }

    private fun provideCommonLocalDataSource(): CommonLocalDataSource {
        return CommonLocalDataSource(dao = provideRoom())
    }

    private fun provideRemoteDataSource(): CommonRemoteDataSource {
        return CommonRemoteDataSource(apiService = provideApiService())
    }

    private fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    private fun provideRepository(): CommonRepositoryImpl {
        val local = commonRepositorySingleton

        return local ?: run {
            val newRepository = CommonRepositoryImpl(
                remoteDataSource = provideRemoteDataSource(),
                commonDataMapper = CommonDataMapper(),
                commonDomainMapper = CommonDomainMapper(),
                commonLocalDataSource = provideCommonLocalDataSource(),
                coroutineDispatcher = provideIOCoroutineDispatcher()
            )
            commonRepositorySingleton = newRepository
            newRepository
        }
    }
}