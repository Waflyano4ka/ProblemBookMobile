package com.example.problembookmobile.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Users(
    val email: String,
    val gender: String,
    val id: String,
    val image: String,
    val lastVisit: String,
    val locale: String,
    val mobileToken: String,
    val name: String
) : Parcelable