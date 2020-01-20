package com.github.jaccek.gerbera.config.entities

import com.google.gson.annotations.SerializedName

data class StatusParsing(
    @SerializedName("version_path")
    val versionPath: String,

    @SerializedName("status_path")
    val statusPath: String,

    val ok: String,

    val fail: String
)
