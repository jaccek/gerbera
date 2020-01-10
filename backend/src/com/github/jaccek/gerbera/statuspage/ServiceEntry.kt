package com.github.jaccek.gerbera.statuspage

data class ServiceEntry(
    val serviceName: String,
    val environment: String,
    val statusPageUrl: String
)
