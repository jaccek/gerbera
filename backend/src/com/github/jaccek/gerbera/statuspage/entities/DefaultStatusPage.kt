package com.github.jaccek.gerbera.entities

import com.github.jaccek.gerbera.statuspage.entities.HealthConfig

data class DefaultStatusPage(
    val status: Status,     // TODO: different status than response
    val healthConfig: HealthConfig
)
