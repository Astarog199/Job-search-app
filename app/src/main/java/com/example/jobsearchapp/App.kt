package com.example.jobsearchapp

import android.app.Application
import androidx.room.Room

import com.example.jobsearchapp.ui.common.data.room.CommonDB
import com.example.jobsearchapp.ServiceLocator as serviceLocator
import com.example.jobsearchapp.ui.common.ServiceLocator as commonLocator

class App: Application() {
    lateinit var db: CommonDB

    override fun onCreate() {
        super.onCreate()
        serviceLocator.applicationContext = this
        commonLocator.applicationContext = this

        db = Room.databaseBuilder(
            applicationContext,
            CommonDB::class.java,
            "db"
        ).build()
    }
}