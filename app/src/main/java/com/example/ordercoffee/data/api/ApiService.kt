package com.example.ordercoffee.data.api

import com.example.ordercoffee.data.dto.AuthRequest
import com.example.ordercoffee.data.dto.AuthResponse
import com.example.ordercoffee.data.dto.LocationDto
import com.example.ordercoffee.data.dto.MenuItemDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/auth/login")
    suspend fun login(@Body body: AuthRequest): retrofit2.Response<AuthResponse>

    @POST("/auth/register")
    suspend fun register(@Body body: AuthRequest): retrofit2.Response<AuthResponse>

    @GET("/locations")
    suspend fun getLocations(): retrofit2.Response<List<LocationDto>>

    @GET("/location/{id}/menu")
    suspend fun getMenu(@Path("id") locationId: String): retrofit2.Response<List<MenuItemDto>>
}

