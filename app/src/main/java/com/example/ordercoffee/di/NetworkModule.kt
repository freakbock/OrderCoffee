package com.example.ordercoffee.di

import com.example.ordercoffee.data.api.ApiService
import com.example.ordercoffee.data.interceptor.AuthInterceptor
import com.example.ordercoffee.data.repository.provideOkHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sin

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://212.41.30.90:35005/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(AuthInterceptor(get()))
                    .build()
            )
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
    single { AuthInterceptor(get()) }
    single { provideOkHttpClient(get()) }
}

