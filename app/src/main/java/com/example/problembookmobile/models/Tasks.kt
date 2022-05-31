package com.example.problembookmobile.models

data class Tasks(
    val archive: Boolean,
    val author: Users,
    val createDatetime: String,
    val datetime: String,
    val description: String,
    val editDatetime: String?,
    val enableTime: Boolean,
    val id: Int,
    val name: String
)