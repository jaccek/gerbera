package com.github.jaccek.statuspageadapters.entities

import com.github.jaccek.com.github.jaccek.statuspageadapters.entities.HealthConfig
import com.github.jaccek.entities.Status

data class DefaultStatusPage(
    val status: Status,     // TODO: different status than response
    val healthConfig: HealthConfig
)
