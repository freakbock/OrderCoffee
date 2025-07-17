package com.example.ordercoffee.di

import com.example.ordercoffee.presentation.location_list.LocationListViewModel
import com.example.ordercoffee.presentation.location_map.MapViewModel
import com.example.ordercoffee.presentation.login.LoginViewModel
import com.example.ordercoffee.presentation.menu.MenuViewModel
import com.example.ordercoffee.presentation.order.OrderViewModel
import com.example.ordercoffee.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { LocationListViewModel(get()) }
    viewModel { MapViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { OrderViewModel() }
}
