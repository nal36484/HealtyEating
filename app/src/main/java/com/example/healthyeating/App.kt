package com.example.healthyeating

import android.app.Application
import com.example.healthyeating.di.module.repositoriesModule
import com.example.healthyeating.di.module.useCasesModule
import com.example.healthyeating.di.module.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(repositoriesModule, useCasesModule, viewModelsModule)
        }
    }
}