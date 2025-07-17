package com.example.ordercoffee.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuItem(
    val id: Int,
    val name: String,
    val imageURL: String,
    val price: Double
) : Parcelable
