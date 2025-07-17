package com.example.ordercoffee

import android.app.Application
import com.example.ordercoffee.di.networkModule
import com.example.ordercoffee.di.repositoryModule
import com.example.ordercoffee.di.useCaseModule
import com.example.ordercoffee.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OrderCoffeeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@OrderCoffeeApp)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule,
                useCaseModule
            )
        }
    }
}