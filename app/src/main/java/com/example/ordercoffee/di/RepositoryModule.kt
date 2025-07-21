package com.example.ordercoffee.di

import com.example.ordercoffee.data.TokenStorage
import com.example.ordercoffee.data.repository.AuthRepositoryImpl
import com.example.ordercoffee.data.repository.LocationRepositoryImpl
import com.example.ordercoffee.data.repository.MenuRepositoryImpl
import com.example.ordercoffee.domain.repository.AuthRepository
import com.example.ordercoffee.domain.repository.LocationRepository
import com.example.ordercoffee.domain.repository.MenuRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<MenuRepository> { MenuRepositoryImpl(get()) }
    single {TokenStorage(get())}
}

