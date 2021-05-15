package com.example.quasar.api

data class ApodResponse(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val url: String,
    val media_type: String,
    val title: String
)
