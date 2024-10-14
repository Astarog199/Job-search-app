package com.example.jobsearchapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.jobsearchapp.App
import com.example.jobsearchapp.ui.common.data.CommonApiService
import com.example.jobsearchapp.ui.common.data.room.CommonDao
import com.example.jobsearchapp.ui.home.data.HomeApiService
import com.example.jobsearchapp.ui.home.data.HomeLocalDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
object DataModule {

    @Provides
     fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideCommonApiService(
        retrofit: Retrofit
    ): CommonApiService {
        return retrofit.create(CommonApiService::class.java)
    }

    @Provides
    fun provideRoom(
        context: Context
    ): CommonDao {
        return (context as App).db.commonDao()
    }

    @Provides
    fun provideHomeApiService(
        retrofit: Retrofit
    ) : HomeApiService {
        return retrofit.create(HomeApiService::class.java)
    }

    @Provides
    fun provideHomeLocalDataSource(context:Context): HomeLocalDataSource {
        return HomeLocalDataSource (
            context.appDataStore
        )
    }

    private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = "start_app")
}