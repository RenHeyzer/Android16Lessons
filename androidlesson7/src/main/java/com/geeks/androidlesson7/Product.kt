package com.geeks.androidlesson7

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @DrawableRes val coverImage: Int,
    val price: Double,
    val firm: String,
    val body: String
) : Parcelable