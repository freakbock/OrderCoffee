package com.example.ordercoffee.data.mapper

import com.example.ordercoffee.data.dto.AuthResponse
import com.example.ordercoffee.data.dto.LocationDto
import com.example.ordercoffee.data.dto.MenuItemDto
import com.example.ordercoffee.domain.model.AuthToken
import com.example.ordercoffee.domain.model.Location
import com.example.ordercoffee.domain.model.MenuItem
import com.example.ordercoffee.domain.model.Point

fun AuthResponse.toDomain(): AuthToken =
    AuthToken(token = token, tokenLifeTime = tokenLifeTime)


fun LocationDto.toDomain(): Location =
    Location(
        id = id,
        name = name,
        point = Point(latitude = point.latitude, longitude = point.longitude)
    )

fun MenuItemDto.toDomain(): MenuItem =
    MenuItem(
        id = id,
        name = name,
        imageURL = imageURL,
        price = price
    )
