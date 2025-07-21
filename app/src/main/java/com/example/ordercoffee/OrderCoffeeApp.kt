package com.example.ordercoffee

import android.app.Application
import com.example.ordercoffee.di.networkModule
import com.example.ordercoffee.di.repositoryModule
import com.example.ordercoffee.di.useCaseModule
import com.example.ordercoffee.di.viewModelModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OrderCoffeeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey("05b892cf-2cfe-482e-b1fb-ae3253468d43")
        MapKitFactory.initialize(this)

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