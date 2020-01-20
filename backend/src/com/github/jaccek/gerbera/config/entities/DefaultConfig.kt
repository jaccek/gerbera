package com.github.jaccek.gerbera.config.entities

import com.google.gson.annotations.SerializedName

data class DefaultConfig(
    @SerializedName("status_parsing")
    val statusParsing: StatusParsing
)
