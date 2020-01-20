package com.github.jaccek.gerbera.config.entities

import com.google.gson.annotations.SerializedName

data class ServiceEntry(
    @SerializedName("name")
    val serviceName: String,

    val environment: String,

    @SerializedName("status_page_url")
    val statusPageUrl: String,

    @SerializedName("status_parsing")
    val statusParsing: StatusParsing? = null
)
