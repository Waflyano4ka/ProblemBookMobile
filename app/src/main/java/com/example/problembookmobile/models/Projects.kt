package com.example.problembookmobile.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Projects(
    val active: Boolean,
    val color: String,
    var dailyMessage: String?,
    val id: Int,
    val keyToConnect: String,
    val name: String,
    val user: Users
) : Parcelable