package com.github.jaccek.gerbera.statuspage

import com.google.gson.annotations.SerializedName

data class ServiceEntry(
    @SerializedName("name")
    val serviceName: String,

    val environment: String,

    @SerializedName("status_page_url")
    val statusPageUrl: String
)
