package com.zygotecnologia.zygotv.main

import android.app.Application
import com.zygotecnologia.zygotv.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ZygoTvApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@ZygoTvApplication)
            modules(appModule)
        }
    }
}