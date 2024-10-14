package com.example.jobsearchapp

import android.app.Application
import androidx.room.Room

import com.example.jobsearchapp.ui.common.data.room.CommonDB
import com.example.jobsearchapp.di.AppComponent
import com.example.jobsearchapp.di.DaggerAppComponent


class App: Application() {
    lateinit var db: CommonDB
    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)

        db = Room.databaseBuilder(
            applicationContext,
            CommonDB::class.java,
            "db"
        ).build()
    }
}