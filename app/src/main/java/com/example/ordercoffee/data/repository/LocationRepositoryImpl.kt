package com.example.ordercoffee.data.repository

import android.util.Log
import com.example.ordercoffee.data.api.ApiService
import com.example.ordercoffee.data.dto.LocationDto
import com.example.ordercoffee.data.interceptor.AuthInterceptor
import com.example.ordercoffee.domain.model.Location
import com.example.ordercoffee.domain.repository.LocationRepository
import com.example.ordercoffee.data.mapper.toDomain
import okhttp3.OkHttpClient

class LocationRepositoryImpl(private val api: ApiService) : LocationRepository {
    override suspend fun getLocations(): Result<List<Location>> {
        return try {
            val response = api.getLocations()
            Log.d("ERROR", response.code().toString() + "|||" + response.message())
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body.map { it.toDomain() })
                } else Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()
}

