package com.example.ordercoffee.di

import com.example.ordercoffee.domain.usecase.GetLocationsUseCase
import com.example.ordercoffee.domain.usecase.GetMenuUseCase
import com.example.ordercoffee.domain.usecase.LoginUseCase
import com.example.ordercoffee.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { GetLocationsUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { GetMenuUseCase(get()) }
}